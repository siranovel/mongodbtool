import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.GammaDistribution;
class CrtGmDist extends AbstractNum4DistDB {
    private double shape;
    private double scale;
    public CrtGmDist(String connectionString, double shape, double scale) {
        super(connectionString);
        this.shape = shape;
        this.scale = scale;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int j = 0; j < scale; j++) {
            for(int i = 0; i < shape; i++) {
                crtDistDt0_1(col, i + 1, j + 1, 0.05);
            }
        }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, double shape, double scale, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntGMDist(col, shape, scale, pp)) {
            insGMDist(col, shape, scale, pp);
        }
    }
    private long cntGMDist(MongoCollection<Document> col, double shape, double scale, double p) {
        Document query = new Document();

        query.append("shape", shape);
        query.append("scale", scale);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insGMDist(MongoCollection<Document> col, double shape, double scale, double p) {
        GammaDistribution gmDist = new GammaDistribution(shape, scale);
        double ga = gmDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("shape", shape);
        doc.append("scale", scale);
        doc.append("p", p);
        doc.append("ga", ga);
        col.insertOne(doc);
    }
}

