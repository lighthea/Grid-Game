package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.grotteOrcs;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

import static java.lang.System.exit;

public class Enigme extends AreaGame {

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
        healthPercentageMax = player.getHealth() / player.maxHealth;

        if (player.isPassingDoor()) {
            this.getCurrentArea().leaveAreaCells(this.player, player.getCurrentCells());
            this.setCurrentArea(player.getLastDoor().getDestinationArea(), false);
            player.enterArea(getCurrentArea(), player.getLastDoor().getLandingCoordinates());
            player.setPassingDoor(false);
            player.setLastDoor(null);
            this.getCurrentArea().setViewCandidate(player);
        }

        health.setFontSize(0.5f);

        health.setText(Float.toString((int)(healthPercentageMax * 100)));
        health.setDepth(Float.POSITIVE_INFINITY);
        health.draw(getWindow());

        if (player.getHealth() <= 0){
            getCurrentArea().unregisterActor(player);
            player.enterArea(this.getCurrentArea(), ((EnigmeArea)getCurrentArea()).getSpawnPoint());
            getCurrentArea().registerActor(player);
            player.setHealth(100);
        }

        if (this.getCurrentArea() instanceof bossFinal)
            if (((bossFinal)this.getCurrentArea()).isFinished()) {
                health.setText("CONGRATULATIONS !!!");
                health.setFontSize(10);
                health.draw(getWindow());
                exit(0);
            }
    }

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        this.addArea(new bossFinal());
        this.addArea(new ile());
        this.addArea(new Prasinateratapolis());
        this.addArea(new templeMaya());
        this.addArea(new grotteOrcs());
        this.addArea(new Lavamountain());
        this.addArea(new Peristerapolis());
        this.addArea(new Grotte());
        this.addArea(new Dendropolis());

        this.setCurrentArea("prasinatefratapolis", true);

        player = new EnigmePlayer(getCurrentArea(), Orientation.DOWN, ((EnigmeArea)getCurrentArea()).getSpawnPoint());
        this.getCurrentArea().registerActor(player);

        healthPercentageMax = player.getHealth()/player.maxHealth;
        health = new TextGraphics(Float.toString((int)healthPercentageMax) , 0.03f, Color.RED);
        health.setParent(player);
        health.setAnchor(new Vector(1,1));

        this.getCurrentArea().setViewCandidate(player);
        return true;
    }
}
