package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

import java.util.List;
import java.util.function.DoublePredicate;

public class Animation {
    private Vector anchor;
    private Sprite sprite;

    public Sprite[][] getAnimation() {
        return animation;
    }

    private Sprite[][] animation;
    public Animation(Sprite sprite, Vector anchor, int colNumber, int lineNumber, Actor parent) {
        this.anchor = anchor;
        this.sprite = sprite;

        animation = new Sprite[colNumber][lineNumber];
        for (int i = 0; i < colNumber; i++) {
            for (int j = 0; j < lineNumber; j++) {
                animation[i][j] = new Sprite(sprite.getName().substring(14, sprite.getName().length() - 4),0.6f , 0.6f,parent,new RegionOfInterest(0, j * 21, 16, 21), anchor) ;
            }
        }
    }
}
