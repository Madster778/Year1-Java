package game;

import Bodies.Driver;
import Levels.*;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

// Creates a class for the game
public class GameView extends UserView {

    private Image background;
    private GameLevel level = Game.getLevel();

    // Creates a constructor to make the size and insert the background image for each levels game view
    public GameView(World w, int width, int height) {
        super(w, width, height);
        if (Game.level instanceof Level1){
            background = new ImageIcon("data/background1.png").getImage();
        }
        else if (Game.level instanceof Level2){
            background = new ImageIcon("data/background2.png").getImage();
        }
        else if (Game.level instanceof Level3){
            background = new ImageIcon("data/background3.png").getImage();
        }
        else if (Game.level instanceof Level4){
            background = new ImageIcon("data/background4.png").getImage();
        }
    }

    // Positions the background image to fit the game view
    @Override
    protected void paintBackground(Graphics2D g) { g.drawImage(background, 0, 0, this); }

    // This makes a score and health counter in the top left of the screen
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 15));
        g.drawString("Score: " + DriverController.getScore(), 2, 20);
        g.drawString("HP: " + Driver.getLives(), 2, 40 );
    }

}
