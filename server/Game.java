public class Game{
    private char[][] board;
    private char currentPlayer;

    public Game(){
        this.board = new char[3][3];
        this.currentPlayer = 'X';

        this.fillBoard();
    }

    private void fillBoard(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    public String showBoard(){
        String message = "";
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                message += this.board[i][j];
                if(j != 2) {
                    message += " | ";
                }
            }
            if(i != 2){
                message += "\n- + - + - ";
            }
            message += " \n";
        }
        return message;
    }

    private boolean validateCoordinates(int i, int j){
        return i >= 0 && i <= 2 && j >= 0 && j <= 2 && this.board[i][j] == ' ';
    }

    private void switchPlayer(){
        if(this.currentPlayer == 'X'){
            this.currentPlayer = 'O';
        }else{
            this.currentPlayer = 'X';
        }
    }

    public boolean makeMove(int i, int j){
        if(!this.validateCoordinates(i, j)){
            return false;
        }        
        this.board[i][j] = this.currentPlayer;
        this.switchPlayer();
        return true;
    }

    public char checkWinner() {
        for(int i = 0; i < this.board.length; i++) {
            if(this.board[0][i] != ' ' && this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i]) {
                return this.board[0][i];
            }
            if(this.board[i][0] != ' ' && this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2]) {
                return this.board[i][0];
            }
        }
        if(this.board[0][0] != ' ' && this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
            return this.board[0][0];
        }
        if(this.board[0][2] != ' ' && this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
            return this.board[0][2];
        }

        

        return 'n';
    }

    public boolean checkDraw(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if(this.board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayer(){
        return this.currentPlayer;
    }
    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(game.makeMove(0, 0));
        System.out.println(game.makeMove(1, 1));
        System.out.println(game.makeMove(1, 0));
        System.out.println(game.makeMove(2, 1));
        System.out.println(game.makeMove(2, 0));
        System.out.println(game.showBoard());
        System.out.println(game.checkWinner());
    }
}

//salvar primeiro 