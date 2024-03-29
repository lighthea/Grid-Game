package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmeNPC;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {
    private boolean inPause;
    // Context objects
    private Window window;
    private FileSystem fileSystem;
    // List of Actors inside the area
    private List<Actor> actors;
    private List<Interactor> interactors;

    private List<Actor> registeredActors;
    private List<Actor> unregisteredActors;

    // Camera Parameter
    // actor on which the view is centered
    private Actor viewCandidate;

    private Map<Interactable , List <DiscreteCoordinates >> interactablesToEnter ;
    private Map<Interactable , List <DiscreteCoordinates >> interactablesToLeave ;

    // effective center of the view
    protected Vector viewCenter;

    private Transform viewTransform;

    public AreaBehavior getAreaBehavior() {
        return areaBehavior;
    }

    private AreaBehavior areaBehavior;

    private boolean isUsed = false;

    public boolean isUsed() {
        return isUsed;
    }

    public void setAreaBehavior(AreaBehavior areaBehavior) {
        this.areaBehavior = areaBehavior;
    }



    /** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
        // Here decisions at the area level to decide if an actor
        // must be added or not

        try {
            if(a instanceof Interactable)
                enterAreaCells(((Interactable)a), ((Interactable) a).getCurrentCells()) ;

            if(a instanceof  Interactor){
             this.interactors.add((Interactor)a);
            }
            this.actors.add(a);

        } catch (Exception E){
            if(!forced) {
                System.out.println("Error : "+ E);
                System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
                removeActor(a, true);
            }
            else {
                addActor(a, true);
            }
        }

    }


    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced) {
        Actor tempActor = a;
        if (!forced /*&& !vetoFromGrid()*/) {
            try {
                if(a instanceof Interactable)
                    leaveAreaCells(((Interactable)a), ((Interactable) a).getCurrentCells()) ;
                if(a instanceof  Interactor){
                    this.interactors.remove(a);
                }
                this.actors.remove(a);
            } catch (Exception E) {
            if (!forced)
            {
                System.out.println("Error : " + E);
                System.out.println("Actor " + a + " cannot be completely deleted, so nothing happened");

                this.actors.set(this.actors.indexOf(a), tempActor);
            }
            else{
                removeActor(a, true);
            }
        }
    }
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
        if (a != null)
            try {
                this.registeredActors.add(a);
                return true;
            } catch (Exception E){
                System.out.println("Error cant add actor : " + E + a.toString());
                return false;
            }
        else
            return false;
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
        try {
            this.unregisteredActors.add(a);
            return true;
        } catch (Exception E){
            System.out.println("Error cant remove actor : " + E);
            return false;
        }
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        return this.areaBehavior.getCells().length;
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        return this.areaBehavior.getCells()[0].length;
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        return this.window.getKeyboard();
    }

    /// Area implements Playable

    public boolean isInPause() {
        return inPause;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        this.actors = new LinkedList<>();
        this.registeredActors = new LinkedList<>();
        this.unregisteredActors = new LinkedList<>();
        this.window = window;
        this.fileSystem = fileSystem;

        this.viewCandidate = null;
        this.viewCenter = Vector.ZERO;

        this.viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        window.setRelativeTransform(viewTransform);

        this.interactablesToEnter = new HashMap<>();
        this.interactablesToLeave = new HashMap<>();
        this.interactors = new LinkedList<>();

        this.isUsed = true;
        this.inPause = false;
        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){

        inPause = false;
        return true;
    }

    @Override
    public void update(float deltaTime) {
        if (! inPause) {
            purgeRegistration();
            if (this.actors != null){
                this.actors.parallelStream().forEach((i) -> i.update(deltaTime));
                this.actors.forEach((i) -> i.draw(window));

            }
            if (interactors != null)
            {
                for (Interactor interactor :
                        interactors) {
                    if (interactor.wantsCellInteraction())
                        this.getAreaBehavior().cellInteractionOf(interactor);
                    if (interactor.wantsViewInteraction())
                        this.getAreaBehavior().viewInteractionOf(interactor);
                }
            }
            this.updateCamera();
        } else {
            if (this.actors != null) {
                this.actors.forEach((i) -> {
                    i.draw(window);
                    if (getKeyboard().get(Keyboard.ENTER).isPressed()){
                        if (i instanceof EnigmeNPC)
                            ((EnigmeNPC) i).setInteract(false);
                        resume(window, fileSystem);
                    }
                });
            }
        }

    }
    public final boolean leaveAreaCells(Interactable entity ,List <DiscreteCoordinates > coordinates){
        if (this.areaBehavior.canLeave(entity, coordinates)){
            this.interactablesToLeave.put(entity, coordinates);
            return true;}
        return false;
    }
    public final boolean enterAreaCells(Interactable entity ,List <DiscreteCoordinates > coordinates){
        if (this.areaBehavior.canEnter(entity, coordinates)){
            this.interactablesToEnter.put(entity, coordinates);
            return true;
        }
        return false;
    }
    private void purgeRegistration() {
        if (this.registeredActors != null){
            this.registeredActors.forEach((i) -> this.addActor(i, false));
            this.registeredActors.clear();
            }

            if (this.unregisteredActors != null){
                this.unregisteredActors.forEach((i) -> this.removeActor(i, false));
                this.unregisteredActors.clear();
            }

            if (this.interactablesToLeave != null){
                for (Interactable key : this.interactablesToLeave.keySet()) {
                        this.areaBehavior.leave(key, this.interactablesToLeave.get(key));
                }
                this.interactablesToLeave.clear();
            }

            if (this.interactablesToEnter != null){
                for (Interactable key : this.interactablesToEnter.keySet()) {
                    this.areaBehavior.enter(key, this.interactablesToEnter.get(key));
                }
                this.interactablesToEnter.clear();
            }


    }

    public boolean vetoFromGrid(Interactable entity , List <DiscreteCoordinates> coordinates){
        return !(this.areaBehavior.canLeave(entity, entity.getCurrentCells()) && this.areaBehavior.canEnter(entity, coordinates));
    }

    private void updateCamera () {
        if (this.viewCandidate != null) {
            this.viewCenter = this.viewCandidate.getPosition();
        }
        this.viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        inPause = true;
        purgeRegistration();

    }

    public final void setViewCandidate(Actor a){ this.viewCandidate = a; }

    @Override
    public void end() {
            this.actors.forEach((i) -> {
                try {
                    fileSystem.write(i.toString() + " " + i.getPosition());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}

