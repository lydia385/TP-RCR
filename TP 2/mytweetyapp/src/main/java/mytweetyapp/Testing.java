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

		MlParser parser = new MlParser();
		FolSignature sig = new FolSignature();
		sig.add(new Predicate("p",0));
		sig.add(new Predicate("q",0));
		sig.add(new Predicate("a",0));
		sig.add(new Predicate("b",0));
		parser.setSignature(sig);
		MlBeliefSet w1 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n"+"(q) \n");
		MlBeliefSet w2 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n");
		MlBeliefSet w3 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n"+"(q) \n");
		MlBeliefSet w4 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(q) \n");
		MlBeliefSet w5 = parser.parseBeliefBase("type(p) \n type(q) \n"+"(p) \n");
		//retourne si vrai dans monde 
		SimpleMlReasoner reasoner = new SimpleMlReasoner();
		//formule 
		FolFormula f1 = (FolFormula) parser.parseFormula("p&&q");
		FolFormula f2 = (FolFormula) parser.parseFormula("[](p)");
		FolFormula f3 = (FolFormula) parser.parseFormula("[](p=>q)");
		FolFormula f4 = (FolFormula) parser.parseFormula("[](q&&(<>(!p)))");	
		
		//relation ex: w2 peut acceder a w2 w3 w4
		MlBeliefSet[] Relation1= {w2,w3,w4};
		MlBeliefSet[] Relation2= {w5};
		MlBeliefSet[] Relation3= {w4};
		MlBeliefSet[] Relation5= {w4};
		System.out.println("========================EXERCICE 2 SERIE 2 : LOGIQUE MODALE=============================================");
		boolean correct=false;
		//ON VOIT SI ELLE EST VRAI DANS 1 DES MONDE ACCESSIBLE DEPUIT W1(RELATION 2) Car <>
		for(MlBeliefSet w : Relation1) {
			if(reasoner.query(w, f1)) {
				correct=true;
			}
		}
		System.out.println("M,w1 |== <>(p&&q) : " + correct);
		
		//on voit dans tout les monde car cest [] est on retourne la negation dans le print car non ..
		correct=false;		
		for(MlBeliefSet w : Relation2) {
			if(reasoner.query(w, f2)) {
				correct=true;
			}
		}
		System.out.println("M,w2 |== 7[](p) : " + !correct);
		//on a une seule relation pas la paine de faire une boucle
		System.out.println("M,w3 |== [](p=>q) : "+ reasoner.query(w4, f3));		
		System.out.println("M,w5 |== [](q && <>7p) : "+ reasoner.query(w4, f4));

		
	}

}