package ClientSide.Views;

import ClientSide.Controllers.Signup2_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Signup2_Views extends JFrame {

    private String formno;
    private JComboBox comboBox,comboBox2,comboBox3,comboBox4,comboBox5;
    private JRadioButton r1,r2;
    private JTextField textPAN;
    private JButton next;
    private Socket socket;
    public Signup2_Views(){
        this.setLayout(null);
        this.setSize(850,750);
        this.setLocation(450,80);
        this.getContentPane().setBackground(new Color(252,208,76));
        this.init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Signup2_Views(String formno,Socket socket) {
        super("APPLICATION FROM NO." +formno);
        this.socket = socket;
        this.formno = formno;
        this.setLayout(null);
        this.setSize(850,750);
        this.setLocation(450,80);
        this.getContentPane().setBackground(new Color(252,208,76));
        this.init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150,5,100,100);
        this.add(image);

        JLabel l1 = new JLabel("Page 2 :-");
        l1.setFont(new Font("Times New Roman",Font.BOLD,22));
        l1.setBounds(300,30,600,40);
        this.add(l1);

        JLabel l2 = new JLabel("Bổ sung thông tin:");
        l2.setFont(new Font("Times New Roman",Font.BOLD,22));
        l2.setBounds(300,60,600,40);
        this.add(l2);

        JLabel l3 = new JLabel("Tôn giáo :");
        l3.setFont(new Font("Times New Roman",Font.BOLD,18));
        l3.setBounds(100,120,100,30);
        this.add(l3);

        String religion[] = {"Hindu","Muslim","Sikh","Christian","Other"};
        comboBox = new JComboBox(religion);
        comboBox.setBackground(new Color(252,208,76));
        comboBox.setFont(new Font("Times New Roman",Font.BOLD,14));
        comboBox.setBounds(350,120,320,30);
        this.add(comboBox);

        JLabel l4 = new JLabel("Loại Ngân Hàng:");
        l4.setFont(new Font("Times New Roman",Font.BOLD,18));
        l4.setBounds(100,170,300,30);
        this.add(l4);

        String category[] = {"MB","Agribank","TPBank","Vietcombank","Other"};
        comboBox2 = new JComboBox(category);
        comboBox2.setBackground(new Color(252,208,76));
        comboBox2.setFont(new Font("Times New Roman",Font.BOLD,14));
        comboBox2.setBounds(350,170,320,30);
        this.add(comboBox2);

        JLabel l5 = new JLabel("Thu nhập :");
        l5.setFont(new Font("Times New Roman",Font.BOLD,18));
        l5.setBounds(100,220,100,30);
        this.add(l5);

        String income[] = {"Null","Từ 5 triệu đến dưới 10 triệu VND","Từ 10 triệu đến dưới 20 triệu VND",
                "Từ 20 triệu đến dưới 30 triệu VND","Từ 30 triệu đến dưới 50 triệu","Trên 50 triệu VND"};
        comboBox3 = new JComboBox(income);
        comboBox3.setBackground(new Color(252,208,76));
        comboBox3.setFont(new Font("Times New Roman",Font.BOLD,14));
        comboBox3.setBounds(350,220,320,30);
        this.add(comboBox3);

        JLabel l6 = new JLabel("Giáo dục :");
        l6.setFont(new Font("Times New Roman",Font.BOLD,18));
        l6.setBounds(100,270,150,30);
        this.add(l6);

        String educational[] = {"Trung học cơ sở","Trung học phổ thông","Đại học",
                "Other"};
        comboBox4 = new JComboBox(educational);
        comboBox4.setBackground(new Color(252,208,76));
        comboBox4.setFont(new Font("Times New Roman",Font.BOLD,14));
        comboBox4.setBounds(350,270,320,30);
        this.add(comboBox4);

        JLabel l7 = new JLabel("Nghề nghiệp :");
        l7.setFont(new Font("Times New Roman",Font.BOLD,18));
        l7.setBounds(100,340,150,30);
        this.add(l7);

        String occupation[] = {"Nhân viên", "Tự kinh doanh", "Kinh doanh", "Sinh viên", "Nghỉ hưu", "Khác"};
        comboBox5 = new JComboBox(occupation);
        comboBox5.setBackground(new Color(252,208,76));
        comboBox5.setFont(new Font("Times New Roman",Font.BOLD,14));
        comboBox5.setBounds(350,340,320,30);
        this.add(comboBox5);

        JLabel l8 = new JLabel("Số tài khoản :");
        l8.setFont(new Font("Times New Roman",Font.BOLD,18));
        l8.setBounds(100,390,150,30);
        this.add(l8);

        textPAN = new JTextField();
        textPAN.setFont(new Font("Times New Roman",Font.BOLD,18));
        textPAN.setBounds(350,390,150,30);
        this.add(textPAN);

        JLabel l9 = new JLabel("Existing Account");
        l9.setFont(new Font("Times New Roman",Font.BOLD,18));
        l9.setBounds(100,440,180,30);
        this.add(l9);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Times New Roman",Font.BOLD,18));
        r1.setBackground(new Color(252,208,76));
        r1.setBounds(350,440,100,30);
        this.add(r1);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Times New Roman",Font.BOLD,18));
        r2.setBackground(new Color(252,208,76));
        r2.setBounds(460,440,100,30);
        this.add(r2);


        JLabel l10 = new JLabel("Form No:");
        l10.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l10.setBounds(570, 10, 100, 30);
        this.add(l10);

        JLabel l11 = new JLabel(this.formno);
        l11.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l11.setBounds(680, 10, 100, 30);
        this.add(l11);

        ActionListener ac = new Signup2_Controller(this.socket,this);

        next = new JButton("Next");
        next.setFont(new Font("Times New Roman",Font.BOLD,14));
        next.setBounds(570,640,100,30);
        next.setForeground(Color.BLACK);
        next.setBackground(Color.WHITE);
        next.addActionListener(ac);
        this.add(next);
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

    public String getFormno() {
        return formno;
    }

    public void setFormno(String formno) {
        this.formno = formno;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.comboBox2 = comboBox2;
    }

    public JComboBox getComboBox3() {
        return comboBox3;
    }

    public void setComboBox3(JComboBox comboBox3) {
        this.comboBox3 = comboBox3;
    }

    public JComboBox getComboBox4() {
        return comboBox4;
    }

    public void setComboBox4(JComboBox comboBox4) {
        this.comboBox4 = comboBox4;
    }

    public JComboBox getComboBox5() {
        return comboBox5;
    }

    public void setComboBox5(JComboBox comboBox5) {
        this.comboBox5 = comboBox5;
    }

    public JTextField getTextPAN() {
        return textPAN;
    }

    public void setTextPAN(JTextField textPAN) {
        this.textPAN = textPAN;
    }

    public JButton getNext() {
        return next;
    }

    public void setNext(JButton next) {
        this.next = next;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        new Signup2_Views();
    }
}
