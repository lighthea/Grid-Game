package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(Apple apple){
    }
    default void interactWith(Door door){
    }
    default void interactWith(EnigmeBehaviour.EnigmeCell cell){
    }
    default void interactWith(EnigmePlayer player) {
    }
}
