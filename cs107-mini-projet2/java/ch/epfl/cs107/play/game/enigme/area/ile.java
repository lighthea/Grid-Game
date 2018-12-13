package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class ile extends EnigmeArea {

    public String getTitle() {
        return "ile";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    private int scaleFactor = 15;
    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            XMLTexts.initialize(fileSystem,"strings/enigme_fr" );
            super.begin(window, fileSystem);
            this.registerActor(new EnigmeAI(this, Orientation.RIGHT, new DiscreteCoordinates(125, 71), 10, false,1500,
                    new DiscreteCoordinates(124,71),new DiscreteCoordinates(123,71), new DiscreteCoordinates(122,71)
                    , new DiscreteCoordinates(121,71),new DiscreteCoordinates(122,71)
                    , new DiscreteCoordinates(123,71)));
            this.registerActor(new patapon(this, Orientation.DOWN, new DiscreteCoordinates(98,66), 0,
                                            true, 0, new String[]{XMLTexts.getText("inspect_signalrock")}, false));
            return true;

        } catch (Exception E){

            System.out.println("Error  : "+ E);
            return false;

        }

    }
}