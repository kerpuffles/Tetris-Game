import java.io.*;
import javax.sound.sampled.*;
public class Sound {
    public static void play(String file){
    try {
    File yourFile = new File(file);
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;

    stream = AudioSystem.getAudioInputStream(yourFile);
    format = stream.getFormat();
    info = new DataLine.Info(Clip.class, format);
    clip = (Clip) AudioSystem.getLine(info);
    clip.open(stream);
    clip.start();
    
   }
   catch (Exception e) {
    System.out.println("exception");
   }
  }
}