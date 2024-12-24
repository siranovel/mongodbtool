import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.NakagamiDistribution;
class CrtNkDist extends AbstractNum4DistDB {
    private double mu;
    private double omega;
    public CrtNkDist(String connectionString, double mu, double omega) {
        super(connectionString);
        this.mu = mu;
        this.omega = omega;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntNGDist(col, pp)) {
            insNGDist(col, pp);
        }
    }
    private long cntNGDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("mu", mu);
        query.append("omega", omega);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insNGDist(MongoCollection<Document> col, double p) {
        NakagamiDistribution ngDist = new NakagamiDistribution(mu, omega);
        double ng = ngDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("mu", mu);
        doc.append("omega", omega);
        doc.append("p", p);
        doc.append("ng", ng);
        col.insertOne(doc);
    }
}

