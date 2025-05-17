package tictactoe;

public class RecursivePlayer implements TicTacToePlayer {
    @Override
    public String chooseMove(Map<String, String> original_board, String playerSymbolSelf) {
    	Map<String, String> board = new LinkedHashMap<>(original_board);
 
        int bestScore = Integer.MIN_VALUE;
        String bestMove = null;
        String playerSymbolNextMove = playerSymbolSelf.equals("X") ? "O" : "X";

        for (String pos : TicTacToe.POSITIONS) {
            if (board.get(pos).equals(" ")) {
                board.put(pos, playerSymbolSelf);
                int score = evaluate(board, playerSymbolSelf, playerSymbolNextMove);
                board.put(pos, " ");
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = pos;
                }
            }
        }
        return bestMove;
    }

    private int evaluate(Map<String, String> board, String playerSymbolSelf, String playerSymbolThisMove ) {
        String playerSymbolNextMove = playerSymbolThisMove.equals("X") ? "O" : "X";
        if (TicTacToe.checkWin(playerSymbolSelf, board)) return +1;
        if (TicTacToe.checkWin(playerSymbolNextMove, board)) return -1;
        if (TicTacToe.isDraw(board)) return 0;

        if (playerSymbolThisMove.equals(playerSymbolSelf)) {
            int best = Integer.MIN_VALUE;
            for (String pos : TicTacToe.POSITIONS) {
                if (board.get(pos).equals(" ")) {
                    board.put(pos, playerSymbolThisMove);
                    int score = evaluate(board, playerSymbolSelf, playerSymbolNextMove);
                    board.put(pos, " ");
                    best = Math.max(best, score);
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (String pos : TicTacToe.POSITIONS) {
                if (board.get(pos).equals(" ")) {
                    board.put(pos, playerSymbolThisMove);
                    int score = evaluate(board, playerSymbolSelf, playerSymbolNextMove);
                    board.put(pos, " ");
                    best = Math.min(best, score);
                }
            }
            return best;
        }
    }
}
