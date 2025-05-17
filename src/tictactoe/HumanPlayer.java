package tictactoe;

public class HumanPlayer implements TicTacToePlayer {
    private Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String chooseMove(Map<String, String> board, String playerSymbolSelf) {
    	
        while (true) {
            System.out.print("Player " + playerSymbolSelf + ", enter your move (e.g. A1): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (board.containsKey(input) && board.get(input).equals(" ")) {
                return input;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
