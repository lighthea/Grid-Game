package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.*;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {

    private final Image behaviorMap;
    private final int width, height;

    protected final Cell[][] cells;

    public Image getBehaviorMap() {
        return behaviorMap;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Cell[][] getCells() {
        return cells;
    }

    public boolean canLeave(Interactable entity, List<DiscreteCoordinates>coordinates){
        boolean b = true;
        for (int i = 0; i < coordinates.size(); ++i)
        {
            b = b && coordinates.get(i).x < getWidth() && coordinates.get(i).y < getHeight();
        }
        return b;
    }
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates>coordinates){

        boolean b = true;
        for (int i = 0; i < entity.getCurrentCells().size(); ++i)
        {
            b = b && coordinates.get(i).x < getWidth() && coordinates.get(i).y < getHeight();
        }
        return b;
    }

    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates)
                coordToCell(coord).addInteractable(entity);
    }

    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates){

        for (DiscreteCoordinates coord : coordinates)
            coordToCell(coord).removeInteractable(entity);
    }
    public Cell coordToCell (DiscreteCoordinates coord){
        return this.cells[coord.x][coord.y];
    }
    /**
     * Default AreaBehavior Constructor
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName) {
        this.behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        this.width = behaviorMap.getWidth();
        this.height =  behaviorMap.getHeight();
        this.cells = new Cell[width][height];
    }

    public static abstract class Cell implements Interactable {
        public Set<Interactable> getInteractablesList() {
            return interactableList;
        }

        private Set<Interactable> interactableList;
        private DiscreteCoordinates coordinates;
        public void addInteractable (Interactable i){
            interactableList.add(i);
        }
        public void removeInteractable (Interactable i){
            interactableList.remove(i);
        }
        public DiscreteCoordinates getCoordinates() {
            return coordinates;
        }

        public boolean canLeave(Interactable entity){
            return true;
        }

        public boolean canEnter(Interactable entity){
            return true;
        }

        public Cell (int x, int y) {

            this.coordinates = new DiscreteCoordinates(x, y);
            this.interactableList = new HashSet<>();
        }
        public Cell (DiscreteCoordinates d) {
            this.coordinates = d;
        }

        @Override
        public List<DiscreteCoordinates> getCurrentCells(){

            return new ArrayList<>(Arrays.asList(this.getCoordinates()));
        }
    }
}