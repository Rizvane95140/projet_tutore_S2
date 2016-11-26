import java.util.*;

public class Arme extends Capacite{
	
	private int IMP;	//impact
	private int MAN;	//maniabilite
	private String nomArme;
	
	
	/* accesseurs */
	public int getIMP(){
		return this.IMP;
	}
	
	public int getMAN(){
		return this.MAN;
	}
	
	public String getNomArme(){
		return this.nomArme;
	}
	
	/* Mutateurs */
	public void setIMP(int imp){
		if(imp >= 0){
			this.IMP = imp;
		}
	}
	
	public void setMAN(int man){
		if(man >= 0){
			this.MAN = man;
		}
	}
		
	public void setNomArme(String n){
		this.nomArme = n;
	}
	
	/* constructeur par defaut */
	public Arme(){
		
	}
	
	/* constructeur champ a champ */
	public Arme(String nomArme, int IMP, int MAN){
		super();
		this.IMP = IMP;
		this.MAN = MAN;
		this.nomArme = nomArme;
	}
	
	/* constructeur par copie */
	public Arme(Arme a){
		super(a);
		this.IMP = a.IMP;
		this.MAN = a.MAN;
		this.nomArme = new String(a.getNomArme());
	}
	
	
	/*
	 * atttribution des bonnes valeurs pour pba et eff
	 * avant une attaque en fonction des armes
	 */
	public void ArmeModeAttaque(Combattant c){
		Random rand = new Random();
		this.setPba((double) c.getDEX() * this.MAN / 1000);	//normalement MAN/10000

		//attribution de l'efficacite
		int pourcentage = 25;
		int normal = (int) (c.getFOR() * this.IMP / 100);
		//math.random => [0;1)
		// -25%
		if(Math.random() < 0.5){
			int pourcentageEnleve = rand.nextInt(pourcentage+1);	//random dans l'intervalle [0;25]
			double real = normal - normal*pourcentageEnleve/100;
			this.setEff(real);
		}
		// +25%
		else{
			int pourcentageAjoute = rand.nextInt(pourcentage+1);
			double real = normal + normal*pourcentageAjoute/100;
			this.setEff(real);
		}
	}
	
	/* 
	 * Attribution des bonnes valeurs pour pba et eff
	 * avant une defense selon les armes
	 */
	public void ArmeModeDefense(Combattant c){
		Random rand = new Random();
		//attribution de pba
		this.setPba((double) c.getDEX() * this.MAN / 5000);
		
		//Attribution de l'eff
		int pourcentage = 25;
		double normal = (c.getFOR() * this.IMP / 50); 
		// -25%
		if(Math.random() < 0.5){
			int pourcentageEnleve = rand.nextInt(pourcentage+1);
			double real = normal - normal*pourcentageEnleve/100;
			this.setEff(real);
		}
		// +25%
		else{
			int pourcentageAjoute = rand.nextInt(pourcentage+1);
			double real = normal + normal*pourcentageAjoute/100;
			this.setEff(real);
		}
	}


	public String toString(){
		String s = "Arme: "+this.nomArme+", IMP: "+this.IMP+", MAN: "+this.MAN;
		s = s + super.toString();
		return s;
	}
	
	
	public static void main(String[] args) {
		Guerrier g = new Guerrier();
		Capacite[] tcaps = new Capacite[2];
		Arme a = new Arme("Epee", 60, 40);
		tcaps[0] = a;
		g.setTCapacite(tcaps);
		a.ArmeModeDefense(g);
		System.out.println(a);
		
	}

}
