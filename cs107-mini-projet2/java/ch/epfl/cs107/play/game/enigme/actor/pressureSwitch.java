package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class pressureSwitch extends AreaEntity implements Interactable, Signal {
    private float intensity;
    private Sprite sprite;
    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public pressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        sprite = new Sprite("GroundLightOff", 1, 1,this);
        intensity = 0;

    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean uniqueInteractable(){
        return true;
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
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        if (intensity == 1) intensity = 0;
        else intensity = 1;
        v.interactWith(this);

    }


    @Override
    public void draw(Canvas canvas) {
        if (is(this, 1)) sprite = new Sprite("GroundLightOn", 1, 1,this);
        else sprite = new Sprite("GroundLightOff", 1, 1,this);
        sprite.draw(canvas);
    }

    @Override
    public float getIntensity(float t) {
        return intensity;
    }

    @Override
    public boolean is(Signal other, float t) {
        return intensity == t && other.getIntensity(t) == t;
    }
}