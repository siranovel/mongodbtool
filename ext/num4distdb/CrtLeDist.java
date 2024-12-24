import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.LevyDistribution;
class CrtLeDist extends AbstractNum4DistDB {
    private double mu;
    private double c;
    public CrtLeDist(String connectionString, double mu, double c) {
        super(connectionString);
        this.mu = mu;
        this.c = c;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntLeDist(col, pp)) {
            insLeDist(col, pp);
        }
    }
    private long cntLeDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("mu", mu);
        query.append("c", c);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insLeDist(MongoCollection<Document> col, double p) {
        LevyDistribution ledist = new LevyDistribution(mu, c);
        double le = ledist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("mu", mu);
        doc.append("c", c);
        doc.append("p", p);
        doc.append("le", le);
        col.insertOne(doc);
    }
}

