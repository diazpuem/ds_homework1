import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {

    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try{
            InputStream input = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String textReceived;
            do {
                textReceived = reader.readLine();
                if (textReceived.equals("time")) {
                    writer.println(new Date());
                } else {
                    writer.println(textReceived.toUpperCase());
                }
            } while(!textReceived.isEmpty());
            socket.close();
        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        }
    }





}
