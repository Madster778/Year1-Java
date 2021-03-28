package game;

import Bodies.Driver;
import Levels.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public static void save(GameLevel level, String fileName)
            throws IOException
    {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "," + Driver.getLives() + "," + Driver.getKills() + "," + DriverController.getScore() + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static GameLevel load(Game game, String fileName)
        throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");
            String name = tokens[0];
            int lives = Integer.parseInt(tokens[1]);
            int kills = Integer.parseInt(tokens[2]);
            int score = Integer.parseInt(tokens[3]);

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

            level.getDriver().setLives(lives);
            level.getDriver().setKills(kills);

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
