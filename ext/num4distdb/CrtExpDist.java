import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.ExponentialDistribution;
class CrtExpDist extends AbstractNum4DistDB {
    private double mean;
    public CrtExpDist(String connectionString, double mean) {
        super(connectionString);
        this.mean = mean;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntExpDist(col, pp)) {
            insExpDist(col, pp);
        }
    }
    private long cntExpDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("mu", mean);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insExpDist(MongoCollection<Document> col, double p) {
        ExponentialDistribution expDist = new ExponentialDistribution(mean);
        double exp = expDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("mu", mean);
        doc.append("p", p);
        doc.append("ep", exp);
        col.insertOne(doc);
    }
}

