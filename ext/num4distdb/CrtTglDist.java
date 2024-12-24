import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.TriangularDistribution;
class CrtTglDist extends AbstractNum4DistDB {
    private double a;
    private double b;
    private double c;
    public CrtTglDist(String connectionString, double a, double b, double c) {
        super(connectionString);
        this.a = a;
        this.b = b;
        this.c = c;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        double pp = (int)(CrtDistDt.P * 1000) / 1000.0;

        if (0 == cntTglDist(col, pp)) {
            insTglDist(col, pp);
        }
    }
    private long cntTglDist(MongoCollection<Document> col, double p) {
        Document query = new Document();

        query.append("a", a);
        query.append("b", b);
        query.append("c", c);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insTglDist(MongoCollection<Document> col, double p) {
        TriangularDistribution tglDist = new TriangularDistribution(a, c, b);
        double tgl = tglDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("a", a);
        doc.append("b", b);
        doc.append("c", c);
        doc.append("p", p);
        doc.append("tgl", tgl);
        col.insertOne(doc);
    }
}

