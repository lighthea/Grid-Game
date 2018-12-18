package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class LevelSelector extends EnigmeArea {
    @Override
    public String getTitle() {
        return "LevelSelector";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    private int scaleFactor = 11;
    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            this.registerActor(new Door(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN,
                    new DiscreteCoordinates(1, 7), Arrays.asList(new DiscreteCoordinates(1, 7))));
            this.registerActor(new Door(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.DOWN,
                    new DiscreteCoordinates(2, 7), Arrays.asList(new DiscreteCoordinates(2, 7))));

            for (int i = 3; i <= 8; i++) {
                this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(5, 3), Orientation.DOWN,
                        new DiscreteCoordinates(i, 7), Arrays.asList(new DiscreteCoordinates(i, 7))));
            }
            return true;

        } catch (Exception E){

            System.out.println("Error  : "+ E);
            return false;

        }

    }
}
