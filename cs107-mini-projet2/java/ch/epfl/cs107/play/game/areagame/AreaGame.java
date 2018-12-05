package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import java.util.HashMap;
import java.util.Map;


/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area
 * An AreaGame has multiple Areas
 */
abstract public class AreaGame implements Game {

    private Window window;
    private FileSystem fileSystem;
    protected Map<String, Area> areas;

    public Area getCurrentArea() {
        return currentArea;
    }

    private Area currentArea;

    /**
     * Add an Area to the AreaGame list
     * @param a (Area): The area to add, not null
     */
    protected final void addArea(Area a){
        areas.put(a.getTitle(), a);
    }

    /**
     * Setter for the current area: Select an Area in the list from its key
     * - the area is then begin or resume depending if the area is already started or not and if it is forced
     * @param key (String): Key of the Area to select, not null
     * @param forceBegin (boolean): force the key area to call begin even if it was already started
     * @return (Area): after setting it, return the new current area
     */
    protected final Area setCurrentArea(String key, boolean forceBegin){
        assert this.areas.get(key) != null;

        if (this.currentArea != null)
            this.currentArea.suspend();

        this.currentArea = this.areas.get(key);

        if (forceBegin || !this.areas.get(key).isUsed())
            this.areas.get(key).begin(this.window, this.fileSystem);

        else
            this.areas.get(key).resume(this.window, this.fileSystem);

        return this.areas.get(key);
    }


    /**@return (Window) : the Graphic and Audio context*/
    protected final Window getWindow(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }

    /**@return (FIleSystem): the linked file system*/
    protected final FileSystem getFileSystem(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }


    /// AreaGame implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        this.areas = new HashMap<>();
        this.window = window;
        this.fileSystem = fileSystem;

        return true;
    }


    @Override
    public void update(float deltaTime) {
       this.currentArea.update(deltaTime);
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }

}
