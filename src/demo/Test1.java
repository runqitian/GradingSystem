package demo;

import data.Database;
import org.junit.*;

public class Test1 {

    @Test
    public void test1(){
        Database db = new Database();
        System.out.println(db.getUser("cpk","123"));
    }
}
