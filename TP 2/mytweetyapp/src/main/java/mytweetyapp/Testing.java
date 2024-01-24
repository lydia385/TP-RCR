package mytweetyapp;

import java.io.IOException;
import java.util.ArrayList;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.ml.reasoner.SimpleMlReasoner;
import org.tweetyproject.logics.ml.syntax.MlBeliefSet;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;
import org.tweetyproject.logics.ml.parser.MlParser;

public class Testing {

    public static void main(String[] args) throws ParserException, IOException {

        // Suppose we have a computer vision scenario with objects (p and q) and their types.
        MlParser parser = new MlParser();
        FolSignature sig = new FolSignature();
        sig.add(new Predicate("type", 1));
        sig.add(new Predicate("p", 0));
        sig.add(new Predicate("q", 0));
        parser.setSignature(sig);

        MlBeliefSet w1 = parser.parseBeliefBase("type(p) \n type(q) \n" + "(p) \n" + "(q) \n");
        MlBeliefSet w2 = parser.parseBeliefBase("type(p) \n type(q) \n" + "(p) \n");
        MlBeliefSet w3 = parser.parseBeliefBase("type(p) \n type(q) \n" + "(p) \n" + "(q) \n");
        MlBeliefSet w4 = parser.parseBeliefBase("type(p) \n type(q) \n" + "(q) \n");
        MlBeliefSet w5 = parser.parseBeliefBase("type(p) \n type(q) \n" + "(p) \n");

        // Modal logic formulas related to computer vision
        SimpleMlReasoner reasoner = new SimpleMlReasoner();
        FolFormula f1 = (FolFormula) parser.parseFormula("p && q");
        FolFormula f2 = (FolFormula) parser.parseFormula("[](p)");
        FolFormula f3 = (FolFormula) parser.parseFormula("[](p => q)");
        FolFormula f4 = (FolFormula) parser.parseFormula("[](q && <>(!p))");

        // Relations between worlds
        MlBeliefSet[] Relation1 = { w2, w3, w4 };
        MlBeliefSet[] Relation2 = { w5 };
        MlBeliefSet[] Relation3 = { w4 };
        MlBeliefSet[] Relation5 = { w4 };

        // Example application of modal logic in computer vision scenario
        System.out.println("========================Computer Vision Example with Modal Logic=============================================");

        boolean correct = false;

        // Check if the formula is true in any accessible world from w1 (Relation1)
        for (MlBeliefSet w : Relation1) {
            if (reasoner.query(w, f1)) {
                correct = true;
            }
        }
        System.out.println("In the computer vision scenario, is it possible to observe both p and q in accessible world from w1? : " + correct);

        // Check if the formula is true in all accessible worlds from w2 (Relation2)
        correct = false;
        for (MlBeliefSet w : Relation2) {
            if (reasoner.query(w, f2)) {
                correct = true;
            }
        }
        System.out.println("In the computer vision scenario, is it always the case that p is observed in all accessible worlds from w2? : " + !correct);

        // Check a specific relation (w3 to w4) for the formula (p => q)
        System.out.println("In the computer vision scenario, does the observation of p always imply the observation of q for all word accesible from w3? : "
                + reasoner.query(w4, f3));

        // Check a specific relation (w4 to w4) for the formula (q && <>(!p))
        System.out.println("In the computer vision scenario, is it possible to observe q and eventually not observe p? : "
                + reasoner.query(w4, f4));
    }
}
