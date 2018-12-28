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

public class Dendropolis extends EnigmeArea {
    private int scaleFactor = 15;

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }


    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(73,61);

    public String getTitle() {
        return "ville";
    }

    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    private String[] dialog =  {"Je suis madame Lochowsko maire de la ville",
            "Il y a quelques années j'ai proposé que les anneaux d'annanas suivent une suite...",
            "Je crois qu'ils ont intégré ca dans un autre univers...",
    "On t'a déjà parlé des intégrales quintuples sur les plans d'Euleur ?", "Ils n'apprennent plus ça au CE2 ?!"};
    private String[] dialog2 = {"Bonjour ! Quel beau temps aujourd'hui ! Ne trouvez pas ma conversation inutile ?"};
    private String[] dialog3 = {"Le marché est ouvert aujourd'hui ! Allons acheter des fleurs !", "Comment ca je devrais " +
            " ajouter de la profondeur au scénario ?!"};
    private String[] dialog5 = {"Va au port. -Le dev", "hein, quoi ?"};
    private String[] dialog6 = {"C'est pas le bon port ! Il y en a deux."};
    private String[] dialog7 = {"oh ! Un papillon." + "Gotch'a ! Je t'ai encore fais perdre du temps sur un dialogue inutile !"};





    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    private Lochowsko lochowsko = new Lochowsko(this,  Orientation.DOWN, new DiscreteCoordinates(61,55), 0, true,2000,dialog, false  );
    private Plebe pleb1 = new Plebe(this,  Orientation.DOWN, new DiscreteCoordinates(61,45), 0, true,2000,dialog2, false);
    private Plebe pleb2 = new Plebe(this,  Orientation.DOWN, new DiscreteCoordinates(38,45), 0, true,2000,dialog3, false);
    private Plebe pleb3 = new Plebe(this,  Orientation.DOWN, new DiscreteCoordinates(61,25), 0, true,2000,dialog7, false);
    private Plebe pleb4 = new Plebe(this,  Orientation.DOWN, new DiscreteCoordinates(38,35), 0, true,2000,dialog5, false);
    private Plebe pleb5 = new Plebe(this,  Orientation.DOWN, new DiscreteCoordinates(38,25), 0, true,2000,dialog6, false);


    List<DiscreteCoordinates> list = Arrays.asList( new DiscreteCoordinates(76,21));
    private Door LeavingDoor = new Door(this, "ile",new DiscreteCoordinates(130,70),Orientation.DOWN,new DiscreteCoordinates(76,21),list);

    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            super.begin(window, fileSystem);
            registerActor(LeavingDoor);
            registerActor(lochowsko);
            registerActor(pleb1);
            registerActor(pleb2);
            registerActor(pleb3);
            registerActor(pleb4);
            registerActor(pleb5);
            registerActor(new Jomilo(this,  Orientation.DOWN, new DiscreteCoordinates(73,55), 0, true,0,
                    new String[]{"Je suis la légendaire Gandalf la Bleu","Mais pour des raisons de copyright tu m'appelera..."
                            ,"Jo'milo gardienne de la Manne","Jomilo pour les simplets comme toi","Tu vas débuter une quête dans un monde fabuleux",
                            "fascinant, fantasmagorique ( et tout plein d'adjectifs en F)","Tu es à la recherche de ta bien aimée," +
                            " sa divine majesté Constance qui se trouve être sur l'ile de l'ours","Meme si on sait très bien qu'étant en IC, ca va te demander un peu d'imagination",
                            "Allez, va vite la trouver, cherche une porte volante"}, false  ));
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);

    }

}

