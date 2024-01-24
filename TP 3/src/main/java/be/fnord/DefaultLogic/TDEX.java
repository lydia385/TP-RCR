package be.fnord.DefaultLogic;

import a.e;
import be.fnord.util.logic.DefaultReasoner;
import be.fnord.util.logic.WFF;
import be.fnord.util.logic.defaultLogic.DefaultRule;
import be.fnord.util.logic.defaultLogic.RuleSet;
import be.fnord.util.logic.defaultLogic.WorldSet;
import java.util.HashSet;


public class TDEX {
	public static void EXERCISE1() {
		
		// création de l'ensemble des défauts
		RuleSet rules = new RuleSet(); 
		
		// création du défaut d1
		DefaultRule d1 = new DefaultRule();
		d1.setPrerequisite("A"); 
		d1.setJustificatoin("B"); 
		d1.setConsequence("C"); 
		
		// ajout de d1 dans l'ensemble des défauts
		rules.addRule(d1); 
		
		// même chose pour d2
		DefaultRule d2 = new DefaultRule(); 
		d2.setPrerequisite("A");
		d2.setJustificatoin(e.NOT + "C");
		d2.setConsequence("D");
		
		rules.addRule(d2);
		
		// définition d'un monde w1
		WorldSet w1 = new WorldSet();
		w1.addFormula(e.NOT + "A");
		
		// déf w2
		WorldSet w2 = new WorldSet(); 
		w2.addFormula("A");
		w2.addFormula(e.NOT + "B");
		
		// déf w3
		WorldSet w3 = new WorldSet(); 
		w3.addFormula("A");
		w3.addFormula("(" + e.NOT + "C" + e.OR + e.NOT + "D)");
		
		// déf w4
		WorldSet w4 = new WorldSet(); 
		w4.addFormula("A");
		w4.addFormula("(" + e.NOT + "B" + e.AND + "C)");
		
		// W1
		try {
			a.e.println("\nMonde 1:");
			DefaultReasoner r = new DefaultReasoner(w1, rules); // raisonner
			HashSet<String> scenarios = r.getPossibleScenarios(); // extraction des extensions
			a.e.println("w1: [" + w1.toString() + "]\nDefaults : [" + rules.toString() + "]");
			if (scenarios.isEmpty()) a.e.println("ces défauts ne génèrent pas d'extension ");
			for (String c : scenarios) {
				a.e.println("E: Th(W U {" + c + "})");
				WFF world_and_ext = new WFF("(( " + w1.getWorld() + " ) & (" + c + "))");
				a.e.println(world_and_ext.getClosure());
				a.e.decIndent();
			}
			a.e.println("");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// W2
		try {
			a.e.println(" Monde 2:");
			DefaultReasoner r = new DefaultReasoner(w2, rules);
			HashSet<String> scenarios = r.getPossibleScenarios();
			a.e.println("w2: [" + w2.toString() + "]\nDefaults : [" + rules.toString() + "]");
			for (String c : scenarios) {
				a.e.println("E: Th(W U {" + c + "})");
				WFF world_and_ext = new WFF("(( " + w2.getWorld() + " ) & (" + c + "))");
				a.e.println(world_and_ext.getClosure());
				a.e.decIndent();
			}
			a.e.println("");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// W3
		try {
			a.e.println(" Monde 3:");
			DefaultReasoner r = new DefaultReasoner(w3, rules);
			HashSet<String> scenarios = r.getPossibleScenarios();
			a.e.println("w3: [" + w3.toString() + "]\nDefaults : [" + rules.toString() + "]");
			for (String c : scenarios) {
				a.e.println("E: Th(W U {" + c + "})");
				WFF world_and_ext = new WFF("(( " + w3.getWorld() + " ) & (" + c + "))");
				a.e.println(world_and_ext.getClosure());
				a.e.decIndent();
			}
			a.e.println("");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// W4
		try {
			a.e.println(" Monde 4:");
			DefaultReasoner r = new DefaultReasoner(w4, rules);
			HashSet<String> scenarios = r.getPossibleScenarios();
			a.e.println("w3: [" + w4.toString() + " ]\nDefaults : [" + rules.toString() + "]");
			for (String c : scenarios) {
				a.e.println("E: Th(W U {" + c + "})");
				WFF world_and_ext = new WFF("(( " + w4.getWorld() + " ) & (" + c + "))");
				a.e.println(world_and_ext.getClosure());
				a.e.decIndent();
			}
			a.e.println("");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
