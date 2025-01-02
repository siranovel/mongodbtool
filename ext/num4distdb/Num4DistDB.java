import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ClientSession;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

class Num4DistDB {
    private final String DISTDB = "distdb";
    private MongoClient client = null;
    public Num4DistDB(String connectionString) {
        client = create(connectionString);
    }
    private MongoClient create(String connectionString) {
        return MongoClients.create(connectionString);
    }
    public void dspDBs() {
        JFrame f = new DspDBsFrame();

        // ウィンドウを表示
        f.setVisible(true);
    }
    public List<String> dspDistDBs() {
        List<String> retList = new ArrayList<String>();
        MongoDatabase db = client.getDatabase(DISTDB);
        MongoIterable<String> list = db.listCollectionNames();

        for(String tblName : list) {
            retList.add(tblName);
        }
        return retList;
    }
    public void dspDistDts(String tblnm) {
        JFrame f = new DspDistDts(tblnm);

        // ウィンドウを表示
        f.setVisible(true);
    }
    public void dropDist(String tblnm) {
        MongoDatabase db = client.getDatabase(DISTDB);
        MongoCollection<Document> col = db.getCollection(tblnm);
        ClientSession session = client.startSession();

        session.startTransaction();
        col.drop();            
        session.commitTransaction();
    }
    /*********************************/
    /* interface define              */
    /*********************************/
    /*********************************/
    /* Class define                  */
    /*********************************/
    // dbs情報を表示
    private class DspDBsFrame extends JFrame {
        public DspDBsFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("dbs情報");
            setSize(250, 300);
            setResizable(false);

            JPanel p = createPanel();
            this.setContentPane(p);
        }
        private JPanel createPanel() {
            return new DspDBsPanel();
        }
        private class DspDBsPanel extends JPanel {
            public DspDBsPanel() {
                this.setLayout(new BorderLayout());
                this.add(createLabel(), BorderLayout.NORTH);
                this.add(new GridPanel());
            }
            private JComponent createLabel() {
                JLabel l = new JLabel("mongoDB情報");

                l.setFont(new Font("Arial", Font.PLAIN, 30));
                l.setForeground(Color.BLUE);
                l.setHorizontalAlignment(JLabel.CENTER);
                return l;
            }
        }
        private class GridPanel extends JPanel {
            private GridBagLayout gbl = new GridBagLayout();
            private JLabel lbltl= null;
            private JLabel lbls = null;
            public GridPanel() {
                setLayout(gbl);
                addLabel(lbltl = new JLabel("DB名", JLabel.CENTER), 0, 0, 1, 1);
                lbltl.setBorder(new LineBorder(Color.BLACK));

                MongoIterable<String> list = client.listDatabaseNames();
                int i = 0;
                for(String dbName : list) {
                    addLabel(lbls = new JLabel(dbName), 0, i + 1, 1, 1);
                    lbls.setBorder(new LineBorder(Color.CYAN));
                    chgLabelSize(lbls, 150, 20);
                    i++;
                }
            }
            private void chgLabelSize(JLabel lbl, int w, int h) {
                lbl.setPreferredSize(new Dimension(w, h));
            }
            private void addLabel(JLabel lbl, int x, int y, int w, int h) {
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = w;
                gbc.gridheight = h;
                gbl.setConstraints(lbl, gbc);
                add(lbl);
            }
        }
    }
    // distdb内のテーブル一覧を表示
    private class DspDistDBs extends JFrame {
    }
    // コレクション内のデータ
    private class DspDistDts extends JFrame {
        public DspDistDts(String tblnm) {
            String title = String.format("コレクション内のデータ:%s", tblnm);
            MongoCollection<Document> col = getList(tblnm);
            String[] columns = getColumns(col);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            setTitle(title);
            setSize(500, 600);
            setResizable(false);

            if (null != columns) {
                Object[][] data  = getData(col);
                
                JTable table = new JTable(data, columns);
                JScrollPane scrollPane = new JScrollPane(table);

                add(scrollPane);
            } else {
                add(createLabel());
            }
        }
        private JComponent createLabel() {
            JLabel l = new JLabel("データがありません");

            l.setFont(new Font("Arial", Font.PLAIN, 30));
            l.setForeground(Color.RED);
            l.setHorizontalAlignment(JLabel.CENTER);
            return l;
        }
        private MongoCollection<Document> getList(String tblnm) {
            MongoDatabase db = client.getDatabase(DISTDB);
            return  db.getCollection(tblnm);
        }
        private String[] getColumns(MongoCollection<Document> col) {
            FindIterable<Document> list = col.find();
            Document doc = list.first();

            if (null == doc) { return null; }
            String[] columns = new String[doc.size()];
            int i = 0;

            for(String key : doc.keySet()) {
                columns[i] = key;
                i++;                
            }
            return columns;
        }
        private Object[][] getData(MongoCollection<Document> col) {
            int n = (int)col.countDocuments();
            FindIterable<Document> list = col.find();
            Object[][] data = new Object[n][list.first().size()];
            int i = 0;

            for(Document doc : list) {
                int j = 0;
                for(String key : doc.keySet()) {
                    Object o = doc.get(key);

                    if (o instanceof ObjectId id) { data[i][j] = id.toHexString(); }
                    if (o instanceof Double d)    { data[i][j] = d; }
                    j++;
                }
                i++;
            }
            return data;
        }
    }
}

