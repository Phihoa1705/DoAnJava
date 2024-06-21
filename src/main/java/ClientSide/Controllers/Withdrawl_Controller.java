package ClientSide.Controllers;

import ClientSide.Views.MainClass_Views;
import ClientSide.Views.Withdrawl_Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

public class Withdrawl_Controller implements ActionListener {
    private Withdrawl_Views withdrawlViews;
    private Socket socket;
    private DataOutputStream OP;
    private DataInputStream IP;

    public Withdrawl_Controller(Withdrawl_Views withdrawlViews, Socket socket) {
        try {
            this.withdrawlViews = withdrawlViews;
            this.socket = socket;
            this.OP = new DataOutputStream(socket.getOutputStream());
            this.IP = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        try {
            String amount = this.withdrawlViews.getTextField().getText();
            if (nsk.equals("RÚT TIỀN")) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền bạn muốn rút");
                } else {
                    // Kiểm tra xem số tiền có lớn hơn 100,000,000 VNĐ không
                    int withdrawAmount = Integer.parseInt(amount);
                    if (withdrawAmount > 100000000) {
                        JOptionPane.showMessageDialog(null, "Số tiền rút không được vượt quá 100,000,000 VNĐ");
                    } else {
                        OP.writeUTF("Withdrawl");
                        OP.writeUTF(this.withdrawlViews.getPin());

                        int balance = IP.readInt(); // Đọc số dư hiện tại từ server
                        long dateMillis = new Date().getTime();
                        OP.writeLong(dateMillis);
                        OP.writeUTF(amount); // Gửi số tiền rút về server

                        String message = IP.readUTF(); // Đọc thông báo từ server
                        this.withdrawlViews.showMessage(message); // Hiển thị thông báo từ server

                        if (!message.contains("SỐ DƯ KHÔNG ĐỦ")) {
                            this.withdrawlViews.setVisible(false);
                            new MainClass_Views(this.socket, this.withdrawlViews.getPin(),this.withdrawlViews.getCardno());
                        }
                    }
                }
            } else if (nsk.equals("TRỞ VỀ")) {
                this.withdrawlViews.setVisible(false);
                new MainClass_Views(this.socket, this.withdrawlViews.getPin(),this.withdrawlViews.getCardno());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
