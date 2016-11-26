import java.util.*;

public class Guerrier extends Combattant{

	/*
	 * Aleatoire tant que condition pas verifie
	 * Math.random(10)	->	entre 0 et 9
	 * 
	 * */
	
	/* constructeur par defaut */
	/*public Guerrier(){
		super();
		this.setFOR(40);
		this.setDEX(25);
		this.setINT(10);
		this.setCON(5);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
		
	}*/
	
	
	/*
	 * Constructeur par defaut 
	 * les 4 aptitudes sont initialises avec des valeurs aleatoires
	 * et respectant les contraintes
	 */
	public Guerrier(){
		super();
		while(!CheckContrainteGuerrier()){
			do{
				this.setFOR((int) (Math.random()*101));
				this.setDEX((int) (Math.random()*101));
				this.setINT((int) (Math.random()*101));
				this.setCON((int) (Math.random()*101));
			}while(!CheckEquilibre());
			this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		}	
	}
	
	/* constructeur champ a champ */
	public Guerrier(String nom, int f, int d, int i, int c, int e, Capacite[] tcaps){
		super(nom, f, d, i, c, e, tcaps);
	}
	
	/* constructeur par copie */
	public Guerrier(Guerrier g){
		super(g);
	}
/*	
	public void init(){
		super.init();		//initialise le nom
		this.setFOR(40);
		this.setDEX(25);
		this.setINT(10);
		this.setCON(5);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
	}
*/	
	public boolean CheckContrainteGuerrier(){
		return (this.getFOR() >= (this.getDEX() + 10) &&
		       (this.getDEX() + 10 >= this.getINT() + 10) && (this.getINT() + 10) >= this.getCON());	
	}
	
	
	
	public String toString(){
		
		String s = "Guerrier "+this.getNom()+" [FOR="+this.getFOR()+", DEX="+this.getDEX()+", INT="+this.getINT()+
				", CON="+this.getCON()+", VIT="+this.getVIT()+", EXP="+this.getEXP()+"]\n";
		int n = this.getTCapacites().length;
		for(int i = 0; i<n; i++){
			s = s + getTCapacites()[i] + "\n";
		}
		
		
		return s;
	}
	
		
	public static void main(String[] args) {
		
		Guerrier G1 = new Guerrier();
		G1.init();
		System.out.println(G1);
		
	}

}
