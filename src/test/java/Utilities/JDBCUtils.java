package Utilities;

import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    /*STATIC METHODS
    .establishConection();
    .runQuery(String query);->returns List of Maps
    .countRows(String query);
    .closeDatabase();
     */
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    /*
    This statement will establish connection with Oracle SQL database
     */

    public static void establishConnection(){
        try {
            connection= DriverManager.getConnection(
                    CommonUtils.getProperty("DBURL"),
                    CommonUtils.getProperty("DBUsername"),
                    CommonUtils.getProperty("DBPassword")
            );
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * This methos will return list of map as a result of excecuted query
     * @param query
     * @return
     * @throws SQLException
     */
    public static List<Map<String,Object>>runQuery(String query) throws SQLException {
        List<Map<String,Object>>data=new ArrayList<>();

        try {
            resultSet = statement.executeQuery(query);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
          //  connection.close();
          //      statement.close();
            }

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        //looping through rows of that table
        while(resultSet.next()){
            Map<String,Object>map=new HashMap<>();
            //it is looping through each column of current row and stores to map
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                map.put(resultSetMetaData.getColumnName(i),resultSet.getObject(resultSetMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        return data;
    }

     public static int countRows(String query) throws SQLException {
        resultSet=statement.executeQuery(query);
        resultSet.last();
        return resultSet.getRow();

     }

    /**
     * this method will close connection to database
     * @throws SQLException
     */
     public static void closeDatabase() throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
        if(resultSet!=null){
            resultSet.close();
        }
     }

}
