package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Bahamut;
import ch.epfl.cs107.play.game.enigme.actor.interactiveButton;
import ch.epfl.cs107.play.game.enigme.actor.patapon;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class templeMaya extends EnigmeArea {
    private interactiveButton b1, b2;
    private Bahamut boss;
    public String getTitle() {
        return "templeMaya";
    }
    public int getScaleFactor() {
        return this.scaleFactor;
    }
    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    private int scaleFactor = 150;
    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return this.spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(16, 77);
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (b1.isHasBeenPressed() || b2.isHasBeenPressed()) {
            leaveAreaCells(boss, boss.getCurrentCells());
            unregisterActor(boss);
        }
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        try {
            super.begin(window,fileSystem);

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,66), Orientation.DOWN,
                    new DiscreteCoordinates(16, 78), Arrays.asList(new DiscreteCoordinates(16, 78))));
//**********************************************************************************************************************************
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(13, 69), Arrays.asList(new DiscreteCoordinates(13, 69))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(19, 69), Arrays.asList(new DiscreteCoordinates(19, 69))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(19, 64), Arrays.asList(new DiscreteCoordinates(19, 64))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(11,44), Orientation.DOWN,
                    new DiscreteCoordinates(13, 64),  Arrays.asList(new DiscreteCoordinates(13, 64))));
//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,66), Orientation.DOWN,
                    new DiscreteCoordinates(9, 48), Arrays.asList(new DiscreteCoordinates(9 , 63 - 15  ))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,77), Orientation.DOWN,
                    new DiscreteCoordinates(9, 44), Arrays.asList(new DiscreteCoordinates(9 , 63 - 19  ))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(18, 52), Orientation.DOWN,
                    new DiscreteCoordinates(13, 48), Arrays.asList(new DiscreteCoordinates(13 , 48))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(17, 42), Orientation.DOWN,
                    new DiscreteCoordinates(13, 44), Arrays.asList(new DiscreteCoordinates(13 , 44))));
//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(11,63-23), Orientation.DOWN,
                    new DiscreteCoordinates(17, 40), Arrays.asList(new DiscreteCoordinates(17, 40))));

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(10, 52), Orientation.DOWN,
                    new DiscreteCoordinates(17, 44), Arrays.asList(new DiscreteCoordinates(17, 44))));
//**********************************************************************************************************************************


            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(13, 52), Orientation.DOWN,
                    new DiscreteCoordinates(17, 52), Arrays.asList(new DiscreteCoordinates(17, 52))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(18, 45), Orientation.DOWN,
                    new DiscreteCoordinates(21, 52), Arrays.asList(new DiscreteCoordinates(21, 52))));
//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16,48), Orientation.DOWN,
                    new DiscreteCoordinates(13, 52), Arrays.asList(new DiscreteCoordinates(13, 52))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(9, 45), Orientation.DOWN,
                    new DiscreteCoordinates(9, 52), Arrays.asList(new DiscreteCoordinates(9, 52))));
//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(15, 51), Orientation.DOWN,
                    new DiscreteCoordinates(13, 40), Arrays.asList(new DiscreteCoordinates(13, 40))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(22, 51), Orientation.DOWN,
                    new DiscreteCoordinates(9, 40), Arrays.asList(new DiscreteCoordinates(9, 40))));

//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(20, 42), Orientation.DOWN,
                    new DiscreteCoordinates(21, 48), Arrays.asList(new DiscreteCoordinates(21, 48))));

//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(20, 48), Orientation.DOWN,
                    new DiscreteCoordinates(17, 48), Arrays.asList(new DiscreteCoordinates(17, 48))));
//**********************************************************************************************************************************

            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16, 11), Orientation.DOWN,
                    new DiscreteCoordinates(21, 44), Arrays.asList(new DiscreteCoordinates(21, 44))));
            this.registerActor(new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16, 77), Orientation.DOWN,
                    new DiscreteCoordinates(21, 40), Arrays.asList(new DiscreteCoordinates(21, 40))));



            b1 = new interactiveButton(this, "templeMaya", new DiscreteCoordinates(16, 77), Orientation.DOWN,
                    new DiscreteCoordinates(17, 6), Arrays.asList(new DiscreteCoordinates(17, 6)));
            b2 = new interactiveButton(this, "bossFinal", new DiscreteCoordinates(16,16), Orientation.DOWN,
                    new DiscreteCoordinates(16, 6), Arrays.asList(new DiscreteCoordinates(16, 6)));

            registerActor(new patapon(this, Orientation.DOWN, new DiscreteCoordinates(16, 19), 0, true, 0,
                    new String[]{"Ce n'est pas la récompense qui compte mais l'effort... gnagna gna gnagna gna"},false));

        } catch (Exception e) {

        }
        return true;
    }
}