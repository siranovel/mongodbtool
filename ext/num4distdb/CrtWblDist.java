import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.WeibullDistribution;
class CrtWblDist extends AbstractNum4DistDB {
    private double alpha;
    private double beta;
    public CrtWblDist(String connectionString, double alpha, double beta) {
        super(connectionString);
        this.alpha = alpha;
        this.beta = beta;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntWeiDist(col, pp)) {
            insWeiDist(col, pp);
        }
    }
    private long cntWeiDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("alpha", alpha);
        query.append("beta", beta);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insWeiDist(MongoCollection<Document> col, double p) {
        WeibullDistribution weiDist = new WeibullDistribution(alpha, beta);
        double wbl = weiDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("alpha", alpha);
        doc.append("beta", beta);
        doc.append("p", p);
        doc.append("wbl", wbl);
        col.insertOne(doc);
    }
}

