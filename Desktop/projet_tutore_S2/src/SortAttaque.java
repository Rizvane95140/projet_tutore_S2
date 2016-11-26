
public class SortAttaque extends Capacite{

	private String categorie;
	private int PUI; 	//puissance
	private int FAC; 	//facilite
	private String nomSortAttaque;
	
	//accesseurs
	public String getCategorie(){
		return this.categorie;
	}
	
	public int getPUI(){
		return this.PUI;
	}
	
	public int getFAC(){
		return this.FAC;
	}
	
	public String getNomSortAttaque(){
		return this.nomSortAttaque;
	}
	
	//mutateurs
	public void setCategorie(String cat){
		this.categorie = cat;
	}
	
	public void setPUI(int p){
		if(p >= 0){
			this.PUI = p;
		}
	}
	
	public void setFAC(int f){
		if(f >= 0){
			this.FAC = f;
		}
	}
	
	public void setNomSortAttaque(String n){
		this.nomSortAttaque = n;
	}
	
	/* constructeur par defaut */
	public SortAttaque(){
		
	}
	
	/* constructeur champ a champ */
	public SortAttaque(String nomSortAttaque, int PUI, int FAC){
		super();
		this.PUI = PUI;
		this.FAC = FAC;
		this.nomSortAttaque = nomSortAttaque;
	}
	
	/* constructeur par copie */
	public SortAttaque(SortAttaque sa){
		super();
		this.FAC = sa.FAC;
		this.PUI = sa.PUI;
		this.nomSortAttaque = new String(sa.getNomSortAttaque());
		
	}
	
	/*
	 * Arme le sort d'attaque (attribution des valeurs de pba et eff)
	 */
	public void SortAttaqueModeAttaque(Combattant c){
		this.setPba((double) c.getINT() * this.FAC/1000);		//Normalement FAC/10000
		this.setEff(c.getCON() * this.PUI/100);
	}
	
	
	public String toString(){
		String s = "Sort d'attaque: "+this.nomSortAttaque+", PUI: "+this.PUI+", FAC: "+this.FAC;
		s = s + super.toString();
		return s;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Guerrier g = new Guerrier();
		SortAttaque sortArme = new SortAttaque("Eclair", 60, 40);
		sortArme.SortAttaqueModeAttaque(g);
		System.out.println(sortArme);
		
	}

}
