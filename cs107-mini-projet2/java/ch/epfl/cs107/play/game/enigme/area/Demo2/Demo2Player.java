package ch.epfl.cs107.play.game.enigme.area.Demo2;

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

public class Demo2Player extends MovableAreaEntity {

    private Sprite sprite;

    public boolean isThroughDoor() {
        return isThroughDoor;
    }

    public void setThroughDoor(boolean throughDoor) {
        isThroughDoor = throughDoor;
    }

    private boolean isThroughDoor;

    private final static int ANIMATION_DURATION = 8 ;
    private boolean canMove(){
        return true;
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
        return Collections.singletonList(getCurrentMainCellCoordinates()) ;
    }
    public void enterArea(Area area , DiscreteCoordinates position){

        this.getOwnerArea().unregisterActor(this);

        area.registerActor(this);
        this.setCurrentPosition(position.toVector());
        this.resetMotion();
        this.setOwnerArea(area);


    }
    public boolean getisThroughDoor() {
        return isThroughDoor;
    }

    @Override
    public void update (float deltaTime){
        super.update(deltaTime);
        Keyboard key = getOwnerArea().getKeyboard();
        Button downArrow = key.get(Keyboard.DOWN) ;
        Button upArrow = key.get(Keyboard.UP) ;
        Button leftArrow = key.get(Keyboard.LEFT) ;
        Button rightArrow = key.get(Keyboard.RIGHT) ;

        if(!isMoving && canMove()) {
            if (downArrow.isDown())
                if(getOrientation().equals(Orientation.DOWN))
                    move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.DOWN);


            else if (upArrow.isDown())
                if(getOrientation().equals(Orientation.UP))
                    move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.UP);

            else if (leftArrow.isDown())
                if(getOrientation().equals(Orientation.LEFT))
                    move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.LEFT);

            else if (rightArrow.isDown())
                if(getOrientation().equals(Orientation.RIGHT))
                    move(ANIMATION_DURATION);
                else
                    setOrientation(Orientation.RIGHT);
        }
    }
    @Override
    protected  boolean move(int frameForMove){
       return super.move(frameForMove);
    }

    public Demo2Player(Area area , Orientation orientation , DiscreteCoordinates coordinates){
        super(area, orientation, coordinates);
        this.setOrientation(Orientation.valueOf("DOWN"));
        this.sprite = new Sprite("ghost.1", 1, 1.f,this) ;
    }
}
