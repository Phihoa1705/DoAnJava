package ServerSide;

import Models.AdminLogin_Models;
import Models.UserLogin_Models;
import ServerSide.DAO.AdminLoginDAO;
import ServerSide.DAO.UserLoginDAO;
import ServerSide.Database.JDBCUtil;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ClientHandler implements Runnable{
    private Socket mySocket;
    private String id;
    private DataInputStream IP;
    private DataOutputStream OP;

    public ClientHandler(Socket mySocket, String id) {
        this.mySocket = mySocket;
        this.id = id;
        try {
            this.IP = new DataInputStream(mySocket.getInputStream());
            this.OP = new DataOutputStream(mySocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = "xxxxxxxxxxx";
                // Đọc dữ liệu từ client
                String message = IP.readUTF();

                // Xử lý yêu cầu từ client
                if("Đăng Nhập".equals(message)) {
                    // Đọc dữ liệu từ client
                    String userName = IP.readUTF();
                    String userPass = IP.readUTF();
                    String role = IP.readUTF();
                    boolean check = checkUser(userName,userPass,role);
                    // Viết dữ liệu cho client
                    OP.writeBoolean(check);

                } else if ("Next Singup1".equals(message)){
                    // Đọc dữ liệu từ client
                    String formno = IP.readUTF();
                    name = IP.readUTF();
                    String fName = IP.readUTF();
                    String dob = IP.readUTF();
                    String gender = IP.readUTF();
                    String email = IP.readUTF();
                    String add = IP.readUTF();
                    String city = IP.readUTF();
                    String state = IP.readUTF();
                    int pin = IP.readInt();
                    String marital = IP.readUTF();

                    insertSignup1(formno,name,fName,dob,gender,email,add,city,state,pin,marital);
                } else if("Next Singup2".equals(message)){
                    String formno = IP.readUTF();
                    String rel = IP.readUTF();
                    String cate = IP.readUTF();
                    String income = IP.readUTF();
                    String edu = IP.readUTF();
                    String occ = IP.readUTF();
                    String pan = IP.readUTF();
                    String eAccount = IP.readUTF();

                    insertSignup2(formno,rel,cate,income,edu,occ,pan,eAccount);
                } else if ("Submit Singup3".equals(message)) {
                    String formno = IP.readUTF();
                    String sccount_Type = IP.readUTF();
                    String card_number = IP.readUTF();
                    String pin = IP.readUTF();
                    String facility = IP.readUTF();
                    insertSignup3(name,formno,sccount_Type,card_number,pin,facility);
                } else if ("Deposit".equals(message)) {
                    String pin = IP.readUTF();
                    long dateMillis = IP.readLong();
                    Date date = new Date(dateMillis);
                    String amount = IP.readUTF();
                    insertBank(pin,date,"Deposit",amount);
                } else if ("Withdrawl".equals(message)) {
                    String pin = IP.readUTF();
                    int balance = selectBank(pin);
                    OP.writeInt(balance); // Gửi số dư hiện tại cho client

                    long dateMillis = IP.readLong();
                    Date date = new Date(dateMillis);
                    String amount = IP.readUTF(); // Đọc số tiền từ client

                    if (balance >= Integer.parseInt(amount)) {
                        insertBank(pin, date, "Withdrawl", amount);
                        OP.writeUTF("RÚT TIỀN THÀNH CÔNG: " + amount + " VNĐ"); // Gửi thông báo rút tiền thành công
                    } else {
                        OP.writeUTF("SỐ DƯ KHÔNG ĐỦ"); // Gửi thông báo số dư không đủ
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int selectBank(String pin) {
        int balance = 0;
        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = "select * from bank where pin = ?";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1,pin);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                if(rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    private  void  insertBank(String pin, Date date,String type,String amount) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "insert into bank(pin, date, type, amount)" +
                    " values (?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1,pin);
            pst.setDate(2, new java.sql.Date(date.getTime()));
            pst.setString(3,type);
            pst.setString(4,amount);

            System.out.println("Đang chuẩn bị thực thi truy vấn");
            int kqua = pst.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + kqua + " dòng bị thay đổi");

            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void insertSignup3(String name,String form_no,String sccount_Type, String card_number,String pin,String facility){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql1 = "insert into signupthree(form_no, sccount_Type, card_number, pin, facility)" +
                    " values (?, ?, ?, ?, ?)";
            String sql2 = "insert into userlogin(userName, passWord, fullName)" +
                    " values (?, ?, ?)";

            PreparedStatement pst1 = connection.prepareStatement(sql1);
            PreparedStatement pst2 = connection.prepareStatement(sql2);

            pst1.setString(1,form_no);
            pst1.setString(2,sccount_Type);
            pst1.setString(3,card_number);
            pst1.setString(4,pin);
            pst1.setString(5,facility);
            pst1.executeUpdate();

            pst2.setString(1,card_number);
            pst2.setString(2,pin);
            pst2.setString(3,name);
            pst2.executeUpdate();


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertSignup2(String formno, String religion, String category, String income, String education,
                               String occuption,String pan, String existing_account){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "insert into signuptwo(form_no, religion, category, income, education," +
                    " occuption, pan, existing_account) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, formno);
            pst.setString(2,religion);
            pst.setString(3,category);
            pst.setString(4,income);
            pst.setString(5,education);
            pst.setString(6,occuption);
            pst.setString(7,pan);
            pst.setString(8,existing_account);

            System.out.println("Đang chuẩn bị thực thi truy vấn");
            int kqua = pst.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + kqua + " dòng bị thay đổi");


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertSignup1(String formno, String name, String fName, String dob, String gender,
                              String email, String add, String city, String state, int pin, String marital){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "insert into signup(form_no, name, farther_name, DOB, gender, " +
                    "email, maritalStatus, address, city, pincode, state)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, formno);
            pst.setString(2, name);
            pst.setString(3, fName);
            pst.setString(4, dob);
            pst.setString(5, gender);
            pst.setString(6, email);
            pst.setString(7, marital);
            pst.setString(8, add);
            pst.setString(9, city);
            pst.setInt(10, pin);
            pst.setString(11, state);

            System.out.println("Đang chuẩn bị thực thi truy vấn");
            int kqua = pst.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + kqua + " dòng bị thay đổi");


            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkUser(String userName, String userPass, String role) {
        boolean kqua = true;
        if(role.equals("User")) {
            System.out.println(userName);
            System.out.println(userPass);
            UserLogin_Models u_find =  new UserLogin_Models(userName,userPass);

            UserLogin_Models u_result = UserLoginDAO.getInstance().selectById(u_find);

            if (u_find.equals(u_result)) {
                kqua = true;
            } else {
                kqua = false;
            }
        } else {
            System.out.println(userName);
            System.out.println(userPass);
            AdminLogin_Models a_find =  new AdminLogin_Models(userName,userPass);

            AdminLogin_Models a_result = AdminLoginDAO.getInstance().selectById(a_find);
            if (a_find.equals(a_result)) {
                kqua = true;
            } else {
                kqua = false;
            }
        }
        return kqua;
    }

    public void sendMessage(String message) {
        try {
            OP.writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
