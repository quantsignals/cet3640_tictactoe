package tictactoe;

public class NextMovePlayer implements TicTacToePlayer {

    public String chooseMove(Map<String, String> original_board, String playerSymbolSelf) {
    	Map<String, String> board = new LinkedHashMap<>(original_board);
    	
        String opponent = playerSymbolSelf.equals("X") ? "O" : "X";
        
        List<String> positions = new ArrayList<>(Arrays.asList(TicTacToe.POSITIONS));
        Collections.shuffle(positions);

        // Try to win
        for (String pos : positions) {
            if (board.get(pos).equals(" ")) {
                board.put(pos, playerSymbolSelf);
                if (TicTacToe.checkWin(playerSymbolSelf, board)) {
                    board.put(pos, " ");
                    return pos;
                }
                board.put(pos, " ");
            }
        }

        // Block opponent
        for (String pos : positions) {
            if (board.get(pos).equals(" ")) {
                board.put(pos, opponent);
                if (TicTacToe.checkWin(opponent, board)) {
                    board.put(pos, " ");
                    return pos;
                }
                board.put(pos, " ");
            }
        }

        // Fallback
        for (String pos : positions) {
            if (board.get(pos).equals(" ")) return pos;
        }

        return null;
    }
}
