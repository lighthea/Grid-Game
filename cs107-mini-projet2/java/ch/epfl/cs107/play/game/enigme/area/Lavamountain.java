package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Bahamut;
import ch.epfl.cs107.play.game.enigme.actor.EnigmeAI;
import ch.epfl.cs107.play.game.enigme.actor.patapon;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Lavamountain extends EnigmeArea {
    private int scaleFactor = 15;

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }
    private Bahamut boss1 = new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(16,16), 40, false,2000,
            new DiscreteCoordinates(16,15), new DiscreteCoordinates(16,14),new DiscreteCoordinates(16,13),
            new DiscreteCoordinates(16,12), new DiscreteCoordinates(16,11),new DiscreteCoordinates(16,10),
            new DiscreteCoordinates(16,9), new DiscreteCoordinates(16,8), new DiscreteCoordinates(16,9),
            new DiscreteCoordinates(16,10),new DiscreteCoordinates(16,11),new DiscreteCoordinates(16,12),
            new DiscreteCoordinates(16,13),new DiscreteCoordinates(16,14));
    private Bahamut boss2 =  new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(22,12), 40, false,2000,
            new DiscreteCoordinates(22,11), new DiscreteCoordinates(22,10),new DiscreteCoordinates(22,9),
            new DiscreteCoordinates(22,8), new DiscreteCoordinates(22,9),new DiscreteCoordinates(22,10));
    private Bahamut boss3 =  new Bahamut(this,  Orientation.DOWN, new DiscreteCoordinates(10,12), 40, false,2000,
            new DiscreteCoordinates(10,11), new DiscreteCoordinates(10,10),new DiscreteCoordinates(10,9),
            new DiscreteCoordinates(10,8), new DiscreteCoordinates(10,9),new DiscreteCoordinates(10,10));

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



            this.registerActor(boss1);
            this.registerActor(boss2);
            this.registerActor(boss3);

            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(boss1.getHealth() <=0 &&boss2.getHealth() <=0  && boss3.getHealth() <=0){
            registerActor(new Apple(this,Orientation.DOWN, new DiscreteCoordinates(16,16)));
        }
    }

    }

