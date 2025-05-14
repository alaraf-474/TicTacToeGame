import java.util.Scanner;

// Entry Point
public class TicTacToeApp {
    public static void main(String[] args) {
        Player playerX = new HumanPlayer('X');
        Player playerO = new HumanPlayer('O');
        Game game = new Game(playerX, playerO, new ConsoleRenderer());
        game.play();
    }
}

// Game State Enum
enum GameState {
    ONGOING, DRAW, WIN
}

// Board Class
class Board {
    private final char[][] grid;
    private static final int SIZE = 3;

    public Board() {
        grid = new char[SIZE][SIZE];
        clear();
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && col >= 0 && row < SIZE && col < SIZE && grid[row][col] == ' ';
    }

    public boolean isFull() {
        for (char[] row : grid)
            for (char cell : row)
                if (cell == ' ')
                    return false;
        return true;
    }

    public boolean hasWinner(char symbol) {
        // Rows, Columns, Diagonals
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }
        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
               (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }

    public char[][] getGrid() {
        return grid;
    }

    public void clear() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = ' ';
    }
}

// Player Strategy Interface
interface Player {
    Move getMove(Board board);
    char getSymbol();
}

// Human Player
class HumanPlayer implements Player {
    private final char symbol;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Move getMove(Board board) {
        while (true) {
            System.out.print("Player " + symbol + ", enter your move (row and column 0-2): ");
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (board.isValidMove(row, col)) return new Move(row, col);
            } catch (Exception e) {
                scanner.nextLine(); // clear buffer
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}

// Move Model
class Move {
    final int row;
    final int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

// Console Renderer (View)
class ConsoleRenderer {
    public void render(Board board) {
        System.out.println("-------------");
        for (char[] row : board.getGrid()) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public void announceWinner(char player) {
        System.out.println("🏆 Player " + player + " wins!");
    }

    public void announceDraw() {
        System.out.println("🤝 It's a draw!");
    }
}

// Game Controller
class Game {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private final ConsoleRenderer renderer;

    public Game(Player player1, Player player2, ConsoleRenderer renderer) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.renderer = renderer;
    }

    public void play() {
        Player currentPlayer = player1;
        GameState state = GameState.ONGOING;

        while (state == GameState.ONGOING) {
            renderer.render(board);
            Move move = currentPlayer.getMove(board);
            board.makeMove(move.row, move.col, currentPlayer.getSymbol());

            if (board.hasWinner(currentPlayer.getSymbol())) {
                state = GameState.WIN;
                renderer.render(board);
                renderer.announceWinner(currentPlayer.getSymbol());
                break;
            } else if (board.isFull()) {
                state = GameState.DRAW;
                renderer.render(board);
                renderer.announceDraw();
                break;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}
