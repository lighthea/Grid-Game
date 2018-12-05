package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity {
    private Sprite sprite;
    private final static int ANIMATION_DURATION = 8 ;

    public boolean isPassingDoor() {
        return isPassingDoor;
    }

    public void setPassingDoor(Door door) {
        isPassingDoor = true;
        lastDoor = door;

    }

    private boolean isPassingDoor;
    private Door lastDoor;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.setOrientation(Orientation.valueOf("DOWN"));
        this.sprite = new Sprite("old.man.1", 1, 1.f,this) ;
        this.isPassingDoor = false;
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

    public void enterArea(Area area , DiscreteCoordinates position){

        this.getOwnerArea().unregisterActor(this);

        area.registerActor(this);
        this.setCurrentPosition(position.toVector());
        resetMotion();
        this.setOwnerArea(area);
    }

    @Override
    public void update (float deltaTime){
        super.update(deltaTime);

        Keyboard key = getOwnerArea().getKeyboard();
        Button downArrow = key.get(Keyboard.DOWN) ;
        Button upArrow = key.get(Keyboard.UP) ;
        Button leftArrow = key.get(Keyboard.LEFT) ;
        Button rightArrow = key.get(Keyboard.RIGHT) ;

        if(!isMoving) {
            if (downArrow.isDown())
                if(getOrientation().equals(Orientation.DOWN))
                    this.move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.DOWN);


            else if (upArrow.isDown())
                if(getOrientation().equals(Orientation.UP))
                    this.move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.UP);

            else if (leftArrow.isDown())
                if(getOrientation().equals(Orientation.LEFT))
                    this.move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.LEFT);

            else if (rightArrow.isDown())
                if(getOrientation().equals(Orientation.RIGHT))
                    this.move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.RIGHT);
        }
    }

}
