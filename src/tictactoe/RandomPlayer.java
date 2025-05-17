package tictactoe;

public class RandomPlayer implements TicTacToePlayer {
    private Random rand = new Random();

    public String chooseMove(Map<String, String> board, String playerSymbolSelf) {
        List<String> moves = new ArrayList<>();
        for (String pos : TicTacToe.POSITIONS) {
            if (board.get(pos).equals(" ")) {
                moves.add(pos);
            }
        }
        return moves.get(rand.nextInt(moves.size()));
    }
}
