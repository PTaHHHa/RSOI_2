package sample;/*Клиент посылает слово серверу,
сервер возвращает назад в обратном
порядке следования букв это слово клиенту.
 */

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Server {
    Server() throws InterruptedException{
        int port = 1024;
        while (true){
            try {
                ServerSocket ss = new ServerSocket(port); //создание сокета
                System.out.println("Waiting for client...");
                Socket socket = ss.accept(); //подключение клиента
                System.out.println("sample.Client connected.");
                InputStream sin = socket.getInputStream(); //создание входного потока
                OutputStream sout = socket.getOutputStream(); //создние выходного потока
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout) {
                };

                String word1 = null;
                try {
                    word1 = in.readUTF(); //читает от клиента инфу
                } catch (Exception x) {
                    x.printStackTrace();
                }
                String xml = "";
                String path = "C:\\Users\\IT HYPE\\Desktop\\RSOI_2\\src\\sample\\"+word1;
                File file = new File(path);
                if(file.exists()){
                    xml = Files.lines(Paths.get(path)).collect(Collectors.joining("\n"));
                }
                else {
                    xml = "FILE DOESN'T EXISTS. PLEASE, TRY AGAIN!";
                }
                System.out.println(word1);
                out.writeUTF(xml); //посылает ответ
                ss.close(); //закрытие сокета клиента
                socket.close(); //закрытие сокета сервера
                out.flush();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }
}





