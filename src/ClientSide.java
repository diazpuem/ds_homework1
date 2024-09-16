import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSide {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter IP address: ");
        String host = scanner.nextLine();
        int port = Integer.parseInt(args[0]);
        try (Socket socket = new Socket(host, port)) {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String welcomeMessage = reader.readLine();
            System.out.println(welcomeMessage);

            Console console = System.console();
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String textInput;
            do {
                System.out.println("Please enter an input: ");
                textInput = scanner.nextLine();
                writer.println(textInput);

                String receivedResponse = reader.readLine();
                System.out.println(receivedResponse);
            } while (!textInput.isEmpty());
            scanner.close();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + host + " error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }

    }
}
