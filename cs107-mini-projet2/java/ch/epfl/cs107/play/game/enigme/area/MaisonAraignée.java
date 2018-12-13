package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;


public class MaisonAraignée extends EnigmeArea {
    private int scaleFactor = 15;

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(16,5);
    public String getTitle() {
        return "MaisonAraignée";
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
    private String[] dialog;
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            //this.registerActor(new araignée(this, Orientation.UP,new DiscreteCoordinates(61,45), 0, false,2000,dialog, false, );
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }
}}
