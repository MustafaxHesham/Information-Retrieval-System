package ir_system;
import java.io.*;
import java.util.*;
public class IR_System {
    
    public final static int CollectionNumber = 10;
    public static LinkedHashMap<String, Term> Tokens = new LinkedHashMap<String, Term>();
    public static Document[] docs = new Document[CollectionNumber];

    public void Similarity(String Query, Document[] docs) {
        
    }
    
    public void DisplayTf_IDF_Matrix() {
        for (String ter : Tokens.keySet()) {
            System.out.println("=========================================");
            Term term = Tokens.get(ter);
            System.out.println("Term : " + term.Term);
            for (int i = 0; i < 10 ;i++) {
                System.out.println("Doc_ID : " + (i+1) + "  Tf.IDF:  " + term.GetTF_IDF(i + 1));
            }
            System.out.println("=========================================");            
        }
    }
    public void DisplayTermAuxiliaryStructure(String Term) {
        Term term = Tokens.get(Term);
        System.out.println("<" +term.Term + " : " + term.Get_DF() + ";");
        for (Integer Key : term.Positional_Index.keySet())
        {
            System.out.print("doc_id:" +Key + "  ");
            Positions pos = term.Positional_Index.get(Key);
            for (Integer Positions : pos.Positions)
                            System.out.println(Positions + ",");
            
        }
                    System.out.println(">");
    }
    public class QueryToken {
        int tf = 1;
        String Token;
        int Positions;
        Set<Integer> Including_Docs;
        public QueryToken(int p, String s)
        {
            Token = s;
            Positions = p;
        }
    }
    
    public void QueryProcess(String Query) {
        String[] QueryTokens = Query.split(" ");
        int index = 1;
        ArrayList<QueryToken> qt = new ArrayList<QueryToken>();
        LinkedHashMap<QueryToken, Positions> positionsInDocs = new LinkedHashMap<QueryToken, Positions>();
        for (String token : QueryTokens) {
            token = token.replace(".", "");
            token = token.toLowerCase();
                if (token.equals("to") || token.equals("in") || token.equals("be")
                || token.equals("am") || token.equals("i") || token.equals("where") )
                        continue;
                else {
                    qt.add(new QueryToken(index, token));
                }
                index++;
        }

        for (QueryToken key : qt) {
            try {
                key.Including_Docs = Tokens.get(key.Token).Docs_ID();                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        ArrayList<Integer> tempArr = new ArrayList<Integer>();
        for (QueryToken key : qt) {
            for (Integer pos : key.Including_Docs) {
                tempArr.add(pos);
            }
        }
        
        ArrayList<Integer> Result = new ArrayList<Integer>();
        
        for (int i = 0; i < tempArr.size() ;i++) {
            int RedundacyTimes = 0;
            for (int j = i; j < tempArr.size() - 1;j++) {
                    if (tempArr.get(i) == tempArr.get(j))
                        RedundacyTimes++;
            }
            if (RedundacyTimes == qt.size() && RedundacyTimes > 0)
                Result.add(tempArr.get(i));
        }

        if (Result.size() > 0) {
            for (Integer doc_id : Result) {
                double QueryLength = Math.log10(1 + (index - 1));
                System.out.println("doc_ID : " + doc_id);
            }
        }
        else {
            System.out.println("Sorry Query Does Not Match Any Document");
        }
            
        //Loop Which Will Return The Result For Positional Index
        ArrayList<Document> Final_Result = new ArrayList<Document>();
        
        
        /*for (QueryToken token : qt) {
                positionsInDocs.put(token , new Positions());
                for (Integer document_id : Result) {
                    try {
                        Term term = Tokens.get(token);
                        if (term != null){
                            Positions pos = term.Get_Positional_For_Doc(document_id); 
                            positionsInDocs.replace(token, pos);
                        }
                    }   catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }*/
    }
    
   
    public static void main(String[] args) {
        for (int i = 0;i < CollectionNumber;i++)
            docs[i] = new Document();
           
        //Document 1
        docs[0].Doc_ID = 1;
        docs[0].Doc_Name = "File 1";
        docs[0].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\1.txt");
        
        //Document 2
        docs[1].Doc_ID = 2;
        docs[1].Doc_Name = "File 2";
        docs[1].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\2.txt");
        
        //Document 3
        docs[2].Doc_ID = 3;
        docs[2].Doc_Name = "File 3";
        docs[2].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\3.txt");
        
        //Document 4
        docs[3].Doc_ID = 4;
        docs[3].Doc_Name = "File 4";
        docs[3].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\4.txt");
        
        //Document 5
        docs[4].Doc_ID = 5;
        docs[4].Doc_Name = "File 5";
        docs[4].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\5.txt");
        
        //Document 6
        docs[5].Doc_ID = 6;
        docs[5].Doc_Name = "File 6";
        docs[5].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\6.txt");

        //Document 7
        docs[6].Doc_ID = 7;
        docs[6].Doc_Name = "File 7";
        docs[6].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\7.txt");

        //Document 8
        docs[7].Doc_ID = 8;
        docs[7].Doc_Name = "File 8";
        docs[7].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\8.txt");

        //Document 9
        docs[8].Doc_ID = 9;
        docs[8].Doc_Name = "File 9";
        docs[8].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\9.txt");

        //Document 10
        docs[9].Doc_ID = 10;
        docs[9].Doc_Name = "File 10";
        docs[9].FILE_Path = new File
        ("D:\\My Projects\\Faculty Projects\\IR_System\\IR_System\\src\\ir_system\\Collection\\10.txt");
        
        for (Document file : docs) {
            String doc_content = "";
            try {
                Scanner reader = new Scanner(file.FILE_Path);
                while(reader.hasNextLine())
                    doc_content += reader.nextLine();              
                String[] CurrentFile_OldTokens = doc_content.split(" ");
                int index = 1;
                //Remove dots which appear in end of sentences.
                for (String token : CurrentFile_OldTokens) {
                        token = token.replace(".", "");
                        token = token.toLowerCase(); 
                        Term term;

                        //Remove some of stop words
                        if (token.equals("to") || token.equals("in") || token.equals("be")
                                || token.equals("am") || token.equals("i") || token.equals("where") )
                            continue;
                        else if(!Tokens.containsKey(token) && (token != "")) {
                            if (token == "fools")
                                token = "fool";
                            term = new Term(token);
                            term.InsertPosition(file.Doc_ID , index);
                            Tokens.put(token, term);
                        }

                        else {
                            term = Tokens.get(token);
                            if (term != null) {
                                term.InsertPosition(file.Doc_ID, index);
                                Tokens.replace(token, term);
                            }
                        }
                        index++;
                        file.Terms.add(term);
                }                    

            } catch(Exception e) {
                e.printStackTrace();
            }
        } 

       
        Scanner QueryReader = new Scanner(System.in);
        System.out.println("Enter Input Type\na for tf.idf matrix\nb display aux structure\nc for query");
        
        Scanner inputType = new Scanner(System.in);
        String type = inputType.next();
        switch (type) {
            case "a":
            new IR_System().DisplayTf_IDF_Matrix();
            break;
            
            case "b":
            Scanner scan = new Scanner(System.in);
            String term = scan.next();
            new IR_System().DisplayTermAuxiliaryStructure(term);
            break;
            
            case "c":
            Scanner scan1 = new Scanner(System.in);
            String Query = scan1.nextLine();
            new IR_System().QueryProcess(Query);
            
        }
    }    
}
