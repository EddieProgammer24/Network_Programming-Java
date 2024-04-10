import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        String serverHostname = "localhost";
        int serverPort = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(serverHostname);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while ((message = userInput.readLine()) != null) {
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String modifiedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + modifiedMessage);
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
