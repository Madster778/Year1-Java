package game;

import Bodies.Driver;
import Levels.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Class to save and load the stats of the game
public class GameSaverLoader {

    /**
     * The following code is used to save and load the game stats.
     * This methods allows me to save the level, lives and kills.
     *
     * @param level        The location of level
     * @param fileName     The location of the file
     * @throws IOException Handles an IOException using the try-catch
     */
    // Function to save the game state within the text file
    public static void save(GameLevel level, String fileName)
            throws IOException
    {
        // Set append to false so that the save can be overwritten on one line
        boolean append = false;
        FileWriter writer = null;
        try {
            System.out.println("Saving in " + fileName);
            writer = new FileWriter(fileName, append);
            // Save the state of the level, lives, kills and scores
            writer.write(level.getLevelName() + "," + Driver.getLives() + "," + Driver.getKills() + "," + DriverController.getScore() + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Function to load the game state into the game
    public static GameLevel load(Game game, String fileName)
        throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Loading up " + fileName);
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");
            String name = tokens[0];
            int lives = Integer.parseInt(tokens[1]);
            int kills = Integer.parseInt(tokens[2]);
            int score = Integer.parseInt(tokens[3]);

            // Load in the level that was saved
            GameLevel level = null;
            GameLevel.gameMusic.stop();
            if (name.equals("Level1"))
                level = new Level1(game);
            else if (name.equals("Level2"))
                level = new Level2(game);
            else if (name.equals("Level3"))
                level = new Level3(game);
            else if (name.equals("Level4"))
                level = new Level4(game);
            else if (name.equals("Level5"))
                level = new Level5(game);

            // Load in the lives, kills and score
            level.getDriver().setLives(lives);
            level.getDriver().setKills(kills);
            DriverController.setScore(score);

            game.setLevel(level);
            System.out.println(level);
            return level;

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

}
