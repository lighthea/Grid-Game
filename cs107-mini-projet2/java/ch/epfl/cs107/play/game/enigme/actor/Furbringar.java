package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Furbringar extends EnigmeNPC {
    public Door getDoor() {
        return door;
    }

    private Door door;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param damages     Here equals to 0
     * @param fixed       Decides whether the actor is fixed or movable
     * @param coolDown    the cooldown between two interactions
     * @param dialogText
     * @param active
     * @param path        the path to follow
     */
    public Furbringar(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed, int coolDown, String[] dialogText, boolean active, DiscreteCoordinates... path) {
        super(area, orientation, position, damages, fixed, coolDown, dialogText, active, path);
        initialHeigth = .55f;
        initialWidth = .55f;

        this.sprite = new Sprite("old.man.1", 1f, 1f, this);
        Vector anchor =new Vector(1/6f, 1/3f);

        this.animation = new Animation(this.sprite, anchor, 4, 4, this,3f , 3f);
        sprite = animation.getAnimation()[0][0];
        door = new Door(this.getOwnerArea(), this.getOwnerArea().getTitle(), new DiscreteCoordinates(79,16), Orientation.DOWN, this.getCurrentMainCellCoordinates(), this.getCurrentCells());
        this.handler = new EnigmeFURHandler();
    }

    @Override
    public void interactWith(Interactable other) {
        if (isActive)
            ((EnigmeFURHandler)handler).interactWith(other);
    }
    private class EnigmeFURHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(EnigmePlayer player) {
            utilisationCount++;
            setInteract(!isInteract());
            if (utilisationCount % getDialog().length == 0) {
                System.out.println("aaaaaaaa");
                player.setPassingDoor(true);
                player.setLastDoor(door);
            }
        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
        @Override
        public void interactWith(EnigmeNPC npc) {

        }

    }

}