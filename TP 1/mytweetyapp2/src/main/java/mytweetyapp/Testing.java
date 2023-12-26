package mytweetyapp;
import java.io.IOException;
import org.tweetyproject.logics.commons.syntax.Constant;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.fol.parser.FolParser;
import org.tweetyproject.logics.fol.syntax.*;
import org.tweetyproject.logics.fol.reasoner.*;

public class Testing {
    public static void main(String[] args) throws IOException {

        FolSignature signature = new FolSignature();
        FolBeliefSet BC = new FolBeliefSet();
        FolParser parser = new FolParser();

        Predicate véhicule = new Predicate("véhicule", 1);
        signature.add(véhicule);

        Predicate Roues = new Predicate("Roues", 1);
        signature.add(Roues);

        Constant velo = new Constant("velo");
        signature.add(velo);

        BC.setSignature(signature);
        parser.setSignature(signature);

        BC.add(parser.parseFormula("forall X:(véhicule(X) => Roues(X))"));
        BC.add(parser.parseFormula("Roues(velo)"));

        FolReasoner.setDefaultReasoner(new SimpleFolReasoner());
        FolReasoner prover = FolReasoner.getDefaultReasoner();
        System.out.println("Roues(velo) is: " + prover.query(BC, (FolFormula) parser.parseFormula("Roues(velo)")));
    }
}
