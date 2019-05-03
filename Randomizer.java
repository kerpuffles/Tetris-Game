
/**
 * Write a description of class Randomizer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Color;
public class Randomizer
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Randomizer
     */
    public Randomizer()
    {
        // initialise instance variables
        x = 0;
    }
    public static Color randomColor(){
     int shape = (int)(Math.random()*7);  
     Color color = Color.RED;
     if (shape == 0){
     color = Color.RED;
    
       }   
       else if (shape == 1){
     color = Color.GRAY;
    
    }
    else if (shape == 2){
     color = Color.cyan;
    
    }
        else if (shape == 3){
     color = Color.yellow;
    
    }
    else if (shape == 4){
     color = Color.MAGENTA;
    
    }
    else if (shape == 5){
     color = Color.blue;
    
    }
    else if (shape == 6){
     color = Color.green;
    
    } 
    return color;
    }
    public static String nextShape(Color color){
    if (color == Color.RED){
       return "I.png";
    }   
    else if (color == Color.GRAY){
       return "T.png";
    }
    else if (color == Color.cyan){
       return "O.png";
    }
    else if (color == Color.yellow){
       return "L.png";
    }
    else if (color == Color.MAGENTA){
       return "J.png";
    }
    else if (color == Color.blue){
       return "S.png";
    }
    else if (color == Color.green){
       return "Z.png";
    }
    else return "I.png";
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
