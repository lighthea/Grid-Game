package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class EnigmeArea extends Area {
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
    public String getTitle() {
        return this.getClass().toString() ;
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            this.setAreaBehavior(new EnigmeBehaviour(window, this.getTitle()));
            while (!this.registerActor(new Foreground(this)))
                System.out.println("Loading Foreground...");
            while (!this.registerActor(new Background(this)))
                System.out.println("Loading Foreground...");


            setViewCenter(viewCenter);

            return true;

        } catch (Exception E){

            System.out.println("Error  : " + E);
            return false;

        }

    }
}
