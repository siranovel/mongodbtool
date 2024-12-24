import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.ZipfDistribution;
class CrtZipfDist extends AbstractNum4DistDB {
    private int nElm;
    private double exponent;
    public CrtZipfDist(String connectionString, int nElm, double exponent) {
        super(connectionString);
        this.nElm = nElm;
        this.exponent = exponent;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int j = 0; j < exponent; j++) {
            for(int i = 0; i < nElm; i++) {
                crtDistDt0_1(col, i+1, j+1, 0.05);
            }
        }
    }
    protected void crtDistDt0_1(MongoCollection<Document> col, int nElm, double exponent, double p) {
	double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntZipfInv(col, nElm, exponent, pp)) {
            insZipfInv(col, nElm, exponent, pp);
        }  
    }
    private long cntZipfInv(MongoCollection<Document> col, int nElm, double exponent, double p) {
        Document query = new Document();

        query.append("elm", nElm);
        query.append("exponent", exponent);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insZipfInv(MongoCollection<Document> col, int nElm, double exponent, double p) {
        ZipfDistribution zipfDist = new ZipfDistribution(nElm, exponent);
        int zipf = zipfDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("elm", nElm);
        doc.append("exponent", exponent);
        doc.append("p", p);
        doc.append("zipf", zipf);
        col.insertOne(doc);
    }
}

