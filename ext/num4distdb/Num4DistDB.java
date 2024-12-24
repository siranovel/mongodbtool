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
class Num4DistDB {
    private final String DISTDB = "distdb";
    private MongoClient client = null;
    public Num4DistDB(String connectionString) {
        client = create(connectionString);
    }
    private MongoClient create(String connectionString) {
        return MongoClients.create(connectionString);
    }
    public List<String> dspDBs() {
        List<String> retList = new ArrayList<String>();
        MongoIterable<String> list = client.listDatabaseNames();

        for(String dbName : list) {
            retList.add(dbName);
        }
        client.close();
        return retList;
    }
    public List<String> dspDistDBs() {
        List<String> retList = new ArrayList<String>();
        MongoDatabase db = client.getDatabase(DISTDB);
        MongoIterable<String> list = db.listCollectionNames();

        for(String tblName : list) {
            retList.add(tblName);
        }
        client.close();
        return retList;
    }
    public List<Map<String, Object>> dspDistDts(String tblnm) {
        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
        MongoDatabase db = client.getDatabase(DISTDB);
        MongoCollection<Document> col = db.getCollection(tblnm);
        FindIterable<Document>   list = col.find();

        for(Document doc : list) {
            Map<String, Object> map = new HashMap<>();
            for(String key : doc.keySet()) {
                Object o = doc.get(key);

                if (o.getClass().equals(ObjectId.class)) {
                    ObjectId id = (ObjectId)o;

                    map.put(key, id.toHexString());
                }
                if (o.getClass().equals(Double.class)) {
                    Double d = (Double)o;

                    map.put(key, d.doubleValue());
                }
            }
            retList.add(map);
        }
        client.close();
        return retList;
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
}

