package ch.epfl.cs107.play.game.enigme.area.Demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
public class Room1 extends Area {

    public Demo2Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Demo2Behavior behavior) {
        this.behavior = behavior;
    }

    private Demo2Behavior behavior;

    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    private int scaleFactor = 10;
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    @Override
    public String getTitle() {
        return "Level1" ;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            setBehavior(new Demo2Behavior(window, getTitle()));
            registerActor(new Background(this));

            return true;
        } catch (Exception E){
            System.out.println("Error : "+ E);
            return false;
        }

    }
}
