package ClientSide.Controllers;

import ClientSide.Views.Deposit_Views;
import ClientSide.Views.Signup3_Views;
import ServerSide.Database.JDBCUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

public class Signup3_Controller implements ActionListener {
    private DataInputStream IP;
    private DataOutputStream OP;
    private Socket socket;
    private Signup3_Views signup3Views;

    public Signup3_Controller(Socket socket, Signup3_Views signup3Views) {
        this.socket = socket;
        this.signup3Views = signup3Views;
        try {
            this.IP = new DataInputStream(socket.getInputStream());
            this.OP = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        String atype = null;
        if (this.signup3Views.getR1().isSelected()){
            atype = "Tài khoản tiết kiệm";
        } else if (this.signup3Views.getR2().isSelected()) {
            atype = "Tài khoản tiền gửi cố định";
        }

        Random ran = new Random();
        // Tạo số thẻ ngẫu nhiên trong khoảng 1409963000000000L đến 1409963089999999L
        long first7 = Math.abs(ran.nextLong() % 90000000L) + 1409963000000000L;
        String cardno = Long.toString(first7);

        // Tạo mã PIN ngẫu nhiên trong khoảng 1000 đến 9999
        int first3 = ran.nextInt(9000) + 1000;
        String pin = Integer.toString(first3);

        String fac = "";
        if (this.signup3Views.getC1().isSelected()) {
            fac = "ATM CARD";
        } else if (this.signup3Views.getC2().isSelected()) {
            fac = fac + ", Internet Banking";
        } else if (this.signup3Views.getC3().isSelected()) {
            fac = fac + ", Mobile Banking";
        } else if (this.signup3Views.getC4().isSelected()) {
            fac = fac + ", EMAIL Alerts";
        } else if (this.signup3Views.getC5().isSelected()){
            fac = fac + ", Cheque Book";
        } else if (this.signup3Views.getC6().isSelected()) {
            fac = fac + ", E-Statement";
        }
        if (nsk.equals("Submit")) {
            try {
                if (atype.equals("")) {
                    JOptionPane.showMessageDialog(null,"Fill all the fields");
                } else{
                    OP.writeUTF("Submit Singup3");
                    OP.writeUTF(this.signup3Views.getFormno());
                    OP.writeUTF(atype);
                    OP.writeUTF(cardno);
                    OP.writeUTF(pin);
                    OP.writeUTF(fac);
                    JOptionPane.showMessageDialog(null,"Card Number :" + cardno + "\n Pin : " + pin);
                    this.signup3Views.setVisible(false);
                    new Deposit_Views(this.socket,pin);
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (nsk.equals("Cancel")) {
            System.out.println("Bạn đã nhấn nút Cancel");
            System.exit(0);
        }
    }
}
