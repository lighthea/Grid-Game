package ch.epfl.cs107.play.signal.logic;

public class MultipleAnd extends LogicSignal {
private Logic[] s;
private boolean tmp = true;
MultipleAnd(Logic ... s){
    this.s = s;
}


public boolean isOn(){
    boolean tmp = true;
    for(Logic values:s){
        if(values == null) tmp = false;
        else tmp = tmp && values.isOn();

    }
    return tmp;
    }
}
