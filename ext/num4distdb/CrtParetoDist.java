import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.ParetoDistribution;
class CrtParetoDist extends AbstractNum4DistDB {
    private final double DT = 0.001;
    private ParetoDistribution paretoDist = new ParetoDistribution(1, 1);
    public CrtParetoDist(String connectionString) {
        super(connectionString);
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(double p = 0; p < 1.0; p+= DT) {
            double pp = (int)(p * 1000) / 1000.0;

            if (0 == cntParetoInv(col, pp)) {
                insParetoInv(col, pp);
            }
        }
    }
    private long cntParetoInv(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insParetoInv(MongoCollection<Document> col, double p) {
        double pareto = paretoDist.inverseCumulativeProbability(p);
        Document doc = new Document();

        doc.append("p", p);
        doc.append("pareto", pareto);
        col.insertOne(doc);
    }
}

