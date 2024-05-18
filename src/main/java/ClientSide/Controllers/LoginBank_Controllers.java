package ClientSide.Controllers;

import ClientSide.Views.LoginBank_Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginBank_Controllers implements ActionListener {
    private Socket socket;
    private DataInputStream IP;
    private DataOutputStream OP;
    private LoginBank_Views loginBankViews;

    public LoginBank_Controllers(Socket socket, LoginBank_Views loginBankViews) {
        try {
            this.socket = socket;
            this.IP = new DataInputStream(socket.getInputStream());
            this.OP = new DataOutputStream(socket.getOutputStream());
            this.loginBankViews = loginBankViews;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        if (nsk == "Đăng Nhập") {
            System.out.println("Nút đăng nhập click");
            String userName = this.loginBankViews.getUserNameTF().getText();
            String userPass = this.loginBankViews.getUserPassTF().getText();
            String role = this.loginBankViews.getSelectRole().getSelectedItem().toString();

//            Gửi yêu cầu kiểm tra cho Server
            Thread checkAcc = new Thread(new Thread_Check_Acc(socket,role,userPass,userName));
            checkAcc.start();

            try {
                checkAcc.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }else {
            System.out.println("Thoát!!");
        }
    }
}
