package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Door extends AreaEntity {

    public String getDestinationArea() {
        return DestinationArea;
    }

    public DiscreteCoordinates getLandingCoordinates() {
        return landingCoordinates;
    }

    private String DestinationArea;
    private DiscreteCoordinates landingCoordinates;
    private List<DiscreteCoordinates> currentCells;

    private Sprite opened;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, String destination, DiscreteCoordinates tpCoordiinate, Orientation orientation, DiscreteCoordinates position, List<DiscreteCoordinates> otherPositions) {
        super(area, orientation, position);
        this.DestinationArea = destination;
        this.landingCoordinates = tpCoordiinate;
        currentCells = otherPositions;
        this.opened = new Sprite("door.open.1", 1,1, this);
    }

    @Override
    public void draw(Canvas canvas) {
        this.opened.draw(canvas);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currentCells;
    }
}
