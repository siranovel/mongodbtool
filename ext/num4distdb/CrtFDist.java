import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.FDistribution;
class CrtFDist extends AbstractNum4DistDB {
    private double nf;
    private double df;
    public CrtFDist(String connectionString, double nf, double df) {
        super(connectionString);
        this.nf = nf;
        this.df = df;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int j = 0; j < df; j++) {
            for(int i = 0; i < nf; i++) {
                crtDistDt0_1(col, i + 1, j + 1, 0.05);
                crtDistDt0_1(col, i + 1, j + 1, 0.025);
            }
        }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, double nf, double df, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntFInv(col, nf, df, pp)) {
            insFInv(col, nf, df, pp);
        }
    }        
    private long cntFInv(MongoCollection<Document> col, double nf, double df, double p) {
        Document query = new Document();

        query.append("nf", nf);
        query.append("df", df);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insFInv(MongoCollection<Document> col, double nf, double df, double p) {
        FDistribution fDist = new FDistribution(nf, df);
        double f = fDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();
       
        doc.append("nf", nf);
        doc.append("df", df);
        doc.append("p", p);
        doc.append("f", f);
        col.insertOne(doc);
    }
}

