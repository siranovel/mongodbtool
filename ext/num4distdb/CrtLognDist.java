import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.LogNormalDistribution;
class CrtLognDist extends AbstractNum4DistDB {
    private final double DT = 0.001;
    public CrtLognDist(String connectionString) {
        super(connectionString);
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(double p = 0; p < 1.0; p+= DT) {
            double pp = (int)(p * 1000) / 1000.0;

            if (0 == cntLogNormInv(col, pp)) {
                insLogNormInv(col, pp);
            }
        }
    }
    private long cntLogNormInv(MongoCollection<Document> col, double p) {
        Document query = new Document();
        
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insLogNormInv(MongoCollection<Document> col, double p) {
        LogNormalDistribution lognDist = new LogNormalDistribution(0, 1);
        double logZ = lognDist.inverseCumulativeProbability(p);
        Document doc = new Document();

        doc.append("p", p);
        doc.append("logz", logZ);
        col.insertOne(doc);
    }
    
}

