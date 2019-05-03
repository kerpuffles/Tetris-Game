import java.awt.Color;

/**
 * Tetrad.java  MM/DD/YYYY
 * AP Computer Science A - Mr. Tolleson
 *
 * @author - 
 */

// An object from the Tetrad class represents a Tetris game piece.
public class Tetrad {
    private Block[] blocks; // An array of blocks used for a Tetris game piece.
    public boolean gameOver = false;
    
    // Constructs a Tetrad.
    public Tetrad(BoundedGrid<Block> grid,Color color) {
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++)blocks[i] = new Block();
        
       Location[] locs = new Location[4];
        //blocks[0].setColor(color);
       // blocks[1].setColor(color);
        //blocks[2].setColor(color);
       // blocks[3].setColor(color);
      
       if (color == Color.RED){
     locs[0] = new Location(0, 5);
     locs[1] = new Location(0, 6);
     locs[2] = new Location(0, 3);
     locs[3] = new Location(0, 4);
       }   
       else if (color == Color.GRAY){
    locs[0] = new Location(0, 4);
    locs[1] = new Location(0, 3);
    locs[2] = new Location(1, 4);
    locs[3] = new Location(0, 5);
    }
    else if (color == Color.cyan){
    locs[0] = new Location(0, 4);
    locs[1] = new Location(0, 5);
    locs[2] = new Location(1, 4);
    locs[3] = new Location(1, 5);
    }
        else if (color == Color.yellow){
    locs[0] = new Location(2, 4);
    locs[1] = new Location(1, 4);
    locs[2] = new Location(0, 4);
    locs[3] = new Location(2, 5);
    }
    else if (color == Color.MAGENTA){
    locs[0] = new Location(2, 5);
    locs[1] = new Location(1, 5);
    locs[2] = new Location(0, 5);
    locs[3] = new Location(2, 4);
    }
    else if (color == Color.blue){
    locs[0] = new Location(1, 4);
    locs[1] = new Location(0, 5);
    locs[2] = new Location(1, 3);
    locs[3] = new Location(0, 4);
    }
    else if (color == Color.green){
    locs[0] = new Location(1, 4);
    locs[1] = new Location(0, 4);
    locs[2] = new Location(0, 3);
    locs[3] = new Location(1, 5);
    }
       blocks[0].setColor(color);
       blocks[1].setColor(color);
       blocks[2].setColor(color);
       blocks[3].setColor(color);
       if(areEmpty(grid,locs)){
       addToLocations(grid,locs);
    }
    else gameOver = true;
    
    }

    /**
     * Postcondition: Attempts to move this tetrad deltaRow rows down and
     *                deltaCol columns to the right, if those positions are
     *                valid and empty.
     * @return true if successful and false otherwise.
     * 
     * @param deltaRow is the change in the block's row position
     * @param deltaCol is the change in the block's column position
     */
    public boolean translate(int deltaRow, int deltaCol) {
        BoundedGrid<Block> grid;    
    grid = blocks[0].getGrid(); 
    Location[] tempLocs = removeBlocks();
    Location[] tempLocs2 = new Location[blocks.length]; 
    if(tempLocs != null){
    for (int i = 0; i < tempLocs2.length; i++){
        
        if(tempLocs[i].getRow() + deltaRow > 19){
            addToLocations(grid, tempLocs);
            return false;
           }
         else if(tempLocs[i].getRow() + deltaRow < 0 || tempLocs[i].getCol() + deltaCol > 9 || tempLocs[i].getCol() + deltaCol < 0){
            addToLocations(grid, tempLocs);
            return true;
           }
        tempLocs2[i] = new Location(tempLocs[i].getRow() + deltaRow, tempLocs[i].getCol() + deltaCol);
    
  }
        if (areEmpty(grid, tempLocs2)){
     addToLocations(grid, tempLocs2);
     return true;
    }
    else{
         addToLocations(grid, tempLocs);
     return false;
    }
}
return false;
    }

    /** 
     * Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
     *                about its center, if the necessary positions are empty.
     * @return true if successful and false otherwise.
     */
    public boolean rotate() {
        BoundedGrid<Block> grid;    
    grid = blocks[0].getGrid(); 
    Location[] tempLocs = removeBlocks();
    Location[] tempLocs2 = new Location[blocks.length];
    int row = tempLocs[0].getRow();
    int col = tempLocs[0].getCol();
    for (int i = 0; i < tempLocs2.length; i++){
       if(row - col + tempLocs[i].getCol() > 19 || row + col - tempLocs[i].getRow() > 9 || row + col - tempLocs[i].getRow() < 0 || row - col + tempLocs[i].getCol() < 0){
            addToLocations(grid, tempLocs);
            return false;
           }
        tempLocs2[i] = new Location(row - col + tempLocs[i].getCol(), row + col - tempLocs[i].getRow());
    }
        if (areEmpty(grid, tempLocs2)){
     addToLocations(grid, tempLocs2);
     return true;
    }
    else{
         addToLocations(grid, tempLocs);
     return false;
    }
    
    }

    /**
     * Precondition:  The elements of blocks are not in any grid;
     *                locs.length = 4.
     * Postcondition: The elements of blocks have been put in the grid
     *                and their locations match the elements of locs.
     * @param grid is the BoundedGrid object that is used as the gameplay
     *             area.
     * @param locs is an Array of location objects for each block within
     *             the Tetrad.
     */
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs) {
            for(int i= 0; i < locs.length; i++){
           if(blocks[i] != null)blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    /**
     * Precondition:  The elements of blocks are in the grid.
     * Postcondition: The elements of blocks have been removed from the grid
     *                and their old locations returned.
     * @return an Array of location objects for each block within the Tetrad
     *         that is removed from the grid.
     */
    private Location[] removeBlocks() {
     Location[] locs = new Location[blocks.length];
     for (int i = 0; i < blocks.length; i++){
      locs[i] = blocks[i].getLocation();
      if(blocks[i].getLocation() != null)blocks[i].removeSelfFromGrid();
     }
     return locs;
    }

    /**
     * Postcondition: Returns true if each of the elements of locs is valid
     *                and empty in grid; false otherwise.
     */
    private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs) {
       Location[] oldSpots = new Location[grid.getOccupiedLocations().size()];
       oldSpots = grid.getOccupiedLocations().toArray(oldSpots); 
        for(int i = 0; i < oldSpots.length; i++){
            for(int x = 0; x < locs.length; x++){
                if(locs[x] != null && oldSpots[i] != null){
                if(oldSpots[i].equals(locs[x])){
                    return false;
                }
            }
           }
        }
       return true;
}
}
