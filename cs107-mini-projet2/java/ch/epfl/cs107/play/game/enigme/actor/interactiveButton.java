package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class interactiveButton extends Door {
    /**
     * Default AreaEntity constructor
     *
     * @param area           (Area): Owner area. Not null
     * @param destination
     * @param tpCoordiinate
     * @param orientation    (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position       (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * @param otherPositions
     */
    public interactiveButton(Area area, String destination, DiscreteCoordinates tpCoordiinate, Orientation orientation, DiscreteCoordinates position, List<DiscreteCoordinates> otherPositions) {
        super(area, destination, tpCoordiinate, orientation, position, otherPositions);
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
    public boolean takeCellSpace() {
        return true;
    }
    @Override
    public void draw(Canvas canvas){ return; }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((Door)this);
    }
}
