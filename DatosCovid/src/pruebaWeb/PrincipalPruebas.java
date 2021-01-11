package pruebaWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class PrincipalPruebas {
	private final static String url = "https://disease.sh/v3/covid-19";

	public static void main(String[] args) {

		java.util.Scanner entrada = new java.util.Scanner(System.in);

		int n = 0;

		while (n != 9) {

			System.out.println("-----------------------------------------------------------");
// System.out.println("You are in Directory " + this.getName());
			System.out.println("Elige una opcion:");
			System.out.println("0: Mostrar un listado de los paises");
			System.out.println("1: Datos de un pais concreto");
			System.out.println("2: Datos de un continente");
			System.out.println("3: Datos de un estado de EEUU");
			System.out.println("4: Create a new Text File");
			System.out.println("5: Create a new Binary File");
			System.out.println("6: Remove a Directory (with its contents) or File");
			System.out.println("7: Select a Directory");
			System.out.println("8: Select a File");
			System.out.println("9: Exit");
			System.out.println("");

			n = entrada.nextInt();
			entrada.nextLine();

			switch (n) {

			case 0:
				//String url = "https://disease.sh/v3/covid-19/countries";
				String respuesta0 = "";
				try {
					respuesta0 = peticionHttpGet(url+ "/countries");
					Gson gson = new Gson();
					// Para que no haya conflicto con la palabra reservada long
					String copiaArreglada=respuesta0.replace("long", "longi");

					 Pais[] listaPaises = gson.fromJson(copiaArreglada, Pais[].class);
					 for (int i=0; i<listaPaises.length; i++) {
						 System.out.println(listaPaises[i].getCountry());
					 }
					 System.out.println(listaPaises.length);
					System.out.println("prueba");
				} catch (Exception e) {
					// Manejar excepción
					e.printStackTrace();
				}
				break;

			case 1:
				String respuesta ="";
				System.out.println("Escripe el pais (en ingles) a consultar:");
				String stringPais = entrada.nextLine();
				try {

					respuesta = peticionHttpGet(url + "/countries/" + stringPais);
					Gson gson = new Gson();

// Para que no haya conflicto con la palabra reservada long
					String copiaArreglada = respuesta.replace("long", "longi");
					Pais pais = gson.fromJson(copiaArreglada, Pais.class);
					System.out.println(pais.toString());
//System.out.println("La respuesta es:\n" + peticionHttpGet(url+"/countries/"+stringPais));
				} catch (Exception e) {
// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("hola, caso 2");
				break;

			case 3:
				System.out.println("hola, caso 3");
				break;

			case 4:
				System.out.println("hola, caso 4");
				break;

			case 5:
				System.out.println("hola, caso 5");
				break;

			case 6:
				System.out.println("hola, caso 6");
				break;

			case 7:
				System.out.println("hola, caso 7");
				break;

			case 8:
				System.out.println("hola, caso 8");
				break;

			case 9:
				System.out.println("hola, caso 9");
				break;
			}
		}
	}

	public static String peticionHttpGet(String urlParaVisitar) throws Exception {
// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
//String resultado ="";
// Crear un objeto de tipo URL
		URL url = new URL(urlParaVisitar);

// Abrir la conexión e indicar que será de tipo GET
		URLConnection conexion = url.openConnection();

// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
// Cerrar el BufferedReader
		rd.close();
		return resultado.toString();
	}
}