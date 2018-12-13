package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.interactiveButton;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class templeMaya extends EnigmeArea {

    public String getTitle() {
        return "templeMaya";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }
    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    private int scaleFactor = 15;
    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return this.spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(16, 77);

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        try {
            super.begin(window,fileSystem);

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,66), Orientation.DOWN,
                    new DiscreteCoordinates(16, 78), Arrays.asList(new DiscreteCoordinates(16, 78))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(13, 69), Arrays.asList(new DiscreteCoordinates(13, 69))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(19, 69), Arrays.asList(new DiscreteCoordinates(19, 69))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(19, 64), Arrays.asList(new DiscreteCoordinates(19, 64))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(11,44), Orientation.DOWN,
                    new DiscreteCoordinates(13, 64),  Arrays.asList(new DiscreteCoordinates(13, 64))));


            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,66), Orientation.DOWN,
                    new DiscreteCoordinates(9, 63 - 15), Arrays.asList(new DiscreteCoordinates(9 , 63 - 15  ))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(9, 63 - 19), Arrays.asList(new DiscreteCoordinates(9 , 63 - 19  ))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(19,63 - 11), Orientation.DOWN,
                    new DiscreteCoordinates(13, 63 - 15), Arrays.asList(new DiscreteCoordinates(13 , 63 - 15  ))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(17,63-21), Orientation.DOWN,
                    new DiscreteCoordinates(13, 63 - 19), Arrays.asList(new DiscreteCoordinates(13 , 63 - 19  ))));



            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(11,63-23), Orientation.DOWN,
                    new DiscreteCoordinates(17,63-23), Arrays.asList(new DiscreteCoordinates(17,63-23))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(13, 52), Orientation.DOWN,
                    new DiscreteCoordinates(17, 44), Arrays.asList(new DiscreteCoordinates(17,63-19))));


            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(13, 52), Orientation.DOWN,
                    new DiscreteCoordinates(17, 52), Arrays.asList(new DiscreteCoordinates(19, 50))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(20, 48), Orientation.DOWN,
                    new DiscreteCoordinates(21, 52), Arrays.asList(new DiscreteCoordinates(19, 54))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(11,44), Orientation.DOWN,
                    new DiscreteCoordinates(15, 52), Arrays.asList(new DiscreteCoordinates(15, 52))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(9, 59), Orientation.DOWN,
                    new DiscreteCoordinates(11, 52), Arrays.asList(new DiscreteCoordinates(11, 52))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(15, 51), Orientation.DOWN,
                    new DiscreteCoordinates(11, 40), Arrays.asList(new DiscreteCoordinates(11, 40))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 61), Orientation.DOWN,
                    new DiscreteCoordinates(9, 40), Arrays.asList(new DiscreteCoordinates(9, 40))));


            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 61), Orientation.DOWN,
                    new DiscreteCoordinates(21, 48), Arrays.asList(new DiscreteCoordinates(21, 48))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 61), Orientation.DOWN,
                    new DiscreteCoordinates(17, 48), Arrays.asList(new DiscreteCoordinates(17, 48))));


            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 61), Orientation.DOWN,
                    new DiscreteCoordinates(21, 40), Arrays.asList(new DiscreteCoordinates(21, 40))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 61), Orientation.DOWN,
                    new DiscreteCoordinates(21, 40), Arrays.asList(new DiscreteCoordinates(21, 40))));


        } catch (Exception e) {

        }
        return true;
    }
}