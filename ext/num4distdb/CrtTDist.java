import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.TDistribution;
class CrtTDist extends AbstractNum4DistDB {
    private double df;
    public CrtTDist(String connectionString, double df) {
        super(connectionString);
        this.df = df;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int i = 0; i < df; i++) {
            crtDistDt0_1(col, i+1, 0.25);
            crtDistDt0_1(col, i+1, 0.1);
            crtDistDt0_1(col, i+1, 0.05);
            crtDistDt0_1(col, i+1, 0.025);
            crtDistDt0_1(col, i+1, 0.01);
            crtDistDt0_1(col, i+1, 0.005);
        }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, double df, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntTInv(col, df, pp)) {
            insTInv(col, df, pp);
        }
    }
    private long cntTInv(MongoCollection<Document> col, double df, double p) {
        Document query = new Document();

        query.append("df", df);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insTInv(MongoCollection<Document> col, double df, double p) {
        TDistribution tDist = new TDistribution(df);
        double t = tDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();
        
        doc.append("df", df);
        doc.append("p", p);
        doc.append("t", t);
        col.insertOne(doc);
    }
}

