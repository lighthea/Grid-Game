package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class Grotte extends EnigmeArea{
    private int scaleFactor = 15;

    public String getTitle() {
        return "Grotte";
    }

    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(20,4);
    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        try {
            XMLTexts.initialize(fileSystem, "strings/enigme_fr");
            super.begin(window, fileSystem);
            this.registerActor(new Door(this,"ile", new DiscreteCoordinates(98,65), Orientation.DOWN, new DiscreteCoordinates(20,3), Arrays.asList(new DiscreteCoordinates(20,3))));
            this.registerActor(new Door(this,"ile", new DiscreteCoordinates(98,73), Orientation.DOWN, new DiscreteCoordinates(20,17), Arrays.asList(new DiscreteCoordinates(20,17))));
            return true;

        } catch (Exception E) {

            System.out.println("Error  : " + E);
            return false;

        }
    }
}
