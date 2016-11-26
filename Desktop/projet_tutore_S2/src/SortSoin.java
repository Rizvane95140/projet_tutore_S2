

public class SortSoin extends Capacite {
	private int PUI;
	private int FAC;
	private String nomSortSoin;
	
	public int getPUI() {
		return PUI;
	}
	public void setPUI(int pUI) {
		PUI = pUI;
	}
	public int getFAC() {
		return FAC;
	}
	public void setFAC(int fAC) {
		FAC = fAC;
	}
	public String getNomSortSoin() {
		return nomSortSoin;
	}
	public void setNomSortSoin(String nomSortSoin) {
		this.nomSortSoin = nomSortSoin;
	}
	
	public SortSoin(){
		
	}
	
	public SortSoin(String nomSortSoin, int PUI, int FAC){
		this.PUI = PUI;
		this.FAC = FAC;
		this.nomSortSoin = nomSortSoin;
		
	}
	
	public SortSoin(SortSoin s){
		this.PUI = s.PUI;
		this.FAC = s.FAC;
		this.nomSortSoin = new String(s.nomSortSoin);
	}
	
	public String toString(){
		String s = "Sort de soin: "+this.nomSortSoin+", PUI: "+this.PUI+", FAC: "+this.FAC;
		s = s + super.toString();
		return s;
	}
	
	
	/*
	 * Attribution des points de PBA et EFF en fonction du combattant
	 */
	public void SortSoinModeSoin(Combattant c){
		this.setPba(c.getINT() * this.FAC/1000);		//Normalement FAC/10000
		this.setEff(c.getCON() * this.PUI/100); 
	}
	
	public static void main(String[] args){
		Mage g = new Mage();
		SortSoin ss = new SortSoin("Guerison", 70, 30);
		ss.SortSoinModeSoin(g);
		System.out.println(ss);
	}
	
}
