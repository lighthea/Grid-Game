package ch.epfl.cs107.play.signal.logic;

public class LogicSignal implements Logic {


    @Override
    public boolean isOn() {
        return false;
    }

    @Override
    final public float getIntensity() {
        if(isOn()) return 1.0f;
        return 0.0f;
    }

    @Override
    final public float getIntensity(float t) {
        return getIntensity();
    }
}
