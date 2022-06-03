package ir_system;
import java.util.ArrayList;
import java.util.Collections;
public class Positions {
    public int Doc_ID;
    public ArrayList<Integer> Positions;
    public int Positional_Index_Skip_Pointer;

    public Positions() {
        Positions = new ArrayList<Integer>();
    }
    
    public void Add_Position(Integer pos) {
        this.Positions.add(pos);
        Collections.sort(Positions);
        Positional_Index_Skip_Pointer = Positions.size();
    }
    public double getTf_Weight() {
        return Math.log10(1 + this.Positions.size());
    }
    public ArrayList<Integer> get() {
        return this.Positions;
    }
}

