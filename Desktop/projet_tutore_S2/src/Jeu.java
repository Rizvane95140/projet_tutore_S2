import java.util.*;
import java.io.*;
import java.util.InputMismatchException;

public class Jeu implements Serializable{
	
	
	public static void afficheMenu(){
		int rep = 0;
		boolean err;
		Scanner input = new Scanner(System.in);
		
		Combattant c1 = new Combattant();
		Combattant c2 = new Combattant();
		
		do{
			err = false;
			System.out.println("1- Creation d'un personnage");
//			System.out.println("2- Charger personnage");
			System.out.println("3- Demarrer combat");
			try{
				rep = input.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Choississez 1, 2 ou 3!");
				err = true;
			}
		}while(err || rep < 1 || rep > 3);
		switch(rep){
		case 1:
			System.out.println("Creation joueur 1");
			Jeu.creerCombattant1();
			System.out.println("Creation joueur 2");
			Jeu.creerCombattant2();
			Jeu.afficheMenu();
			break;
		case 2:
			c1 = Jeu.chargerCombattant1();
			c2 = Jeu.chargerCombattant2();
			Jeu.afficheMenu();
			break;
		case 3:
			
			c1 = Jeu.chargerCombattant1();
			c2 = Jeu.chargerCombattant2();
			
			//Checker si vie correct a cause de combat precedent
			if(c1.CheckEquilibre() == false){
				c1.setVIT(100 + c1.getEXP());
			}
			if(c2.CheckEquilibre() == false){
				c2.setVIT(100 + c2.getEXP());
			}
			System.out.println(c1);
			System.out.println(c2);
		
			boolean game_over;
			do{
				Capacite[] tab1 = Jeu.tourDeJeu(c1);
				Capacite[] tab2 = Jeu.tourDeJeu(c2);
				game_over = Jeu.combat(c1, tab1, c2, tab2);

			}while(!game_over);
			
			if(c1.dead()){
				//Augmente niveau et baisse niveau
				c2.LevelUp();
				c1.LevelDown();
				//Verifier si level pair et entre 4 et 20 alors augmente nb capacite
				if(c2.getEXP() >= 4 && c2.getEXP() <= 20 && c2.getEXP()%2 == 0){
					System.out.println(c2.getNom()+": Vous allez ajouter une capacite!");
					c2.AddCapacite();
				}
				
				if(c1.getEXP() > 5){
					c1.retireCapacite();
				}
				
			}
			else{
				c1.LevelUp();
				c2.LevelDown();
				
				if(c1.getEXP() >= 4 && c1.getEXP() <= 20 && c1.getEXP()%2 == 0){
					System.out.println(c1.getNom()+": Vous allez ajouter une capacite!");
					c1.AddCapacite();
				}
				
				if(c2.getEXP() > 5){
					c1.retireCapacite();
				}
			}
						
			//Checker si vie correct et remettre a 100+EXP
			if(c1.CheckEquilibre() == false){
				c1.setVIT(100 + c1.getEXP());
			}
			if(c2.CheckEquilibre() == false){
				c2.setVIT(100 + c2.getEXP());
			}
			System.out.println(c1);
			System.out.println(c2);

			
			//Sauvegarde
			ObjectOutputStream ois = null;
			File f = new File("combattant1.txt");
			System.out.println("Sauvegarde combattant 1");
			try{
				ois = new ObjectOutputStream(new FileOutputStream(f));
				ois.writeObject(c1);		//*******
				ois.close();
			}
			catch(FileNotFoundException e){
				System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
			}
			catch(IOException e){
				System.out.println("Erreur lors du chargement: "+e.getMessage());
			}
			System.out.println("Sauvegarde combattant 1 reussi");
			
			//Sauvegarde
			File f2 = new File("combattant2.txt");
			System.out.println("Sauvegarde combattant 2");
			try{
				ois = new ObjectOutputStream(new FileOutputStream(f));
				ois.writeObject(c2);		//*******
				ois.close();
			}
			catch(FileNotFoundException e){
				System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
			}
			catch(IOException e){
				System.out.println("Erreur lors du chargement: "+e.getMessage());
			}
			System.out.println("Sauvegarde combattant 2 reussi");
			Jeu.afficheMenu();
			
			break;
		}
	}
	
	/*
	 * Creation d'un combattant + sauvegarde
	 */
	public static void creerCombattant1(){
		Combattant c = new Combattant();
		int rep;
		boolean err;
		Scanner sc = new Scanner(System.in);
		File f = new File("combattant1.txt");

		System.out.println("*******Creation personnage 1*******");
		c.init();	//initialise le combattant(demande le nom)
		rep = 0;		
			
		//demande le type de personnage	
		do{
			err = false;
			System.out.println("Quel type de personnage?\n1-Guerrier\n" +
			"2-Mage\n3-Chasseur");
			try{
				rep = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Choississez 1, 2 ou 3!");
				err = true;
				sc.nextLine();	//vider le buffer
			}
		}while(rep<1 || rep>3 || err);	
			
		String nom = c.getNom();
		
		switch(rep){
		case 1: Guerrier g = new Guerrier();
				c = g;
		break;
		case 2: Mage m = new Mage();
				c = m;
		break;
		case 3: Chasseur ch = new Chasseur();
				c = ch;
		break;
		}
		c.setNom(nom);			
		c.initCap();	//initialise les capacites
		
		System.out.println("Personnage cree: \n"+c);
		
		//Sauvegarde
		ObjectOutputStream ois = null;
		
		System.out.println("Sauvegarde combattant 1");
		try{
			ois = new ObjectOutputStream(new FileOutputStream(f));
			ois.writeObject(c);		//*******
			ois.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
		}
		catch(IOException e){
			System.out.println("Erreur lors du chargement: "+e.getMessage());
		}
		System.out.println("Sauvegarde combattant 1 reussi");
		
		
	}
	
	public static void creerCombattant2(){
		Combattant c = new Combattant();
		int rep;
		boolean err;
		Scanner sc = new Scanner(System.in);
		File f = new File("combattant2.txt");

		System.out.println("*******Creation personnage 2*******");
		c.init();	//initialise le combattant(demande le nom)
		rep = 0;		
			
		//demande le type de personnage	
		do{
			err = false;
			System.out.println("Quel type de personnage?\n1-Guerrier\n" +
			"2-Mage\n3-Chasseur");
			try{
				rep = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Choississez 1, 2 ou 3!");
				err = true;
				sc.nextLine();	//vider le buffer
			}
		}while(rep<1 || rep>3 || err);	
			
		String nom = c.getNom();
		
		switch(rep){
		case 1: Guerrier g = new Guerrier();
				c = g;
		break;
		case 2: Mage m = new Mage();
				c = m;
		break;
		case 3: Chasseur ch = new Chasseur();
				c = ch;
		break;
		}
		c.setNom(nom);			
		c.initCap();	//initialise les capacites
		
		System.out.println("Personnage cree: \n"+c);
		
		//Sauvegarde
		ObjectOutputStream ois = null;
		
		System.out.println("Sauvegarde combattant 2");
		try{
			ois = new ObjectOutputStream(new FileOutputStream(f));
			ois.writeObject(c);		//*******
			ois.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
		}
		catch(IOException e){
			System.out.println("Erreur lors du chargement: "+e.getMessage());
		}
		System.out.println("Sauvegarde combattant 2 reussi");
		
	}
	
	
	/*
	 * Charger les combattants
	 */
	public static Combattant chargerCombattant1(){
		File f = new File("combattant1.txt");
		Combattant c = new Combattant();
		ObjectInputStream ois = null;
		
		System.out.println("Chargement combattant 1");
		try{
			ois = new ObjectInputStream(new FileInputStream(f));
			c = (Guerrier) ois.readObject();		//*******
			System.out.println("Personnage charge:\n"+c);
			ois.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
		}
		catch(IOException e){
			System.out.println("Erreur lors du chargement: "+e.getMessage());
		}
		catch(ClassNotFoundException e){
			System.out.println("Erreur dans le chargement: "+ e.getMessage());
		}
		System.out.println("Chargement combattant 1 reussi");
		return c;
	}
	
	public static Combattant chargerCombattant2(){
		File f = new File("combattant2.txt");
		Combattant c = new Combattant();
		ObjectInputStream ois = null;
		
		System.out.println("Chargement combattant 2");
		try{
			ois = new ObjectInputStream(new FileInputStream(f));
			c = (Combattant) ois.readObject();
			System.out.println("Personnage charge:\n"+c);
			ois.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Erreur lors du chargement, fichier non trouve "+e.getMessage());
		}
		catch(IOException e){
			System.out.println("Erreur lors du chargement: "+e.getMessage());
		}
		catch(ClassNotFoundException e){
			System.out.println("Erreur dans le chargement: "+ e.getMessage());
		}
		System.out.println("Chargement combattant 2 reussi");
		return c;
	}
	
	/*
	 * Tour de jeu d'un combattant
	 * Renvoie un tableau de taille 4 avec une seule case remplie
	 * case 0: attaquer, case 1: defendre, case 2: se soigner, 3: capituler (representer par une capacite
	 * 
	 */
	public static Capacite[] tourDeJeu(Combattant c1){
		Scanner input = new Scanner(System.in);
		int choix_faire = 0;
		int choix_cap = 0;
		boolean err = false;
		Capacite[] tabChoix_Capacite = new Capacite[4];
		
		//Que faire? on obtient un int
		do{
			boolean error = false;
			do{
				error = false;
				System.out.println(c1.getNom()+"\nQue voulez-vous faire?\n" +
						"1-Attaquer, 2-Defendre, 3-Se soigner, 4-Capituler");
				try{
					choix_faire = input.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1, 2, 3 ou 4!");
					error = true;
					input.nextLine();
				}
			}while(error || choix_faire < 1 || choix_faire > 4);
			
			switch(choix_faire){
/**/			case 1:
				for(int i=0; i<c1.getTCapacites().length; i++){
					if(c1.getTCapacites()[i] instanceof Arme ||
							c1.getTCapacites()[i] instanceof Bouclier ||
							c1.getTCapacites()[i] instanceof SortAttaque){
						err = false;
						i = c1.getTCapacites().length;	//on force a sortir de la boucle
					}
					else{
						err = true;
						System.out.println("Vous ne possedez pas de capacite pouvant attaquer");
					}
				}
				break;
			case 2:
				for(int i=0; i<c1.getTCapacites().length; i++){
					if(c1.getTCapacites()[i] instanceof Arme ||
							c1.getTCapacites()[i] instanceof Bouclier ||
							c1.getTCapacites()[i] instanceof SortDefense){
						err = false;
						i = c1.getTCapacites().length;	//on force a sortir de la boucle
					}
					else{
						err = true;
						System.out.println("Vous ne possedez pas de capacite pouvant defendre");
					}
				}
				break;
			case 3:
				for(int i=0; i<c1.getTCapacites().length; i++){
					if(c1.getTCapacites()[i] instanceof Remede ||
							c1.getTCapacites()[i] instanceof SortSoin){
						err = false;
						i = c1.getTCapacites().length;	//on force a sortir de la boucle
					}
					else{
						err = true;
						System.out.println("Vous ne possedez pas de capacite pouvant soigner");
					}
				}
				break;
			case 4: 
				System.out.println(c1.getNom()+" capitule");
				break;
			}				
		}while(err);
		
		boolean armeCorrespondant = false;
		Arme a = new Arme();
		Bouclier b = new Bouclier();
		SortAttaque sortAtt = new SortAttaque();
		SortDefense sortDef = new SortDefense();
		Remede remede = new Remede();
		SortSoin sortSoin = new SortSoin();
		
		switch(choix_faire){
		case 1: 
			//Faire une fonction pour le choix de la capacité
			do{
				System.out.println("Quelle capacite voulez-vous utilise?");
				for(int i = 0; i < c1.getTCapacites().length; i++){
					System.out.println(i+"- "+c1.getTCapacites()[i]);
				}			
				try{
					choix_cap = input.nextInt();
				}
				catch(InputMismatchException e){
					int taille = c1.getTCapacites().length - 1;
					System.out.println("Choississez entre 0 et "+taille);
					err = true;
					input.nextLine();
				}
		
				if(c1.getTCapacites()[choix_cap] instanceof Arme){
					armeCorrespondant = true;
					a = (Arme)c1.getTCapacites()[choix_cap];
					a.ArmeModeAttaque(c1);
				}
				else if(c1.getTCapacites()[choix_cap] instanceof Bouclier){
					armeCorrespondant = true;
					b = (Bouclier)c1.getTCapacites()[choix_cap];
					b.BouclierModeAttaque(c1);
				}
				else if(c1.getTCapacites()[choix_cap] instanceof SortAttaque){
					armeCorrespondant = true;
					sortAtt = (SortAttaque)c1.getTCapacites()[choix_cap];
					sortAtt.SortAttaqueModeAttaque(c1);
				}
				else{
					armeCorrespondant = false;
				}		
			}while(err || choix_cap < 0 || choix_cap > c1.getTCapacites().length - 1 || !armeCorrespondant);
			
			break;
		case 2: //Defendre
			do{
				System.out.println("Quelle capacite voulez-vous utilise?");
				for(int i = 0; i < c1.getTCapacites().length; i++){
					System.out.println(i+"- "+c1.getTCapacites()[i]);
				}			
				try{
					choix_cap = input.nextInt();
				}
				catch(InputMismatchException e){
					int taille = c1.getTCapacites().length - 1;
					System.out.println("Choississez entre 0 et "+taille);
					err = true;
					input.nextLine();
				}
		
				if(c1.getTCapacites()[choix_cap] instanceof Arme){
					armeCorrespondant = true;
					a = (Arme)c1.getTCapacites()[choix_cap];
					a.ArmeModeDefense(c1);
				}
				else if(c1.getTCapacites()[choix_cap] instanceof Bouclier){
					armeCorrespondant = true;
					b = (Bouclier)c1.getTCapacites()[choix_cap];
					b.BouclierModeDefense(c1);
				}
				else if(c1.getTCapacites()[choix_cap] instanceof SortDefense){
					armeCorrespondant = true;
					sortDef = (SortDefense)c1.getTCapacites()[choix_cap];
					sortDef.SortDefenseModeDefense(c1);
				}
				else{
					armeCorrespondant = false;
				}		
			}while(err || choix_cap < 0 || choix_cap > c1.getTCapacites().length - 1 || !armeCorrespondant);		
			break;
		case 3: // Se soigner
			do{
				System.out.println("Quelle capacite voulez-vous utilise?");
				for(int i = 0; i < c1.getTCapacites().length; i++){
					System.out.println(i+"- "+c1.getTCapacites()[i]);
				}			
				try{
					choix_cap = input.nextInt();
				}
				catch(InputMismatchException e){
					int taille = c1.getTCapacites().length - 1;
					System.out.println("Choississez entre 0 et "+taille);
					err = true;
					input.nextLine();
				}
		
				if(c1.getTCapacites()[choix_cap] instanceof Remede){
					armeCorrespondant = true;
					remede = (Remede)c1.getTCapacites()[choix_cap];
					remede.RemedeModeSoin(c1);
				}
				else if(c1.getTCapacites()[choix_cap] instanceof SortSoin){
					armeCorrespondant = true;
					sortSoin = (SortSoin)c1.getTCapacites()[choix_cap];
					sortSoin.SortSoinModeSoin(c1);
				}
				else{
					armeCorrespondant = false;
				}		
			}while(err || choix_cap < 0 || choix_cap > c1.getTCapacites().length - 1 || !armeCorrespondant);			
			break;
		case 4:
			tabChoix_Capacite[choix_faire - 1] = new Capacite();
			break;
				
		}//Fin switch
		
		tabChoix_Capacite[choix_faire - 1] = c1.getTCapacites()[choix_cap];
		
		return tabChoix_Capacite;
		
		
	}//Fin methode tourDeJeu
	
	
	public static boolean combat(Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		/*
		 * J1 attaque
		 */
		if(tCaps1[0] != null){
			
			//J2 attaque, defend, se soigne ou capitule?
			//J2 attaque
			if(tCaps2[0] != null){
				
				//Attaque reussie de J1?
				if(tCaps1[0].getPba() > -0.1){		//Normalement > 0.5
					System.out.println("Attaque de "+c1.getNom()+"reussie");
	
					Jeu.c1Attaque_c2Attaque(c1, tCaps1, c2, tCaps2);
					if(c1.dead()){
						System.out.println(c1.getNom()+" perd");
						System.out.println(c1);
						return true;
					}
					if(c2.dead()){
						System.out.println(c2.getNom()+" perd");
						System.out.println(c2);
						return true;
					}
					System.out.println(c1);
					System.out.println(c2);
					return false;
					
					//C2 contre attaque, memoriser capacite de c2
			
				}//Fin if
				else{
					//Attaque ratee
					System.out.println("Attaque echouee PBA_A: "+ c1.getTCapacites()[0].getPba());
					return false;
				}
			}		
			/*
			 * J2 defend immediatement ou attend
			 */
			else if(tCaps2[1] != null){
				if(tCaps1[0].getPba() > -0.1){		//Normalement > 0.5
					System.out.println("Attaque de "+c1.getNom()+"reussie");
					Jeu.c1Attaque_c2Defend(c1, tCaps1, c2, tCaps2);
					
					if(c2.dead()){
						System.out.println(c2.getNom()+" perd");
						System.out.println(c2);
						return true;
					}
					System.out.println(c1);
					System.out.println(c2);
					return false;
				}
				else{
					System.out.println("Attaque echouee PBA_A: "+ c1.getTCapacites()[0].getPba());
					return false;
				}
			}
			// J2 se soigne
			else if(tCaps2[2] != null){
				if(tCaps1[0].getPba() > -0.1){		//Normalement > 0.5
					System.out.println("Attaque de "+c1.getNom()+" reussie");
					//Soin reussi?
					if(tCaps2[2].getPba() > -0.1){	//normalement 0.5
						System.out.println("Soin de "+c2.getNom()+" reussie");
						c2.setVIT(c2.getVIT() + (int) tCaps2[2].getEff());
						if(c2.getVIT() > tCaps1[0].getEff()){
							c2.setVIT(c2.getVIT() - (int) tCaps1[0].getEff());
							if(c2.dead()){
								System.out.println(c2.getNom()+" perd");
								System.out.println(c2);
								return true;
							}
							System.out.println(c1);
							System.out.println(c2);
							return false;
						}						
					}
					else{
						System.out.println("Soin de "+c2.getNom()+" echoue");
						return false;
					}			
				}
				else{
					System.out.println("Attaque echouee PBA_A: "+ c1.getTCapacites()[0].getPba());
					return false;
				}				
			}
			//J2 capitule
			else if(tCaps2[3] != null){
				System.out.println(c2.getNom()+" capitule");
				return true;
			}				
		}
		//Si J1 defend
		else if(tCaps1[1] != null){
			//J2 attaque
			if(tCaps2[0] != null){
				//Si attaque de J2 reussie
				if(tCaps2[0].getPba() > -0.1){		//Normalement 0.5
					System.out.println("Attaque de "+c2.getNom()+" reussie");
					Jeu.c1Defend_c2Attaque(c1, tCaps1, c2, tCaps2);
					
					if(c1.dead()){
						System.out.println(c1.getNom()+" perd");
						System.out.println(c1);
						return true;
					}
					return false;
				}
				else{
					System.out.println("Attaque de "+c2.getNom()+" echouee");
					return false;
				}			
			}
			//J2 defend
			else if(tCaps2[1] != null){
				System.out.println(c1.getNom()+" et "+c2.getNom()+" defendent");
				return false;
			}
			//J2 se soigne
			else if(tCaps2[2] != null){
				System.out.println(c2.getNom()+" se soigne");
				c2.setVIT(c2.getVIT() + (int) tCaps2[2].getEff());
				System.out.println(c2);
				return false;
			}
			//J2 capitule
			else if(tCaps2[3] != null){
				System.out.println(c2.getNom()+" capitule");
				return true;
			}
		}
		//Si J1 se soigne
		else if(tCaps1[2] != null){
			System.out.println(c1.getNom()+" se soigne");
			//J2 attaque
			if(tCaps2[0] != null){
				if(tCaps1[2].getPba() > -0.1){		//Normalement 0.5
					System.out.println("Soin de "+c1.getNom()+" reussi");
					c1.setVIT(c1.getVIT() + (int) tCaps1[2].getEff());
				}
				else{
					System.out.println("Soin de "+c1.getNom()+" echoue");
				}
				
				if(tCaps2[0].getPba() > -0.1){		//normalement 0.5
					System.out.println("Attaque de "+c2.getNom()+" reussie");
					c1.setVIT(c1.getVIT() - (int) tCaps2[0].getEff());
					if(c1.dead()){
						System.out.println(c1.getNom()+" perd");
						System.out.println(c1);
						return true;
					}
				}
				else{
					System.out.println("Attaque de "+c2.getNom()+" echouee");
					return false;
				}
				
			}
			// J2 defend
			else if(tCaps2[1] != null){
				System.out.println(c2.getNom()+" defend");
				
				if(tCaps1[2].getEff() > -0.1){
					System.out.println("Soin de "+c1.getNom()+" reussi");
					c1.setVIT(c1.getVIT() + (int) tCaps1[2].getEff());
					System.out.println(c1);
					System.out.println(c2);
				}
				else{
					System.out.println("Soin de "+c2.getNom()+" echoue");
				}
				return false;
			}
			//J2 se soigne
			else if(tCaps2[2] != null){
				System.out.println(c2.getNom()+" se soigne");
				
				//Appliquer soin a c1
				if(tCaps1[2].getPba() > -0.1){		//Normalement 0.5
					System.out.println("Soin de "+c1.getNom()+" reussi");
					c1.setVIT(c1.getVIT() + (int) tCaps1[2].getEff());
					System.out.println(c1);
				}
				else{
					System.out.println("Soin de "+c1.getNom()+" echoue");
				}
				
				//Appliquer soin a c2
				if(tCaps2[2].getPba() > -0.1){
					System.out.println("Soin de "+c2.getNom()+" reussi");
					c2.setVIT(c2.getVIT() + (int) tCaps2[2].getEff());
					System.out.println(c2);
				}
				else{
					System.out.println("Soin de "+c2.getNom()+" echoue");
				}
				return false;								
			}
			else if(tCaps2[3] != null){
				System.out.println(c2.getNom()+" capitule");
				return true;
			}
		}
		//J1 capitule
		else{
			System.out.println(c1.getNom()+" capitule");
			return true;
		}
		return false;
	}
	
	
	
	
	/*
	 * J1 attaque, J2 attaque
	 */
	public static void c1Attaque_c2Attaque(Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		System.out.println("J2 encaisse puis attaque");
		double eff_a_c1 = tCaps1[0].getEff();
		int eff_a_c1_int = (int) eff_a_c1;
		c2.setVIT(c2.getVIT() - eff_a_c1_int);
		
		//Si attaque de J2 reussie
		if(tCaps2[0].getEff() > -0.1){
			c1.setVIT(c1.getVIT() - (int) tCaps2[0].getEff());
		}
	}
	
	/*
	 * J1 attaque, J2 defend
	 */
	public static void c1Attaque_c2Defend(Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		int rep = 0;
		boolean err = false;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println(c2.getNom()+"\nVoulez-vous defendre: 1-immediatement ou " +
						"2-evaluer la puissance de l'attaque de l'adversaire?");
			try{
				rep = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Choississez 1 ou 2!");
				err = true;
				sc.nextLine();
			}	
		}while(err || rep < 1 || rep > 2);
		Jeu.switchc2Defense(rep, c1, tCaps1, c2, tCaps2);
	}
	
	/*
	 * Actions en fonction de reponse de defense de J2
	 */
	public static void switchc2Defense(int i, Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		Scanner sc = new Scanner(System.in);
		switch(i){
		//Immediadement
		case 1:
			System.out.println(c2.getNom() +" defend immediatement");
			//Si defense reussie
			if(tCaps2[1].getPba() > -0.1){		//Normalement 0.5
				System.out.println("Defense reussie de "+c2.getNom());
				double res_diff;
				
				//si eff de attaque < eff de defense, attaque nulle
				if(tCaps1[0].getEff() <= tCaps2[1].getEff()){
					res_diff = 0;
				}
				else{
					res_diff = tCaps1[0].getEff() - tCaps2[1].getEff();
				}
				System.out.println("Resultat: eff_a - eff_d = "+res_diff);	
				
				c2.setVIT(c2.getVIT() - (int) res_diff);
			}
			else{
				System.out.print("Defense de "+c2.getNom()+" echouee");
				c2.setVIT(c2.getVIT() - (int) tCaps1[0].getEff());
			}
			break;
		//Evaluer la puissance	
		case 2:
			System.out.println(c2.getNom()+" evalue la puissance de l'attaque de "+c1.getNom());
			System.out.println("Efficacite de "+c1.getNom()+":"
		+tCaps1[0].getEff());
			int reponse = 0;
			boolean error = false;
			do{
				error = false;
				System.out.println("Voulez-vous defendre? 1-Oui, 2-Non");
				try{
					reponse = sc.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1 ou 2!");
					error = true;
					sc.nextLine();
				}
			}while(error || reponse < 1 || reponse > 2);
			
			switch(reponse){
			//Defendre avec penalite
			case 1:
				System.out.println(c2.getNom()+" souhaite finalement defendre");
				double newPba_def = tCaps2[1].getPba() - 0.25*tCaps2[1].getPba();

				System.out.println("Nouvelle pba_def: "+newPba_def);
				
				if(newPba_def > -0.1){						//Normalement > 0.5
					System.out.println("Defense reussie de "+c2.getNom());
					double newEff_def = tCaps2[1].getEff() - 0.25*tCaps2[1].getEff();
					int res_diff_new_int = 0;
					double eff_Att = tCaps1[0].getEff();
					int eff_Att_int = (int) eff_Att;
					int newEff_def_int = (int) newEff_def;
					
					//Efficacite de l'attaque nulle si eff_a < eff_d
					if(newEff_def_int >= eff_Att_int){
						res_diff_new_int = 0;
					}
					else{
						res_diff_new_int = eff_Att_int - newEff_def_int;
					}
					System.out.println("Resultat: eff_a - newEff_d = "+res_diff_new_int);
					
				}
				else{
					System.out.println("Defense de "+c2.getNom()+" echouee");
				}
				break;
			//Encaisser puis attaquer a ce tour	
			case 2:
				System.out.println(c2.getNom()+" encaisse puis attaque");
				c2.setVIT(c2.getVIT() - (int) tCaps1[0].getEff());

				/***Contre attaque***/
			}								
			break;
		}//Fin Switch
	}//Fin methode switchc2Defense
	

	
	public static void c1Defend_c2Attaque(Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		int rep = 0;
		boolean err = false;
		Scanner sc = new Scanner(System.in);

		if(tCaps2[0].getPba() > -0.1){	//Normalement 0.5
				
			do{
				System.out.println(c1.getNom()+": Voulez-vous defendre 1-immediatement ou 2-evaluer la puissance?");
				try{
					rep = sc.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1 ou 2!");
					err = true;
					sc.nextLine();
				}
			}while(err || rep < 1 || rep > 2);
			Jeu.switchc1Defense(rep, c1, tCaps1, c2, tCaps2);
				
		}
		else{
			System.out.println("Attaque de "+c2.getNom()+" echouee");
		}
		
	}
	
	/*
	 * Actions en fonction de reponse de defense j1
	 */
	public static void switchc1Defense(int i, Combattant c1, Capacite[] tCaps1, Combattant c2, Capacite[] tCaps2){
		Scanner sc = new Scanner(System.in);
		switch(i){
		//J1 Defend immediatemment
		case 1:
			System.out.println(c1.getNom()+" defend immediatement");
			//Si defense reussie
			if(tCaps1[1].getPba() > 0.0){	//normalement 0.5
				System.out.println("Defense de "+c1.getNom()+" reussie");
				
				double res_diff;
				//si eff de attaque < eff de defense, attaque nulle
				if(tCaps2[0].getEff() <= tCaps1[1].getEff()){
					res_diff = 0;			
				}
				else{
					res_diff = tCaps2[0].getEff() - tCaps1[1].getEff();
				}
				System.out.println("Resultat: eff_a - eff_d = "+res_diff);	
						
				c1.setVIT(c1.getVIT() - (int) res_diff);
				System.out.println(c1);		
			}
			else{
				System.out.println("Defense de "+c1.getNom()+" echouee");
				c1.setVIT(c1.getVIT() - (int)tCaps2[0].getEff());
			}			
			break;
		//J1 evalue la puissance de J2	
		case 2:
			System.out.println(c1.getNom()+" evalue la puissance de l'attaque de "+c2.getNom());
			System.out.println("Efficacite de "+c2.getNom()+":"
		+tCaps2[0].getEff());
			int reponse = 0;
			boolean error = false;
			do{
				error = false;
				System.out.println("Voulez-vous defendre? 1-Oui, 2-Non");
				try{
					reponse = sc.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1 ou 2!");
					error = true;
					sc.nextLine();
				}
			}while(error || reponse < 1 || reponse > 2);
			
			switch(reponse){
			//Defense avec penalite
			case 1:
				System.out.println(c1.getNom()+" souhaite finalement defendre");
				double newPba_def = tCaps1[1].getPba() - 0.25*tCaps1[1].getPba();
				System.out.println("Nouvelle pba_def: "+newPba_def);
				
				if(newPba_def > -0.1){		//normalement > 0.5
					System.out.println("Defense de "+c1.getNom()+" reussie");
					double newEff_def = tCaps1[1].getEff() - 0.25*tCaps1[1].getEff();
					int res_diff_new_int = 0;
					double eff_att = tCaps2[0].getEff();
					int eff_att_int = (int) eff_att;
					int newEff_def_int = (int) newEff_def;
					
					///Efficacite de l'attaque nulle si eff_a <= eff_def
					if(newEff_def_int >= eff_att_int){
						res_diff_new_int = 0;
					}
					else{
						res_diff_new_int = eff_att_int - newEff_def_int;
					}
					System.out.println("Resultat: eff_a - newEff_d = "+res_diff_new_int);
					
					c1.setVIT(c1.getVIT() - res_diff_new_int);
					System.out.println(c1);				
				}
				else{
					System.out.println("Defense de "+c1.getNom()+" echouee");
				}
				break;
			//Encaisser puis attaquer a ce tour	
			case 2:
				System.out.println(c1.getNom()+" encaisse puis attaque");
				c1.setVIT(c1.getVIT() - (int) tCaps2[0].getEff());
				
				System.out.println(c1);
				break;
			}
			
			break;
		}
	}
	
	
	public static void main(String[] args) {
		Jeu.afficheMenu();
		//Niveau 1 - 5, 2CAP
		//Niveau 6 - 7, 3CAP
		//Niveau 8 - 9, 4CAP
		//Niveau 10 - 11, 5CAP
		//Niveau 12 - 12, 6CAP
		//Niveau 14
		
		
	}//fin du main
}//fin de la classe		
		
		
		
		
		
		
		

		
		
	
