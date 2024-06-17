package ClientSide.Controllers;

import ClientSide.Views.BalanceEnquiry_Views;
import ClientSide.Views.Deposit_Views;
import ClientSide.Views.MainClass_Views;
import ClientSide.Views.Withdrawl_Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

public class MainClass_Controller implements ActionListener {
    private Socket socket;
    private DataOutputStream OP;
    private MainClass_Views mainClassViews;

    public MainClass_Controller(Socket socket, MainClass_Views mainClassViews) {
        if (socket == null) {
            throw new IllegalArgumentException("Socket is null");
        }
        this.socket = socket;
        this.mainClassViews = mainClassViews;
        try {
            this.OP = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        if (nsk.equals("GỬI TIỀN")) {
            new Deposit_Views(this.socket, this.mainClassViews.getPin());
            this.mainClassViews.setVisible(false);
        } else if (nsk.equals("EXIT")) {
            System.exit(0);
        } else if (nsk.equals("RÚT TIỀN MẶT")) {
            new Withdrawl_Views(this.socket,this.mainClassViews.getPin());
            this.mainClassViews.setVisible(false);
        } else if (nsk.equals("BALANCE ENQUIRY")) {
            new BalanceEnquiry_Views(this.socket,this.mainClassViews.getPin());
            this.mainClassViews.setVisible(false);
        }
    }
}
