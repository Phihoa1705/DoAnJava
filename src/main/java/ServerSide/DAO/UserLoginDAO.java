package ServerSide.DAO;

import ServerSide.Database.JDBCUtil_Login;
import Models.UserLogin_Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserLoginDAO implements DAOInterface<UserLogin_Models>{

    public static UserLoginDAO getInstance(){
        return new UserLoginDAO();
    }

    @Override
    public int insert(UserLogin_Models userlogin) {
        return 0;
    }

    @Override
    public int update(UserLogin_Models userlogin) {
        return 0;
    }

    @Override
    public int delete(UserLogin_Models userlogin) {
        return 0;
    }

    @Override
    public ArrayList<UserLogin_Models> selectAll() {
        return null;
    }

    @Override
    public UserLogin_Models selectById(UserLogin_Models userlogin) {
        UserLogin_Models kqua = null;
        try {
//          Tạo kết nối
            Connection connection = JDBCUtil_Login.getConnection();
//          Tạo đối tượng PreparedStatement
            String sql = "select * from userlogin where userName=?";
            PreparedStatement pst = connection.prepareStatement(sql);
//          Thực thi câu lệnh
            pst.setString(1,userlogin.getUserName());
            System.out.println(sql);

            ResultSet rs = pst.executeQuery();
//          Lấy dữ liệu ra
            while (rs.next()) {
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");

                kqua  = new UserLogin_Models(userName,passWord,fullName);
            }
//          Đóng kết nối
            JDBCUtil_Login.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kqua;
    }

    @Override
    public ArrayList<UserLogin_Models> selectByCondition(String condition) {
        return null;
    }
}
