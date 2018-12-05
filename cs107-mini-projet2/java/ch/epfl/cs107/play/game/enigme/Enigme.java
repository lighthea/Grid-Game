package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;
import java.util.List;

public class Enigme extends AreaGame {

    private EnigmePlayer player;
    private Apple apple;
    private Door door;

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public void update (float deltaTime){

        super.update(deltaTime);
        if (player.isPassingDoor()) {
            this.setCurrentArea(player.getLastDoor().getDestinationArea(), false);
            player.enterArea(getCurrentArea(), player.getLastDoor().getLandingCoordinates());
            player.setPassingDoor(false);
            this.getCurrentArea().setViewCandidate(player);
        }
    }

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        this.addArea(new LevelSelector());
        this.addArea(new Level1());
        this.addArea(new Level2());
        this.addArea(new Level3());



        this.setCurrentArea("LevelSelector", true);
        player = new EnigmePlayer(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5,5));
        apple = new Apple(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5,6));
        List<DiscreteCoordinates> DoorCoord = Arrays.asList((new DiscreteCoordinates(6,7)));

        door = new Door(getCurrentArea(), "Level1", new DiscreteCoordinates(5,5),Orientation.DOWN,
                new DiscreteCoordinates(6,7),
                DoorCoord);
        this.getCurrentArea().registerActor(player);
        this.getCurrentArea().registerActor(apple);
        this.getCurrentArea().registerActor(door);

        this.getCurrentArea().setViewCandidate(player);
        return true;
    }
}
