package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

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
            registerActor(new Background(this));
            setViewCenter(viewCenter);
            this.setAreaBehavior(new EnigmeBehaviour(window, this.getTitle()));
            return true;

        } catch (Exception E){

            System.out.println("Error  : "+ E);
            return false;

        }

    }
}