package pruebaWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

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
			System.out.println("3: Consultar top paises");
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
				// String url = "https://disease.sh/v3/covid-19/countries";
				String respuesta0 = "";
				try {
					respuesta0 = peticionHttpGet(url + "/countries");
					Gson gson = new Gson();
					// Para que no haya conflicto con la palabra reservada long
					String copiaArreglada = respuesta0.replace("long", "longi");
					String fichPaises = "fichPaises.txt";
					// OutputStream out= null;
					try (BufferedWriter out = new BufferedWriter (new FileWriter(fichPaises))) {

						Pais[] listaPaises = gson.fromJson(copiaArreglada, Pais[].class);
						for (int i = 0; i < listaPaises.length; i++) {
							out.write(listaPaises[i].getCountry()+", "+listaPaises[i].getCountryInfo().getIso3()+"\r\n");
							System.out.println(listaPaises[i].getCountry()+", "+listaPaises[i].getCountryInfo().getIso3());
						}
						out.flush();
						System.out.println(listaPaises.length);
						System.out.println("prueba");
					}
					catch (IOException e) {
						e.printStackTrace();
					}
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
				int N=Runtime.getRuntime().availableProcessors();
				System.out.println(Runtime.getRuntime().availableProcessors());
				
				
				break;

			case 3:
				long start = System.currentTimeMillis();

				// vamos a crear 4 hilos para ir mas rapido
				
				int lineasFich=cuentaLineas("fichPaises.txt");
				int tamBloque=lineasFich/4;
				
				HiloPais h1= new HiloPais(1, tamBloque, "fichPaises.txt");
				h1.start();
				HiloPais h2= new HiloPais(tamBloque+1, 2*tamBloque, "fichPaises.txt");
				h2.start();
				HiloPais h3= new HiloPais(2*tamBloque+1, 3*tamBloque, "fichPaises.txt");
				h3.start();
				HiloPais h4= new HiloPais(3*tamBloque+1, lineasFich, "fichPaises.txt");
				h4.start();
				
				try {
					h1.join(); h2.join(); h3.join(); h4.join();
					long tiempo = System.currentTimeMillis() - start;
					System.out.println("He tardado:" + tiempo + " ms.");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				break;

			case 4:
				System.out.println("caso 4");
				
				
				break;

			case 5:
				System.out.println("hola, caso 5");
				int lineas=cuentaLineas("fichPaises.txt");
				int tamBloq=lineas/4;
				HiloPais h= new HiloPais(3*tamBloq+1, lineas, "fichPaises.txt");
				h.start();
				try {
					h.join();
					ArrayList<Pais> listaP= h.getPaisesHilo();
					Collections.sort(listaP);
					for (int i = 0; i < listaP.size(); i++) {
						System.out.println(listaP.get(i).getCountry()+": "+listaP.get(i).getCases());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;

			case 6:
				long startt = System.currentTimeMillis();
				int lineasFichh=cuentaLineas("fichPaises.txt");
				HiloPais hh= new HiloPais(1, lineasFichh, "fichPaises.txt");
				hh.start();

				try {
					hh.join();

					long tiempoo = System.currentTimeMillis() - startt;
					System.out.println("He tardado:" + tiempoo + " ms.");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");

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
	
	public static int cuentaLineas (String fich) {
		int numLineas=0;
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fich)))){
			String linea;				
			while ((linea = in.readLine()) != null) {
				numLineas++;
			}		
		}catch(IOException e) {
			e.printStackTrace();
		}
		return numLineas;
	}
	
	public static ArrayList<Pais> getTop5 (ArrayList<Pais> lista) {
		ArrayList<Pais> listaDevolver=new ArrayList<>();
		for (int i=0; i<5; i++) {
			listaDevolver.add(lista.get(i));			
		}
		System.out.println("dentro del getTop5");
		for (int i = 0; i < listaDevolver.size(); i++) {
			System.out.println(listaDevolver.get(i).getCountry()+": "+listaDevolver.get(i).getCases());
		}
		
		int j=5;
		int top1, top2, top3, top4, top5, aspirante;
		while (j<lista.size()) {
			aspirante=0;
			try {
				top1= Integer.parseInt(listaDevolver.get(0).getCases());
			}catch (NumberFormatException e){
				top1=0;
			}
			
			try {
				top2= Integer.parseInt(listaDevolver.get(1).getCases());
			}catch (NumberFormatException e){
				top2=0;
			}
			
			try {
				top3= Integer.parseInt(listaDevolver.get(2).getCases());
			}catch (NumberFormatException e){
				top3=0;
			}
			
			try {
				top4= Integer.parseInt(listaDevolver.get(3).getCases());
			}catch (NumberFormatException e){
				top4=0;
			}
			
			try {
				top5= Integer.parseInt(listaDevolver.get(4).getCases());
			}catch (NumberFormatException e){
				top5=0;
			}
			
			try {
				aspirante= Integer.parseInt(lista.get(j).getCases());
			}catch (NumberFormatException e){
				aspirante=0;
			}
			
			if (aspirante>top1) {
				if (lista.get(j).getCountry().equals("UK")) {
					System.out.println("ha entrado cuando se compara con top1"+", paso "+j);
				}
				
				if (listaDevolver.get(0).getCountry().equals("UK")) {
					System.out.println("ha salido cuando se compara con top2 y "+lista.get(j).getCountry()+", paso "+j);
				}
				listaDevolver.set(0, lista.get(j));
			}
			else if (aspirante>top2) {
				if (lista.get(j).getCountry().equals("UK")) {
					System.out.println("ha entrado cuando se compara con top2"+", paso "+j);
				}
				
				if (listaDevolver.get(1).getCountry().equals("UK")) {
					System.out.println("ha salido cuando se compara con top2 y "+lista.get(j).getCountry()+", paso "+j);
				}
				listaDevolver.set(1, lista.get(j));
			}
			else if (aspirante>top3) {
				if (lista.get(j).getCountry().equals("UK")) {
					System.out.println("ha entrado cuando se compara con top3"+", paso "+j);
				}
				
				if (listaDevolver.get(2).getCountry().equals("UK")) {
					System.out.println("ha salido cuando se compara con top2 y "+lista.get(j).getCountry()+", paso "+j);
				}
				listaDevolver.set(2, lista.get(j));
			}
			else if (aspirante>top4) {
				if (lista.get(j).getCountry().equals("UK")) {
					System.out.println("ha entrado cuando se compara con top4"+", paso "+j);
				}
				
				if (listaDevolver.get(3).getCountry().equals("UK")) {
					System.out.println("ha salido cuando se compara con top2 y "+lista.get(j).getCountry()+", paso "+j);
				}
				listaDevolver.set(3, lista.get(j));
			}
			else if (aspirante>top5) {
				if (lista.get(j).getCountry().equals("UK")) {
					System.out.println("ha entrado cuando se compara con top5"+", paso "+j);
				}
				
				if (listaDevolver.get(4).getCountry().equals("UK")) {
					System.out.println("ha salido cuando se compara con top2 y "+lista.get(j).getCountry()+", paso "+j);
				}
				listaDevolver.set(4, lista.get(j));
			}
			j++;
		}
		System.out.println("fin del getTop5");
	
		
		
		
		return listaDevolver;

	}
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<Integer> getTop5Integer (ArrayList<Integer> lista) {
		ArrayList<Integer> listaDevolver=new ArrayList<>();
		for (int i=0; i<5; i++) {
			listaDevolver.add(lista.get(i));			
		}
//		System.out.println("dentro del getTop5");
//		for (int i = 0; i < listaDevolver.size(); i++) {
//			System.out.println(listaDevolver.get(i).getCountry()+": "+listaDevolver.get(i).getCases());
//		}
		
		int j=5;
		int top1, top2, top3, top4, top5, aspirante;
		while (j<lista.size()) {
			aspirante=0;
			top1= listaDevolver.get(0);
			top2= listaDevolver.get(1);
			top3= listaDevolver.get(2);
			top4= listaDevolver.get(3);
			top5= listaDevolver.get(4);
			aspirante= lista.get(j);
		
			
			if (aspirante>top1) {
				listaDevolver.set(0, lista.get(j));
			}
			else if (aspirante>top2) {
				listaDevolver.set(1, lista.get(j));
			}
			else if (aspirante>top3) {
				listaDevolver.set(2, lista.get(j));
			}
			else if (aspirante>top4) {
				listaDevolver.set(3, lista.get(j));
			}
			else if (aspirante>top5) {
				listaDevolver.set(4, lista.get(j));
			}
			j++;
		}

		return listaDevolver;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}