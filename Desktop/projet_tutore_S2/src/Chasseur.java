
public class Chasseur extends Combattant{

	
	/* constructeur par defaut */
/*	public Chasseur(){
		super();
		this.setFOR(20);
		this.setDEX(20);
		this.setCON(20);
		this.setINT(20);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
	}*/
	
	/*
	 * Constructeur par defaut
	 * Initialise les 4 capacites avec des valeurs aleatoires
	 * et respectant les contraintes
	 */
	public Chasseur(){
		super();
		while(!CheckContrainteChasseur()){
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
	public Chasseur(String nom, int f, int d, int i, int c, int e, Capacite[] tcaps){
		super(nom, f, d, i, c, e, tcaps);
	}
	
	/* constructeur par copie */
	public Chasseur(Chasseur c){
		super(c);
	}
/*	
	public void init(){
		super.init();
		this.setFOR(20);
		this.setDEX(20);
		this.setCON(20);
		this.setINT(20);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
	}
*/	
	public boolean CheckContrainteChasseur(){
		if(this.getFOR() >= 20 && this.getDEX() >= 20
				&& this.getINT() >= 20 && this.getCON() >= 20){
			return true;
		}
		return false;
	}
	
	public String toString(){
		String s = "Chasseur "+this.getNom()+" [FOR="+this.getFOR()+", DEX="+this.getDEX()+", INT="+this.getINT()+
				", CON="+this.getCON()+", VIT="+this.getVIT()+", EXP="+this.getEXP()+"]\n";
		return s;
	}
	
	
	public static void main(String[] args) {
		Chasseur chasseur_1 = new Chasseur();
		chasseur_1.init();
		System.out.println(chasseur_1);
		

	}

}
