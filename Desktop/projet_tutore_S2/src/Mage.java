
public class Mage extends Combattant{

	
	/* constructeur par defaut */
/*	public Mage(){
		super();
		this.setFOR(12);
		this.setDEX(8);
		this.setINT(30);
		this.setCON(30);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
	}*/
	
	/*
	 * Constructeur par defaut 
	 * Initialise les 4 aptitudes avec des valeurs aleatoires
	 * et respectant les contraintes
	 */
	public Mage(){
		super();
		while(!CheckContrainteMage()){
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
	public Mage(String nom, int f, int d, int i, int c, int e, Capacite[] tcaps){
		super(nom, f, d, i, c, e, tcaps);
	}
	
	/* constructeur par copie */
	public Mage(Mage m){
		super(m);
	}
/*	
	public void init(){
		super.init();
		this.setFOR(12);
		this.setDEX(8);
		this.setINT(30);
		this.setCON(30);
		this.setVIT(200 - (this.getFOR() + this.getDEX() + this.getINT() + this.getCON()) + this.getEXP() * 3);
		
		Capacite[] tcaps = new Capacite[2];
		this.setTCapacite(tcaps);
	}
*/
	public boolean CheckContrainteMage(){
		int max;
		if(this.getFOR() >= this.getDEX()){
			max = this.getFOR();
		}
		else{
			max = this.getDEX();
		}
		
		if(this.getINT() >= max + 15 && this.getCON() >= max + 15){
			return true;
		}
		return false;	
	}
	
	public String toString(){
		String s = "Mage "+this.getNom()+" [FOR="+this.getFOR()+", DEX="+this.getDEX()+", INT="+this.getINT()+
				", CON="+this.getCON()+", VIT="+this.getVIT()+", EXP="+this.getEXP()+"]\n";
		int n = this.getTCapacites().length;
		for(int i = 0; i<n; i++){
			s = s + getTCapacites()[i] + "\n";
		}
		return s;
	}
	
	
	
	public static void main(String[] args) {
		Mage M1 = new Mage();
		M1.init();
		System.out.println(M1);

	}

}
