package ch.epfl.cs107.play.Demo.MovingRock;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Canvas;
import java.awt.*;


public class MovingRock extends GraphicsEntity implements Actor {

    private final TextGraphics text;

    public MovingRock(Vector position, String text){

        super(position , new ImageGraphics(ResourcePath.getSprite("rock3"), 0.1f,0.1f, null, Vector.ZERO , 1.0f, -Float.MAX_VALUE));
        this.text = new TextGraphics(text , 0.03f, Color.BLUE) ;
        this.text.setParent(this);

    }
    @Override
    public void draw(Canvas canvas) {
        this.getGraphics().draw(canvas);
        this.text.draw(canvas);
    }

}