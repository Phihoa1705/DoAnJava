package ClientSide.Views;

import ClientSide.Controllers.LoginBank_Controllers;
import Models.LoginBank_Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class LoginBank_Views extends JFrame {
    private Socket socket;
    private LoginBank_Models loginBankModels;
    private JTextField userNameTF;
    private TextField userPassTF;
    private JButton loginBT;
    private JButton exitBT;
    private JComboBox<String> selectRole;

    public LoginBank_Views(Socket socket) {
        this.loginBankModels = new LoginBank_Models();
        this.socket = socket;
        this.setTitle("Login");
        this.setSize(350,350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
    }

    private void init() {
//        Action
        ActionListener ac = new LoginBank_Controllers(this.socket,this);
//        String Role
        String[] role = new String[]{"Admin","User"};
//        JList
        selectRole = new JComboBox<String>(role);
//        Font
        Font font = new Font("Time New Roman", Font.BOLD, 20);
        Font font_1 = new Font("Time New Roman", Font.BOLD, 14);

//        Label
        Label loginLB = new Label("Tài Khoản");
        Label passLB = new Label("Mật Khẩu");
        Label dnLB = new Label("ĐẰNG NHẬP");
        dnLB.setFont(font);
        loginLB.setFont(font_1);
        passLB.setFont(font_1);

//        TextField
        userNameTF = new JTextField(60);
        userPassTF = new TextField(60);
        userPassTF.setEchoChar('*');
//        Button
        loginBT = new JButton("Đăng Nhập");
        loginBT.addActionListener(ac);
        exitBT = new JButton("Thoát");
        exitBT.addActionListener(ac);
//        Pannel
        JPanel dangNhapPN = new JPanel();
        dangNhapPN.setLayout(new FlowLayout());
        dangNhapPN.add(dnLB);

        JPanel loginPN = new JPanel();
        loginPN.setLayout(new GridLayout(2,2,15,15));
        loginPN.add(loginLB);
        loginPN.add(userNameTF);
        loginPN.add(passLB);
        loginPN.add(userPassTF);

        JPanel rolePN = new JPanel();
        rolePN.setLayout(new FlowLayout());
        rolePN.add(selectRole);

        JPanel inputIfPN = new JPanel();
        inputIfPN.setLayout(new GridLayout(2,1));
        inputIfPN.add(loginPN);
        inputIfPN.add(rolePN);

        JPanel buttonPN = new JPanel();
        buttonPN.setLayout(new FlowLayout());
        buttonPN.add(loginBT);
        buttonPN.add(exitBT);

//        Layout(JFRAEM)
        this.setLayout(new BorderLayout());

//        Add Frame
        this.add(dangNhapPN,BorderLayout.NORTH);
        this.add(inputIfPN,BorderLayout.CENTER);
        this.add(buttonPN,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JTextField getUserNameTF() {
        return userNameTF;
    }

    public void setUserNameTF(JTextField userNameTF) {
        this.userNameTF = userNameTF;
    }

    public LoginBank_Models getLoginBankModels() {
        return loginBankModels;
    }

    public void setLoginBankModels(LoginBank_Models loginBankModels) {
        this.loginBankModels = loginBankModels;
    }

    public TextField getUserPassTF() {
        return userPassTF;
    }

    public void setUserPassTF(TextField userPassTF) {
        this.userPassTF = userPassTF;
    }

    public JButton getLoginBT() {
        return loginBT;
    }

    public void setLoginBT(JButton loginBT) {
        this.loginBT = loginBT;
    }

    public JButton getExitBT() {
        return exitBT;
    }

    public void setExitBT(JButton exitBT) {
        this.exitBT = exitBT;
    }

    public JComboBox<String> getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(JComboBox<String> selectRole) {
        this.selectRole = selectRole;
    }


}
