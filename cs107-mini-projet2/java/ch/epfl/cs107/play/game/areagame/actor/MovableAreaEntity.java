package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
    /// Indicate if the actor is currently moving
    protected boolean isMoving;
    private int framesForCurrentMove;
    private DiscreteCoordinates targetMainCellCoordinates;
    private List<DiscreteCoordinates> currentCells;

    public DiscreteCoordinates getTargetMainCellCoordinates() {
        return targetMainCellCoordinates;
    }

    final protected List<DiscreteCoordinates> getLeavingCells(){

        return this.getCurrentCells();
    }

    final protected List<DiscreteCoordinates> getEnteringCells(){
       return new ArrayList<>(Arrays.asList(this.getCurrentMainCellCoordinates().jump(getOrientation().toVector())));
    }
    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        this.isMoving = false;
        this.framesForCurrentMove = 0;
        this.targetMainCellCoordinates = this.getCurrentMainCellCoordinates();
        this.setCurrentPosition(getCurrentMainCellCoordinates().toVector());
    }

    /**
     * 
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected  boolean move(int frameForMove){

        if (!this.isMoving && !this.getOwnerArea().vetoFromGrid(this, this.getEnteringCells()))
        {
            System.out.println("Monologuing !");
            this.framesForCurrentMove = frameForMove;
            Vector orientation = getOrientation().toVector();
            targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
            isMoving = true;
            return true;
        }
        return false;
    }


    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {

        if(isMoving && ((targetMainCellCoordinates.x != this.getCurrentMainCellCoordinates().x) ||
                        (targetMainCellCoordinates.y != this.getCurrentMainCellCoordinates().y))){
            /*if(getOwnerArea().enterAreaCells(this, getEnteringCells()))
                getOwnerArea().leaveAreaCells(this, getLeavingCells());*/
            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance));

        } else{
            resetMotion();
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        return new Vector(getOrientation().toVector().x * this.framesForCurrentMove,
                            getOrientation().toVector().y * this.framesForCurrentMove) ;
    }
}
