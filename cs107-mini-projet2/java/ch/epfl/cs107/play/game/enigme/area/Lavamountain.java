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

public class Lavamountain extends EnigmeArea {
    private int scaleFactor = 15;

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }
    private Bahamut boss1 = new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(16,16), 40, true,2000 );
    private Bahamut boss2 = new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(10,10), 40, true,2000 );
    private Bahamut boss3 = new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(22,10), 40, true,2000 );
    private Bahamut boss4 = new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(16,13), 40, true,2000 );

    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(16,4);

    public String getTitle() {
        return "Lavamountain";
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
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            XMLTexts.initialize(fileSystem,"strings/enigme_fr" );
            super.begin(window, fileSystem);
            this.registerActor(new Door(this,"ile", new DiscreteCoordinates(92,22),Orientation.DOWN, new DiscreteCoordinates(16,4), Arrays.asList(new DiscreteCoordinates(16,4))));


            this.registerActor(boss1);
            this.registerActor(boss2);
            this.registerActor(boss3);
            this.registerActor(boss4);
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(boss1.getHealth() <=0 &&boss2.getHealth() <=0  && boss3.getHealth() <=0 && boss4.getHealth() <=0){
            registerActor(new Apple(this,Orientation.DOWN, new DiscreteCoordinates(16,16)));
        }
    }

    }

