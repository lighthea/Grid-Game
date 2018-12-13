package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmeAI;
import ch.epfl.cs107.play.game.enigme.actor.Furbringar;
import ch.epfl.cs107.play.game.enigme.actor.Plebe;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class Prasinateratapolis extends EnigmeArea {

    public String getTitle() {
        return "prasinatefratapolis";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }


    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return this.spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(74, 27);
    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
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
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(65, 30), 0,
                    true, 0, new String[]{"J'ai perdu ma fille... elle est entre les mains des... des... !"},
                    false));
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(65, 23), 0,
                    true, 0, new String[]{"Le maire as des plans... pour le moins peu conventionnels !"},
                    false));

            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(84, 29), 0,
                    true, 0, new String[]{"Les... les... je n'arrives pas à prononcer leurs noms je ne suis pas " +
                    " en utf-16 moi !"},
                    false));
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(81, 29), 0,
                    true, 0, new String[]{"Quand est-ce que cette folie prendra fin..."},
                    false));
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(78, 29), 0,
                    true, 0, new String[]{"Et dire qu'avant on vivait tranquillement les equationd'eh mouvement"},
                    false));
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(87, 29), 0,
                    true, 0, new String[]{"Je ne suis pas venu pour souffrir okay !"},
                    false));
            this.registerActor(new Plebe(this, Orientation.RIGHT, new DiscreteCoordinates(90, 29), 0,
                    true, 0, new String[]{"Allez trouver M. Fürbringar, il saura quoi faire..."},
                    false));

            this.registerActor(new Furbringar(this, Orientation.RIGHT, new DiscreteCoordinates(41+64, 29), 0,
                    true, 0, new String[]{"Les diff'Ehrentzielles... comment nommer de telles abominations...",
            "Nul ne sait d'ou ils viennent... ni ce qu'ils sont, mais si vous voulez mon avis..."," ils viennent juste d'à côté ",
            "et ils sont plutot verts", " Je vous propose de m'aider à en finir avec eux... mais pour cela il faudrait encore ",
            "pouvoir sauver nos chers amis prisonniers de ces monstres !","Pour cela il vous faudra traverser la rame de train",
            "Pour cela je vais user de mon pouvoir...", "Le changement de coordonées !"},
                    false));

            this.registerActor(new EnigmeAI(this, Orientation.DOWN, new DiscreteCoordinates(70, 16), 15, false, 2000,
                    new DiscreteCoordinates(70,15),new DiscreteCoordinates(70,14),new DiscreteCoordinates(70,13),
                    new DiscreteCoordinates(70,12),new DiscreteCoordinates(70,11),new DiscreteCoordinates(70,10),
                    new DiscreteCoordinates(70,9),new DiscreteCoordinates(70,8),new DiscreteCoordinates(70,7),
                    new DiscreteCoordinates(71,7),new DiscreteCoordinates(72 ,7),new DiscreteCoordinates(72,8),
                    new DiscreteCoordinates(72,9),new DiscreteCoordinates(72,10),new DiscreteCoordinates(72,11),
                    new DiscreteCoordinates(72,12),new DiscreteCoordinates(72,13),new DiscreteCoordinates(72,14),
                    new DiscreteCoordinates(72,15),new DiscreteCoordinates(71,15)
                    ));

            this.registerActor(new EnigmeAI(this, Orientation.DOWN, new DiscreteCoordinates(42, 16), 15, false, 2000,
                    new DiscreteCoordinates(42,15),new DiscreteCoordinates(42,14),new DiscreteCoordinates(42,13),
                    new DiscreteCoordinates(42,12),new DiscreteCoordinates(42,11),new DiscreteCoordinates(42,10),
                    new DiscreteCoordinates(42,9),new DiscreteCoordinates(43, 9),
                    new DiscreteCoordinates(44,9),new DiscreteCoordinates(44,10),new DiscreteCoordinates(44,11),
                    new DiscreteCoordinates(44,12),new DiscreteCoordinates(44,13),new DiscreteCoordinates(44,14),
                    new DiscreteCoordinates(44,15),new DiscreteCoordinates(43,15)
            ));

            this.registerActor(new Door(this, "templeMaya", new DiscreteCoordinates(16, 77),Orientation.UP,
                    new DiscreteCoordinates(38, 6), Arrays.asList( new DiscreteCoordinates(38, 6))));

            this.registerActor(new Door(this, "grotteOrcs", new DiscreteCoordinates(15,15),Orientation.DOWN,
                    new DiscreteCoordinates(79 + 64, 7), Arrays.asList( new DiscreteCoordinates(79 + 64, 7))));
        }catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
}