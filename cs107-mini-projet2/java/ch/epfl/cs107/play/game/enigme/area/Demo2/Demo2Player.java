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
    private boolean isThroughDoor;

    private final static int ANIMATION_DURATION = 8 ;

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

        area.registerActor(this);
        this.resetMotion();
        this.getOwnerArea().unregisterActor(this);
        this.setOwnerArea(area);


    }

    private void displacement (boolean pressed, Orientation o)
    {
        if (pressed) {
            if (this.getOrientation() == o)
                move(ANIMATION_DURATION);

            setOrientation(o);
        }
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

        displacement(downArrow.isDown(), Orientation.valueOf("DOWN"));
        displacement(upArrow.isDown(),  Orientation.valueOf("UP"));
        displacement(leftArrow.isDown(), Orientation.valueOf("LEFT"));
        displacement(rightArrow.isDown(), Orientation.valueOf("RIGHT"));


    }
    @Override
    protected  boolean move(int frameForMove){

       /*if (getEnteringCells().forEach((i) -> getOwnerArea().getAreaBehavior().getCells()[i.x][i.y] == "DOOR"))
           isThroughDoor = true;*/
       return super.move(frameForMove);
    }

    public Demo2Player(Area area , Orientation orientation , DiscreteCoordinates coordinates){
        super(area, orientation, coordinates);
        this.setOrientation(Orientation.valueOf("DOWN"));
        this.sprite = new Sprite("ghost.1", 1, 1.f,this) ;
    }
}
