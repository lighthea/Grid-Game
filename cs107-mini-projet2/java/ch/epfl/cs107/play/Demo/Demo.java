package ch.epfl.cs107.play.Demo;

import ch.epfl.cs107.play.Demo.MovingRock.MovingRock;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import java.awt.*;

public class Demo implements Game {
    private Actor actor1;
    private MovingRock Rock;
    private Window window;
    private FileSystem fileSystem;
    private TextGraphics Boom;

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
        actor1 = new GraphicsEntity(Vector.ZERO,new ShapeGraphics(new Circle(0.1f),null, Color.RED, 0.005f));
        this.window = window;
        this.fileSystem = fileSystem;

        Rock = new MovingRock(new Vector(0.4f, 0.4f), "foo");
        this.Boom = new TextGraphics("BOOM !", 0.05f, Color.RED);

        return true;
    }
    @Override
    public void end() {
    }
    @Override
    public void update(float deltaTime) {
        actor1.draw(window);
        Keyboard keyboard = window.getKeyboard() ;
        Button downArrow = keyboard.get(Keyboard.DOWN) ;

        if (downArrow.isDown()) {
            Rock.update(deltaTime);
        }
        Rock.draw(window);
        double len = Rock.getPosition().getLength();
        if (len <= 0.1f)
        {
            this.Boom.draw(window);
        }
    }
}