package ClientSide.Controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Thread_Check_Acc implements Runnable{

    private Socket socket;
    private String userName;
    private String userPass;
    private String role;
    private DataOutputStream OP;
    private DataInputStream IP;

    public Thread_Check_Acc(Socket socket,String role, String userPass, String userName) {
        this.role = role;
        this.socket = socket;
        this.userPass = userPass;
        this.userName = userName;
        try {
            OP = new DataOutputStream(socket.getOutputStream());
            IP = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            OP.writeUTF("Đăng Nhập");
            OP.writeUTF(userName);
            OP.writeUTF(userPass);
            OP.writeUTF(role);

            boolean ktra = IP.readBoolean();
            if(ktra == true) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Đăng nhập thất bại");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
