package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
    private Sprite sprite;
    private final static int ANIMATION_DURATION = 8 ;
    private boolean pressed;
    public boolean isPassingDoor() {
        return isPassingDoor;
    }

    public void setPassingDoor(boolean b) {
        isPassingDoor = b;
    }

    private boolean isPassingDoor;
    private Door lastDoor;
    private EnigmePlayerHandler handler;
    /**
     * Default MovableAreaEntity constructor
     *  @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        this.setOrientation(Orientation.valueOf("DOWN"));
        this.sprite = new Sprite("ghost.1", 1, 1.f,this) ;
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
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
        if (pressed)
        {
            return true;
        }
        return false;
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
        Keyboard key = getOwnerArea().getKeyboard();

        Button downArrow = key.get(Keyboard.DOWN) ;
        Button upArrow = key.get(Keyboard.UP) ;
        Button leftArrow = key.get(Keyboard.LEFT) ;
        Button rightArrow = key.get(Keyboard.RIGHT) ;
        Button L = key.get(Keyboard.L) ;

        if (L.isDown()) {
            pressed = true;
            System.out.println("L");
        }
        else pressed = false;

        if(!isMoving) {
            if (downArrow.isDown()) {
                if (getOrientation().equals(Orientation.DOWN)) {
                    this.move(ANIMATION_DURATION);
                    return;
                } else {
                    setOrientation(Orientation.DOWN);
                    return;
                }
            }

            else if (upArrow.isDown()) {
                if (getOrientation().equals(Orientation.UP)) {
                    this.move(ANIMATION_DURATION);
                    return;
                }
                else {
                    setOrientation(Orientation.UP);
                    return;
                }
            }
            else if (leftArrow.isDown()) {
                if (getOrientation().equals(Orientation.LEFT)) {
                    this.move(ANIMATION_DURATION);
                    return;
                } else {
                    setOrientation(Orientation.LEFT);
                    return;
                }
            }
            else if (rightArrow.isDown()) {
                if (getOrientation().equals(Orientation.RIGHT)) {
                    this.move(ANIMATION_DURATION);
                    return;
                }
                else {
                    setOrientation(Orientation.RIGHT);
                    return;
                }
            }
        }

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
        other.acceptInteraction(handler);
    }

    private class EnigmePlayerHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(Door door) {
            setPassingDoor(true);
            setLastDoor(door);
            System.out.println("Nous traversons une poooooorte");
        }

        @Override
        public void interactWith(Apple apple){
            System.out.println("Nous Mangeons des pommes");
        }

        @Override
        public void interactWith(EnigmePlayer player) {

        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
    }
}

