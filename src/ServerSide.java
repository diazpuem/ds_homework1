import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerSide {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("The Server is running");
            int numberOfClients = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("Hello you are client #" + ++numberOfClients);
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
