package ServerSide.DAO;

import Models.Bank_Models;
import Models.Signup3_Models;
import ServerSide.Database.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BankDAO implements DAOInterface<Bank_Models>{

    public static BankDAO getInstance() {
        return new BankDAO();
    }
    @Override
    public int insert(Bank_Models bankModels) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,bankModels.getPin());
            pst.setString(2,bankModels.getDate());
            pst.setString(3,bankModels.getType());
            pst.setString(4,bankModels.getAmount());

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
    public int update(Bank_Models bankModels) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE bank SET date=?, type=?, amount=? " +
                    "WHERE pin=?  ";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1,bankModels.getDate());
            pst.setString(2,bankModels.getType());
            pst.setString(3,bankModels.getAmount());
            pst.setString(4,bankModels.getPin());

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
    public int delete(Bank_Models bankModels) {
        int kqua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM bank WHERE pin = ?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,bankModels.getPin());

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
    public ArrayList<Bank_Models> selectAll() {
        ArrayList<Bank_Models> resultList = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM bank";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String pin = rs.getString("pin");
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                Bank_Models bankModels = new Bank_Models(pin,date,type,amount);

                resultList.add(bankModels);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Bank_Models selectById(Bank_Models bankModels) {
        return null;
    }

    @Override
    public ArrayList<Bank_Models> selectByCondition(String condition) {
        return null;
    }
}
