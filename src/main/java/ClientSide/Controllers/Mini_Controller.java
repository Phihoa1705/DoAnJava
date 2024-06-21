package ClientSide.Controllers;

import ClientSide.Views.Mini_Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Mini_Controller implements ActionListener {
    private Socket socket;
    private DataInputStream IP;
    private DataOutputStream OP;
    private Mini_Views miniViews;

    public Mini_Controller(Socket socket, Mini_Views miniViews) {
        try {
            this.socket = socket;
            this.miniViews = miniViews;
            this.IP = new DataInputStream(this.socket.getInputStream());
            this.OP = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.miniViews.setVisible(false);
    }
}
