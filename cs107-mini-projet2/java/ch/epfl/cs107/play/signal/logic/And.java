package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public class And extends LogicSignal {

    private Logic s;
    private Logic t;
    And(Logic a, Logic b){
        this.s = a;
        this.t = b;
    }
    @Override
    public boolean isOn(){
        if((s != null && s.isOn())&& (t != null && t.isOn())){
            return true;
        }
        return false;
    }
}
