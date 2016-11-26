
public class SortDefense extends Capacite{

	private String nomSortDefense;
	private int PUI;	//puissance
	private int FAC;	//facilite
	
	/* accesseurs */
	public String getNomSortDefense(){
		return this.nomSortDefense;
	}
	
	public int getPUI(){
		return this.PUI;
	}
	
	public int getFAC(){
		return this.FAC;
	}
	
	/* mutateurs */
	public void setNomSortDefense(String n){
		this.nomSortDefense = n;
	}
	
	public void setPUI(int p){
		if(p>=0){
			this.PUI = p;
		}
	}
	
	public void setFAC(int f){
		if(f>=0){
			this.FAC = f;
		}
	}
	
	/* constructeur par defaut */
	public SortDefense(){
		
	}
	
	/* constructeur champ a champ */
	public SortDefense(String nomSortDefense, int PUI, int FAC){
		super();
		this.nomSortDefense = nomSortDefense;
		this.PUI = PUI;
		this.FAC = FAC;
	}
	
	/* constructeur par copie */
	public SortDefense(SortDefense sd){
		super();
		this.nomSortDefense = new String(sd.getNomSortDefense());
		this.PUI = sd.PUI;
		this.FAC = sd.FAC;
	}
	
	public String toString(){
		String s = "Sort de defense: "+this.nomSortDefense+", PUI: "+this.PUI+", FAC: "+this.FAC;
		s = s + super.toString();
		return s;
	}
	
	/*
	 * Arme le sort de defense (attribution des valeurs de pba et eff)
	 */
	public void SortDefenseModeDefense(Combattant c){
		this.setPba((double) c.getINT() * this.FAC/1000);		//Normalement FAC/10000
		this.setEff(c.getFOR() * this.PUI/100);
	}
	
	public static void main(String[] args) {
		Guerrier g = new Guerrier();
		SortDefense sortDef = new SortDefense("Champ de protection", 40, 60);
		sortDef.SortDefenseModeDefense(g);
		System.out.println(sortDef);

	}

}
