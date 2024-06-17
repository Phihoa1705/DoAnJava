package ClientSide.Views;

import ClientSide.Controllers.Signup3_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Signup3_Views extends JFrame{
    private String formno;
    private Socket socket;
    private JRadioButton r1,r2,r3,r4;
    private JCheckBox c1,c2,c3,c4,c5,c6;
    private JButton s,c;

    public Signup3_Views(String formno,Socket socket) {
        this.formno = formno;
        this.socket = socket;
        this.init();
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150,5,100,100);
        this.add(image);

        Label lb1 = new Label("Page 3:");
        lb1.setFont(new Font("Times New Roman",Font.BOLD,22));
        lb1.setBounds(280,40,400,40);
        this.add(lb1);

        Label lb2 = new Label("Account Details");
        lb2.setFont(new Font("Times New Roman",Font.BOLD,22));
        lb2.setBounds(280,70,400,40);
        this.add(lb2);

        Label lb3 = new Label("Tài khoản ngân hàng:");
        lb3.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb3.setBounds(100,140,200,30);
        this.add(lb3);

        r1 = new JRadioButton("Tài khoản tiết kiệm");
        r1.setFont(new Font("Times New Roman",Font.BOLD,16));
        r1.setBackground(new Color(215,252,252));
        r1.setBounds(100,180,170,30);
        this.add(r1);

        r2 = new JRadioButton("Tài khoản tiền gửi cố định");
        r2.setFont(new Font("Times New Roman",Font.BOLD,16));
        r2.setBackground(new Color(215,252,252));
        r2.setBounds(350,180,300,30);
        this.add(r2);

//        r3 = new JRadioButton("Tài khoản tiền gửi định kỳ");
//        r3.setFont(new Font("Times New Roman",Font.BOLD,16));
//        r3.setBackground(new Color(215,252,252));
//        r3.setBounds(100,220,250,30);
//        this.add(r3);
//
//        r4 = new JRadioButton("Recurring Deposit Account");
//        r4.setFont(new Font("Times New Roman",Font.BOLD,16));
//        r4.setBackground(new Color(215,252,252));
//        r4.setBounds(100,220,250,30);
//        this.add(r4);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        Label lb4 = new Label("Card Number:");
        lb4.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb4.setBounds(100,300,200,30);
        this.add(lb4);

        Label lb5 = new Label("(Your 16-digit Card Number)");
        lb5.setFont(new Font("Times New Roman",Font.BOLD,12));
        lb5.setBounds(100,330,200,20);
        this.add(lb5);

        Label lb6 = new Label("XXXX-XXXX-XXXX-4841:");
        lb6.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb6.setBounds(330,300,250,30);
        this.add(lb6);

        Label lb7 = new Label("(It would appear on atm card/cheque Book and Statements)");
        lb7.setFont(new Font("Times New Roman",Font.BOLD,12));
        lb7.setBounds(330,330,500,20);
        this.add(lb7);

        Label lb8 = new Label("PIN:");
        lb8.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb8.setBounds(100,370,200,30);
        this.add(lb8);

        Label lb9 = new Label("XXXX");
        lb9.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb9.setBounds(330,370,200,30);
        this.add(lb9);

        Label lb10 = new Label("(4-digit Password)");
        lb10.setFont(new Font("Times New Roman",Font.BOLD,12));
        lb10.setBounds(100,400,200,20);
        this.add(lb10);

        Label lb11 = new Label("Services Required:");
        lb11.setFont(new Font("Times New Roman",Font.BOLD,18));
        lb11.setBounds(100,450,200,30);
        this.add(lb11);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(new Color(215,252,252));
        c1.setFont(new Font("Times New Roman",Font.BOLD,16));
        c1.setBounds(100,500,200,30);
        this.add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(new Color(215,252,252));
        c2.setFont(new Font("Times New Roman",Font.BOLD,16));
        c2.setBounds(350,500,200,30);
        this.add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(new Color(215,252,252));
        c3.setFont(new Font("Times New Roman",Font.BOLD,16));
        c3.setBounds(100,550,200,30);
        this.add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setBackground(new Color(215,252,252));
        c4.setFont(new Font("Times New Roman",Font.BOLD,16));
        c4.setBounds(350,550,200,30);
        this.add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(new Color(215,252,252));
        c5.setFont(new Font("Times New Roman",Font.BOLD,16));
        c5.setBounds(100,600,200,30);
        this.add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(new Color(215,252,252));
        c6.setFont(new Font("Times New Roman",Font.BOLD,16));
        c6.setBounds(350,600,200,30);
        this.add(c6);

        JCheckBox c7 = new JCheckBox("I here by decleares that the above enteed details correct to the best of my knlowledge.",true);
        c7.setBackground(new Color(215,252,252));
        c7.setFont(new Font("Times New Roman",Font.BOLD,12));
        c7.setBounds(100,680,600,20);
        this.add(c7);

        JLabel lb12 = new JLabel("Form No:");
        lb12.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lb12.setBounds(700, 10, 100, 30);
        this.add(lb12);

        JLabel lb13 = new JLabel(this.formno);
        lb13.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lb13.setBounds(760, 10, 60, 30);
        this.add(lb13);

        ActionListener ac = new Signup3_Controller(this.socket,this);

        s = new JButton("Submit");
        s.setFont(new Font("Times New Roman", Font.BOLD, 14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setBounds(250,720,100,30);
        s.addActionListener(ac);
        this.add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Times New Roman", Font.BOLD, 14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(420,720,100,30);
        c.addActionListener(ac);
        this.add(c);

        this.setSize(850,800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,20);
        this.getContentPane().setBackground(new Color(215,252,252));
        this.setVisible(true);
    }

    public Signup3_Views(Socket socket){
        this.socket = socket;
        this.init();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public JRadioButton getR1() {
        return r1;
    }

    public void setR1(JRadioButton r1) {
        this.r1 = r1;
    }

    public JRadioButton getR2() {
        return r2;
    }

    public void setR2(JRadioButton r2) {
        this.r2 = r2;
    }

    public JRadioButton getR3() {
        return r3;
    }

    public void setR3(JRadioButton r3) {
        this.r3 = r3;
    }

    public JRadioButton getR4() {
        return r4;
    }

    public void setR4(JRadioButton r4) {
        this.r4 = r4;
    }

    public JCheckBox getC1() {
        return c1;
    }

    public void setC1(JCheckBox c1) {
        this.c1 = c1;
    }

    public JCheckBox getC2() {
        return c2;
    }

    public void setC2(JCheckBox c2) {
        this.c2 = c2;
    }

    public JCheckBox getC3() {
        return c3;
    }

    public void setC3(JCheckBox c3) {
        this.c3 = c3;
    }

    public JCheckBox getC4() {
        return c4;
    }

    public void setC4(JCheckBox c4) {
        this.c4 = c4;
    }

    public JCheckBox getC5() {
        return c5;
    }

    public void setC5(JCheckBox c5) {
        this.c5 = c5;
    }

    public JCheckBox getC6() {
        return c6;
    }

    public void setC6(JCheckBox c6) {
        this.c6 = c6;
    }

    public JButton getC() {
        return c;
    }

    public void setC(JButton c) {
        this.c = c;
    }

    public JButton getS() {
        return s;
    }

    public void setS(JButton s) {
        this.s = s;
    }

    public String getFormno() {
        return formno;
    }

    public void setFormno(String formno) {
        this.formno = formno;
    }
}
