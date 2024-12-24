import com.mongodb.client.MongoCollection;
import org.bson.Document;
class CrtAsinDist extends AbstractNum4DistDB {
    private final double DT = 0.001;
    public CrtAsinDist(String connectionString) {
        super(connectionString);
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for (double p = 0.0; p < 1.0; p+=DT) {
            double pp = (int)(p * 1000) / 1000.0;

            if (0 == cntASinInv(col, pp)) { 
                insASinInv(col, pp); 
            }
        }
    }
    private long cntASinInv(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insASinInv(MongoCollection<Document> col, double p) {
        double x = Math.sin(Math.PI * p / 2.0);
        Document doc = new Document();

        doc.append("p", p);
        doc.append("x", x * x);
        col.insertOne(doc);
    }
}

