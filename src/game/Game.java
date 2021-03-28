package game;

import javax.swing.JFrame;

import GUI.GUIButtonControl;
import GUI.GUIMusicSlider;
import Levels.*;
import city.cs.engine.DebugViewer;

import java.awt.*;


// A game world with bodies
public class Game {

    // This is the world that contains bodies and interactions
    public static GameLevel level;

    // This loads the view
    private static GameView view;

    // This loads the JFrame
    private JFrame frame;

    // This loads the DriverController
    private DriverController controller;

    // Initialise a new Game
    public Game() {

        // create the world for the game
        level = new Level5(this);

        level.addStepListener(new TurretCarHandler(level));
        level.addStepListener(new TankHandler(level));

        // create a view for the game
        view = new GameView(level, 650, 800);

        // uncomment this to draw a 1-metre grid on top of the view
        // view.setGridResolution(1);

        // adds the keyboard actions so the player car can be controlled
        // allows the game to recognise the key presses
        controller = new DriverController(level.getDriver());
        view.addKeyListener(controller);

        // gives focus to the game view
        view.addMouseListener(new Focus(view));


        // add the view to a frame
        frame = new JFrame("Road Riot");
        frame.add(view);

        frame.add(GUIMusicSlider.createSlider(), BorderLayout.EAST);
        frame.add(GUIButtonControl.createButton(), BorderLayout.SOUTH);

        // allows the frame to quit the application, when the x button (top right) is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // prevents let the frame from being resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // allows the frame to be made visible
        frame.setVisible(true);

        // uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(level, 650, 720);

        // start the simulation of the game world
        level.start();
    }

    public void setLevel(GameLevel level){
        GameLevel.gameMusic.stop();
        Game.level.stop();
        frame.remove(view);
        Game.level = level;
        view.setWorld(Game.level);
        view = new GameView(level, 650, 800);
        controller.updateDriver(level.getDriver());
        view.addMouseListener(new Focus(view));
        frame.add(view);
        view.setWorld(level);
        frame.pack();
        controller.updateDriver(level.getDriver());
        controller = new DriverController(level.getDriver());
        view.addKeyListener(controller);
        Game.level.start();
    }

    // A constructor to go to the next level when conditions are met
    public void goToNextLevel(){

        // Stops level 1 view and music, in order to load up the view and music of level 2
        if (level instanceof Level1){
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Level2(this);
            view.setWorld(level);
            view = new GameView(level, 650, 800);
            controller.updateDriver(level.getDriver());
            view.addMouseListener(new Focus(view));
            frame.add(view);
            view.setWorld(level);
            frame.pack();
            controller.updateDriver(level.getDriver());
            controller = new DriverController(level.getDriver());
            view.addKeyListener(controller);
            level.start();
        }
        // Stops level 2 view and music, in order to load up the view and music of level 3
        else if (level instanceof Level2){
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Level3(this);
            view.setWorld(level);
            view = new GameView(level, 650, 800);
            controller.updateDriver(level.getDriver());
            view.addMouseListener(new Focus(view));
            frame.add(view);
            view.setWorld(level);
            frame.pack();
            controller.updateDriver(level.getDriver());
            controller = new DriverController(level.getDriver());
            view.addKeyListener(controller);
            level.start();
        }
        // Stops level 3 view and music, in order to load up the view and music of level 4
        else if (level instanceof Level3) {
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Level4(this);
            view.setWorld(level);
            view = new GameView(level, 650, 800);
            controller.updateDriver(level.getDriver());
            view.addMouseListener(new Focus(view));
            frame.add(view);
            view.setWorld(level);
            frame.pack();
            controller.updateDriver(level.getDriver());
            controller = new DriverController(level.getDriver());
            view.addKeyListener(controller);
            level.start();
        }
        // Stops level 4 view and music, in order to load up the view and music of level 5
        else if (level instanceof Level4) {
            level.stop();
            frame.remove(view);
            GameLevel.gameMusic.stop();
            level = new Level5(this);
            view.setWorld(level);
            view = new GameView(level, 650, 800);
            controller.updateDriver(level.getDriver());
            view.addMouseListener(new Focus(view));
            frame.add(view);
            view.setWorld(level);
            frame.pack();
            controller.updateDriver(level.getDriver());
            controller = new DriverController(level.getDriver());
            view.addKeyListener(controller);
            level.start();
        }
        // Stops level 5 by exiting game when completion requirements are met
        else if (level instanceof Level5){
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }
    }

    // A public static getter for GameLevel to return level
    public static GameLevel getLevel() {
        return level;
    }

    // Run the game
    public static void main(String[] args) {
        new Game();
    }

}
