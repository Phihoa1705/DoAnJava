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
        this.setTitle("Bank Management System");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
    }

    private void init() {
        ActionListener ac = new LoginBank_Controllers(this.socket, this);
        String[] role = new String[]{"Admin", "User"};

        selectRole = new JComboBox<>(role);
        Font font = new Font("Time New Roman", Font.BOLD, 20);
        Font font_1 = new Font("Time New Roman", Font.BOLD, 14);

        Label loginLB = new Label("Tài Khoản");
        Label passLB = new Label("Mật Khẩu");
        Label dnLB = new Label("ĐĂNG NHẬP");
        dnLB.setFont(font);
        loginLB.setFont(font_1);
        passLB.setFont(font_1);

        userNameTF = new JTextField(60);
        userPassTF = new TextField(60);
        userPassTF.setEchoChar('*');

        loginBT = new JButton("Đăng Nhập");
        loginBT.addActionListener(ac);
        exitBT = new JButton("Đăng ký");
        exitBT.addActionListener(ac);

        JPanel dangNhapPN = new JPanel(new FlowLayout());
        dangNhapPN.add(dnLB);

        JPanel loginPN = new JPanel(new GridLayout(2, 2, 15, 15));
        loginPN.add(loginLB);
        loginPN.add(userNameTF);
        loginPN.add(passLB);
        loginPN.add(userPassTF);

        JPanel rolePN = new JPanel(new FlowLayout());
        rolePN.add(selectRole);

        JPanel inputIfPN = new JPanel(new GridLayout(2, 1));
        inputIfPN.add(loginPN);
        inputIfPN.add(rolePN);

        JPanel buttonPN = new JPanel(new FlowLayout());
        buttonPN.add(loginBT);
        buttonPN.add(exitBT);

        this.setLayout(new BorderLayout());
        this.add(dangNhapPN, BorderLayout.NORTH);
        this.add(inputIfPN, BorderLayout.CENTER);
        this.add(buttonPN, BorderLayout.SOUTH);
//        this.setUndecorated(true);
        this.setVisible(true);
    }

    public JTextField getUserNameTF() {
        return userNameTF;
    }

    public TextField getUserPassTF() {
        return userPassTF;
    }

    public JComboBox<String> getSelectRole() {
        return selectRole;
    }
    public void thongBaoDangNhapThatBai(){
        JOptionPane.showMessageDialog(null,"Sai Tài Khoản hoặc Mật Khẩu");
    }
    public void thongBaoNhapDuLieu(){
        JOptionPane.showMessageDialog(null,"Xin hãy nhập dữ liệu đầy đủ");
    }
}
