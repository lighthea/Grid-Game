package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.grotteOrcs;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

import static java.lang.System.exit;

public class EnigmePart3 extends AreaGame {

    private EnigmePlayer player;
    private float healthPercentageMax;
    private TextGraphics health;
    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public void update (float deltaTime){
        super.update(deltaTime);
        if (player.isPassingDoor()) {
            this.getCurrentArea().leaveAreaCells(this.player, player.getCurrentCells());
            this.setCurrentArea(player.getLastDoor().getDestinationArea(), false);
            player.enterArea(getCurrentArea(), player.getLastDoor().getLandingCoordinates());
            player.setPassingDoor(false);
            player.setLastDoor(null);
            getCurrentArea().setViewCandidate(player);
            }
    }


    @Override
    public String getTitle() {
        return "EnigmePart3";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        this.addArea(new LevelSelector());
        this.addArea(new Level1());
        this.addArea(new Level2());


        this.setCurrentArea("LevelSelector", true);

        player = new EnigmePlayer(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5,3));
        this.getCurrentArea().registerActor(player);
        this.getCurrentArea().setViewCandidate(player);
        return true;
    }
}
