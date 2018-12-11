package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnigmeAI extends MovableAreaEntity implements Interactor {
    public final float maxHealth = 100;
    private Sprite sprite;
    private final static int ANIMATION_DURATION = 8 ;
    private EnigmeAI.EnigmeAIHandler handler;
    private float damages;
    private boolean fixed;
    private DiscreteCoordinates[] path;
    private int currentPathIndex;
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    private float health;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmeAI(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed, DiscreteCoordinates... path) {
        super(area, orientation, position);
        this.damages = damages;
        this.fixed = fixed;
        this.path = path;
        this.sprite = new Sprite("mob.1", 1, 1.f,this) ;
        this.health = maxHealth;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
    @Override
    public void update (float deltaTime) {
        if (health <= 0) {
            this.getOwnerArea().unregisterActor(this);
        }
        if (!fixed && !isMoving){
           setTargetMainCellCoordinates(path[currentPathIndex + 1]);
           this.move(ANIMATION_DURATION);
           currentPathIndex += 1;
        }
        setOrientation(Orientation.RIGHT);

    }
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Arrays.asList(
                getCurrentMainCellCoordinates().up(),
                getCurrentMainCellCoordinates().down(),
                getCurrentMainCellCoordinates().left(),
                getCurrentMainCellCoordinates().right());
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public void interactWith(Interactable other){
        other.acceptInteraction(handler);

    }

    private class EnigmeAIHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(EnigmePlayer player) {
            System.out.println("Player " + player.getHealth());
            player.setHealth(player.getHealth() - damages);
            player.setTargetMainCellCoordinates(new DiscreteCoordinates(getCurrentMainCellCoordinates().x + 1, getCurrentMainCellCoordinates().y + 1));
        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
        @Override
        public void interactWith(EnigmeAI ai) {

        }

    }
}
