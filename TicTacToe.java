import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static char[][] board = new char[3][3];
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static char currentPlayer;
    public static boolean isGameOver = false;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            initializeBoard();
            printBoard();

            System.out.println("Pilih mode permainan:");
            System.out.println("1. Pemain vs Pemain");
            System.out.println("2. Pemain vs Komputer");
            int mode = scanner.nextInt();

            if (mode == 1) {
                playTwoPlayers();
            } else if (mode == 2) {
                playWithComputer();
            } else {
                System.out.println("Mode tidak valid!");
                continue; // Mengulang permainan jika input mode tidak valid
            }

            System.out.println("Ingin bermain lagi? (Y/N): ");
            char playAgain = scanner.next().charAt(0);
            if (playAgain == 'N' || playAgain == 'n') {
                break; // Keluar jika pemain tidak ingin bermain lagi
            }

        } while (true);
    }

    // Fitur 1: Pemain vs Pemain
    public static void playTwoPlayers() {
        currentPlayer = PLAYER_X;
        while (!isGameOver) {
            System.out.println("Giliran Pemain " + currentPlayer);
            printBoard();
            playerMove();
            checkGameOver();
            switchPlayer();
        }
    }

    // Fitur 2: Pemain vs Komputer
    public static void playWithComputer() {
        currentPlayer = PLAYER_X;
        while (!isGameOver) {
            if (currentPlayer == PLAYER_X) {
                System.out.println("Giliran Anda (X):");
                printBoard();
                playerMove();
            } else {
                System.out.println("Giliran Komputer (O):");
                computerMove();
            }
            checkGameOver();
            switchPlayer();
        }
    }

    // Fitur 3: Pemain melakukan gerakan
    public static void playerMove() {
        int row, col;
        while (true) {
            System.out.print("Masukkan baris (0-2): ");
            row = scanner.nextInt();
            System.out.print("Masukkan kolom (0-2): ");
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Posisi tidak valid, coba lagi.");
            }
        }
    }

    // Fitur 4: Komputer memilih gerakan acak
    public static void computerMove() {
        Random random = new Random();
        int row, col;

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            }
        }
    }

    // Fitur 5: Memeriksa kondisi permainan
    public static void checkGameOver() {
        if (checkWinner()) {
            printBoard();
            System.out.println("Pemain " + currentPlayer + " menang!");
            isGameOver = true;
        } else if (isBoardFull()) {
            printBoard();
            System.out.println("Permainan berakhir seri!");
            isGameOver = true;
        }
    }

    // Memeriksa apakah ada pemenang
    public static boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // Memeriksa apakah papan penuh (seri)
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Mengganti giliran pemain
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    // Menyiapkan papan permainan
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        isGameOver = false; // Reset status permainan
    }

    // Menampilkan papan permainan dengan format yang rapi
    public static void printBoard() {
        System.out.println("\n---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n---------");
        }
    }
}
