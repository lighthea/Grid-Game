package ch.epfl.cs107.play.game.enigme.area;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;

public class maisonAraignee extends EnigmeArea {
    private int scaleFactor = 15;
    private int counter = 0;
    private spider_dialog boss = new spider_dialog(this, Orientation.DOWN, new DiscreteCoordinates(12,42),
                    0, true, 0,new String[]{"HAHAHA JE VAIS VOUS DEVORER !"," VOUS EN REGRETTEREZ MEME JUSQU'A VOS INTERRO DE PROG"}, false);

    private Constance_fix constance = new Constance_fix(this, Orientation.DOWN, new DiscreteCoordinates(12,41),
            0, true, 0,new String[]{"Laisse moi m'occuper d'elle ! " +
            "Je vais me servir de mon pouvoir ancien,"," l'overflow de coordonées !!!"}, false);

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(15,13);

    public String getTitle() {
        return "MaisonAraignee";
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
            super.begin(window, fileSystem);
            this.registerActor(new Door(this,"ile", new DiscreteCoordinates(42,37),
                    Orientation.DOWN, new DiscreteCoordinates(15,12), Arrays.asList(new DiscreteCoordinates(15,12))));
            this.registerActor(new Door(this,"ile", new DiscreteCoordinates(42,37),
                    Orientation.DOWN, new DiscreteCoordinates(16,12), Arrays.asList(new DiscreteCoordinates(16,12))));
            this.registerActor(boss);
            this.registerActor(constance);
            counter = 0;
            return true;

        } catch (Exception E){


            System.out.println("Error  : "+ E);
            return false;

        }

    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
