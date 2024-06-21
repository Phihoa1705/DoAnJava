package ClientSide.Controllers;

import ClientSide.Views.Deposit_Views;
import ClientSide.Views.MainClass_Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

public class Deposit_Controller implements ActionListener {
    private Socket socket;
    private DataOutputStream OP;
    private DataInputStream IP;
    private Deposit_Views depositViews;

    public Deposit_Controller(Socket socket, Deposit_Views depositViews) {
        try {
            this.socket = socket;
            this.depositViews = depositViews;
            this.IP = new DataInputStream(socket.getInputStream());
            this.OP = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        try {
            String amount = depositViews.getTextField().getText();
            Date date = new Date();
            if (nsk.equals("GỬI TIỀN")) {
                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    OP.writeUTF("Deposit");
                    OP.writeUTF(depositViews.getPin());
                    OP.writeLong(date.getTime());
                    OP.writeUTF(amount);
                    JOptionPane.showMessageDialog(null, amount + " GỬI TIỀN THÀNH CÔNG ");
                    depositViews.setVisible(false);
                    new MainClass_Views(this.socket,this.depositViews.getPin(),this.depositViews.getCardno());
                }
            } else if (nsk.equals("TRỞ VỀ")) {
                depositViews.setVisible(false);
                new MainClass_Views(this.socket,this.depositViews.getPin(),this.depositViews.getCardno());
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
