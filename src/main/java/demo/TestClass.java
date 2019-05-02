package demo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestClass {
    @Test
    public void test1(){
        Object[][] arr = {{1,2},{3,4}};
        List a2 = Arrays.asList(arr);
        System.out.println(a2.size());
        for (int i=0; i<a2.size(); i++){
            System.out.println();
        }
    }
}
