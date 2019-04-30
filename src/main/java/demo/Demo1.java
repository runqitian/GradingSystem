package demo;

import core.Course;
import core.Student;

import java.util.List;
import java.util.Vector;

public class Demo1 {

    Course belongCourse;
    List<Student> students;
    Row<Object> row = new Row<Object>();

    Vector<Row<Object>> table = new Vector<Row<Object>>();

    public void test(){

    }

}

class Row<E> extends Vector<E>{
}


