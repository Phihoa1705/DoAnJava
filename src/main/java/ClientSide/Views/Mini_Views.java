package ClientSide.Views;

import ClientSide.Controllers.Mini_Controller;
import ServerSide.Database.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mini_Views extends JFrame {
    private Socket socket;
    private String pin;
    private String cardno;
    private JButton button;
    public Mini_Views(Socket socket,String pin,String cardno) {
        this.socket = socket;
        this.cardno = cardno;
        this.pin = pin;
        this.init();
        this.setVisible(true);
    }

    private void init() {
        JLabel label1 = new JLabel("");
        label1.setOpaque(true); // To ensure the background color is applied
        label1.setBackground(new Color(255, 204, 204));
        JScrollPane scrollPane = new JScrollPane(label1);
        scrollPane.setBounds(20, 140, 340, 200);
        scrollPane.getViewport().setBackground(new Color(255, 204, 204));
        this.add(scrollPane);

        JLabel label2 = new JLabel("Kết Quả Sao Kê");
        label2.setFont(new Font("Times New Roman",Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        this.add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        this.add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        this.add(label4);

        try {
            Connection connection =  JDBCUtil.getConnection();

            String sql1 = "select * from userlogin where userName = ?";

            PreparedStatement pst1 = connection.prepareStatement(sql1);

            pst1.setString(1,this.cardno);

            ResultSet rs = pst1.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("userName");
                if (userName.length() >= 12) {
                    label3.setText("Card Number: " + userName.substring(0, 4) +
                            "XXXXXXXX" + userName.substring(12));
                } else {
                    label3.setText("Card Number: " + userName);  // Hoặc xử lý theo cách khác nếu chuỗi không đủ dài
                }
            }

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            int balance = 0;

            connection = JDBCUtil.getConnection();

            String sql = "select * from bank where pin = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1,pin);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                label1.setText(label1.getText() + "<html>" + rs.getString("date") +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");


                if(rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            label4.setText("Your Total Balance is: " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
        }

        ActionListener ac = new Mini_Controller(this.socket,this);

        button = new JButton("Thoát");
        button.setBounds(20,500,100,25);
        button.addActionListener(ac);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        this.add(button);


        this.getContentPane().setBackground(new Color(255,204,204));
        this.setSize(400,600);
        this.setLayout(null);
        this.setLocation(20,20);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
