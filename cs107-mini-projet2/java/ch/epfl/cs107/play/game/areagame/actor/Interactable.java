package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {

    boolean takeCellSpace();
    boolean isViewInteractable();
    boolean isCellInteractable();
    public List<DiscreteCoordinates> getCurrentCells();
    void acceptInteraction(AreaInteractionVisitor v) ;
}
