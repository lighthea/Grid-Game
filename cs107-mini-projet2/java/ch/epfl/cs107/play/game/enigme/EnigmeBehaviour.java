package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehaviour extends AreaBehavior {

    /**
     * Default AreaBehavior Constructor
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public EnigmeBehaviour(Window window, String fileName) {
        super(window, fileName);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new EnigmeCell(i, j);
            }
        }

    }

    public int getCellNature (DiscreteCoordinates coordinates){
        return ((EnigmeCell)coordToCell(coordinates)).getNature().type;
    }

    public enum EnigmeCellType {
        NULL(0),
        WALL(-16777216), // RGB code of black
        WATER(-16776961), // RGB code of blue
        INDOOR_WALKABLE(-1),
        DOOR(-65536), // RGB code of red
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        static EnigmeCellType toType(int type) {
            switch (type) {
                case -16777216:
                    return WALL;
                case -65536:
                    return DOOR;
                case -2:
                    return NULL;//Because the linter was mad about copy pasting...
                case -1:
                    return INDOOR_WALKABLE;
                case -14112955:
                    return OUTDOOR_WALKABLE;
                case -16776961:
                    return WATER;
                default:
                    return NULL;
            }
        }

        EnigmeCellType(int type) {
            this.type = type;
        }
    }
    /// The player is a concept of RPG games
    // TODO implements me #PROJECT

    public class EnigmeCell extends AreaBehavior.Cell {

        private EnigmeCellType nature;
        public EnigmeCellType getNature(){
            return nature;
        }

        public EnigmeCell(int x, int y) {
            super(x, y);
            this.nature = EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - x, y));
        }

        @Override
        public boolean takeCellSpace() {
            return true;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public boolean canLeave (Interactable entity) { return true;}
        @Override
        public boolean canEnter (Interactable entity) {
            if (super.canEnter(entity)) {
                if ((this.nature.type == -16777216 || this.nature.type == -16776961 || this.nature.type == 0)) {

                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }
    }

}

