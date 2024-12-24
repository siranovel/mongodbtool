import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.ClientSession;
import org.bson.Document;
abstract class AbstractNum4DistDB implements CrtDistDt {
    private MongoClient client = null;
    private ClientSession session = null;
    abstract void crtDistDt0(MongoCollection<Document> col);
    public AbstractNum4DistDB(String connectionString) {
        client = create(connectionString);
        session = client.startSession();
    }
    private MongoClient create(String connectionString) {
        return MongoClients.create(connectionString);
    }
    public void crtDistDt(String tblnm) {
        MongoDatabase db = client.getDatabase("distdb");
        MongoCollection<Document> col = db.getCollection(tblnm);

        session.startTransaction();
        crtDistDt0(col);
        session.commitTransaction();
        client.close();
    }
}

