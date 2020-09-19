package Utilities;

import javax.xml.transform.sax.SAXSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        /*
        Connection
        statement
        resulSet
         */

        Connection connection= DriverManager.getConnection(
                "jdbc:oracle:thin:@beccadatabase.chthqlpiqmvy.us-east-2.rds.amazonaws.com:1521/ORCL",
                "becca",
               "ruslanbema90"

        );
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet=statement.executeQuery("Select * from employees order by employee_id");
        resultSet.next();
       String first_name= resultSet.getString("FIRST_NAME");
        System.out.println(first_name);
        //System.out.println(resultSet.getString("employee_id"));

        resultSet.next();
        System.out.println(resultSet.getString("First_name"));

        resultSet.last();
        System.out.println(resultSet.getString("first_name"));

        resultSet.first();
        System.out.println(resultSet.getString("First_name"));

        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getRow());

      //  System.out.println(resultSet.getObject("Employee_id"));

        //====================REsultSetMetaData======================
        //resultSetMetaData ->gives us all details about the columns

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnName(2));


        //i store all the data in java in collection type
        List<Map<String,Object>>data=new ArrayList<>();
        //loop throught resultSet
        resultSet.beforeFirst();
        while(resultSet.next()){
            Map<String,Object>map=new HashMap<>();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                map.put(resultSetMetaData.getColumnName(i),resultSet.getObject(resultSetMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        System.out.println(data.get(10).get("FIRST_NAME"));
        System.out.println(data.get(45).get("EMAIL"));

        for(int i=0;i<data.size();i++){
            System.out.println(data.get(i).get("SALARY"));
        }
        for(int i=0;i<data.size();i++) {
            if (data.get(i).get("FIRST_NAME").toString().startsWith("A")) {
                System.out.println(data.get(i).get("FIRST_NAME"));
            }
        }
            int total=0;
            for(int i=0;i<data.size();i++) {
                total = total + Integer.parseInt(data.get(i).get("SALARY").toString());
            }
                System.out.println(total/data.size());

            }
        }




