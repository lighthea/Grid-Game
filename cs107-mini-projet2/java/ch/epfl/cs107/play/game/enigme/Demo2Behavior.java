package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.area.Demo2.Demo2Player;
import ch.epfl.cs107.play.window.Window;
public class Demo2Behavior extends AreaBehavior {
    /**
     * Default AreaBehavior Constructor
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public Demo2Behavior(Window window, String fileName) {

        super(window, fileName);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[j][i] = new Demo2Cell(i, j, Demo2CellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - i, j)));
            }
        }
    }
    public enum Demo2CellType {
        NULL(0),
        WALL(-16777216), // RGB code of black
        DOOR(-65536), // RGB code of red
        WATER(-16776961), // RGB code of blue
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        static Demo2CellType toType(int type) {
            switch (type) {
                case -16777216:
                    return WALL;
                case -65536:
                    return DOOR;
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

        Demo2CellType(int type) {
            this.type = type;
        }
    }
    /// The player is a concept of RPG games
    // TODO implements me #PROJECT

    public class Demo2Cell extends AreaBehavior.Cell {

        private Demo2CellType nature;
        public Demo2CellType getNature(){
            return nature;
        }

        public Demo2Cell(int x, int y, Demo2CellType type) {
            super(x, y);
            this.nature = type;
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
            if ((this.nature.type == -16777216 || this.nature.type == -16776961 || this.nature.type == 0 )&& !super.canEnter(entity)) {
                System.out.println(Demo2CellType.toType(nature.type).toString());
                return false;
            } else if (this.nature.type == -65536){
                if (entity instanceof Demo2Player) {
                    ((Demo2Player) entity).setThroughDoor(true);
                    return true;
                }
                return false;
            }
            System.out.println(Demo2CellType.toType(nature.type).toString());
            return true;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {

        }
    }
}
