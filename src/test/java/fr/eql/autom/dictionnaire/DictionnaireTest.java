package fr.eql.autom.dictionnaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import fr.eql.autom.dictionnaire.exceptions.CategorieNonSupporteeException;
import fr.eql.autom.dictionnaire.exceptions.EntreeInexistanteException;
import fr.eql.autom.dictionnaire.exceptions.MotInexistantException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteDupliqueeException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteObligatoireIndefinieException;
import fr.eql.autom.modele.entrees.Entree;
import fr.eql.autom.modele.mots.Mot;
import fr.eql.autom.modele.proprietes.Categorie;
import fr.eql.autom.modele.proprietes.Genre;
import fr.eql.autom.modele.proprietes.IPropriete;
import fr.eql.autom.modele.proprietes.Nombre;

public class DictionnaireTest {

	@Test
	public void tpJunit1() throws CategorieNonSupporteeException {

		String identite = "whatever";
		Categorie categorie = Categorie.ADJ;
		Dictionnaire dict = new Dictionnaire();
		boolean result = dict.ajouterEntree(identite, categorie);
		assertEquals("message", true, result);
	}
	
	@Test(expected=Exception.class, timeout=1000)
	public void tpJunit2() throws CategorieNonSupporteeException {

		String identite = "whatever";
		Categorie categorie = Categorie.NOM;
		Dictionnaire dict = new Dictionnaire();
		boolean result = dict.ajouterEntree(identite, categorie);
		assertNotEquals("message", true, result);
	}
	
	@Test
	public void tpJunit3() throws CategorieNonSupporteeException {

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "mot unique";
		Categorie categorie = Categorie.ADJ;
		
		dict.ajouterEntree(identite, categorie);
		
		boolean result = dict.ajouterEntree(identite, categorie);
		assertNotEquals("message", true, result);
	}
	
	@Test
	public void tpJunit4(){

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADJ;
		Genre genre = Genre.F;
		
		boolean result = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("message", true, result);
	}
	
	@Test
	public void tpJunit5(){

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.NOM;
		Genre genre = Genre.F;
		
		boolean result = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("message", true, result);
	}
	
	@Test
	public void tpJunit6(){

		Dictionnaire dict = new Dictionnaire();
		
		String identite1 = "mot unique";
		Categorie categorie1 = Categorie.NOM;
		Genre genre1 = Genre.F;
		String identite2 = "mot unique";
		Categorie categorie2 = Categorie.NOM;
		Genre genre2 = Genre.F;
		
		dict.ajouterEntree(identite1, categorie1, genre1);
		boolean result2 = dict.ajouterEntree(identite2, categorie2, genre2);
		assertNotEquals("message", true, result2);
	}
	
	@Test
	public void tpJunit7() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie cat = Categorie.ADJ;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(cat);
		
		boolean result = dict.ajouterEntree(identite, properties);
		assertEquals("message", true, result);
	}
	
	@Test
	public void tpJunit8() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		
		dict.ajouterEntree(identite, properties);
		boolean result = dict.ajouterEntree(identite, properties);
		assertNotEquals("message", true, result);
	}
	
	@Test(expected=ProprieteObligatoireIndefinieException.class, timeout=1000)
	public void tpJunit9() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.NOM);
		
		dict.ajouterEntree(identite, properties);
		boolean result = dict.ajouterEntree(identite, properties);
		assertNotEquals("message", true, result);
	}
	
	@Test(expected=ProprieteDupliqueeException.class, timeout=1000)
	public void tpJunit10() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		properties.add(Categorie.ADV);
		
		dict.ajouterEntree(identite, properties);
		boolean result = dict.ajouterEntree(identite, properties);
		assertNotEquals("message", true, result);
	}
	
	@Test
	public void tpJunit11() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADV);
		properties.add(Genre.F);
		properties.add(Genre.M);
		
		boolean result = dict.ajouterEntree(identite, properties);
		assertEquals("message", true, result);
	}
	
	@Test
	public void tpJunit12() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		properties.add(Nombre.PL);
		properties.add(Genre.F);
		dict.ajouterEntree(identite, properties);
		
		boolean result = dict.ajouterMot(identite, "whatevers", properties);
		assertEquals("message", true, result);
	}
	
	@Test(expected=EntreeInexistanteException.class, timeout=1000)
	public void tpJunit13() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		properties.add(Nombre.PL);
		properties.add(Genre.F);
		
		dict.ajouterMot(identite, "whatevers", properties);
	}
	
	@Test(expected=ProprieteObligatoireIndefinieException.class, timeout=1000)
	public void tpJunit14() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		properties.add(Nombre.PL);
		dict.ajouterEntree(identite, properties);
		
		dict.ajouterMot(identite, "whatevers", properties);
	}
	
	@Test(expected=ProprieteDupliqueeException.class, timeout=1000)
	public void tpJunit15() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADJ);
		properties.add(Nombre.PL);
		properties.add(Nombre.SG);
		properties.add(Genre.F);
		
		dict.ajouterEntree(identite, properties);
		
		dict.ajouterMot(identite, "whatevers", properties);
	}
	
	@Test
	public void tpJunit16() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(Categorie.ADV);
		properties.add(Nombre.PL);
		properties.add(Nombre.SG);
		properties.add(Genre.F);
		dict.ajouterEntree(identite, properties);
		
		boolean result = dict.ajouterMot(identite, "whatevers", properties);
		assertEquals("message", true, result);
		
	}
	
	@Test
	public void tpJunit17() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		Set<Mot> set = dict.trouverMotsAssociesAUneEntree(identite);
		
		assertEquals("message", identite, set.iterator().next().getLexeme().getIdentite());
		
	}
	
	@Ignore
	public void tpJunit18() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		Set<Mot> set = dict.trouverMotsAssociesAUneEntree("whatevers");
		
		assertEquals("message", 0, set.size());
		
	}
	
	@Test(expected=EntreeInexistanteException.class, timeout=1000)
	public void tpJunit19() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterMot(identite, "whatevers", properties);
		
		dict.trouverMotsAssociesAUneEntree("something else");
		
	}
	
	@Test
	public void tpJunit20() throws EntreeInexistanteException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, MotInexistantException {

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		Set<Entree> set = dict.trouverEntreesAssocieesAuMot("whatevers");
		
		assertEquals("message", identite, set.iterator().next().getIdentite());
		
	}
	
	@Test(expected=MotInexistantException.class, timeout=1000)
	public void tpJunit21() throws EntreeInexistanteException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, MotInexistantException {

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		dict.trouverEntreesAssocieesAuMot("something different");
		
	}
	
	@Test
	public void tpJunit22() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException, MotInexistantException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		String identite2 = "whateverz";
		Categorie categorie2 = Categorie.ADJ;
		Set<IPropriete> properties2 = new HashSet<IPropriete>();
		properties2.add(categorie2);
		properties2.add(Nombre.PL);
		properties2.add(Genre.F);
		dict.ajouterEntree(identite2, properties2);
		dict.ajouterMot(identite2, "whatevers", properties2);
		
		Set<Entree> set = dict.trouverEntreesAssocieesAuMotParCategorie("whatevers", categorie);
		
		assertEquals("message", 1, set.size());
		assertEquals("message", identite, set.iterator().next().getIdentite());
		
	}
	
	@Test
	public void tpJunit23() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException, MotInexistantException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		String identite2 = "whateverz";
		Categorie categorie2 = Categorie.ADJ;
		Set<IPropriete> properties2 = new HashSet<IPropriete>();
		properties2.add(categorie2);
		properties2.add(Nombre.PL);
		properties2.add(Genre.F);
		dict.ajouterEntree(identite2, properties2);
		dict.ajouterMot(identite2, "whatevers", properties2);
		
		Set<Entree> set = dict.trouverEntreesAssocieesAuMotParCategorie("whatevers", Categorie.VERBE);
		
		assertEquals("message", 0, set.size());
		
	}
	
	@Test(expected=MotInexistantException.class, timeout=1000)
	public void tpJunit24() throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException, MotInexistantException{

		Dictionnaire dict = new Dictionnaire();
		
		String identite = "whatever";
		Categorie categorie = Categorie.ADV;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(categorie);
		dict.ajouterEntree(identite, properties);
		dict.ajouterMot(identite, "whatevers", properties);
		
		String identite2 = "whateverz";
		Categorie categorie2 = Categorie.ADJ;
		Set<IPropriete> properties2 = new HashSet<IPropriete>();
		properties2.add(categorie2);
		properties2.add(Nombre.PL);
		properties2.add(Genre.F);
		dict.ajouterEntree(identite2, properties2);
		dict.ajouterMot(identite2, "whatevers", properties2);
		
		dict.trouverEntreesAssocieesAuMotParCategorie("whateverzz", Categorie.ADV);
		
	}

}
