package core;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseStorage {

    // Connects to mongo instance
    public static MongoDatabase connectToDB(){
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("591Test");
        return database;
    }

    // Allows users to sign up - might not but necessary for what we are doing
    public void signUp(String user, String pass){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("testAccounts");
        Document newDocument = new Document("username", user).append("password", pass);
        collection.insertOne(newDocument);
    }

    // Allows user to login. Returns true if the login was successful or false otherwise
    public Boolean logIn(String user, String pass){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("testAccounts");
        Document possibleMatch = collection.find(eq("username", user)).first();
        if((possibleMatch != null) && (possibleMatch.get("password") == pass)){
            return true;
        }
        return false;
    }

    // Adds a new category to the database. Based off the design found here: https://docs.google.com/document/d/1Eq5BSkV4JUp511XVhcvLPc3SBE5irwBFiqChFFtwCpQ/edit
    public void addSubCategory (String name, Double gradWeight, Double ugradWeight, ArrayList<SubCategory> subCategories, String categoryBelongTo, Double maxPossible, HashMap<String, Double> grades){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("testCategories");
        Document newDocument = new Document("name", name).append("gradWeight", gradWeight).append("uGradWeight", ugradWeight).append("subCategories", subCategories.toArray()).append("categoryBelongTo", categoryBelongTo).append("maxPossible", maxPossible).append("grades", grades);
        collection.insertOne(newDocument);
    }


    // Fetches a category entry from the database.
    public ArrayList findSubCategory(String name, String categoryBelongTo){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("testCategories");
        Document possibleMatch = collection.find(eq("name", name)).first();
        if((possibleMatch != null) && (possibleMatch.get("categoryBelongTo") == categoryBelongTo)){
            ArrayList ret = new ArrayList();
            ret.add(possibleMatch.get("name"));
            ret.add(possibleMatch.get("gradWeight"));
            ret.add(possibleMatch.get("uGradWeight"));
            ret.add(possibleMatch.get("subCategories"));
            ret.add(possibleMatch.get("categoryBelongTo"));
            ret.add(possibleMatch.get("maxPossible"));
            ret.add(possibleMatch.get("grades"));
            return ret;
        }
        return new ArrayList();
    }

}
