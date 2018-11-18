package ch.epfl.cs107.play.Demo;

import ch.epfl.cs107.play.Demo.MovingRock.MovingRock;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import java.awt.*;

public class Demo implements Game {
    private Actor actor1;
    private MovingRock Rock;
    private Window window;
    private FileSystem fileSystem;

    @Override
    public int getFrameRate() {
        return 24;
    }
    @Override
    public String getTitle() {
        return "bar";
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        actor1 = new GraphicsEntity(Vector.ZERO,new ShapeGraphics(new Circle(0.2f),null, Color.RED, 0.005f));
        this.window = window;
        this.fileSystem = fileSystem;

        Rock = new MovingRock(Vector.ZERO, "foo");


        return true;
    }
    @Override
    public void end() {
    }
    @Override
    public void update(float deltaTime) {
        actor1.draw(window);
        Rock.draw(window);
    }
}