package ClientSide.Views;

import ClientSide.Controllers.Signup_Controller;
import LimitText.LimitDocumentFilter;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Random;

public class Signup_Views extends JFrame{
    private JTextField textName,textFname,
            textEmail,textAdd,textCity,textState,textPin;
    private JRadioButton r1,r2,m1,m2,m3;
    private JButton next;
    private JDateChooser dateChooser;
    private Random ran = new Random();
    private long first4 = (ran.nextLong() % 9000L) + 1000L;
    private String first = " " + Math.abs(first4);
    private Socket socket;
    public Signup_Views(Socket socket){
        this.setLayout(null);
        this.socket = socket;
        this.getContentPane().setBackground(new Color(222,255,228));
        this.setSize(850,800);
        this.setLocation(360,40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25,10,100,100);
        this.add(image);

        JLabel label1 = new JLabel("APPLICATION FROM NO." + first);
        label1.setBounds(160,20,600,40);
        label1.setFont(new Font("Times New Roman",Font.BOLD,38));
        this.add(label1);

        JLabel label2 = new JLabel("Page 1");
        label2.setFont(new Font("Times New Roman",Font.BOLD,22));
        label2.setBounds(330,70,600,30);
        this.add(label2);

        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Times New Roman",Font.BOLD,22));
        label3.setBounds(290,90,600,30);
        this.add(label3);

        JLabel labelName = new JLabel("Tên:");
        labelName.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelName.setBounds(100,190,100,30);
        this.add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Times New Roman",Font.BOLD,14));
        textName.setBounds(300,190,400,30);
        this.add(textName);

        JLabel labelfName = new JLabel("Họ:");
        labelfName.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelfName.setBounds(100,240,200,30);
        this.add(labelfName);

        textFname = new JTextField();
        textFname.setFont(new Font("Times New Roman",Font.BOLD,14));
        textFname.setBounds(300,240,400,30);
        this.add(textFname);

        JLabel DOB = new JLabel("Ngày Sinh:");
        DOB.setFont(new Font("Times New Roman",Font.BOLD,20));
        DOB.setBounds(100,340,200,30);
        this.add(DOB);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300,340,400,30);
        this.add(dateChooser);

        JLabel labelG =  new JLabel("Giới tính:");
        labelG.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelG.setBounds(100,290,200,30);
        this.add(labelG);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Times New Roman",Font.BOLD,14));
        r1.setBackground(new Color(222,255,228));
        r1.setBounds(300,290,60,30);
        this.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBackground(new Color(222,255,228));
        r2.setFont(new Font("Times New Roman",Font.BOLD,14));
        r2.setBounds(450,290,90,30);
        this.add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel labelEmail = new JLabel("Địa chỉ email: ");
        labelEmail.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelEmail.setBounds(100,390,200,30);
        this.add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Times New Roman",Font.BOLD,14));
        textEmail.setBounds(300,390,400,30);
        this.add(textEmail);

        JLabel labelMs = new JLabel("Tình trạng hôn nhân: ");
        labelMs.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelMs.setBounds(100,440,200,30);
        this.add(labelMs);

        m1 = new JRadioButton("Married");
        m1.setBackground(new Color(222,255,228));
        m1.setBounds(300,440,100,30);
        m1.setFont(new Font("Times New Roman",Font.BOLD,20));
        this.add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setBackground(new Color(222,255,228));
        m2.setBounds(440,440,140,30);
        m2.setFont(new Font("Times New Roman",Font.BOLD,20));
        this.add(m2);

        m3 = new JRadioButton("Other");
        m3.setBackground(new Color(222,255,228));
        m3.setBounds(635,440,100,30);
        m3.setFont(new Font("Times New Roman",Font.BOLD,20));
        this.add(m3);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(m1);
        buttonGroup1.add(m2);
        buttonGroup1.add(m3);


        JLabel labelAdd = new JLabel("Địa chỉ: ");
        labelAdd.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelAdd.setBounds(100,490,200,30);
        this.add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Times New Roman",Font.BOLD,14));
        textAdd.setBounds(300,490,400,30);
        this.add(textAdd);

        JLabel labelCity = new JLabel("Công ty: ");
        labelCity.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelCity.setBounds(100,540,200,30);
        this.add(labelCity);

        textCity= new JTextField();
        textCity.setFont(new Font("Times New Roman",Font.BOLD,14));
        textCity.setBounds(300,540,400,30);
        this.add(textCity);

        JLabel labelPin= new JLabel("Pin Code: ");
        labelPin.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelPin.setBounds(100,590,200,30);
        this.add(labelPin);

        textPin= new JTextField(4);
        // Áp dụng DocumentFilter để giới hạn ký tự nhập vào
        ((AbstractDocument) textPin.getDocument()).setDocumentFilter(new LimitDocumentFilter(4));
        textPin.setFont(new Font("Times New Roman",Font.BOLD,14));
        textPin.setBounds(300,590,400,30);
        this.add(textPin);

        JLabel labelState = new JLabel("Quận/Huyện/Phường/Xã: ");
        labelState.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelState.setBounds(100,640,200,30);
        this.add(labelState);

        textState= new JTextField();
        textState.setFont(new Font("Times New Roman",Font.BOLD,14));
        textState.setBounds(300,640,400,30);
        this.add(textState);

        ActionListener ac = new Signup_Controller(this.socket,this);

        next = new JButton("Next");
        next.setFont(new Font("Times New Roman",Font.BOLD,14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620,710,80,30);
        next.addActionListener(ac);
        this.add(next);

        this.setVisible(true);
    }

    public JTextField getTextName() {
        return textName;
    }

    public void setTextName(JTextField textName) {
        this.textName = textName;
    }

    public JTextField getTextFname() {
        return textFname;
    }

    public void setTextFname(JTextField textFname) {
        this.textFname = textFname;
    }

    public JTextField getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(JTextField textEmail) {
        this.textEmail = textEmail;
    }

    public JTextField getTextAdd() {
        return textAdd;
    }

    public void setTextAdd(JTextField textAdd) {
        this.textAdd = textAdd;
    }

    public JTextField getTextState() {
        return textState;
    }

    public void setTextState(JTextField textState) {
        this.textState = textState;
    }

    public JTextField getTextCity() {
        return textCity;
    }

    public void setTextCity(JTextField textCity) {
        this.textCity = textCity;
    }

    public JTextField getTextPin() {
        return textPin;
    }

    public void setTextPin(JTextField textPin) {
        this.textPin = textPin;
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

    public JRadioButton getM2() {
        return m2;
    }

    public void setM2(JRadioButton m2) {
        this.m2 = m2;
    }

    public JRadioButton getM1() {
        return m1;
    }

    public void setM1(JRadioButton m1) {
        this.m1 = m1;
    }

    public JRadioButton getM3() {
        return m3;
    }

    public void setM3(JRadioButton m3) {
        this.m3 = m3;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

    public JButton getNext() {
        return next;
    }

    public void setNext(JButton next) {
        this.next = next;
    }

    public Random getRan() {
        return ran;
    }

    public void setRan(Random ran) {
        this.ran = ran;
    }

    public long getFirst4() {
        return first4;
    }

    public void setFirst4(long first4) {
        this.first4 = first4;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
