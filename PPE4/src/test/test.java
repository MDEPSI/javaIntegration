package test;

import Garderie.Garderie;
import org.junit.Test;
import static org.junit.Assert.*;

public class test {

	@Test
	public final static void testResultat() {
		double resultat = Garderie.prixTotal;
		double prixQH = Garderie.prixQuartHeure;
		int nbQH = Garderie.nbQuartHeure;
		int nbQHRetard = Garderie.nbQuartHeureRetard;
		int nbRepas = Garderie.nbRepas;
		
		if(prixQH*nbQH+nbQHRetard*5+nbRepas*4!=resultat){
			fail("Failed"+prixQH+" "+nbQH+" "+nbQHRetard+" "+nbRepas+" "+resultat); // TODO
		}		
	}

}
