import java.util.List;
import java.util.Map;
public class Num4DistDBInfo {
    private Num4DistDB mongo = null;
    public Num4DistDBInfo(String connectionString) {
        mongo = new Num4DistDB(connectionString);
    }
    public void dspDBs() {
        mongo.dspDBs();
    }
    public List<String> dspDistDBs() {
        return mongo.dspDistDBs();
    }
    public void dspDistDts(String tblnm) {
        mongo.dspDistDts(tblnm);
    }
    public void dropDist(String tblnm) {
        mongo.dropDist(tblnm);
    }
    /*********************************/
    /* interface define              */
    /*********************************/
    /*********************************/
    /* Class define                  */
    /*********************************/
}


