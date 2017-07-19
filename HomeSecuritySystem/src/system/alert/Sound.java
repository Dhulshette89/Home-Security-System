package system.alert;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


// To play sound using Clip, the process need to be alive.
/**
 * The Class Sound.
 */
// Hence, we use a Swing application.
public class Sound extends JFrame {
	
	/** The clip. */
	Clip clip;
	
	/** The loop. */
	int loop=100;
   
   /**
    * Instantiates a new sound.
    *
    * @param fileName the file name
    */
   public Sound(String fileName) {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Test Sound Clip");
      this.setSize(300, 200);
      //this.setVisible(true);

      try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource(fileName);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         clip.loop(loop);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Stop sound.
    */
   public void StopSound()
   {
	   clip.close();
   }

}

