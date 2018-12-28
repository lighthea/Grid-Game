package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;
import java.util.Collections;

public class ile extends EnigmeArea {

    public String getTitle() {
        return "ile";
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(130,70);
    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }

    private int scaleFactor = 15;

    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {

            super.begin(window, fileSystem);
            this.registerActor(new Door(this,"Grotte", new DiscreteCoordinates(20,4),
                    Orientation.DOWN, new DiscreteCoordinates(98,66), Arrays.asList(new DiscreteCoordinates(98,66))));
            this.registerActor(new Door(this,"Peristerapolis", new DiscreteCoordinates(99,44),
                    Orientation.DOWN, new DiscreteCoordinates(42,35), Arrays.asList(new DiscreteCoordinates(42,35))));
            this.registerActor(new interactiveButton(this,"Lavamountain", new DiscreteCoordinates(16,5),
                    Orientation.DOWN, new DiscreteCoordinates(92,23), Arrays.asList(new DiscreteCoordinates(92,23))));
            this.registerActor(new interactiveButton(this,"Lavamountain", new DiscreteCoordinates(16,5),
                    Orientation.DOWN, new DiscreteCoordinates(93,23), Arrays.asList(new DiscreteCoordinates(93,23))));
            this.registerActor(new Door(this,"Grotte", new DiscreteCoordinates(20,14),
                    Orientation.DOWN, new DiscreteCoordinates(98,74), Arrays.asList(new DiscreteCoordinates(98,74))));
            this.registerActor(new Door(this,"MaisonAraignee", new DiscreteCoordinates(15,13),
                    Orientation.DOWN, new DiscreteCoordinates(51,48), Arrays.asList(new DiscreteCoordinates(51,48))));
            this.registerActor(new Constance(this, Orientation.RIGHT, new DiscreteCoordinates(80, 64),
                    0,true,0,new String[]{"Ooooh tu es enfin là !","Je vais à la maison ! Rejoins moi vite !"
                    ,"Il faut que tu me prépare à manger quand même !", "Je vais rejoindre Grand-mère !"},false));
            this.registerActor(new patapon(this, Orientation.UP, new DiscreteCoordinates(92,4),
                    0, true,0, new String[]{"Ici, il y a une grotte spéciale...",
            "A l'intérieur tu ne trouvera que des ennemis", "Et tu ne pourras en sortir que si tu vainc tout tes ennemis",
            "Alors intéragis avec précaution..."},false));

            this.registerActor(new Jomilo(this, Orientation.RIGHT, new DiscreteCoordinates(129,69),
                    0, true,0, new String[]{"Bon, me demande pas pourquoi une porte dans un dock t'as ramené ici...",
            "Imagine que tu viens de prendre un bateau...", "Bon en bref au sud il y a des balerines... spéciales...",
            "Au milieu un petit labirynthe pour vérifier que tu ne sois pas lobotomisé", "Et tout au fond tu trouvera ton destin...",
            "POUF"},false));
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
}