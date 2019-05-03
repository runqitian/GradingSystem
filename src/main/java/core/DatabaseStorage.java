package core;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import com.mongodb.Block;

import javax.print.Doc;
import java.sql.Array;
import java.util.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class DatabaseStorage {

    // Connects to mongo instance
    public static MongoDatabase connectToDB(){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://test:testing1@ds135624.mlab.com:35624/cs591"));
        MongoDatabase database = mongoClient.getDatabase("cs591");

        return database;
    }

    // Allows users to sign up - might not but necessary for what we are doing
    public static void signUp(String user, String pass){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Users");
        Document newDocument = new Document("username", user).append("password", pass);
        collection.insertOne(newDocument);
    }

    // Allows user to login. Returns true if the login was successful or false otherwise
    public static Boolean logIn(String user, String pass){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Users");
        Document possibleMatch = collection.find(eq("username", user)).first();
        if((possibleMatch != null) && (possibleMatch.get("password").equals(pass))){
            return true;
        }
        return false;
    }


    public static Vector<Student> getAllStudents() {
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Students");
        FindIterable<Document> possibleMatch = collection.find();
        Vector<Student> ret = new Vector<Student>();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
                ret.add(new Student(document.getString("name"), document.getString("email"), document.getString("student_id")));
            }
        };
        possibleMatch.forEach(printBlock);
        return ret;
    }

    public static Vector<Category> getCategories(String courseName){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Courses");
        Document possibleMatch = collection.find(eq("Coursename", courseName)).first();
        Vector<Category> ret = new Vector<Category>();
        if(possibleMatch != null) {
            JSONParser jsonP = new JSONParser();
            try{
                JSONObject jsonO = (JSONObject) jsonP.parse(possibleMatch.toJson());
                Map categories = (Map)jsonO.get("Categories");
                Iterator iter = categories.keySet().iterator();
                while(iter.hasNext()){
                    Object x = iter.next();
                    Map subc = (Map)categories.get(x);
                    String name = (String) x;
                    String weight = subc.get("Weight").toString();
                    ret.add(new Category(name,Double.parseDouble(weight)));
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static Vector<SubCategory> getSubCategories(String courseName, String categoryName){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Courses");
        Document possibleMatch = collection.find(eq("Coursename", courseName)).first();
        Vector<SubCategory> ret = new Vector<SubCategory>();
        if(possibleMatch != null){
            JSONParser jsonP = new JSONParser();
            try{
                JSONObject jsonO = (JSONObject) jsonP.parse(possibleMatch.toJson());
                Map categories = (Map)jsonO.get("Categories");
                Iterator iter = categories.keySet().iterator();
                while(iter.hasNext()){
                    String x = (String) iter.next();
                    if(x.equals(categoryName)){
                        Map subCategories = (Map) categories.get(x);
                        Iterator innerIterator = subCategories.keySet().iterator();
                        String weight = subCategories.get("Weight").toString();
                        while(innerIterator.hasNext()){
                            String z = (String) innerIterator.next();
                            if(!z.equals("Weight")){
                                Map temp3 = (Map) subCategories.get(z);
                                String currWeight = temp3.get("Weight").toString();
                                String maxScore = (String) temp3.get("maxscore");
                                SubCategory newSub = new SubCategory(new Category(x, Double.parseDouble(weight)),z,Double.parseDouble(currWeight),Integer.parseInt(maxScore));
                                ret.add(newSub);
                            }
                        }
                    }
                }
            } catch(Exception e) {
                System.out.println("broke");
            }
        }
        return ret;
    }

    public static Map<String, String> getGradesFor(String courseName, String categoryName, String subCategoryName){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Courses");
        Document possibleMatch = collection.find(eq("Coursename", courseName)).first();
        if(possibleMatch != null){
            JSONParser jsonP = new JSONParser();
            try{
                JSONObject jsonO = (JSONObject) jsonP.parse(possibleMatch.toJson());
                Map categories = (Map)jsonO.get("Categories");
                Iterator iter = categories.keySet().iterator();
                while(iter.hasNext()){
                    String x = (String) iter.next();
                    if(x.equals(categoryName)){
                        Map subCategories = (Map) categories.get(x);
                        Map assignment = (Map) subCategories.get(subCategoryName);
                        Map ret = (Map) assignment.get("studentGrades");
                        return ret;
                    }
                }
            } catch(Exception e) {
                System.out.println("broke");
            }
        }
        return new HashMap<String, String>();
    }
//
//    public static Vector<Vector<Object>> getAllSubCategories(){
//
//        final Vector<Vector<Object>> item = new Vector<Vector<Object>>();
//
//        Block<Document> operateBlock = new Block<Document>() {
//            public void apply(final Document document) {
//                System.out.println(document.toJson());
//                Vector<Object> temp = new Vector<Object>();
//                temp.add(document.get("categoryBelongTo").toString());
//                temp.add(document.get("name").toString());
//                item.add(temp);
//            }
//        };
//
//        MongoDatabase db = connectToDB();
//        MongoCollection<Document> collection = db.getCollection("Courses");
//        collection.find().forEach(operateBlock);
//        System.out.println(item);
//        return item;
//    }
//

    //    /***
//     *  to do list
//     */
//
    public static Vector<String> loadCourseNameList(String username){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Users");
        Document possibleMatch = collection.find(eq("username", username)).first();
        Vector<String> ret = new Vector<String>();
        if(possibleMatch != null){
            ArrayList<String> temp = (ArrayList<String>) possibleMatch.get("courses");
            for(int i = 0; i < temp.size(); i++){
                ret.add(temp.get(i).toString());
            }
        }
        return ret;
    }

    public static boolean updateGradesFor(String CourseName, SubCategory sub){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Courses");
        Document possibleMatch = collection.find(eq("Coursename", CourseName)).first();
        if(possibleMatch != null){
            String query = "Categories." + sub.getCategory().getName() + "." + sub.getName() + ".studentGrades";
            collection.updateOne(eq("Coursename", CourseName), new Document("$set",new Document(query, sub.getAllGrades())));
            return true;
        }
        return false;
    }


    public static void writeWeightsChange(String CourseName, Vector<Category> categories, Vector<SubCategory> subs){
        MongoDatabase db = connectToDB();
        MongoCollection<Document> collection = db.getCollection("Courses");
        Document possibleMatch = collection.find(eq("Coursename", CourseName)).first();
        if(possibleMatch != null){
            for (Category cat: categories){
                String query = "Categories." + cat.getName() + ".Weight";
                collection.updateOne(eq("Coursename", CourseName), new Document("$set",new Document(query, cat.getWeight())));
            }
            for (SubCategory sub: subs){
                String query = "Categories." + sub.getCategory().getName() + "." + sub.getName() + ".Weight";
                collection.updateOne(eq("Coursename", CourseName), new Document("$set",new Document(query, sub.getWeight())));
            }
        }
    }

}
