package ServerSide.DAO;

import ServerSide.Database.JDBCUtil_Login;
import Models.AdminLogin_Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminLoginDAO implements DAOInterface<AdminLogin_Models>{

    public static AdminLoginDAO getInstance() {
        return new AdminLoginDAO();
    }

    @Override
    public int insert(AdminLogin_Models adminLoginModels) {
        return 0;
    }

    @Override
    public int update(AdminLogin_Models adminLoginModels) {
        return 0;
    }

    @Override
    public int delete(AdminLogin_Models adminLoginModels) {
        return 0;
    }

    @Override
    public ArrayList<AdminLogin_Models> selectAll() {
        return null;
    }

    @Override
    public AdminLogin_Models selectById(AdminLogin_Models adminLoginModels) {
        AdminLogin_Models kqua = null;
        Connection connection = null;
        try {

//            Tạo kết nối
            connection = JDBCUtil_Login.getConnection();
//            Tạo đối tượng PreparedStatement
            String sql = "select * from admin where adminName=?";
            PreparedStatement pst = connection.prepareStatement(sql);
//            Thực thi câu lệnh
            pst.setString(1, adminLoginModels.getAdminName());
            System.out.println(sql);

            ResultSet rs = pst.executeQuery();
//            Lấy dữ liệu ra
            while (rs.next()) {
                String adminName = rs.getString("adminName");
                String adminPass = rs.getString("adminPass");
                String fullName = rs.getString("fullName");

                kqua = new AdminLogin_Models(adminName, adminPass, fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Đóng kết nối
        JDBCUtil_Login.closeConnection(connection);
        return kqua;
    }

    @Override
    public ArrayList<AdminLogin_Models> selectByCondition(String condition) {
        return null;
    }
}
