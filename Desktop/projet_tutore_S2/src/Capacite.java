import java.util.*;
import java.io.*;

public class Capacite implements Serializable{
	
	private double pba;  
	private double eff;
	
	/* accesseurs */	
	public double getPba(){
		return this.pba;
	}
	
	public double getEff(){
		return this.eff;
	}
	
	
	/* Mutateurs */
	public void setPba(double p){
		if(p <= 1 && p >= 0){
			this.pba = p;
		}
	}
	
	public void setEff(double e){
		this.eff = e;
	}
	

	public String toString(){
		String s = " [PBA="+this.pba+", EFF="+this.eff+"]";
		return s;
	}
	
	/* constructeur par defaut */
	public Capacite(){
		
	}
	
	/* constructeur champ a champ */
	public Capacite(int pba, int eff){
		this.pba = pba;
		this.eff = eff;
	}
	
	/* constructeur par copie */
	public Capacite(Capacite c){
		this.eff = c.eff;
		this.pba = c.pba;
	}	
		
	public static void main(String[] args) {
		
		
	}

}
