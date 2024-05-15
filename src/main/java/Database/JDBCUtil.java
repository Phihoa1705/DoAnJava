package Database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection(){
        Connection c = null;
        try{
//          Đăng ký MySQL Driver với DriverManager
            DriverManager.registerDriver(new Driver());

//          Các thông số
            String URL = "jdbc:mySQL://localhost:3306/doanjvnc";
            String user = "root";
            String passWord = "Hphbo*!123";
//          Tạo kết nối
            c = DriverManager.getConnection(URL,user,passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c) {
        try {
            if(c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printInfo(Connection c){
        try {
            if (c != null) {
                DatabaseMetaData mtdt =  c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
