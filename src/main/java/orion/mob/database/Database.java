package orion.mob.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import orion.mob.mobs.samples.SampleEntityCreature;
import orion.mob.mobs.samples.SampleHostile;

import static com.mongodb.client.model.Filters.eq;

public class Database extends MongoClient {

    public MongoDatabase database = this.getDatabase("orion");
    public MongoCollection<Document> mobDataCollection = database.getCollection("mobData");
    public MongoCollection<Document> spawnerCollection = database.getCollection("spawners");

    public Database(String host, int port) {
        super(host, port);
    }

    public boolean hasMobData(String uuid) {
        Document doc = mobDataCollection.find(eq("uuid", uuid)).first();
        return (doc != null);
    }

    public void addMobData(SampleEntityCreature data) {
        Document doc = data.generateDocument();
        if(!hasMobData(data.getUuid().toString()))
            mobDataCollection.insertOne(doc);
        else
            mobDataCollection.replaceOne(eq("uuid", data.getUuid()), doc);
    }

    public SampleEntityCreature loadMobData(String uuid) throws ClassNotFoundException {
        Document doc = mobDataCollection.find(eq("uuid", uuid)).first();
        if(Class.forName(doc.getString("class")).equals(SampleHostile.class)) {
            return SampleHostile.loadDocument(doc);
        }
        return null;
    }

}
