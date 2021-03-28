package GUI;

import Levels.GameLevel;
import city.cs.engine.SoundClip;
import game.Game;
import game.GameSaverLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Creates a class for the GUIButtonControl
public class GUIButtonControl extends JPanel {

    // A private variable used to check the state of a button press
    private static int state = 0;

    // The following variables are the JButtons I have implemented
    private static JButton instructionButton;
    private static JButton playButton;
    private static JButton pauseButton;
    private static JButton saveButton;
    private static JButton loadButton;
    private static JButton quitButton;
    private static JButton muteButton;

    private static SoundClip gameMusic;

    // Creating the main gui button class
    public static GUIButtonControl createButton(){
        GUIButtonControl but = new GUIButtonControl();

        // Creates an instruction button that shows how to play the game
        instructionButton = new JButton("Instructions");
        instructionButton.setFont(new Font("Segoe", Font.BOLD, 15));
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("----------------------------------");
                System.out.println("       Instructions:");
                System.out.println("          W - Move Upwards");
                System.out.println("          A - Move Left");
                System.out.println("          S - Move Downwards");
                System.out.println("          D - Move Right");
                System.out.println("      Space - Shoot");
                System.out.println("   When the game switches level,");
                System.out.println("     or the GUI is being used,");
                System.out.println(" the mouse must move in the frame");
                System.out.println("----------------------------------");
            }
        });
        but.add(instructionButton);

        // Creates a play button that only allows you to resume the game
        playButton = new JButton("Play");
        playButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level.start();
            }
        });
        but.add(playButton);

        // Creates a pause button that only allows you to pause the game
        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level.stop();
            }
        });
        but.add(pauseButton);

        // Creates a save button that saves the current stats of game in a text file
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameSaverLoader.save(Game.getLevel(),"data/save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        but.add(saveButton);

        // Creates a load button that loads the text file with the saved stats
        loadButton = new JButton("Load");
        loadButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameSaverLoader.load(Game.getLevel().getGame(), "data/save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        but.add(loadButton);

        // Creates a quit button that exits the game when pressed
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        but.add(quitButton);

        // Creates a mute/unmute button that pauses and resumes the gameMusic
        // When state is equal to 0, pressing this button pauses the game music
        // When state is equal to 1, pressing this button resumes the game music
        muteButton = new JButton("Mute/Unmute");
        muteButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == 0){
                    GameLevel.gameMusic.pause();
                    state = 1;
                }else{
                    GameLevel.gameMusic.resume();
                    state = 0;
                }
            }
        });

        but.add(muteButton);

        return but;
    }
}
