package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.area.Demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.Demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.Demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
    private Demo2Player player;
    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public void update (float deltaTime){
        super.update(deltaTime);
        if (player.isThroughDoor())
        {
            setCurrentArea("Level1", true);
            player.enterArea(getCurrentArea(), new DiscreteCoordinates(5,5));
            player.setThroughDoor(false);
            this.getCurrentArea().setViewCandidate(player);
        }

    }
    @Override
    public String getTitle() {
        return "Demo2";
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        this.addArea(new Room0());
        this.addArea(new Room1());

        this.setCurrentArea("LevelSelector", true);
        player = new Demo2Player(getCurrentArea(),  Orientation.DOWN, new DiscreteCoordinates(5,5));
        this.getCurrentArea().registerActor(player);
        this.getCurrentArea().setViewCandidate(player);
        return true;
    }
}
