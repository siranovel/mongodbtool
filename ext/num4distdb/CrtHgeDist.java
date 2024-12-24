import com.mongodb.client.MongoCollection;
import org.bson.Document;

import org.apache.commons.math3.distribution.HypergeometricDistribution;
class CrtHgeDist extends AbstractNum4DistDB {
    private int populationSize;
    public CrtHgeDist(String connectionString, int populationSize) {
        super(connectionString);
        this.populationSize = populationSize;
    }
    protected void crtDistDt0(MongoCollection<Document> col) {
        for(int j = 0; j < populationSize; j++) {
            for(int i = 0; i < populationSize; i++) {
                crtDistDt0_1(col, populationSize, i + 1, j + 1, 0.05);
            }
        }
    }
    private void crtDistDt0_1(MongoCollection<Document> col, int populationSize, int numberOfSuccesses, int sampleSize, double p) {
        double pp = (int)(p * 1000) / 1000.0;

        if (0 == cntHGeDist(col, populationSize, numberOfSuccesses, sampleSize, pp)) {
            insHGeDist(col, populationSize, numberOfSuccesses, sampleSize, pp);
        }
    }
    private long cntHGeDist(MongoCollection<Document> col, int populationSize, int numberOfSuccesses, int sampleSize, double p) {
        Document query = new Document();
        
        query.append("popsz", populationSize);
        query.append("num", numberOfSuccesses);
        query.append("smplsz", sampleSize);
        query.append("p", p);
        return col.countDocuments(query);
    }
    private void insHGeDist(MongoCollection<Document> col, int populationSize, int numberOfSuccesses, int sampleSize, double p) {
        HypergeometricDistribution hgeDist = new HypergeometricDistribution(populationSize, numberOfSuccesses, sampleSize);
        int hge = hgeDist.inverseCumulativeProbability(1.0 - p);
        Document doc = new Document();

        doc.append("popsz", populationSize);
        doc.append("num", numberOfSuccesses);
        doc.append("smplsz", sampleSize);
        doc.append("p", p);
        doc.append("hg", hge);
        col.insertOne(doc);
    }
}

