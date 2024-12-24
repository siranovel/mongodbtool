import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.LogisticDistribution;
class CrtLogDist extends AbstractNum4DistDB {
    private double mu;
    private double s;
    public CrtLogDist(String connectionString, double mu, double s) {
        super(connectionString);
        this.mu = mu;
        this.s = s;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntLogDist(col, pp)) {
            insLogDist(col, pp);
        }
    }
    private long cntLogDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("mu", mu);
        query.append("s", s);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insLogDist(MongoCollection<Document> col, double p) {
        LogisticDistribution logDist = new LogisticDistribution(mu, s);
        double lg = logDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("mu", mu);
        doc.append("s", s);
        doc.append("p", p);
        doc.append("lg", lg);
        col.insertOne(doc);
    }
}

