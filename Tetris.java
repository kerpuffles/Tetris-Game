/**
 * Tetris.java  MM/DD/YYYY
 * AP Computer Science A - Mr. Tolleson
 *
 * @author - 
 */
import java.awt.Color;
import java.util.ResourceBundle;
// Represents a Tetris game.
public class Tetris implements ArrowListener{
    private BoundedGrid<Block> grid;    // The grid containing the Tetris pieces.
    private BlockDisplay display;       // Displays the grid.
    private Tetrad activeTetrad;        // The active Tetrad (Tetris Piece).
    private Tetrad secondTetrad;
    private int score;
    private boolean isDown;
    private boolean end = false;
    public Color color = Color.RED;
    private static Score scores;
    
    // Constructs a Tetris Game
    public Tetris() {
       grid = new BoundedGrid<Block>(20,10);
       display = new BlockDisplay(grid);
       display.setArrowListener(this);
       display.setTitle("Tetris");
       activeTetrad = new Tetrad(grid,color);
       display.showBlocks();
    }
    public void rightPressed(){
        if(!activeTetrad.gameOver)activeTetrad.translate(0,1);
        display.showBlocks();
    }
    public void leftPressed(){
        if(!activeTetrad.gameOver)activeTetrad.translate(0,-1);
        display.showBlocks();
    }
    public void upPressed(){
        if(!activeTetrad.gameOver)activeTetrad.rotate();
        display.showBlocks();
        Sound.play("./rotate_block.wav");
    }
    public void downPressed(){
        if(!activeTetrad.gameOver)activeTetrad.translate(1,0);
        display.showBlocks();
        score += 1;
        scores.count(score);
    }
    // Play the Tetris Game
    public void play(int level) {
        if(level > 1)activeTetrad = new Tetrad(grid,color);
        while(!activeTetrad.gameOver){
        sleep(1000.0/((double)(level)));
        if(!scores.pause())isDown = activeTetrad.translate(1,0);
        
        if(!isDown && !clearCompletedRows(level)){
            Sound.play("./block_hits_bottom.wav");
            activeTetrad = new Tetrad(grid,color);
            color = Randomizer.randomColor();
            scores.nextShape(Randomizer.nextShape(color));
            Sound.play("./block_drop.wav");
        }
        end = activeTetrad.gameOver;
        display.showBlocks();
        if(score > level * (level*1000))break;
      }
      Theme.stop();
      if(end){
      scores.endGame();
      for(int x = 19; x > -1; x--){
               for(int i = 0; i < 10; i++){
                  if(grid.get(new Location(x, i)) != null)grid.get(new Location(x, i)).removeSelfFromGrid();
               }
               display.showBlocks();
               sleep(100.0);
        }
      scores.playAgain();
      retry();
     }
     else { 
         Block b;
         Color colour;
         for(int x = 19; x > -1; x--){
               for(int i = 0; i < 10; i++){
                   b = new Block();
                   colour = Randomizer.randomColor();
                   b.setColor(colour);
                   b.putSelfInGrid(grid,new Location(x, i));
               }
               display.showBlocks();
               sleep(100.0);
        }
        
        for(int x = 19; x > -1; x--){
               for(int i = 0; i < 10; i++){
                  if(grid.get(new Location(x, i)) != null)grid.get(new Location(x, i)).removeSelfFromGrid();
               }
               display.showBlocks();
               sleep(100.0);
        }
        scores.levelUp(level+1);
        play(level+1);
      }
    }
    /**
     * Precondition:  0 <= row < number of rows
     * Postcondition: Returns true if every cell in the given row
     *                is occupied; false otherwise.
     */
    private boolean isCompletedRow(int row) {
       for(int i = 0; i < 10; i++){
           if(grid.get(new Location(row, i)) == null)return false;
        }
        return true;
    }

    /**
     * Precondition: 0 <= row < number of rows;
     *               The given row is full of blocks.
     * Postcondition: Every block in the given row has been removed, and
     *                every block above row has been moved down one row.
     */
    private synchronized void clearRow(int row) {
       for(int i = 0; i < 10; i++){
           grid.get(new Location(row, i)).removeSelfFromGrid();
        }
       if(!(row == 0)){
           for(int x = row-1; x > 0; x--){
               for(int i = 0; i < 10; i++){
                  if(grid.get(new Location(x, i)) != null && x < row)grid.get(new Location(x, i)).moveTo(new Location(x+1, i));
                }
        }
        }
    }

    /**
     * Postcondition: All completed rows have been cleared.
     */
    private boolean clearCompletedRows(int level) {
        boolean checkTrue = false;
        int scoreUp = 0;
        for(int i = 0; i < 20; i++){
         if(isCompletedRow(i)){
             clearRow(i);
             checkTrue = true;
             scoreUp++;
            }
        }
        if(scoreUp == 1){
         score += 150*(level);
         scores.count(score);
         Sound.play("./clear_one_line.wav");
        }
        if(scoreUp == 2){
         score += 300*(level);
         scores.count(score);
         Sound.play("./clear_one_line.wav");
        }
        if(scoreUp == 3){
         score += 600*(level);
         scores.count(score);
         Sound.play("./clear_one_line.wav");
        }
        if(scoreUp == 4){
         score += 1200*(level);
         scores.count(score);
         Sound.play("./clear_four_lines.wav");
        }
        if(checkTrue){
            activeTetrad = new Tetrad(grid,color);
            return true;
        }
        return false;
    }

    /**
     * Suspends the active thread for a duration
     * @param duration the length of time that the game is suspended
     *                 in seconds
     */
    private void sleep(double duration) {
        try {
            Thread.sleep((int)duration); // time is in milliseconds (1000 = 1 second)
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Creates and plays the Tetris game.
    public static void main(String[] args) {
        scores = new Score();
        
        Tetris tetris = new Tetris();
        tetris.play(1);
    }
    public static void retry(){
        Tetris tetris = new Tetris();
        tetris.play(1);
    }
}