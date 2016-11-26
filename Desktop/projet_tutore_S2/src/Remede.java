
public class Remede extends Capacite {
	private int PUI;
	private int FAC;
	private String nomSoin;

	public String getNomSoin() {
		return nomSoin;
	}

	public void setNomSoin(String nomSoin) {
		this.nomSoin = nomSoin;
	}

	public int getPUI() {
		return PUI;
	}
	
	public void setPUI(int p) {
		if (p >= 0) {
			this.PUI = p;
		}
	}

	public void setFAC(int f) {
		if (f >= 0) {
			this.FAC = f;
		}
	}

	public int getFAC() {
		return FAC;
	}


	public Remede() {

	}

	public Remede(String nomSoin, int PUI, int FAC) {
		this.PUI = PUI;
		this.FAC = FAC;
		this.nomSoin = nomSoin;
	}

	public Remede(Remede r) {
		this.PUI = r.PUI;
		this.FAC = r.FAC;
		this.nomSoin = new String(r.getNomSoin());
	}
	
	
	public String toString() {
		String s = "Remede: " + this.nomSoin + ", PUI: " + this.PUI + ", FAC: " + this.FAC;
		s = s + super.toString();
		return s;
	}
	
	/*
	 * Attribution des points de PBA et EFF en fonction du combattant
	 */
	public void RemedeModeSoin(Combattant c){
		this.setPba((double) (c.getDEX() * this.FAC/1000));		//normalement FAC/10000
		this.setEff((double)(c.getFOR() * this.PUI/100));
	}

	public static void main(String[] args){
		Guerrier g = new Guerrier();
		Remede r = new Remede("Potion", 40, 60);
		r.RemedeModeSoin(g);
		System.out.println(r);
	}
}
