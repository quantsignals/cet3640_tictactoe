package tictactoe;

public class TicTacToe {
    static final String[] POSITIONS = {
        "A1", "B1", "C1",
        "A2", "B2", "C2",
        "A3", "B3", "C3"
    };

    static final List<List<String>> WIN_PATTERNS = List.of(
        List.of("A1", "B1", "C1"), // SAME ROWS
        List.of("A2", "B2", "C2"),
        List.of("A3", "B3", "C3"),
        List.of("A1", "A2", "A3"), // SAME COLUMNS
        List.of("B1", "B2", "B3"),
        List.of("C1", "C2", "C3"),        
        List.of("A1", "B2", "C3"), // DIAGONALS
        List.of("A3", "B2", "C1")
    );
    
    static void printBoard(Map<String, String> board) {
        System.out.println();
        for (int i = 0; i < POSITIONS.length; i++) {
            String pos = POSITIONS[i];
            System.out.print(" " + board.get(pos) + " ");
            if ((i + 1) % 3 == 0) System.out.println();
            else System.out.print("|");
        }
        System.out.println();
    };

    static boolean checkWin(String player, Map<String, String> board) {
        for (List<String> pattern : WIN_PATTERNS) {
            boolean allMatch = true;
            for (String pos : pattern) {
                if (!board.get(pos).equals(player)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) return true;
        }
        return false;
    };

    static boolean isDraw(Map<String, String> board) {
        for (String value : board.values()) {
            if (value.equals(" ")) return false;
        }
        return true;
    };
   
    public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);

    TicTacToePlayer playerX = new HumanPlayer(scanner);
    TicTacToePlayer playerO = new RandomPlayer();

    Map<String, String> board = new LinkedHashMap<>();
        
    int playerXWins = 0;
    int playerOWins = 0;
    int draws = 0;

    // reset game board
    for (String pos : POSITIONS) board.put(pos, " ");
    String currentPlayer = "X"; // starting player
    
    while (true) {
        printBoard(board);
        TicTacToePlayer player = currentPlayer.equals("X") ? playerX : playerO;
        String move = player.chooseMove(board, currentPlayer);

        if (move == null || !board.containsKey(move) || !board.get(move).equals(" ")) {
            System.out.println("Invalid move. Skipping.");
            printBoard(board);
            break;
        }

        board.put(move, currentPlayer);

        if (checkWin(currentPlayer, board)) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + " wins!");
            if(currentPlayer.equals("X")) {
            	playerXWins +=1;
            }else {
            	playerOWins +=1;
            }
            break;
            
        } else if (isDraw(board)) {
            printBoard(board);
            System.out.println("It's a draw!");
            draws +=1;
            break;
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }
    
    scanner.close();
    };
}
