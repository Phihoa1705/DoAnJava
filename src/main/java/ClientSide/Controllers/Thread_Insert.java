package ClientSide.Controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Thread_Insert implements Runnable{
    private DataOutputStream OP;
    private DataInputStream IP;
    private Socket socket;
    private String formno,name,fName,dob,gender,email,add,city,state,marital;
    private int pin;

    public Thread_Insert(Socket socket, String formno, String name, String fName, String dob, String gender, String email, String add, String city, String state, int pin, String marital) {
        this.formno = formno;
        this.name = name;
        this.fName = fName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.add = add;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.marital = marital;
        this.socket = socket;
        try {
            this.OP = new DataOutputStream(socket.getOutputStream());
            this.IP = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            OP.writeUTF("Next Singup1");
            OP.writeUTF(this.formno);
            OP.writeUTF(this.name);
            OP.writeUTF(this.fName);
            OP.writeUTF(this.dob);
            OP.writeUTF(this.gender);
            OP.writeUTF(this.email);
            OP.writeUTF(this.add);
            OP.writeUTF(this.city);
            OP.writeUTF(this.state);
            OP.writeInt(this.pin);
            OP.writeUTF(this.marital);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
