import java.util.List;
import java.util.Map;
public class Num4DistDBInfo {
    private String connectionString = null;
    public Num4DistDBInfo(String connectionString) {
        this.connectionString = connectionString;
    }
    public List<String> dspDBs() {
        Num4DistDB mongo = new Num4DistDB(connectionString);

        return mongo.dspDBs();
    }
    public List<String> dspDistDBs() {
        Num4DistDB mongo = new Num4DistDB(connectionString);

        return mongo.dspDistDBs();
    }
    public List<Map<String, Object>> dspDistDts(String tblnm) {
        Num4DistDB mongo = new Num4DistDB(connectionString);

        return mongo.dspDistDts(tblnm);
    }
    public void dropDist(String tblnm) {
         Num4DistDB mongo = new Num4DistDB(connectionString);

         mongo.dropDist(tblnm);
    }
    /*********************************/
    /* interface define              */
    /*********************************/
    /*********************************/
    /* Class define                  */
    /*********************************/
}


