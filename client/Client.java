import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("type the host: ");
        String host = keyboard.readLine();
        try (Socket socket = new Socket(host, 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {

                String fromServer = in.readLine();
                if (fromServer == null)
                    break;

                System.out.println(fromServer);
                if (fromServer.contains("Make your move")) {
                    String move = keyboard.readLine();
                    out.println(move);
                }

            }
            socket.close();
        } catch (IOException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }

    }
}
