package ServerSide.DAO;

import ServerSide.Database.JDBCUtil;
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
//      Bước 1: Tạo kết nối
            Connection connection = JDBCUtil.getConnection();
//      Bước 2: Tạo ra đối tượng PreparedStatement
            String sql = "select * from userlogin where userName=?";
            PreparedStatement pst = connection.prepareStatement(sql);
//      Bước 3: Thực thi câu lệnh SQL
            pst.setString(1,userlogin.getUserName());
            System.out.println(sql);
//      executeQuery là truy vấn
//      executeQuery(sql) trả về đối tượng ResultSet
//      Cứ coi ResultSet như là 1 table có nhiều dòng bên trong chúng ta có thể lấy từng dòng ra thông qua vòng lặp while()
            ResultSet rs = pst.executeQuery();

//      Bước 4:

//      rs.next() là 1 phương thức đi tới (duyệt từng dòng 1) nếu còn dữ liệu thì next() rồi lấy dữ liệu ra
//      getString(<columnName>) tương ứng với dòng đó thì ta sẽ lấy dữ liệu ra (lấy giá trị của cột id ra)
//      getString(<columnInt>) có thể truyền vào thứ tự cột
            while (rs.next()) {
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");

                kqua  = new UserLogin_Models(userName,passWord,fullName);
            }
//      Bước 5:
            JDBCUtil.closeConnection(connection);
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
