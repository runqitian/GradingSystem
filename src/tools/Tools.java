package tools;

import core.Category;
import core.Student;
import core.SubCategory;
import gui.MyTableCellHeaderRenderer;
import gui.MyTableCellRenderer;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

public class Tools {
    public static Object[][] vector2DToArray2D(Vector<Vector<Object>> input){
        Object[][] data = new Object[input.size()][input.get(0).size()];
        for(int i=0; i<input.size();i++){
            for(int j=0; j<input.get(0).size(); j++){
                data[i][j] = input.get(i).get(j);
            }
        }
        return data;
    }

    public static Object[][] list1DToArray2D(List<Object> input){
        Object[][] output = new Object[input.size()][1];
        for (int i=0; i<input.size(); i++){
            output[i][0] = input.get(i);
        }
        return output;
    }

    public static Vector<Object> array1DToVector1D(Object[] arr){
        Vector<Object> vec = new Vector<Object>();
        for (Object obj: arr){
            vec.add(arr);
        }
        return vec;
    }

    public static void beautifyJTable(JTable table, boolean autosize, int fontsize, int rowHeight){
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer(fontsize));
        table.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        table.setRowHeight(rowHeight);
        table.getTableHeader().setBackground(Color.GRAY);
//        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        if (!autosize){
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }

    public static void formattingGradingTable(JTable table){
        int fontsize = 25;
        int rowHeight = 30;
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer(fontsize));
        table.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        table.setRowHeight(rowHeight);
        table.getTableHeader().setBackground(Color.GRAY);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        if (table.getColumnCount()<4){
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }else {
            Enumeration<TableColumn> enr = table.getColumnModel().getColumns();
            while(enr.hasMoreElements()){
                enr.nextElement().setPreferredWidth(200);
            }
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }

    public static Vector<Student> importStudents(String filedir){
        Vector<Student> students = new Vector<Student>();

//        List<List<String>> records = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filedir))) {
            while (scanner.hasNextLine()) {
                students.add(getStudentInfoFromLine(scanner.nextLine()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //remove header
        students.remove(0);
        return students;
    }

    private static Student getStudentInfoFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        String name = values.get(2) + " " + values.get(3) + " " + values.get(4);
        Student stu = new Student(values.get(0), name, values.get(1),"");
        return stu;
    }

    public static void tableToCSV(Vector<Vector<Object>> data, Vector<Object> header, String reportName){
        File csv = new File( reportName + ".csv");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            //write header
            bw.write(writeline(header));
            bw.newLine();
            bw.flush();
            bw.close();
            //write content
            for (Vector<Object> row: data) {
                bw.write(writeline(row));
                bw.newLine();
                bw.flush();
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String writeline(Vector<Object> data){
        String line = "";
        for (Object item: data) {
            line = line + item.toString() + ",";
        }
        line = line.substring(0, line.length()-1);
        return line;
    }

    public static void calculateTotal(Vector<Category> categories, Vector<SubCategory> subCategories, Object[] row, Object[] header, Student student){
        Double categoryWeightTotal = 0.0;
        for (Category category: categories){
            categoryWeightTotal += category.getWeight();
        }
        Map<Category, Double> weightEach = new HashMap<Category, Double>();
        for (SubCategory sub: subCategories){
            if (weightEach.containsKey(sub.getBelong())){
                weightEach.put(sub.getBelong(),weightEach.get(sub.getBelong()) + sub.getWeight());
            }
            else{
                weightEach.put(sub.getBelong(),sub.getWeight());
            }
        }
//        System.out.println(weightEach);
        double totalpoints = 0;
        for (int i=1; i<header.length; i++){
            SubCategory sub = SubCategory.getSubCategoryByName(header[i].toString(),subCategories);
//            System.out.println("sub" + sub.getWeight()/weightEach.get(sub.getBelong()));
//            System.out.println("cat" + sub.getBelong().getWeight()/(categoryWeightTotal==0?1:categoryWeightTotal));
//            System.out.println("ite" + Double.parseDouble(row[i].toString())/sub.getMax());
            totalpoints += (sub.getWeight()/(weightEach.get(sub.getBelong())==0?1:weightEach.get(sub.getBelong())))*(sub.getBelong().getWeight()/(categoryWeightTotal==0?1:categoryWeightTotal)) * (Double.parseDouble(row[i].toString())/sub.getMax());
        }
        totalpoints = Math.round(totalpoints * 10000)/100.0;
//        System.out.println(totalpoints);
        student.setTotalGrade(totalpoints);
    }
}
