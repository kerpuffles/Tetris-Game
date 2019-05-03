import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;
public class Score { 
    static int score;
    static int level = 1;
    static JFrame frame;
    static JLabel label;
    static JButton btnOK;
    static JButton btnPause;
    static boolean paused = false;
    static JLabel label2;
    static JLabel snap;
    static ImageIcon icon;
    static int highScore = 45097;
    static boolean started = false;
    public Score (){
        createAndShowGUI();
    }
    public void count(int x){
        score = x;
        updateScore();
    }
    public void levelUp(int x){
     level = x;   
     updateLevel();
    }
    public void nextShape(String argx) { 
    frame.remove(snap);
    icon = new ImageIcon(argx);
    snap.setIcon(icon);
    frame.add(snap);
    frame.pack();
    frame.repaint();
   } 
    public void endGame(){
        label2.setForeground(Color.RED);
        label2.setText("GAME OVER");
        if(score > highScore){
         highScore = score;
         label2.setText("NEW HIGH SCORE");
         }
         frame.remove(btnPause);
         frame.repaint();
         frame.add(btnOK);
         btnOK.setText("PLAY AGAIN?");
         btnOK.setBounds(60,90,200,50);
         started = false;
    }
    public void updateScore(){
        label.setText("Score: " + score);
        //Sound.play("./clear_one_line.wav");
    }
    public void updateLevel(){
        
        label2.setForeground(Color.GREEN);
        label2.setText("TETRIS: LEVEL " + level);
        Sound.play("./clear_line_with_special_block.wav");
        Theme.play("./theme" + level + ".wav");
    }
    public static void createAndShowGUI() {
        frame = new JFrame("Score"); 
        
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setPreferredSize(new Dimension(350,450));
        frame.setLocation(200,0);
        icon = new ImageIcon("I.png");  
        snap = new JLabel();
        snap.setBounds(75,200,100,100);
        snap.setIcon(icon);  
        frame.add(snap); 
        label = new JLabel("Score: ");
        Dimension size = label.getPreferredSize();
        label.setBounds(100, 50, 200, 50);
        label.setFont(new Font("ARIAL",Font.BOLD,24));
        label.setText("Score: " + score);
        label.setForeground(Color.BLUE);
        label2 = new JLabel("     TETRIS");
        label2.setBounds(60, 10, 250, 50);
        label2.setFont(new Font("ARIAL",Font.BOLD,24));
        label2.setText(" Josef's TETRIS");
        label2.setForeground(Color.GREEN);
        btnOK = new JButton("START");
            // Add event handler for OK button
            btnOK.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        started = true;
                        startUp();
                    }
                });
                btnOK.setBounds(60,65,200,50);
                // Create Cancel button
          btnPause = new JButton("Pause");
            // Add event handler for OK button
            btnPause.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(paused)paused = false;
                        else paused = true;
                    }
                });
                btnPause.setBounds(60,100,200,50);
                // Create Cancel button
          
        frame.pack();
        frame.add(btnOK);
        
        frame.add(label);
        frame.add(label2);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        while(!started);
    }
    public static void startUp(){
        frame.remove(btnOK);
        frame.repaint();
        frame.add(btnPause);
        frame.repaint();
        label2.setForeground(Color.GREEN);
        label2.setText("TETRIS: LEVEL " + level);
        label.setText("Score: " + score);
        Sound.play("./game_start.wav");
        Theme.play("./theme1.wav");
    }
    public static boolean pause(){
        if(paused)return true;
        else return false;
    }
    public static void playAgain(){
        while(!started);
        level = 1;
        score = 0;
        return;
    }
}