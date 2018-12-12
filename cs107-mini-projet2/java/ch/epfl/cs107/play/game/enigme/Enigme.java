package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmeAI;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public class Enigme extends AreaGame {

    private EnigmePlayer player;
    private Apple apple;
    private Door door;
    private float healthPercentageMax;
    private TextGraphics health;
    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public void update (float deltaTime){
        super.update(deltaTime);
        healthPercentageMax = player.getHealth() / player.maxHealth;

        if (player.isPassingDoor()) {
            this.setCurrentArea(player.getLastDoor().getDestinationArea(), false);
            player.enterArea(getCurrentArea(), player.getLastDoor().getLandingCoordinates());
            player.setPassingDoor(false);
            this.getCurrentArea().setViewCandidate(player);
        }

        health.setText(Float.toString((int)healthPercentageMax));
        health.draw(getWindow());
    }

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        this.addArea(new LevelSelector());
        this.addArea(new Level1());
        this.addArea(new Level2());
        this.addArea(new Level3());
        this.addArea(new ile());

        DiscreteCoordinates coordinates = new DiscreteCoordinates(0, 0);
        this.setCurrentArea("ile", true);
        for (int i = 0; i < getCurrentArea().getAreaBehavior().getCells().length; i++)
            for (int j = 0; j < getCurrentArea().getAreaBehavior().getCells()[0].length; j++) {
                if (((EnigmeBehaviour) (getCurrentArea().getAreaBehavior())).getCellNature(new DiscreteCoordinates(i, j)) == -1)
                    coordinates = (getCurrentArea().getAreaBehavior().getCells()[i][j]).getCoordinates();
            }

        player = new EnigmePlayer(getCurrentArea(), Orientation.DOWN, coordinates);
        //apple = new Apple(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5,6));
        //List<DiscreteCoordinates> DoorCoord = Arrays.asList((new DiscreteCoordinates(6,7)));

        //door = new Door(getCurrentArea(), "Level1", new DiscreteCoordinates(5,5),Orientation.DOWN,
          //      new DiscreteCoordinates(6,7),
          //      DoorCoord);
        healthPercentageMax = player.getHealth()/player.maxHealth;
        this.getCurrentArea().registerActor(player);
        //this.getCurrentArea().registerActor(apple);
        //this.getCurrentArea().registerActor(door);
        health = new TextGraphics(Float.toString((int)healthPercentageMax) , 0.03f, Color.GREEN);
        health.setParent(player);
        health.setAnchor(new Vector(1,1));
        this.getCurrentArea().setViewCandidate(player);
        return true;
    }
}
