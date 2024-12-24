import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
class CrtBeBiDist extends AbstractNum4DistDB {
    private int trials;
    private double alpha;
    private double beta;           //
    private double bab = 0.0;
    private double gabx = 0.0;
    public CrtBeBiDist(String connectionString, int trials, double alpha, double beta) {
        super(connectionString);

        this.trials = trials;
        this.alpha = alpha;
        this.beta = beta;

        double ga = Gamma.gamma(alpha);
        double gb = Gamma.gamma(beta);
        double gab = Gamma.gamma(alpha + beta);
        this.bab = ga * gb / gab;
        this.gabx = Gamma.gamma(alpha + beta + trials);
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int x = 0; x <= trials; x++) {
            if (0 == cntBeBi(col, x)) {
                insBeBi(col, x);
            }
        }
    }
    private long cntBeBi(MongoCollection<Document> col, int x) {
        Document query = new Document();

        query.append("n", trials);
        query.append("a", alpha);
        query.append("b", beta);
        query.append("x", x);
        return col.countDocuments(query);
    }
    private void insBeBi(MongoCollection<Document> col, int x) {
        long ncx = CombinatoricsUtils.binomialCoefficient(trials, x);
        Document doc = new Document();

        double ax = alpha + x;
        double bx = beta + trials - x;
        double gax = Gamma.gamma(ax);
	double gbx = Gamma.gamma(bx);
	double babx = gax * gbx / gabx;
	double bp = ncx * babx / bab;

        doc.append("n", trials);
        doc.append("a", alpha);
        doc.append("b", beta);
        doc.append("x", x);
        doc.append("p", bp);
        col.insertOne(doc);
    }
}

