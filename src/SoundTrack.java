import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class SoundTrack implements Runnable {
    private File music; //will hold music file
    private AudioInputStream audioInput; //allows music to be played

    public SoundTrack()
    {
        //loop the song so that it won't stop playing
        Thread audioloop = new Thread(this);
        audioloop.start();
    }
    @Override
    public void run() {
        music = new File("H:\\Web development\\final_knepp\\src\\pirate.wav"); //name of the music file
        try {
            audioInput = AudioSystem.getAudioInputStream(music); //make music playable
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput); //will play music
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);


        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Music messed up " + ex.getMessage());
        }
    }
}
