package advance.au;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;

public class Student {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl="jdbc:mysql://localhost:3306/college?user=root&password=SKMveer37a4@";
            conn = DriverManager.getConnection(dbUrl);
            System.out.println("Connected database successfully!!!");
            statement = conn.createStatement();


            String sql = "CREATE TABLE BLO " +
                    "(id INTEGER not NULL, " +
                    " Name VARCHAR(255), " +
                    " Surname VARCHAR(255), " +
                    " Gender VARCHAR(255), " +
                    " Marks FLOAT, " +
                    " PRIMARY KEY ( id ))";

            statement.executeUpdate(sql);
            System.out.println("Table creation Successful");


            File file = new File("C:\\Users\\Subhash Mandal\\eclipse-workspace\\AdvanceJava\\bin\\info.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document studentXmlDoc = builder.parse(file);

            Element studentsRoot = studentXmlDoc.getDocumentElement();
            NodeList students = studentsRoot.getElementsByTagName("student");

            for(int i=0;i<students.getLength();i++) {
                Element thisStudent = (Element) students.item(i);

                String rollNo = thisStudent.getAttribute("rollno");
                NodeList name = thisStudent.getElementsByTagName("name");
                String studentName = null;
                String surname = null;
                for (int j = 0; j < name.getLength(); j++) {
                    Element thisName = (Element) name.item(j);
                    studentName = thisName.getElementsByTagName("firstname").item(0).getFirstChild().getNodeValue() + " " + thisName.getElementsByTagName("middlename").item(0).getFirstChild().getNodeValue();
                    surname = thisName.getElementsByTagName("lastname").item(0).getFirstChild().getNodeValue();

                }
                String gender = thisStudent.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
                String marks = thisStudent.getElementsByTagName("marks").item(0).getFirstChild().getNodeValue();


                String sqlInsert = "INSERT INTO Student" + "(id, Name, Surname, Gender, Marks)" +
                                "VALUES(" + rollNo + ", '" + studentName + "', '" + surname + "', '" + gender + "'," + marks + ")";
                
                statement.executeUpdate(sqlInsert);
                System.out.println("Function works");
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(statement!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

}

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import java.sql.*;
//
//public class Student{
//	static Connection conn;
//	static Statement statement;
//	public static void main(String[] args) throws Exception {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String dbUrl="jdbc:mysql://localhost:3306/college?user=root&password=SKMveer37a4@";
//		    conn = DriverManager.getConnection(dbUrl);
//		    System.out.println("hey");
//		    statement = conn.createStatement();
//		    String str = "CREATE TABLE STUDENT " +
//                    "(id INTEGER not NULL, " +
//                    " Name VARCHAR(255), " +
//                    " Surname VARCHAR(255), " +
//                    " Gender VARCHAR(255), " +
//                    " Marks FLOAT, " +
//                    " PRIMARY KEY ( id ))";
//            statement.executeUpdate(str);
//            
//		    System.out.println("hey1");
//		    File file = new File("C:\\Users\\Subhash Mandal\\eclipse-workspace\\AdvanceJava\\bin\\info.xml");
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document xmlDoc = builder.parse(file);
//			
//			Element studentsRoot = xmlDoc.getDocumentElement();
//            NodeList students = studentsRoot.getElementsByTagName("student");
//            for(int i=0;i<students.getLength();i++) {
//                Element thisStudent = (Element) students.item(i);
//
//                String rollNo = thisStudent.getAttribute("rollno");
//                NodeList name = thisStudent.getElementsByTagName("name");
//                String studentName = null;
//                String surname = null;
//                for (int j = 0; j < name.getLength(); j++) {
//                    Element thisName = (Element) name.item(j);
//                    studentName = thisName.getElementsByTagName("firstname").item(0).getFirstChild().getNodeValue() + " " + thisName.getElementsByTagName("middlename").item(0).getFirstChild().getNodeValue();
//                    surname = thisName.getElementsByTagName("lastname").item(0).getFirstChild().getNodeValue();
//
//                }
//                String gender = thisStudent.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
//                String marks = thisStudent.getElementsByTagName("marks").item(0).getFirstChild().getNodeValue();
//
//
//                String sqlInsert = "INSERT INTO Student" + "(id, Name, Surname, Gender, Marks)" +
//                                "VALUES(" + rollNo + ", '" + studentName + "', '" + surname + "', '" + gender + "'," + marks + ")";
//                statement.executeUpdate(sqlInsert);
//            }
//		    
//		}catch(SQLException se){
//            se.printStackTrace();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(statement!=null)
//                    conn.close();
//            }catch(SQLException se){
//            }// do nothing
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//	      }
//         }
//	}
//}
