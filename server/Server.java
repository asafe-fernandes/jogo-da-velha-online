import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting players...");

        Socket playerX = serverSocket.accept();
        System.out.println("player X connected.");
        PrintWriter playerXout = new PrintWriter(playerX.getOutputStream(), true);
        BufferedReader playerXin = new BufferedReader(new InputStreamReader(playerX.getInputStream()));

        Socket playerO = serverSocket.accept();
        System.out.println("player O connected.");
        PrintWriter playerOout = new PrintWriter(playerX.getOutputStream(), true);
        BufferedReader playerOin = new BufferedReader(new InputStreamReader(playerX.getInputStream()));

        playerXout.println("You are player X.");
        playerOout.println("You are player O.");

        while (true) {
            PrintWriter currentOut = game.getCurrentPlayer() == 'X' ? playerXout : playerOout;
            BufferedReader currentIn = game.getCurrentPlayer() == 'X' ? playerXin : playerOin;

            currentOut.println(game.showBoard());
            System.out.println("Make your move(rows cols): ");

            String input = currentIn.readLine();
            if (input == null)
                break;

            String[] parts = input.split(" ");
            if (parts.length != 2)
                continue;

            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (!game.makeMove(row, col)) {
                currentOut.println("Invalid move. Try again.");
                continue;
            }

            char winner = game.checkWinner();
            if (winner != 'n') {
                playerXout.println(game.showBoard());
                playerOout.println(game.showBoard());
                playerXout.println("Player " + winner + " wins!");
                playerOout.println("Player " + winner + " wins!");
                break;
            }

            if (game.checkDraw()) {
                playerXout.println(game.showBoard());
                playerOout.println(game.showBoard());
                playerXout.println("Its a draw.");
                playerOout.println("Its a draw.");
                break;
            }
        }
    }
}
