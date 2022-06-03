package ir_system;

import java.io.*;
import java.util.ArrayList;

public class Document {
    public int Doc_ID;
    public String Doc_Name;
    public File FILE_Path;
    public ArrayList<Term> Terms;
    public Document() {
        Terms = new ArrayList<Term>();
    }
    public double GetDocLength() {
        double Doc_Length = 0;
        for (Term term : Terms)
            Doc_Length += term.GetTF_IDF(Doc_ID);
        return Math.sqrt(Doc_Length);
    }
}
