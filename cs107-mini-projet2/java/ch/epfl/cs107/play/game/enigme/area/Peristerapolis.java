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
    private final int scaleFactor = 15;

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

    private String[] dialog = {"M. Deparos : C'est terrible ! Mes 3 assistants ont disparu !", "Peux-tu les retrouver ? J'ai besoin d'eux pour calmer la révolte !", "Les manifestants veulent changer de maire, car le notre veut tout casser !",
    "L'ancien maire a menti pour prendre le pouvoir et il a décidé de construire sa 18eme piscine sur le stade municipal de pétanque !",
    "On peut accépter beaucouip mais pas ce !"};
    private String[] dialog2 = {"Le maire actuel a dit qu'il rasera les quartiers pauvres ! On ne peut pas laisser faire ca !"};
    private String[] dialog3 = {"On laisse personne passer ! pas même toi, même si t'as rien à voir, sinon on comprend pas qu'on est pas contents !"};
    private String[] dialog4 = {"On est là pour un monde meilleur ! Je vais retourner combattre !"};
    private String[] dialog5 = {"Tu as vu la peleteuse sur les toits ?! c'est vraiment devenu l'anarchie... Le devoir m'appelle !"};

    private String[] dialog7 = {"Retrouvez les assistants ! Je sais pas pourquoi mais ca fais avancer l'histoire !" };





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
    private Door exitDoor = new Door(this,"prasinatefratapolis", new DiscreteCoordinates(74,27),Orientation.DOWN, new DiscreteCoordinates(53,70), Arrays.asList(new DiscreteCoordinates(53,70)));

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
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

