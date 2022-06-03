package ir_system;
import java.util.*;
public class Term {
    
        public final static int CollectionNumber = 10;        
        public String Term;
        public int Term_Frequency;
        public LinkedHashMap<Integer, Positions> Positional_Index;
        
        public void InsertPosition(int doc_id, int pos) {
            Positions position;
            if (!Positional_Index.containsKey(doc_id)) {
                position = new Positions();
                Positional_Index.put(doc_id, position);
            }
            
            position = Positional_Index.get(doc_id);
            if (position != null) {
                position.Doc_ID = doc_id;
                position.Add_Position(pos);
                Positional_Index.replace(doc_id, position);
            }
        }
        public Term(String term) {
            this.Term = term;
            Positional_Index = new LinkedHashMap<Integer, Positions>();
        }
        public double Get_IDF() {
            return Math.log10(CollectionNumber/ this.Positional_Index.size());
        }
        public double GetTF_IDF(int doc_id) {
            if (!this.Positional_Index.isEmpty()) {
                Positions position = Positional_Index.get(doc_id);
                if (position != null)
                    return Math.log10(1 + position.Positions.size()) * this.Get_IDF();                
                else
                    return 0;
            }
            return 0;
        }
        public int Get_DF() {
            return this.Positional_Index.size();
        }
        
        public Set<Integer> Docs_ID() {
            return this.Positional_Index.keySet();
        }
        
        public Positions Get_Positional_For_Doc(int doc_id) {
            return this.Positional_Index.get(doc_id);
        } 
        
}
