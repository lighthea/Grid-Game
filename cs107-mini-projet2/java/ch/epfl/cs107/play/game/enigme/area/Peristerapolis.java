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

    private String[] dialog = {"M. Deparos : C'est terrible ! Mes 3 assistants ont disparu !", "Déjà que les dev m'en ont donné que 3...", "On dirait l'EPF... Rien en fait",
    "On pourrait utiliser des matrices pour les trouver tu sais !", "Je veux dire, au fond...", "Après tout ne vivons nous pas dans une matrice",
    "de taille 16 par 21 régie par des loies mystérieuse implémentée en une nuit..."};
    private String[] dialog2 = {"Mais comment ca t'as mis du temps à me troouver c'est pas compliqué !"};
    private String[] dialog3 = {"On laisse personne passer ! pas même toi, même si t'as rien à voir,",
                                " sinon on comprend pas qu'on est pas contents !"};

    private String[] dialogStd = {"Je suis un villageois de la ville", "Tu trouves pas qu'on se répète un peu ?"};
    private String[] dialog4 = {"Oh non je voulais essayer la nouvelle bière à sat..."};
    private String[] dialog5 = {"Si tu me donne 10 francs je te donne les sujets...","Comment ca tu veux que je retourne bosser ?!"};

    private String[] dialog7 = {"Stop à la hausse des prix des roulottes !" };





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
    private interactiveButton exitDoor2 = new interactiveButton(this,"ville", new DiscreteCoordinates(73,61),Orientation.DOWN, new DiscreteCoordinates(146,78), Arrays.asList(new DiscreteCoordinates(146,78),new DiscreteCoordinates(146,77)));
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
            registerActor(exitDoor2);
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(102,76), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(96,61), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(96,74), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(80,73), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(75,89), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(72,95), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(78,103), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(83,107), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(87,115), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(90,106), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(93,108), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(93,103), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(103,103), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(108,93), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(123,90), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(135,72), 0, true,2000,dialogStd, false));
            registerActor(new Plebe(this,  Orientation.LEFT, new DiscreteCoordinates(129,63), 0, true,2000,dialogStd, false));
            registerActor(new Jomilo(this,  Orientation.DOWN, new DiscreteCoordinates(99,45), 0, true,2000,
                    new String[]{"Tu as vraiment laissé ta copine se sacrifier pour toi ?!!", "Mais c'est lache ! Va la sauver illico presto !"}, false));
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

