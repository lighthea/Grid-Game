package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Constance_fix extends EnigmeNPC {
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param damages     Here equals to 0
     * @param fixed       Decides whether the actor is fixed or movable
     * @param coolDown    the cooldown between two interactions
     * @param dialogText
     * @param active
     * @param path        the path to follow
     */
    public Constance_fix(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed, int coolDown, String[] dialogText, boolean active, DiscreteCoordinates... path) {
        super(area, orientation, position, damages, fixed, coolDown, dialogText, active, path);
        initialHeigth = .55f;
        initialWidth = .55f;
        this.sprite = new Sprite("blonde", 1, 1.f,this) ;
        Vector anchor =new Vector(1/6f, 1/3f) ;
        this.animation = new Animation(this.sprite, anchor, 4, 4, this,1 , 1,104,100);
        sprite = animation.getAnimation()[0][0];
    }
}
