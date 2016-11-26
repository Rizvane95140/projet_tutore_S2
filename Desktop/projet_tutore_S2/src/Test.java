
public class Test {
	
	public static void main(String[] args) {
		
		
		Jeu.creerCombattant1();
		Jeu.creerCombattant2();
		Combattant c1 = Jeu.chargerCombattant1();
		Combattant c2 = Jeu.chargerCombattant2();
		boolean game_over;
		do{
			Capacite[] tab1 = Jeu.tourDeJeu(c1);
			Capacite[] tab2 = Jeu.tourDeJeu(c2);
			game_over = Jeu.combat(c1, tab1, c2, tab2);

		}while(!game_over);
/*		
		Combattant c1 = Jeu.creerCombattant();
		Combattant c2 = Jeu.creerCombattant();
		
		Capacite[] tab1 = Jeu.tourDeJeu(c1);
		Capacite[] tab2 = Jeu.tourDeJeu(c2);
		
		System.out.println("****tab1****");
		for(int i=0; i<tab1.length; i++){
			System.out.println(tab1[i]);
		}
		System.out.println("****tab2****");
		for(int i=0; i<tab1.length; i++){
			System.out.println(tab2[i]);
		}
		
		Jeu.combat(c1, tab1, c2, tab2);
*/		
		
		//C1 attaque, C2 attaque
		//C1 attaque, C2 defend immediatement
		//C1 attaque, C2 evalue la puissance avant de defendre
		//C1 attaque, C2 evalue la puissance avant d'encaisser		..contre attaque
		//C1 attaque, C2 se soigne 
		
		//C1 defend immediatement, C2 attaque
		//C1 evalue la puissance avant de defendre, C2 attaque
		//C1 evalue la puissance avant d'encaisser  ... contre attaque, C2 attaque
		//C1 defend, C2 defend
		//C1 defend, C2 se soigne
		
		//C1 se soigne, C2 attaque
		//C1 se soigne, C2 defend
		//C1 se soigne, C2 se soigne
		
		
		//****
		//1 perd
		
		

	}

}
