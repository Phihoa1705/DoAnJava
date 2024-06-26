package ClientSide.Views;

import ClientSide.Controllers.MainClass_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MainClass_Views extends JFrame {
    private Socket socket;
    private JButton b1, b2, b3, b4, b5, b6, b7;
    private String pin;
    private String card_no;

    public MainClass_Views(Socket socket, String pin,String card_no) {
        this.pin = pin;
        this.card_no = card_no;
        this.socket = socket;
        this.init();
        this.setVisible(true);
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        this.add(l3);

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(430, 180, 700, 35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.BOLD, 28));
        l3.add(label);

        ActionListener ac = new MainClass_Controller(this.socket, this);

        b1 = new JButton("GỬI TIỀN");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(410, 274, 150, 35);
        b1.addActionListener(ac);
        l3.add(b1);

        b2 = new JButton("RÚT TIỀN MẶT");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(700, 274, 150, 35);
        b2.addActionListener(ac);
        l3.add(b2);

//        b3 = new JButton("FAST CASH");
//        b3.setForeground(Color.WHITE);
//        b3.setBackground(new Color(65, 125, 128));
//        b3.setBounds(410, 318, 150, 35);
//        700, 318, 150, 35
//        410, 362, 150, 35
//        700, 362, 150, 35
//        b3.addActionListener(ac);
//        l3.add(b3);

        b4 = new JButton("MINI STATEMENT");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65, 125, 128));
        b4.setBounds(410, 318, 150, 35);
        b4.addActionListener(ac);
        l3.add(b4);

        b5 = new JButton("PIN CHANGE");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65, 125, 128));
        b5.setBounds(700, 318, 150, 35);
        b5.addActionListener(ac);
        l3.add(b5);

        b6 = new JButton("BALANCE ENQUIRY");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65, 125, 128));
        b6.setBounds(410, 362, 150, 35);
        b6.addActionListener(ac);
        l3.add(b6);

        b7 = new JButton("EXIT");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65, 125, 128));
        b7.setBounds(700, 362, 150, 35);
        b7.addActionListener(ac);
        l3.add(b7);

        this.setLayout(null);
        this.setSize(1550, 830);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getPin() {
        return pin;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
