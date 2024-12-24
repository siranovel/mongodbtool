import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.BinomialDistribution;
class CrtBiDist extends AbstractNum4DistDB {
    private int trials;
    private double mu;
    public CrtBiDist(String connectionString, int trials, double mu) {
        super(connectionString);
        this.trials = trials;
        this.mu = mu;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        for(int n = 0; n < trials; n++) {
            if (0 == cntBiDist(col, n, mu, pp)) {
                insBiDist(col, n, mu, pp);
            }
        }
    }
    private long cntBiDist(MongoCollection<Document> col, int n, double mu, double p) {
        Document query = new Document();

        query.append("n", n);
        query.append("mu", mu);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insBiDist(MongoCollection<Document> col, int n, double mu, double p) {
        BinomialDistribution biDist = new BinomialDistribution(n, mu);
        int bi = biDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("n", n);
        doc.append("mu", mu);
        doc.append("p", p);
        doc.append("bi", bi);
        col.insertOne(doc);
    }
}

