package pruebaWeb;

import java.util.ArrayList;
import java.util.List;

public class ListaPaises {
	private ArrayList <Pais> paises;

	public ListaPaises() {
		this.paises =new ArrayList<Pais>();
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(ArrayList<Pais> paises) {
		this.paises = paises;
	}
	
	public int getLongitud() {
		return this.paises.size();
	}
	 public Pais getPais (int i) {
		 return this.paises.get(i);
	 }
	

}
