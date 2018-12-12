package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
    public final float maxHealth = 100;
    private Sprite sprite;
    private final static int ANIMATION_DURATION = 6 ;
    private boolean pressed;
    public boolean isPassingDoor() {
        return isPassingDoor;
    }
    private Animation animationSprite;
    public void setPassingDoor(boolean b) {
        isPassingDoor = b;
    }
    private int currentFrame;
    private boolean isPassingDoor;
    private Door lastDoor;
    private EnigmePlayerHandler handler;
    private short orientationInt;
    private boolean wasMoving;
    private boolean wasPressed;
    private boolean sameCellAsBefore;
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    private float health;
    private List<Interactable> inventory;
    /**
     * Default MovableAreaEntity constructor
     *  @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        this.setOrientation(Orientation.valueOf("DOWN"));
        this.sprite = new Sprite("max.new.1", 1, 1.f,this) ;
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
        health = 100;
        Vector anchor = new Vector(0.25f, 0.32f) ;
        animationSprite = new Animation(this.sprite, anchor, 4, 4, this, 0.7f, 0.8f);
        currentFrame =0;
        orientationInt = 0;
        sprite = animationSprite.getAnimation()[0][0];
    }

    @Override
    public void draw(Canvas canvas) {
            sprite.draw(canvas);
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
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }
    public void stepBack(){
        if(!isMoving) {
            setOrientation(getOrientation().opposite());
            move(ANIMATION_DURATION);
        }
    }
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Arrays.asList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {

        return !wasPressed && pressed;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    public void enterArea(Area area , DiscreteCoordinates position){

        this.getOwnerArea().unregisterActor(this);

        area.registerActor(this);
        this.setCurrentPosition(position.toVector());
        resetMotion();
        this.setOwnerArea(area);
    }

    @Override
    public void update (float deltaTime){
        sameCellAsBefore = !wasMoving || isMoving ;
        if (health <= 0){
            this.getOwnerArea().unregisterActor(this);
        }

        Keyboard key = getOwnerArea().getKeyboard();

        Button downArrow = key.get(Keyboard.DOWN) ;
        Button upArrow = key.get(Keyboard.UP) ;
        Button leftArrow = key.get(Keyboard.LEFT) ;
        Button rightArrow = key.get(Keyboard.RIGHT) ;
        Button L = key.get(Keyboard.L) ;
        wasPressed= pressed;
        if (L.isDown()) pressed = true;
        else pressed = false;

        if(!isMoving) {
            if (downArrow.isDown()) {
                if (getOrientation().equals(Orientation.DOWN)) {
                    this.move(ANIMATION_DURATION);
                    orientationInt = 0;
                    return;
                } else {
                    setOrientation(Orientation.DOWN);
                    orientationInt = 0;
                    return;
                }
            }

            else if (upArrow.isDown()) {
                if (getOrientation().equals(Orientation.UP)) {
                    this.move(ANIMATION_DURATION);
                    orientationInt = 2;
                    return;
                }
                else {
                    setOrientation(Orientation.UP);
                    orientationInt = 2;
                    return;
                }
            }
            else if (rightArrow.isDown()) {
                if (getOrientation().equals(Orientation.RIGHT)) {
                    this.move(ANIMATION_DURATION);
                    orientationInt = 3;
                    return;
                } else {
                    setOrientation(Orientation.RIGHT);
                    orientationInt = 3;
                    return;
                }
            }
            else if (leftArrow.isDown()) {
                if (getOrientation().equals(Orientation.LEFT)) {
                    this.move(ANIMATION_DURATION);
                    orientationInt = 1;
                    return;
                } else {
                    setOrientation(Orientation.LEFT);
                    orientationInt = 1;
                    return;
                }
            }

        } else {
            sprite = animationSprite.getAnimation()[orientationInt][currentFrame];
            currentFrame = (currentFrame + 1) % 4;
        }

        wasMoving = isMoving;
        super.update(deltaTime);

    }

    public Door getLastDoor() {
        return lastDoor;
    }

    public void setLastDoor(Door lastDoor) {
        this.lastDoor = lastDoor;
    }

    @Override
    public void interactWith(Interactable other){
        if (other.uniqueInteractable() && other.getCurrentCells().equals(this.getCurrentCells()))
            if (!sameCellAsBefore) {
                other.acceptInteraction(handler);
                return;
            }
            else {
                return;
            }
        other.acceptInteraction(handler);
    }

    private class EnigmePlayerHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(Door door) {
            setPassingDoor(true);
            setLastDoor(door);
            System.out.println("Nous traversons une porte");
        }

        @Override
        public void interactWith(Apple apple){
            health += 5;
            System.out.println("Nous Mangeons des pommes");
        }

        @Override
        public void interactWith(EnigmePlayer player) {
        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
        @Override
        public void interactWith(EnigmeAI ai) {
            ai.setHealth(ai.getHealth() - 5);
        }
    }
}

