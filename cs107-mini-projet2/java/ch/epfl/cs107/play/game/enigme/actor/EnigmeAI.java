package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnigmeAI extends MovableAreaEntity implements Interactor {
    public final float maxHealth = 100;
    protected Sprite sprite;
    protected final static int ANIMATION_DURATION = 8 ;
    protected EnigmeInteractionVisitor handler;
    protected float damages;
    protected boolean fixed;
    protected DiscreteCoordinates[] path;
    protected int currentPathIndex;
    protected int orientationInt, currentFrame;
    protected Animation animation;
    protected long lastDamage;
    protected int coolDown;
    protected boolean canAttack;
    protected float initialWidth, initialHeigth;
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    protected float health;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmeAI(Area area, Orientation orientation, DiscreteCoordinates position, float damages,
                        boolean fixed, int coolDown, DiscreteCoordinates... path) {
        super(area, orientation, position);
        this.damages = damages;
        this.fixed = fixed;
        this.path = path;
        this.sprite = new Sprite("mob.1", 1, 1.f,this) ;
        this.health = maxHealth;
        Vector anchor =new Vector(0.0f, 0.0f) ;
        this.animation = new Animation(this.sprite, anchor, 4, 4, this,1 , 1);
        sprite = animation.getAnimation()[0][0];
        currentPathIndex = 0;
        this.handler = new EnigmeAIHandler();
        this.coolDown = coolDown;
        lastDamage = System.currentTimeMillis();
        initialHeigth = 1;
        initialWidth = 1;
        resetMotion();

    }
    @Override
    public boolean uniqueInteractable(){
        return true;
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
        if (System.currentTimeMillis() - lastDamage > coolDown){
            this.sprite.setHeight(initialHeigth*1.5f);
            this.sprite.setWidth(initialWidth* 1.5f);
            canAttack = true;
            lastDamage = System.currentTimeMillis();
        }else{

            canAttack = false;
            this.sprite.setHeight(initialHeigth);
            this.sprite.setWidth(initialWidth);
        }

        if (!fixed) {

            Vector direction = new Vector(path[currentPathIndex].x - getCurrentMainCellCoordinates().x,
                    path[currentPathIndex].y - getCurrentMainCellCoordinates().y);

            if (!isMoving) {

                if (direction.x == (Orientation.RIGHT.toVector()).x && direction.y == (Orientation.RIGHT.toVector()).y) {
                    if (this.getOrientation().equals(Orientation.RIGHT)) {
                        if(this.move(ANIMATION_DURATION))
                            currentPathIndex = (currentPathIndex + 1) % path.length;
                        orientationInt = 3;
                        return;
                    } else {
                        setOrientation(Orientation.RIGHT);
                        orientationInt = 3;
                        return;
                    }
                }

                if (direction.x == (Orientation.LEFT.toVector()).x && direction.y == (Orientation.LEFT.toVector()).y) {
                    if (this.getOrientation().equals(Orientation.LEFT)) {
                        if(this.move(ANIMATION_DURATION))
                            currentPathIndex = (currentPathIndex + 1) % path.length;
                        orientationInt = 1;
                        return;

                    } else {
                        setOrientation(Orientation.LEFT);
                        orientationInt = 1;
                        return;
                    }
                }

                if (direction.x == (Orientation.DOWN.toVector()).x && direction.y == (Orientation.DOWN.toVector()).y) {
                    if (this.getOrientation().equals(Orientation.DOWN)) {
                        if(this.move(ANIMATION_DURATION))
                            currentPathIndex = (currentPathIndex + 1) % path.length;
                        orientationInt = 0;
                        return;
                    } else {
                        setOrientation(Orientation.DOWN);
                        orientationInt = 0;
                        return;

                    }
                }

                if (direction.x == (Orientation.UP.toVector()).x && direction.y == (Orientation.UP.toVector()).y) {
                    if (this.getOrientation().equals(Orientation.UP)) {
                        if (this.move(ANIMATION_DURATION))
                            currentPathIndex = (currentPathIndex + 1) % path.length;
                        orientationInt = 2;
                        return;
                    } else {
                        setOrientation(Orientation.UP);
                        orientationInt = 2;
                        return;
                    }
                }

            } else {
                sprite = animation.getAnimation()[orientationInt][currentFrame];
                currentFrame = (currentFrame + 1) % 4;
            }
        }
        super.update(deltaTime);
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
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
       return !isMoving;
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public void interactWith(Interactable other){
        if (canAttack) {
            other.acceptInteraction(handler);

        }
    }

    private class EnigmeAIHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(EnigmePlayer player) {
            player.setHealth(player.getHealth() - damages);
            player.stepBack();
        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
        @Override
        public void interactWith(EnigmeAI ai) {

        }

    }
}
