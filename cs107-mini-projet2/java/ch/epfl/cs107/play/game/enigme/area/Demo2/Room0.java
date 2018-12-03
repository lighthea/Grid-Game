package ch.epfl.cs107.play.game.enigme.area.Demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;

public class Room0 extends Area {

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
        return "LevelSelector" ;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            setAreaBehavior(new Demo2Behavior(window, getTitle()));
            registerActor(new Background(this));
            setViewCenter(viewCenter); // c'est full de la triche mais ca run
            return true;
        } catch (Exception E){
            System.out.println("Error  : "+ E);
            return false;
        }

    }
}