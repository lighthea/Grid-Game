package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class ile extends EnigmeArea {
    @Override
    public String getTitle() {
        return "ile";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    private int scaleFactor = 100;
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
        if (getCameraScaleFactor() >= 15) {
            scaleFactor = scaleFactor - 1;
        }
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);

            return true;

        } catch (Exception E){

            System.out.println("Error  : "+ E);
            return false;

        }

    }
}