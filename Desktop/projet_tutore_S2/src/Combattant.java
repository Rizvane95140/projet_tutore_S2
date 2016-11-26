import java.util.*;
import java.io.*;

public class Combattant implements Serializable{
	
	private String nom;
	private int FOR;	//FORce physique
	private int DEX;	//DEXterite
	private int INT;	//INTelligence
	private int CON;	//capacite de CONcentration
	private int VIT;	//VITalite
	private int EXP;	//EXPerience
	
	public final static int EXP_PLANCHER = 1;
	public final static int EXP_PLAFOND = 20;
	
	private Capacite[] tCapacites;
	
	/*
	 * on choisit comme valeur plafond 100 et valeur plancher 0 
	 * pour les 4 aptitudes (FOR, DEX, INT, CON)
	 */	
	public final static int APT_PLANCHER = 0;
	public final static int APT_PLAFOND = 100;
	
	
	/* Accesseurs */
	
	public String getNom(){
		return this.nom;
	}
	
	public int getFOR(){
		return this.FOR;
	}
	
	public int getDEX(){
		return this.DEX;
	}
	
	public int getINT(){
		return this.INT;
	}
	
	public int getCON(){
		return this.CON;
	}
	
	public int getVIT(){
		return this.VIT;
	}
	
	public int getEXP(){
		return this.EXP;
	}
	
	public Capacite[] getTCapacites(){
		return this.tCapacites;
	}
	
	/* Mutateurs */
	
	public void setNom(String n){
		this.nom = n;
	}
	
	public void setFOR(int f){
		if(f >= APT_PLANCHER && f <= APT_PLAFOND)
			this.FOR = f;
	}
	
	public void setDEX(int d){
		if(d >= APT_PLANCHER && d <= APT_PLAFOND)
			this.DEX = d;
	}
	
	public void setINT(int i){
		if(i >= APT_PLANCHER && i <= APT_PLAFOND)
			this.INT = i;
	}
	
	public void setCON(int c){
		if(c >= APT_PLANCHER && c <= APT_PLAFOND)
			this.CON = c;
	}
	
	public void setVIT(int v){
		this.VIT = v;
	}
	
	public void setEXP(int e){
		if(e <= EXP_PLAFOND && e >= EXP_PLANCHER)
			this.EXP = e;
	}
		
	public void setTCapacite(Capacite[] tcaps){
		this.tCapacites = tcaps;
	}
	
	
	/* constructeur par defaut */
	public Combattant(){
		this.EXP = 1;		
		this.tCapacites = new Capacite[2];
		for(int cpt = 0; cpt < tCapacites.length; cpt++){
			this.tCapacites[cpt] = new Capacite();
		}
	}
	
	/* constructeur champ a champ */
	public Combattant(String nom, int f, int d, int i, int c, int e, Capacite[] tcaps){
		this.nom = nom;
		this.FOR = f;
		this.DEX = d;
		this.INT = i;
		this.CON = c;
		this.EXP = e;
		
		int vit_calc = 200 - (FOR + DEX + INT + CON) + EXP * 3;
		this.VIT = vit_calc;	//lors de la creation d'un perso, VIT = 200 - (FOR + DEX + INT + CON) + EXP x 3
	
		this.tCapacites = tcaps;
	}
	
	/* constructeur par copie */
	public Combattant(Combattant c){
		this.nom = new String(c.getNom());
		
		this.FOR = c.FOR;
		this.DEX = c.DEX;
		this.INT = c.INT;
		this.CON = c.CON;
		
		this.VIT = c.VIT;
		this.EXP = c.EXP;
		
		int taille = c.tCapacites.length;
		this.tCapacites = new Capacite[taille];
		for(int i = 0; i < c.tCapacites.length; i++){
			this.tCapacites[i] = new Capacite(c.getTCapacites()[i]);
		}
		
	}
	
	public String toString(){
		
		String s = "Combattant "+this.nom+" [FOR="+this.FOR+", DEX="+this.DEX+", INT="+this.INT+
				", CON="+this.CON+", VIT="+this.VIT+", EXP="+this.EXP+"] \n";
		
		int n = this.tCapacites.length;
		for(int i = 0; i < n; i++){
			s = s +" | "+ this.tCapacites[i].toString()+"\n";
		}
		
		return s;
	}
			
	public void init(){
		Scanner sc = new Scanner(System.in);
		Combattant p;
		System.out.println("Quel est le nom de votre personnage?");
		this.nom = sc.nextLine();		
		this.VIT = 200 - (this.FOR + this.DEX + this.INT + this.CON) + this.EXP * 3;
	}
	
	/*
	 * Baisser la vitalite
	 */
	public void harm(int v){
		this.VIT = this.VIT - v;
	}
	
	/*
	 * Controle si VIT <= 0
	 * retourne true si VIT <= 0
	 */
	public boolean dead(){
		return (this.VIT <= 0);
	}
	
	public void initCap(){
		Scanner input = new Scanner(System.in);
		String nom;
		int categorie = 0;
		int choix_capacite = 0;
		Capacite cap = new Capacite();
		boolean err;		
		
		for(int i = 0; i<this.tCapacites.length; i++){
			
			do{
				err = false;
				int numCap = 1 + i;
				System.out.println("Initialisation de la "+numCap+"e capacite:"
			+"\nQuel type de cat�gorie?\n1-Arme\n2-Sort d'attaque\n3-Bouclier\n4-Sort de defense\n5-Remede\n6-Sort de soin");
				try{
					categorie = input.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez une bonne categorie!");
					err = true;
					input.nextLine();
				}
			}while(categorie<1 || categorie>6 || err);
			
			switch(categorie){
			case 1: cap = new Arme();
					do{
						err = false;
						System.out.println("Quel arme voulez-vous?\n" +
							"1-Epee, 2-Hallebarde");
						try{
							choix_capacite = input.nextInt();
						}
						catch(InputMismatchException e){
							System.out.println("Choississez 1 ou 2");
							err = true;
							input.nextLine();
						}
					}while(choix_capacite > 2 || choix_capacite < 1 || err);
					
					if(choix_capacite == 1){
						cap = new Arme("Epee", 60, 40);
					}
					else{
						cap = new Arme("Hallebarde", 40, 60);
					}
					break;
					
			case 2: cap = new SortAttaque();
					do{
						err = false;
						System.out.println("Quel sort d'attaque?\n"
								+"1-Boule de feu, 2-Eclair");
						try{
							choix_capacite = input.nextInt();
						}
						catch(InputMismatchException e){
							System.out.println("Choississez 1 ou 2");
							err = true;
							input.nextLine();
						}
						
					}while(choix_capacite > 2 || choix_capacite < 1 || err);
					
					if(choix_capacite == 1){
						cap = new SortAttaque("Boule de feu", 50, 50);
					}
					else{
						cap = new SortAttaque("Eclair", 60, 40);
					}
					break;
					
			case 3: cap = new Bouclier();
					do{
							err = false;
							System.out.println("Quel bouclier?\n"
									+"1-Ecu, 2-Pavois");
							try{
								choix_capacite = input.nextInt();
							}
							catch(InputMismatchException e){
								System.out.println("Choississez 1 ou 2!");
								err = true;
								input.nextLine();
							}						
						}while(choix_capacite<1 || choix_capacite>2 || err);
					
					if(choix_capacite == 1){
						cap = new Bouclier("Ecu", 60, 40);
					}
					else{
						cap = new Bouclier("Pavois", 70, 30);
					}
					break;
					
			case 4: cap = new SortDefense();
					do{
						err = false;
						System.out.println("Quel sort de defense?\n"
								+"1-Champ de force, 2-Champ de protection");
						try{
							choix_capacite = input.nextInt();
						}
						catch(InputMismatchException e){
							System.out.println("Choississez 1 ou 2!");
							err = true;
							input.nextLine();
						}
					}while(choix_capacite < 1 || choix_capacite > 2 || err);
					if(choix_capacite == 1){
						cap = new SortDefense("Champ de force", 70, 30);
					}
					else{
						cap = new SortDefense("Champ de protection", 40, 60);
					}
					break;
					
			case 5: cap = new Remede();
					do{
						err = false;
						System.out.println("Quel remede?\n1-Potion, 2-Super-potion");
						try{
							choix_capacite = input.nextInt();
						}
						catch(InputMismatchException e){
							System.out.println("Choissisez 1 ou 2!");
							err = true;
							input.nextLine();
						}
					}while(err || choix_capacite < 1 || choix_capacite > 2);
					if(choix_capacite == 1){
						cap = new Remede("Potion", 40, 60);
					}
					else{
						cap = new Remede("Super-potion", 60, 40);
					}
					break;
			case 6: cap = new SortSoin();
					do{
						err = false;
						System.out.println("Quel sort de soin?\n1-Soin, 2-Guerison");
						try{
							choix_capacite = input.nextInt();
						}
						catch(InputMismatchException e){
							System.out.println("Choississez 1 ou 2!");
							err = true;
						}
					}while(err || choix_capacite < 1 || choix_capacite > 2);
					if(choix_capacite == 1){
						cap = new SortSoin("Soin", 50, 50);
					}
					else{
						cap = new SortSoin("Guerison", 70, 30);
					}
					break;
			}//Fin switch
			
			// init tableau
			this.tCapacites[i] = cap;
		}//Fin for

	}
	
	
	/*
	 * Ajoute une capacite
	 */
	public void AddCapacite(){
		Capacite cap = Combattant.quelCapaciteAjoute();
		int tailleOrigin = this.tCapacites.length;
		//nouvelle taile
		int newlenght = this.tCapacites.length + 1;
		//nouveau tab de cap
		Capacite[] newTcaps = new Capacite[newlenght];
		int i;
		//copie du tab origine vers nouveau tab
		for(i = 0; i<tailleOrigin; i++){
			newTcaps[i] = this.tCapacites[i];
		}
		//ajout de la nouvelle cap
		newTcaps[newlenght-1] = cap;
		
		
		this.tCapacites = newTcaps;
	}
	
	public static Capacite quelCapaciteAjoute(){
		Scanner sc = new Scanner(System.in);
		int rep = 0;
		boolean err = false;
		Capacite cap = new Capacite();
		do{
			err = false;
			System.out.println("Quelle capacite souhaitez-vous ajouter?");
			System.out.println("1-Epee, 2-Hallebarde\n3-Boule de feu, 4-Eclair\n5-Ecu, 6-Pavois\n"
					+ "7-Champ de force, 8-Champ de protection\n9-Potion, 10-Super-potion\n"
					+ "11-Soin, 12-Guerison");
			try{
				rep = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Choississez entre 1 et 12");
				err = true;
				sc.nextLine();
			}
		}while(err || rep < 1 || rep > 12);
		switch(rep){
		case 1:
			cap = new Arme("Epee", 60, 40);
			break;
		case 2:	
			cap = new Arme("Hallebarde", 40, 60);
			break;
		case 3:	
			cap = new SortAttaque("Boule de feu", 50, 50);
			break;
		case 4:
			cap = new SortAttaque("Eclair", 60, 40);
			break;
		case 5:	
			cap = new Bouclier("Ecu", 60, 40);
			break;
		case 6:
			cap = new Bouclier("Pavois", 70, 30);
			break;
		case 7:	
			cap = new SortDefense("Champ de force", 70, 30);
			break;
		case 8:	
			cap = new SortDefense("Champ de protection", 40, 60);
			break;
		case 9:	
			cap = new Remede("Potion", 40, 60);
			break;
		case 10:
			cap = new Remede("Super-potion", 60, 40);
			break;
		case 11:	
			cap = new SortSoin("Soin", 50, 50);
			break;
		case 12:	
			cap = new SortSoin("Guerison", 70, 30);
			break;
		}
		return cap;	
	}
	
	/*
	 * Retire une capacite
	 */
	public void retireCapacite(){
		Scanner sc = new Scanner(System.in);
		int rep = 0;
		boolean err = false;
		do{
			err = false;
			System.out.println("Quelle capacite voulez-vous retirer?");
			for(int i=0; i < this.getTCapacites().length; i++)
				System.out.println(i+"-"+this.getTCapacites()[i]);
			try{
				rep = sc.nextInt();
			}
			catch(InputMismatchException e){
				int n = this.getTCapacites().length - 1;
				System.out.println("Choississez entre 0 et "+n);
				err = true;
				sc.nextLine();
			}
		}while(err || rep < 0 || rep > this.getTCapacites().length-1);
		int newTaille = this.getTCapacites().length-1;
		Capacite[] newTabCap = new Capacite[newTaille];
		
		for(int i=0; i<this.getTCapacites().length; i++){
			if(this.getTCapacites()[i] != this.getTCapacites()[rep]){
				newTabCap[i] = this.getTCapacites()[i];
			}
		}		
	}

	/* 
	 * controle de la vitalite initiale
	 * return true si VIT egale la formule 
	 */
	public boolean CheckVit_initiale(){
		if(this.VIT == (200 - (this.FOR + this.DEX + this.INT + this.CON) + this.EXP * 3)){
			return true;
		}
		return false;
	}
	
	/*
	 * controle de la somme des 4 aptitudes (FOR + DEX + INT + CON) = (100 + EXP)
	 * return true si somme est correct
	 */
	public boolean CheckEquilibre(){
		return (this.getFOR() + this.getDEX() + this.getINT() + this.getCON() == 100 + this.getEXP());
	}
	
	
	/*
	 * Augmente l'experience quand victoire
	 */
	public void LevelUp(){
		if(this.EXP < Combattant.EXP_PLAFOND && this.EXP >= Combattant.EXP_PLANCHER)
			this.EXP = this.EXP + 1;
	}
	
	/*
	 * Descend l'experience quand defaite
	 * Descend capacite ou pas????
	 */
	public void LevelDown(){
		if(this.EXP <= Combattant.EXP_PLAFOND && this.EXP > Combattant.EXP_PLANCHER)
			this.EXP = this.EXP - 1;
	}
	
	
	/*
	 * Compare l'EXP pour savoir qui commence 
	 * renvoie true si le combattant en instance commence
	 */
	public boolean quiCommence(Combattant c){
		double ret;
		Random rand = new Random();
		if(this.EXP > c.EXP){
			return true;
		}
		else if(this.EXP < c.EXP){
			return false;
		}
		else{
			ret = Math.random();
			if(ret > 0.5){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public void attaquer(Combattant c){
		
	}
		
	
	public static void main(String[] args) {
		
/*		
		//Tester contructeur champ a champ
		Capacite[] tCaps = new Capacite[2];
		Arme arme1 = new Arme("armeLOL", 53, 7);
		Arme arme2 = new Arme("armeXD", 43, 1);
		
		
		
		tCaps[0] = arme1;
		tCaps[1] = arme2;
		
		Combattant c1 = new Combattant("combattantFirst", 21, 142, 41, 1, 10, tCaps);
		Guerrier guerrier1 = new Guerrier();
		guerrier1.init();
		Combattant c2 = (Combattant) guerrier1;
		if(c2 instanceof Guerrier)
			System.out.println("Guerrier");
		else
			System.out.println("Combattant");
*/		
		Combattant c1 = new Combattant();
		Combattant c2 = new Combattant();
		boolean err;
		int rep = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Initialisation du combattant numero 1");
		c1.init();
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
		
		String nom = c1.getNom();
	
		switch(rep){
		case 1: Guerrier g = new Guerrier();
				c1 = g;
		break;
		case 2: Mage m = new Mage();
				c1 = m;
		break;
		case 3: Chasseur ch = new Chasseur();
				c1 = ch;
		break;
		}
		c1.setNom(nom);
		c1.initCap();
		//System.out.println(c1);
		
/*		
		//initialisation du combattant numero 2
		System.out.println("Initialisation du combattant numero 2");
		c2.init();
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
		
		String name = c2.getNom();
	
		switch(rep){
		case 1: Guerrier g = new Guerrier();
				c2 = g;
		break;
		case 2: Mage m = new Mage();
				c2 = m;
		break;
		case 3: Chasseur ch = new Chasseur();
				c2 = ch;
		break;
		}
		c2.setNom(name);
		c2.initCap();
*/		
		//Affiche les capacites pour que l'utilisateur choississe
		System.out.println("Choix de la capacite que vous voulez utiliser:");
		for(int i = 0; i<c1.getTCapacites().length;i++){
			System.out.println(i+1 +"- "+ c1.getTCapacites()[i]);
		}
		rep = sc.nextInt();
		Arme a = new Arme();
		Bouclier b = new Bouclier();

		//Verifier si quel type de capacite puis attribution de pba et eff en fonction
		if(c1.getTCapacites()[rep-1] instanceof Arme){
			do{
				System.out.println("Vous avez choisi une arme. Voulez-vous 1)attaquer, 2)defendre?");
				try{
					rep = sc.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1 ou 2!");
					err = true;
					sc.nextLine();
				}
			} while(rep < 1 || rep > 2 || err);
			switch(rep){
			case 1:
				a = (Arme)c1.getTCapacites()[rep-1];
				a.ArmeModeAttaque(c1);
				break;
			case 2:
				a= (Arme)c1.getTCapacites()[rep-1];
				a.ArmeModeDefense(c1);
				break;
			}
		}
		else if(c1.getTCapacites()[rep-1] instanceof Bouclier){
			do{
				System.out.println("Vous avez choisi un bouclier. Voulez-vous 1)defendre, 2)attaquer");
				try{
					rep = sc.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("Choississez 1 ou 2!");
					err = true;
					sc.nextLine();
				}
			} while(rep < 1 || rep > 2 || err);
			switch(rep){
			case 1: 
				b = (Bouclier)c1.getTCapacites()[rep-1];
				b.BouclierModeDefense(c1);
				break;
			case 2:
				b = (Bouclier)c1.getTCapacites()[rep-1];
				b.BouclierModeAttaque(c1);
				break;
			}
		}
		System.out.println(c1);
	}

}
