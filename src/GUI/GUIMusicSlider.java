package GUI;

import Levels.GameLevel;
import city.cs.engine.SoundClip;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Creates a class for the GUIMusicSlider
public class GUIMusicSlider extends JPanel implements ChangeListener {

    // Creates a static JSlider variable set to volumeSlider
    private static JSlider musicSlider;
    private static double volume;

    private static SoundClip gameMusic;

    // Creating the main gui slider class
    public static GUIMusicSlider createSlider() {
        // Creates an object called slide
        GUIMusicSlider slide = new GUIMusicSlider();
        // Creates the volumeSlider
        musicSlider = new JSlider(0, 100, 50);
        // Paints the Tracks and Tick because set to true
        musicSlider.setPaintTrack(true);
        musicSlider.setPaintTicks(true);
        // Set an interval of 10 for small Ticks and 50 for large ticks
        musicSlider.setMajorTickSpacing(50);
        musicSlider.setMinorTickSpacing(10);
        // This will add the ChangeListener to the volumeSlider
        musicSlider.addChangeListener(slide);
        // Sets the orientation of the volume slider to vertical
        musicSlider.setOrientation(SwingConstants.VERTICAL);
        // Sets a font to the slider
        //musicSlider.setFont(new Font("Segoe UI", Font.ITALIC, 15));//
        // Adds the musicSlider to the JPanel
        slide.add(musicSlider);
        // Set the size of the frame
        slide.setSize(100, 100);

        return slide;

    }

    // Prints the new volume when the JSlider is changed
    public void stateChanged(ChangeEvent e) {
        System.out.println(getVolume());
        GameLevel.gameMusic.setVolume(volume);
    }

    // A getter which returns the corresponding volume to the slider
    public static Double getVolume() {
        double temp = musicSlider.getValue();
        if (temp > 0) {
            volume = temp / 1000;
        } else volume = 0.0001;
        return volume;
    }
}