import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.PoissonDistribution;
class CrtPoDist extends AbstractNum4DistDB {
    private double lambda;
    public CrtPoDist(String connectionString, double lambda) {
        super(connectionString);
        this.lambda = lambda;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntPoDist(col, pp)) {
            insPoDist(col, pp);
        }
    }
    private long cntPoDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("lambda", lambda);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insPoDist(MongoCollection<Document> col, double p) {
        PoissonDistribution poDist = new PoissonDistribution(lambda);
        int po = poDist.inverseCumulativeProbability(p);
        Document doc = new Document();

        doc.append("lambda", lambda);
        doc.append("p", p);
        doc.append("po", po);
        col.insertOne(doc);
    }
}

