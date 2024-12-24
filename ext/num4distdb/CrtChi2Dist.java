import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
class CrtChi2Dist extends AbstractNum4DistDB {
    private double df; 
    public CrtChi2Dist(String connectionString, double df) {
        super(connectionString);
        this.df = df;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int i = 0; i < df; i++) {
            crtDistDt0_1(col, i + 1, 0.995);
            crtDistDt0_1(col, i + 1, 0.990);
            crtDistDt0_1(col, i + 1, 0.975);
            crtDistDt0_1(col, i + 1, 0.950);
            crtDistDt0_1(col, i + 1, 0.050);
            crtDistDt0_1(col, i + 1, 0.025);
            crtDistDt0_1(col, i + 1, 0.010);
            crtDistDt0_1(col, i + 1, 0.005);
          }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, double n, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntChi2Dist(col, n, pp)) {
            insChi2Dist(col, n, pp);
        }
    }
    private long cntChi2Dist(MongoCollection<Document> col, double n, double p) {
        Document query = new Document();

        query.append("df", n);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insChi2Dist(MongoCollection<Document> col, double n, double p) {
        ChiSquaredDistribution chi2Dist = new ChiSquaredDistribution(n);
        double chi = chi2Dist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("df", n);
        doc.append("p", p);
        doc.append("chi", chi);
        col.insertOne(doc);
    }
}

