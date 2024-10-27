package gameoflife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class GameOfLife {

    private String golFile;

    private char[][] board = null;
    private int width = 0;
    private int height = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    // offsetX / offsetY: Coordinates where data starts on the board (used when reading data from the file).
    private int generation = 0;

    public GameOfLife(String gol) {
        golFile = gol;
    }
    public GameOfLife(char[][] b) {
        board = b;
        height = b.length;
        width = b[0].length;
    }

    public int getGeneration() { 
        return generation;
    }
    public void setGeneration(int generation) {
        this.generation = generation;
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void readFile() throws Exception {
        Reader r = new FileReader(golFile);
        BufferedReader br = new BufferedReader(r);
        String line; //holds each line read from the file

        while ((line = br.readLine())!= null) {
            line = line.trim();
        
            if (line.startsWith(Constants.COMMENTS))
                continue;

            String terms[] = line.split(" ");
            if (Constants.GRID.equals(terms[0])) {
                width = Integer.parseInt(terms[1]);
                height = Integer.parseInt(terms[2]);
                board = new char[height][0]; // Reserves space for the rows but delays the initialization of columns (width) until later
                // Flexibility to construct each row individually instead of initializing all rows at once with the full size
        
            }else if (Constants.START.equals(terms[0])) {
                offsetX = Integer.parseInt(terms[1]);
                offsetY = Integer.parseInt(terms[2]);
        
            }else if (Constants.DATA.equals(terms[0])) {
                //Set all locations in the board to blank
                board = initializeBoard(width, height);
                populateBoard(br);
                return;

            }

        }
        br.close();
    }    

    public void printBoard() {
        System.out.printf("Generation %d\n", generation);
        for (int i = 0; i < height; i++)
            System.out.printf("|%s|\n", new String(board[i]));
    }

    private char[][] initializeBoard(int width, int height) {
        char [][] board = new char[height][0];
        for (int y = 0; y < height; y++) 
            board[y] = Constants.BLANK.substring(0, width).toCharArray();
        return board;
    }
    
    private void populateBoard(BufferedReader br) throws Exception {
        String line;
        int y = offsetY;
        while ((line = br.readLine()) != null) {
            char[] data = line.toCharArray();
            for (int x =0; x < data.length; x++)
                board[y] [x + offsetX] = data[x];
            y++;
        }
    }
    public GameOfLife nextGeneration() {
        char[][] nextGboard = initializeBoard(width, height);

        // Cycle through very location on the board
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbours = Evaluator.countNeighbours(x, y, board);
                if (board[y][x] == Constants.STAR) {
                    // Die of starvation
                    if (neighbours <= 1)
                        nextGboard[y][x] = Constants.DIE;
                    else if ((x >= 2) && (x <= 3))
                        nextGboard[y][x] = board[y][x];
                    // Die of overpopulation
                    else if (x >= 4)
                        nextGboard[y][x] = Constants.DIE;
                } else {
                    if (neighbours == 3)
                     nextGboard[y][x] = Constants.STAR;
                }

            }
        }
        GameOfLife nextGol = new GameOfLife(nextGboard);
        nextGol.setGeneration(generation +1);

        return nextGol;
    }



}

