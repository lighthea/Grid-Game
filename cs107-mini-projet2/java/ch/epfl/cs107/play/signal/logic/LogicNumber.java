package ch.epfl.cs107.play.signal.logic;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LogicNumber extends LogicSignal {

    private Logic[] e;
    private float nb;
    LogicNumber(float n, Logic ...s){
        this.e = s;
        this.nb = n;
    }
    @Override
    public boolean isOn(){
        return IntStream.range(0, e.length).mapToDouble(a -> Math.pow(2, a) * e[a].getIntensity()).sum() == nb;
    }
}
