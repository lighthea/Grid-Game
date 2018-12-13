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

public class Peristerapolis extends EnigmeArea {
    private int scaleFactor = 15;

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }


    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(99,44);

    public String getTitle() {
        return "Peristerapolis";
    }

    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    private String[] dialog = {"M. Deparos : C'est terrible ! Mes 3 assistants ont disparu !", "Peux-tu les retrouver ? J'ai besoin d'eux pour calmer la révolte !", "Les manifestants veulent changer de maire, car le notre veut tout casser !"};
    private String[] dialog2 = {"Le maire actuel menace de tout casser si on n'agit pas ! Il faut l'arrêter."};
    private String[] dialog3 = {"On laisse personne passer !"};
    private String[] dialog4 = {"On est là pour un monde meilleur !"};
    private String[] dialog5 = {"On demande juste des droits humains !"};

    private String[] dialog7 = {"Retrouvez les assistants !" };





    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    private Deparos deparos = new Deparos(this,  Orientation.DOWN, new DiscreteCoordinates(99,55), 0, true,2000,dialog, false  );

    private Plebe pleb2 = new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(60,71), 0, true,2000,dialog3, false);
    private Plebe pleb3 = new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(60,70), 0, true,2000,dialog7, false);
    private Assistant assistant1 = new Assistant(this,  Orientation.LEFT, new DiscreteCoordinates(75,117), 0, true,2000,dialog2, false);
    private Assistant assistant2 = new Assistant(this,  Orientation.LEFT, new DiscreteCoordinates(128,117), 0, true,2000,dialog4, false);
    private Assistant assistant3 = new Assistant(this,  Orientation.LEFT, new DiscreteCoordinates(136,66), 0, true,2000,dialog5, false);
    List<DiscreteCoordinates> list = Arrays.asList( new DiscreteCoordinates(76,21));
    private Door exitDoor = new Door(this,"Prasinateratapolis", new DiscreteCoordinates(74,27),Orientation.DOWN, new DiscreteCoordinates(53,70), Arrays.asList(new DiscreteCoordinates(53,70)));

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            XMLTexts.initialize(fileSystem,"strings/enigme_fr" );
            super.begin(window, fileSystem);
            registerActor(deparos);
            registerActor(assistant1);
            registerActor(assistant2);
            registerActor(assistant3);
            registerActor(pleb2);
            registerActor(pleb3);
            registerActor(exitDoor);
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
    private boolean tmp = true;
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(assistant1.getUtilisationCount()>0 && assistant2.getUtilisationCount()>0 && assistant3.getUtilisationCount()>0 && tmp){
            tmp = false;
            unregisterActor(pleb2);
            unregisterActor(pleb3);
        }
    }

}

