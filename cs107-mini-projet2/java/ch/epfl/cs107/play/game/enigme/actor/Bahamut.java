package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Bahamut extends EnigmeAI {
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param damages
     * @param fixed
     * @param coolDown
     * @param path
     */
    public Bahamut(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed, int coolDown, DiscreteCoordinates... path) {
        super(area, orientation, position, damages, fixed, coolDown, path);
        this.sprite = new Sprite("flying.mob.3", 1, 1.f,this) ;
        Vector anchor =new Vector(0.0f, 0.0f) ;
        this.animation = new Animation(this.sprite, anchor, 4, 4, this,1 , 1);
        sprite = animation.getAnimation()[0][0];
    }
}
