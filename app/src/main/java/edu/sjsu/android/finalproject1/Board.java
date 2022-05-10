package edu.sjsu.android.finalproject1;

public class Board {
    private static final int rows = 6;
    private static final int columns = 7;

    Piece[][] board = new Piece[rows][columns];

    // initializes all spaces to null
    public Board() {
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                board[i][j] = null;
    }

    // for intelliJ console debugging
    public void printBoard() {
        for(int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                if(board[i][j] == null) System.out.print("_");
                else System.out.print(board[i][j].getColor());
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // places a piece on the board
    public boolean put(int col, String color) {
        boolean success = false;
        if(col >=0 && col < columns) {
            if (board[0][col] == null) {
                for(int row = rows - 1; row >= 0; row--) {
                    if(board[row][col] == null) {
                        board[row][col] = new Piece(color);
                        success = true;
                        break;
                    }
                }
                return success;
            } else {
                System.err.println("Column full");
                return false;
            }
        } else {
            System.err.println("Column not available");
            return false;
        }
    }

    // checks for 4 in a row
    public boolean checkWinner(int column, String player) {

        for(int row = 0; row < rows; row++) {
            if(board[row][column] != null) {
                int streak = 3;

                // check down
                for(int next = row +1; next < rows; next++) {
                    if(board[next][column].getColor().equals(player)) {
                        streak--;
                        if(streak == 0) {
                            return true;
                        }
                    } else {
                        streak = 3;
                    }
                }
                streak = 4;

                // check horizontal
                for(int col = column - 3; col < column + 3; col++) {
                    if(col < 0) continue;
                    if(col >= columns) break;
                    if(board[row][col] != null && board[row][col].getColor().equals(player)) {
                        streak--;
                        if(streak == 0){
                            return true;
                        }
                    } else {
                        streak = 4;
                    }
                }
                streak = 4;

                // check left diagonal
                for(int nextR = row - 3, col = column -3; nextR <= row + 3 && col <= column + 3; nextR++, col++){
                    if(nextR < 0 || col < 0) continue;
                    if(nextR >= rows || col >= columns) break;

                    if(board[nextR][col] != null && board[nextR][col].getColor().equals(player)) {
                        streak--;
                        if(streak == 0) {
                            return true;
                        }
                    } else
                        streak = 4;
                }

                // check right diagonal
                for(int nextR = row - 3, nextC = column + 3; nextR <= row + 3 && nextC >= column - 3; nextR++, nextC--){
                    if(nextR < 0 || nextC >= columns) continue;
                    if(nextR >= rows || nextC < 0) break;

                    if(board[nextR][nextC] != null && board[nextR][nextC].getColor().equals(player)) {
                        streak--;
                        if(streak == 0) {
                            return true;
                        }
                    } else
                        streak = 4;
                }
                break;
            }
        }

        return false;
    }
}