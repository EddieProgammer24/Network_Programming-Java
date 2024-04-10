import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        String serverHostname = "localhost";
        int serverPort = 12345;

        try (
                Socket socket = new Socket(serverHostname, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Server: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverHostname);
            System.exit(1);
        }
    }
}
