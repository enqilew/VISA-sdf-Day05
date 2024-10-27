package gameoflife;

public class Evaluator {
    
    public static int countNeighbours(int x, int y, char[][] board) {
        int star = 0;
        int height = board.length;
        int width = board[0].length;

        for (int i = 0; i < Constants.X_DELTA.length; i++){
            //Get the position to examine
            int lx = x + Constants.X_DELTA[i];
            int ly = y + Constants.Y_DELTA[i];
            if ((lx < 0) || (lx >= width))
                continue;
            if ((ly < 0) || (ly>= height))
                continue;
            if (board[ly][lx] == Constants.STAR)
                star++;
        }

        return star;

    }
    
}
//The key responsibility of the countNeighbours method is to count how many of the neighboring cells 
//contain a specific character (in this case, represented by Constants.STAR)