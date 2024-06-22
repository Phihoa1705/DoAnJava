package ServerSide.DAO;

import ServerSide.Database.JDBCUtil;
import Models.UserLogin_Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserLoginDAO implements DAOInterface<UserLogin_Models> {

    public static UserLoginDAO getInstance() {
        return new UserLoginDAO();
    }

    @Override
    public int insert(UserLogin_Models userlogin) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO userlogin (userName, passWord, fullName) VALUES (?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, userlogin.getUserName());
            pst.setString(2, userlogin.getPassWord());
            pst.setString(3, userlogin.getFullName());
            result = pst.executeUpdate();  // Chỉ gọi executeUpdate() không tham số
            System.out.println("Thực thi câu lệnh: " + sql);
            System.out.println("Có "+result + " dòng thay đổi");
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(UserLogin_Models userlogin) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE userlogin SET passWord=?, fullName=? WHERE userName=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, userlogin.getPassWord());
            pst.setString(2, userlogin.getFullName());
            pst.setString(3, userlogin.getUserName());
            result = pst.executeUpdate();  // Chỉ gọi executeUpdate() không tham số
            System.out.println("Executed: " + sql);
            System.out.println(result + " row(s) affected");
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(UserLogin_Models userlogin) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM userlogin WHERE userName = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, userlogin.getUserName());
            result = pst.executeUpdate();  // Chỉ gọi executeUpdate() không tham số
            System.out.println("Executed: " + sql);
            System.out.println(result + " row(s) affected");
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<UserLogin_Models> selectAll() {
        ArrayList<UserLogin_Models> resultList = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM userlogin";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                UserLogin_Models user = new UserLogin_Models(userName, passWord, fullName);
                resultList.add(user);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public UserLogin_Models selectById(UserLogin_Models userlogin) {
        UserLogin_Models result = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM userlogin WHERE userName=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, userlogin.getUserName());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                result = new UserLogin_Models(userName, passWord, fullName);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<UserLogin_Models> selectByCondition(String condition) {
        return null;
    }
}
