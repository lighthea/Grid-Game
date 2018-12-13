package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public class Animation {
    private Vector anchor;
    private Sprite sprite;
    private int imageWidth = 64;
    private int imageHeigth = 84;
    public Sprite[][] getAnimation() {
        return animation;
    }

    private Sprite[][] animation;
    public Animation(Sprite sprite, Vector anchor, int colNumber, int lineNumber, Actor parent, float width, float heigth, int... imageDimensions) {
        this.anchor = anchor;
        this.sprite = sprite;
        if (imageDimensions.length == 2){
            imageWidth = imageDimensions[0];
            imageHeigth = imageDimensions[1];
        }

        animation = new Sprite[colNumber][lineNumber];
        for (int i = 0; i < colNumber; ++i)
            for (int j = 0; j < lineNumber; ++j)
                animation[i][j] = new Sprite(sprite.getName().substring(14, sprite.getName().length() - 4), width,
                        heigth, parent, new RegionOfInterest(i * imageWidth/colNumber, j * imageHeigth/lineNumber,
                        imageWidth/colNumber, imageHeigth/lineNumber), anchor, 1f, 0);
    }
}
