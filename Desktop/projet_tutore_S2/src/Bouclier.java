import java.util.*;

public class Bouclier extends Capacite{

	private String nomBouclier;
	private int PRO; 	//protection
	private int MAN; 	//maniabilite
	
	/* accesseurs */
	public String getNomBouclier(){
		return this.nomBouclier;
	}
	
	public int getPRO(){
		return this.PRO;
	}
	
	public int getMAN(){
		return this.MAN;
	}
	
	/* mutateurs */
	public void setNomBouclier(String n){
		this.nomBouclier = n;
	}
	
	public void setPRO(int pro){
		if(pro>=0){
			this.PRO = pro;
		}
	}
	
	public void setMAN(int man){
		if(man>0){
			this.MAN = man;
		}
	}
	
	/* constructeur par defaut */
	public Bouclier(){
		
	}
	
	/* constructeur champ a champ */
	public Bouclier(String nomBouclier, int PRO, int MAN){
		super();
		this.PRO = PRO;
		this.MAN = MAN;
		this.nomBouclier = nomBouclier;		
	}
	
	/* constructeur par copie */
	public Bouclier(Bouclier b){
		super();
		this.PRO = b.PRO;
		this.MAN = b.MAN;
		this.nomBouclier = new String(b.getNomBouclier());
	}
	
	public String toString(){
		String s = "Bouclier: "+this.nomBouclier+", PRO: "+this.PRO+", MAN: "+this.MAN;
		s = s + super.toString();
		return s;
	}
	
	/*
	 * atttribution des bonnes valeurs pour pba et eff
	 * avant une attaque en fonction des armes
	 */
	public void BouclierModeAttaque(Combattant c){
		this.setPba((double)c.getDEX() * this.MAN /5000);
		this.setEff((double)c.getFOR() * this.PRO /50);
	}
	
	/*
	 * Attribution des bonnes valeurs pour pba et eff
	 * avant une defense selon les armes
	 */
	public void BouclierModeDefense(Combattant c){
		this.setPba((double)c.getDEX() * this.MAN / 1000);		// Normalement MAN/10000
		this.setEff((double)c.getFOR() * this.PRO / 100);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Guerrier g = new Guerrier();
		Bouclier b = new Bouclier("Pavois", 70, 30);
		b.BouclierModeDefense(g);
		System.out.println(b);
	}

}
