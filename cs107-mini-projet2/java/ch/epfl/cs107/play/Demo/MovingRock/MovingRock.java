package ch.epfl.cs107.play.Demo.MovingRock;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Canvas;
import java.awt.*;
public class MovingRock extends GraphicsEntity {
    private final TextGraphics text;
    public MovingRock(Vector position, String text){
        super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"),0.1f,0.1f, new RegionOfInterest(0,0,0,0), Vector.ZERO , 1.0f, -Float.MAX_VALUE));
        this.text = new TextGraphics(text , 0.04f, Color.BLUE) ;
        this.text.setParent(this);
    }
    @Override
    public void draw(Canvas canvas) {
        if(this.getGraphics()!= null) {
            this.getGraphics().draw(canvas);
        }
    }
}