package ClientSide.Controllers;

import ClientSide.Views.BalanceEnquiry_Views;
import ClientSide.Views.MainClass_Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class BalanceEnquiry_Controller implements ActionListener {
    private Socket socket;
    private DataInputStream IP;
    private DataOutputStream OP;
    private BalanceEnquiry_Views balanceEnquiryViews;

    public BalanceEnquiry_Controller(Socket socket,BalanceEnquiry_Views balanceEnquiryViews) {
        try {
            this.socket = socket;
            this.balanceEnquiryViews = balanceEnquiryViews;
            this.IP = new DataInputStream(this.socket.getInputStream());
            this.OP = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.balanceEnquiryViews.setVisible(false);
        new MainClass_Views(this.socket,this.balanceEnquiryViews.getPin());
    }
}
