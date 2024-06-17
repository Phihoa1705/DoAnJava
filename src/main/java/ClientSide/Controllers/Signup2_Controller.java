package ClientSide.Controllers;

import ClientSide.Views.Signup2_Views;
import ClientSide.Views.Signup3_Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Signup2_Controller implements ActionListener {
    private Socket socket;
    private DataInputStream IP;
    private DataOutputStream OP;
    private Signup2_Views signup2Views;

    public Signup2_Controller(Socket socket, Signup2_Views signup2Views) {
        try {
            this.IP = new DataInputStream(socket.getInputStream());
            this.OP = new DataOutputStream(socket.getOutputStream());
            this.socket = socket;
            this.signup2Views = signup2Views;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formno = this.signup2Views.getFormno();
        String rel = (String) this.signup2Views.getComboBox().getSelectedItem();
        String cate = (String) this.signup2Views.getComboBox2().getSelectedItem();
        String inc = (String) this.signup2Views.getComboBox3().getSelectedItem();
        String edu = (String) this.signup2Views.getComboBox4().getSelectedItem();
        String occ = (String) this.signup2Views.getComboBox5().getSelectedItem();

        String pan = this.signup2Views.getTextPAN().getText();

        String eAccount = "";
        if ((this.signup2Views.getR1().isSelected())){
            eAccount = "Yes";
        } else if (this.signup2Views.getR2().isSelected()) {
            eAccount = "No";
        }
        try {
            if (this.signup2Views.getTextPAN().getText().equals("") ){
                JOptionPane.showMessageDialog(null,"Điền vào tất cả các trường");
            } else {
                OP.writeUTF("Next Singup2");
                OP.writeUTF(formno);
                OP.writeUTF(rel);
                OP.writeUTF(cate);
                OP.writeUTF(inc);
                OP.writeUTF(edu);
                OP.writeUTF(occ);
                OP.writeUTF(pan);
                OP.writeUTF(eAccount);

                new Signup3_Views(this.signup2Views.getFormno(),this.socket);
                this.signup2Views.setVisible(false);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
