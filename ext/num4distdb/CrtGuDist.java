import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.GumbelDistribution;
class CrtGuDist extends AbstractNum4DistDB {
    private double mu;
    private double beta;
    public CrtGuDist(String connectionString, double mu, double beta) {
        super(connectionString);
        this.mu = mu;
        this.beta = beta;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntGUDist(col, pp)) {
            insGUDist(col, pp);
        }
    }
    private long cntGUDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("mu", mu);
        query.append("beta", beta);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insGUDist(MongoCollection<Document> col, double p) {
        GumbelDistribution guDist = new GumbelDistribution(mu, beta);
        double gu = guDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("mu", mu);
        doc.append("beta", beta);
        doc.append("p", p);
        doc.append("gu", gu);
        col.insertOne(doc);
    }
}

