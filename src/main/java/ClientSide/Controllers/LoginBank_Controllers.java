package ClientSide.Controllers;

import ClientSide.Views.LoginBank_Views;
import ClientSide.Views.Signup_Views;

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
        if (nsk.equals("Đăng Nhập")) {
            String userName = this.loginBankViews.getUserNameTF().getText();
            String userPass = this.loginBankViews.getUserPassTF().getText();
//            if (userPass.equals("") || userName.equals("")) {
//                this.loginBankViews.thongBaoNhapDuLieu();
//            }
            String role = this.loginBankViews.getSelectRole().getSelectedItem().toString();
            System.out.println("Nút đăng nhập click với role là: " + role);

            Thread checkAcc = new Thread(new Thread_Check_Acc(this.socket, role, userPass, userName, this.loginBankViews));
            checkAcc.setName("checkAcc");
            checkAcc.start();

            try {
                checkAcc.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            new Signup_Views(this.socket);
            this.loginBankViews.dispose();
        }
    }
}
