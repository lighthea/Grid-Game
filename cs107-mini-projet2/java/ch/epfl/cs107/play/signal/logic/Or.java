package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public class Or extends LogicSignal {

    private Logic s;
    private Logic t;
    Or(Logic a, Logic b){
        this.s = a;
        this.t = b;
    }
    @Override
    public boolean isOn(){
        if((t!=null && s!= null) && ((s.isOn()) || (t.isOn()))){
            return true;
        }
        return false;
    }
}
