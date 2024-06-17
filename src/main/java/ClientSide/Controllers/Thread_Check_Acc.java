package ClientSide.Controllers;

import ClientSide.Views.LoginBank_Views;
import ClientSide.Views.MainClass_Views;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Thread_Check_Acc implements Runnable {

    private Socket socket;
    private String userName;
    private String userPass;
    private String role;
    private DataOutputStream OP;
    private DataInputStream IP;
    private LoginBank_Views loginBankViews;

    public Thread_Check_Acc(Socket socket, String role, String userPass, String userName, LoginBank_Views loginBankViews) {
        if (socket == null) {
            throw new IllegalArgumentException("Socket is null");
        }
        this.role = role;
        this.loginBankViews = loginBankViews;
        this.socket = socket;
        this.userPass = userPass;
        this.userName = userName;
        try {
            this.OP = new DataOutputStream(socket.getOutputStream());
            this.IP = new DataInputStream(socket.getInputStream());
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
            if (ktra) {
                System.out.println("Đăng nhập thành công");
                new MainClass_Views(this.socket, userPass);  // Sửa đổi này để tránh lỗi
                SwingUtilities.invokeLater(() -> {
                    loginBankViews.setVisible(false);
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    loginBankViews.thongBaoDangNhapThatBai();
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
