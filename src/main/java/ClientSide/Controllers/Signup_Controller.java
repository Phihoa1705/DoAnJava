package ClientSide.Controllers;

import ClientSide.Views.Signup2_Views;
import ClientSide.Views.Signup_Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Signup_Controller implements ActionListener {
    private Signup_Views signupViews;
    private Socket socket;
    private DataInputStream IP;
    private DataOutputStream OP;

    public Signup_Controller(Socket socket,Signup_Views signupViews) {
        this.socket = socket;
        this.signupViews = signupViews;
        try {
            this.IP = new DataInputStream(socket.getInputStream());
            this.OP = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Nút next đã được click Singup_Views");
        String formno = this.signupViews.getFirst();
        String  name = this.signupViews.getTextName().getText();
        String Fname = this.signupViews.getTextFname().getText();
        String dob = ((JTextField) this.signupViews.getDateChooser().getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(this.signupViews.getR1().isSelected()) {
            gender = "Male";
        } else if(this.signupViews.getR2().isSelected()) {
            gender = "Female";
        }
        String email = this.signupViews.getTextEmail().getText();
        String marital = null;
        if(this.signupViews.getM1().isSelected()) {
            marital = "Married";
        } else if(this.signupViews.getM2().isSelected()) {
            marital = "Unmarried";
        } else if(this.signupViews.getM3().isSelected()) {
            marital = "Other";
        }
        String address = this.signupViews.getTextAdd().getText();
        String city = this.signupViews.getTextCity().getText();
        String pincodeStr = this.signupViews.getTextPin().getText();
        if (pincodeStr.equals("")){
            JOptionPane.showMessageDialog(null,"Điền trường PIN");
        }
        int pincode = Integer.parseInt(pincodeStr);
        String state = this.signupViews.getTextState().getText();

        try{
            if(this.signupViews.getTextName().getText().equals("") || this.signupViews.getTextPin().equals("")) {
                JOptionPane.showMessageDialog(null,"Điền vào tất cả các trường");
            } else {
                Thread insert = new Thread(new Thread_Insert(this.socket,formno,name,Fname,dob,gender,email,address,city,state,pincode,marital));
                insert.setName("Insert Signup 1");
                insert.start();


                new Signup2_Views(this.signupViews.getFirst(),this.socket);

                this.signupViews.dispose();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
