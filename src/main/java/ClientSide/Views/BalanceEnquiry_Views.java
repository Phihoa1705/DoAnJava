package ClientSide.Views;

import ClientSide.Controllers.BalanceEnquiry_Controller;
import ServerSide.Database.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;



public class BalanceEnquiry_Views extends JFrame {
    private Socket socket;
    private JLabel label2;
    private JButton b1;
    private int balance;
    private String pin;
    private String cardno;
    private DataOutputStream OP;
    private DataInputStream IP;
    public BalanceEnquiry_Views(Socket socket,String pin,String cardno) {
        try {
            this.socket = socket;
            this.IP = new DataInputStream(this.socket.getInputStream());
            this.OP = new DataOutputStream(this.socket.getOutputStream());
            this.cardno = cardno;
            this.pin = pin;
            this.init();
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        this.add(l3);

        JLabel label1 = new JLabel("Your Current Balance is ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Times New Roman",Font.BOLD,16));
        label1.setBounds(430,180,700,35);
        l3.add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label2.setBounds(430,220,400,35);
        l3.add(label2);

        ActionListener ac = new BalanceEnquiry_Controller(this.socket,this);

        b1 = new JButton("Back");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(ac);
        l3.add(b1);

        label2.setText(layDuLieuBalance(pin) + "");

        this.setLayout(null);
        this.setSize(1550,1080);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int layDuLieuBalance(String pin) {
        int balance = 0;
        try {
            OP.writeUTF("GET BALANCE");
            OP.writeUTF(pin);
            balance = IP.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public void setLabel2(JLabel label2) {
        this.label2 = label2;
    }

    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardno() {
        return cardno;
    }
}
