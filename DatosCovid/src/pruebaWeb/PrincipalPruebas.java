package pruebaWeb;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class PrincipalPruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cadena="{ \"long\": -4 }";
		
		String cadena3=cadena.substring(1, cadena.length()-1);
		String cadena4="["+cadena3+"]";
		
		String cadena2=cadena.replace("long", "longi");
		System.out.println(cadena2);
		List <String> listaString= new ArrayList<>();
		listaString.add("qqq");
		listaString.add("qqq");
		
		//Pais p=new Pais("ppp","ppp",listaString,"ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp","ppp");
		
		//Gson g = new Gson();
		//String str = g.toJson(p);
		//System.out.println(str);
		

	}

}
