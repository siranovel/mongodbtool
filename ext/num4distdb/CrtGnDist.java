import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.TDistribution;
class CrtGnDist extends AbstractNum4DistDB {
    private double df;
    public CrtGnDist(String connectionString, double df) {
        super(connectionString);
        this.df = df;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int i = 2; i < df; i++) {
            crtDistDt0_1(col, i + 1, 0.05);
            crtDistDt0_1(col, i + 1, 0.025);
        }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, double df, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntGNInv(col, df, pp)) {
            insGNInv(col, df, pp);
        }
    }
    private long cntGNInv(MongoCollection<Document> col, double df, double p) {
        Document query = new Document();

        query.append("df", df);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insGNInv(MongoCollection<Document> col, double df, double p) {
        // t = t(N-2,a/n)
        // Gn(a) = (N-1)/√N * √{t*t/(N-2+t*t)}
        TDistribution tDist = new TDistribution(df - 2);
        double t = tDist.inverseCumulativeProbability(p/df);
        Document doc = new Document();

        double t2 = t * t;
        double wk = t2 / (df - 2 + t2);
        double gn = (df - 1) / Math.sqrt(df) * Math.sqrt(wk);

        doc.append("df", df);
        doc.append("p", p);
        doc.append("gn", gn);
        col.insertOne(doc);
    }
}

