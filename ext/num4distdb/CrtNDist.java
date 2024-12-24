import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.NormalDistribution;
class CrtNDist extends AbstractNum4DistDB {
    private final double DT = 0.001;
    public CrtNDist(String connectionString) {
        super(connectionString);
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(double p = 0; p < 1.0; p+= DT) {
            double pp = (int)(p * 1000) / 1000.0;

            if (0 == cntNormInv(col, pp)) {
                insNormInv(col, pp);
            }
        }
    }
    private long cntNormInv(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insNormInv(MongoCollection<Document> col, double p) {
        NormalDistribution nDist = new NormalDistribution(0, 1);
        double z = nDist.inverseCumulativeProbability(p);
        Document doc = new Document();

        doc.append("p", p);
        doc.append("z", z);
        col.insertOne(doc);
    }
}

