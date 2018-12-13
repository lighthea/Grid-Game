package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class bossFinal extends EnigmeArea {
    private pressureSwitch p1, p2, p3, p4, p5;
    private spider boss;
    public String getTitle() {
        return "bossFinal";
    }
    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return this.spawnPoint;
    }
    public final DiscreteCoordinates spawnPoint = new DiscreteCoordinates(16, 16);
    private int scaleFactor = 15;
    public void setViewCenter(Vector viewCenter) {
        this.viewCenter = viewCenter;
    }
    @Override
    public float getCameraScaleFactor() {
        return scaleFactor;
    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
        if (p1.is(p2, 1) && p1.is(p2, 1)&& p1.is(p5, 1)&& p1.is(p4, 1)&& p1.is(p3, 1))
            this.unregisterActor(boss);
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem)  {
        try {
            XMLTexts.initialize(fileSystem,"strings/enigme_fr" );
            super.begin(window, fileSystem);

            this.registerActor(new EnigmeAI(this,Orientation.UP, new DiscreteCoordinates(10,72), 10,
                    false, 2000, new DiscreteCoordinates(10, 71), new DiscreteCoordinates(10, 70),
                    new DiscreteCoordinates(10,69), new DiscreteCoordinates(9,69), new DiscreteCoordinates(9, 68),
                    new DiscreteCoordinates(9,67), new DiscreteCoordinates(9, 68), new DiscreteCoordinates(9,69),
                    new DiscreteCoordinates(10,69), new DiscreteCoordinates(10, 70), new DiscreteCoordinates(10, 71),
                    new DiscreteCoordinates(10, 72), new DiscreteCoordinates(10, 73), new DiscreteCoordinates(10, 74),
                    new DiscreteCoordinates(11, 74), new DiscreteCoordinates(10, 74),new DiscreteCoordinates(10, 73),
                    new DiscreteCoordinates(10, 72)));

            this.registerActor(new EnigmeAI(this,Orientation.UP, new DiscreteCoordinates(22,74), 10,
                    false, 2000, new DiscreteCoordinates(22, 73), new DiscreteCoordinates(22, 72),
                    new DiscreteCoordinates(23,72), new DiscreteCoordinates(23,71), new DiscreteCoordinates(24, 71),
                    new DiscreteCoordinates(24,70), new DiscreteCoordinates(24, 69), new DiscreteCoordinates(23,69),
                    new DiscreteCoordinates(23,68), new DiscreteCoordinates(22, 68), new DiscreteCoordinates(21, 68),
                    new DiscreteCoordinates(20, 68), new DiscreteCoordinates(21, 68), new DiscreteCoordinates(22, 68),
                    new DiscreteCoordinates(23,68), new DiscreteCoordinates(23,69), new DiscreteCoordinates(24, 69),
                    new DiscreteCoordinates(24,70), new DiscreteCoordinates(24, 71),new DiscreteCoordinates(23,71),
                    new DiscreteCoordinates(23,72), new DiscreteCoordinates(22, 72)));

            this.registerActor(new EnigmeAI(this,Orientation.UP, new DiscreteCoordinates(17,22), 10,
                    false, 1000, new DiscreteCoordinates(17, 23), new DiscreteCoordinates(17, 24),
                    new DiscreteCoordinates(18,24), new DiscreteCoordinates(18,25), new DiscreteCoordinates(18, 26),
                    new DiscreteCoordinates(18,27), new DiscreteCoordinates(18, 26), new DiscreteCoordinates(18,25),
                    new DiscreteCoordinates(18,24), new DiscreteCoordinates(17, 24)));

            this.registerActor(new EnigmeAI(this,Orientation.DOWN, new DiscreteCoordinates(4,48), 10,
                    false, 100, new DiscreteCoordinates(4, 47), new DiscreteCoordinates(4, 46),
                    new DiscreteCoordinates(5,46), new DiscreteCoordinates(6,46), new DiscreteCoordinates(7, 46),
                    new DiscreteCoordinates(8,46), new DiscreteCoordinates(8, 47), new DiscreteCoordinates(7,47),
                    new DiscreteCoordinates(6,47), new DiscreteCoordinates(5, 47)));

            this.registerActor(new EnigmeAI(this,Orientation.UP, new DiscreteCoordinates(7,46), 10,
                    false, 100,
                    new DiscreteCoordinates(8,46), new DiscreteCoordinates(8, 47), new DiscreteCoordinates(7,47),
                    new DiscreteCoordinates(6,47), new DiscreteCoordinates(5, 47), new DiscreteCoordinates(4, 47)
                    , new DiscreteCoordinates(4, 46), new DiscreteCoordinates(5,46),new DiscreteCoordinates(6,46)
                    , new DiscreteCoordinates(7, 46)));


            this.registerActor(new patapon(this, Orientation.DOWN, new DiscreteCoordinates(16, 35), 0,
                    true, 0, new String[]{"Seuls les braves passent... Ah et peut être les élèves qui ne passent pas leur temps sur leur projet..."},false ));

            boss = (new spider(this, Orientation.DOWN, new DiscreteCoordinates(17,76), 100, false, 0,
                     new DiscreteCoordinates(17,75), new DiscreteCoordinates(17,74), new DiscreteCoordinates(16,74)
                    , new DiscreteCoordinates(16,73), new DiscreteCoordinates(16,72), new DiscreteCoordinates(15,72), new DiscreteCoordinates(15,71)
                    , new DiscreteCoordinates(15,70), new DiscreteCoordinates(16,70), new DiscreteCoordinates(17,70), new DiscreteCoordinates(18,70)
                    , new DiscreteCoordinates(18,71), new DiscreteCoordinates(18,72), new DiscreteCoordinates(18,73), new DiscreteCoordinates(18,74)
                    , new DiscreteCoordinates(18,75)
                    ));
            this.registerActor(boss);
            p1 =(new pressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(8,68)));
            p2 =(new pressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(11,74)));
            p3 = (new pressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(22,72)));
            p4 = (new pressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(21,67)));
            p5 = (new pressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(16,71)));
            this.registerActor(p1);
            this.registerActor(p2);
            this.registerActor(p3);
            this.registerActor(p4);
            this.registerActor(p5);
            return true;

        } catch (Exception E){

            System.out.println("Error  : "+ E);
            return false;

        }

    }
}