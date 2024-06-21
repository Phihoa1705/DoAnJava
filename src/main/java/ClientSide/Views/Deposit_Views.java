package ClientSide.Views;

import ClientSide.Controllers.Deposit_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Deposit_Views extends JFrame {
    private Socket socket;
    private String pin;
    private TextField textField;
    private JButton b1,b2;
    private String cardno;

    public Deposit_Views(Socket socket, String pin, String cardno) {
        this.socket = socket;
        this.cardno = cardno;
        this.pin = pin;
        this.init();
        this.setVisible(true);
    }

    private void init() {
        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        this.add(l3);
        
        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Times New Roman",Font.BOLD,16));
        label1.setBounds(460,180,400,35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Times New Roman",Font.BOLD,22));
        l3.add(textField);

        ActionListener ac = new Deposit_Controller(this.socket,this);

        b1 = new JButton("GỬI TIỀN");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(ac);
        l3.add(b1);

        b2 = new JButton("TRỞ VỀ");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(ac);
        l3.add(b2);

        this.setLayout(null);
        this.setSize(1550,1080);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public JButton getB2() {
        return b2;
    }

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public String getCardno() {
        return cardno;
    }
}
