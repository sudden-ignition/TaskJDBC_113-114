package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    //private static final String URL = "jdbc:mysql://localhost:3306/mytestdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb?useUnicode=true&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "jfdgsh5-TT!5gh-yBg6T";

    private static int cou = 0;



    public static Connection getConnect(){
       System.out.println("count - " + cou++);
       try ( Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
           System.out.println("++++++++++++++++++++");
           System.out.println("Success!");
           System.out.println("++++++++++++++++++++");
           return conn;
        } catch (SQLException e) {
            System.out.println("Looks like something goes wrong - " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
