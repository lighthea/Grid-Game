package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {

   private Logic s;
   Not(Logic S){
       this.s = S;
   }
   @Override
    public boolean isOn(){
       if(s != null && !s.isOn()){
           return true;
       }
       return false;
    }
}
