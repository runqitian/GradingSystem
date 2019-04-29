package demo;

import javax.swing.*;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(900,540);
        JPanel panel1 = new JPanel();
        JButton bt1 = new JButton("hello");
        String headers[] = {"first name","last name", "age", "type"};
        String data[][] = {{"demo","demo","24","grad"},{"demo1","demo1","11","grad"},{"demo2","demo2","12","ugrad"}};
        JTable tb1 = new JTable(data,headers);
        JScrollPane jp = new JScrollPane(tb1);
        panel1.add(bt1);
        panel1.add(jp);
        frame.add(panel1);
    }
}

