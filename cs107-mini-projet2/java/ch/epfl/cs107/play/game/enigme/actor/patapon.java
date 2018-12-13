package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class patapon extends EnigmeNPC {
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
    public patapon(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed, int coolDown, String[] dialogText, boolean active, DiscreteCoordinates... path) {
        super(area, orientation, position, damages, fixed, coolDown, dialogText, active, path);
        this.sprite = new Sprite("flying.mob.1", 0.5f, .5f,this);
        this.animation = new Animation(this.sprite, new Vector(0, 0), 4, 4, this,.5f , .5f);
        sprite = animation.getAnimation()[0][0];

    }
    @Override
    public void update (float deltaTime){
        super.update(deltaTime);
        if (!isInteract())
            if (getUtilisationCount() > 0)
                this.getOwnerArea().unregisterActor(this);
    }
}
