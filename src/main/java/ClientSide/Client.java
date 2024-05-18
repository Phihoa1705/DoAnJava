package ClientSide;

import ClientSide.Views.LoginBank_Views;

import java.net.Socket;

public class Client {
    private static final String URL = "localhost";
    private static final int PORT = 5000;

    private void startClient(){
        try {
            Socket socket = new Socket(URL,PORT);
            System.out.println("Connected to Server");
            new LoginBank_Views(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
