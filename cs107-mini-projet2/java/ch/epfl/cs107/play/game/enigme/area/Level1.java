package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class Level1 extends EnigmeArea {
    @Override
    public String getTitle() {
        return "Level1";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        try {
            super.begin(window, fileSystem);
            this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(5, 3), Orientation.UP,
                    new DiscreteCoordinates(5, 0), Arrays.asList(new DiscreteCoordinates(5, 0))));

            return true;

        } catch (Exception E) {

            System.out.println("Error  : " + E);
            return false;

        }

    }
}