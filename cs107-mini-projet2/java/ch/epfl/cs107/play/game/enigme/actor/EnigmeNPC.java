package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehaviour;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class EnigmeNPC extends EnigmeAI {
    protected final boolean isActive;

    public Dialog[] getDialog() {
        return dialog;
    }

    private Dialog[] dialog;

    public void setUtilisationCount(int utilisationCount) {
        this.utilisationCount = utilisationCount;
    }

    public int getUtilisationCount() {
        return utilisationCount;
    }

    protected int utilisationCount;

    public void setInteract(boolean interact) {
        this.interact = interact;
    }

    public boolean isInteract() {
        return interact;
    }

    private boolean interact;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param damages   Here equals to 0
     * @param fixed     Decides whether the actor is fixed or movable
     * @param coolDown  the cooldown between two interactions
     * @param path      the path to follow
     */
    public EnigmeNPC(Area area, Orientation orientation, DiscreteCoordinates position, float damages, boolean fixed ,
                     int coolDown, String[] dialogText, boolean active,DiscreteCoordinates... path) {
        super(area, orientation, position, damages, fixed, coolDown, path);
        this.damages = 0;
        this.coolDown = 0;
        handler = new EnigmeNPCHandler();
        dialog = new Dialog[dialogText.length];
        for (int i = 0; i < dialogText.length ; i++){
            dialog[i] = new Dialog(dialogText[i], "dialog.2", this.getOwnerArea());
        }

        this.isActive = active;
        this.interact = false;
        this.utilisationCount = 0;
    }

    @Override
    public void interactWith(Interactable other) {
        if (isActive)
            super.interactWith(other);
    }
    @Override
    public void draw (Canvas canvas){
        super.draw(canvas);
        if (interact){

            dialog[(utilisationCount - 1)%dialog.length].draw(canvas);
            this.getOwnerArea().suspend();
        }
    }
    private class EnigmeNPCHandler implements EnigmeInteractionVisitor, AreaInteractionVisitor {

        @Override
        public void interactWith(EnigmePlayer player) {
            utilisationCount++;
            interact = !interact;

        }
        @Override
        public void interactWith(EnigmeBehaviour.EnigmeCell cell) {

        }
        @Override
        public void interactWith(EnigmeAI ai) {

        }
        @Override
        public void interactWith(EnigmeNPC npc) {

        }

    }
}
