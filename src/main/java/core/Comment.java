package core;

import java.util.HashMap;
import java.util.Map;
//import java.util.Vector;

public class Comment {
    Map<String, String> comments = new HashMap<String, String>() {};

    public void addComment(String SubName, String Content){
        this.comments.put(SubName, Content);
    }
    public String getAllComment() {
        String allcomm = "";
        for (Map.Entry<String, String> comm: comments.entrySet()) {
            allcomm += comm.getKey() + ":\n   "+ comm.getValue() + "\n";
        }
        return allcomm;
    }
}
