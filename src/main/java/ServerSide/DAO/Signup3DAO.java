package ServerSide.DAO;

import Models.Signup3_Models;
import ServerSide.Database.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Signup3DAO implements DAOInterface<Signup3_Models>{

    public static Signup3DAO getInstance() {
        return new Signup3DAO();
    }
    @Override
    public int insert(Signup3_Models signup3Models) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO signupthree (form_no, sccount_Type, card_number, pin, facility) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,signup3Models.getFormNo());
            pst.setString(2,signup3Models.getAccountType());
            pst.setString(3,signup3Models.getCardNumber());
            pst.setString(4,signup3Models.getPin());
            pst.setString(5,signup3Models.getFacility());

            kqua = pst.executeUpdate();
            System.out.println("Thực thi câu lệnh: " + sql);
            System.out.println("Có "+kqua + " dòng thay đổi");

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kqua;
    }

    @Override
    public int update(Signup3_Models signup3Models) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE signupthree SET form_no=?, sccount_Type=?, pin=?, facility=? " +
                    "WHERE card_number=?  ";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1,signup3Models.getFormNo());
            pst.setString(2,signup3Models.getAccountType());
            pst.setString(3,signup3Models.getPin());
            pst.setString(4,signup3Models.getFacility());
            pst.setString(5,signup3Models.getCardNumber());

            kqua = pst.executeUpdate();
            System.out.println("Thực thi câu lệnh: " + sql);
            System.out.println("Có "+kqua + " dòng thay đổi");

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kqua;
    }

    @Override
    public int delete(Signup3_Models signup3Models) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM signupthree WHERE card_number = ?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,signup3Models.getCardNumber());

            kqua = pst.executeUpdate();
            System.out.println("Thực thi câu lệnh: " + sql);
            System.out.println("Có "+kqua + " dòng thay đổi");
            JDBCUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return kqua;
    }

    @Override
    public ArrayList<Signup3_Models> selectAll() {
        ArrayList<Signup3_Models> resultList = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM signupthree";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String form_no = rs.getString("form_no");
                String sccount_Type = rs.getString("sccount_Type");
                String card_number = rs.getString("card_number");
                String pin = rs.getString("pin");
                String facility = rs.getString("facility");

                Signup3_Models signup3Models = new Signup3_Models(form_no,sccount_Type,card_number,pin,facility);

                resultList.add(signup3Models);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Signup3_Models selectById(Signup3_Models signup3Models) {
        return null;
    }

    @Override
    public ArrayList<Signup3_Models> selectByCondition(String condition) {
        return null;
    }
}
