package ServerSide;

import Models.AdminLogin_Models;
import Models.UserLogin_Models;
import ServerSide.DAO.AdminLoginDAO;
import ServerSide.DAO.UserLoginDAO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkUser(String userName, String userPass, String role) {
        boolean kqua = true;
        if(role == "User") {
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
