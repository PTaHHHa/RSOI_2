package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class Client {
    public TextField fieldC;
    public TextArea Result;
    public Button SendButton;
    public void xml() throws IOException {
        Socket sock = null;
        String address = "127.0.0.1";
        int port = 1024;
        try {
            sock = new Socket(InetAddress.getByName(address), port);
            InputStream sin = sock.getInputStream();
            OutputStream sout = sock.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String i = fieldC.getText();
            out.writeUTF(i);
            out.flush();
            i = in.readUTF();
            Result.setText(i);

            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}





