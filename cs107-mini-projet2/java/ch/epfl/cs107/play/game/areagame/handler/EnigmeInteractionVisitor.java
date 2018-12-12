package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.Enigme;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.game.enigme.actor.*;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(Apple apple){
    }
    default void interactWith(Door door){
    }
    default void interactWith(EnigmeBehaviour.EnigmeCell cell){
    }
    default void interactWith(EnigmePlayer player) {
    }
    default void interactWith(Key key) {
    }
    default void interactWith(lever lever) {
    }
    default void interactWith(pressureSwitch pswitch) {
    }
    default void interactWith(pressurePlate pplate) {
    }
    default void interactWith(Torch torch) {
    }
    default void interactWith(EnigmeAI ai) {
    }
    default void interactWith(EnigmeNPC npc) {
    }
}
