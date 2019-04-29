package core;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseStorage {

    // Connects to mongo instance
    public static MongoDatabase connectToDB(){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:testing1@ds135624.mlab.com:35624/cs591"));
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
