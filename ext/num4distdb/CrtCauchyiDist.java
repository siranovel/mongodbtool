import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.CauchyDistribution;
class CrtCauchyiDist extends AbstractNum4DistDB {
    private double median;
    private double scale;
    public CrtCauchyiDist(String connectionString, double median, double scale) {
        super(connectionString);
        this.median = median;
        this.scale = scale;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntCauchyDist(col, pp)) {
            insCauchyDist(col, pp);
        }
    }
    private long cntCauchyDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("median", median);
        query.append("scale", scale);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insCauchyDist(MongoCollection<Document> col, double p) {
        CauchyDistribution cauchyDist = new CauchyDistribution(median, scale);
        double cachy = cauchyDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("median", median);
        doc.append("scale", scale);
        doc.append("p", p);
        doc.append("cachy", cachy);
        col.insertOne(doc);
    }
}

