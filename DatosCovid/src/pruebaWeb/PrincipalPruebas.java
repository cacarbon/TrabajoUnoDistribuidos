package pruebaWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

public class PrincipalPruebas {
	private final static String url = "https://disease.sh/v3/covid-19";

	public static void main(String[] args) {

		java.util.Scanner entrada = new java.util.Scanner(System.in);

		int n = 0;

		while (n != 6) {

			System.out.println("-----------------------------------------------------------");
			System.out.println("Elige una opcion:");
			System.out.println("0: Mostrar un listado de los paises");
			System.out.println("1: Descargar las banderas (como imagen) de cada pais con el numero de casos en el nombre de la imagen, de forma concurrente (RECOMENDADO)");
			System.out.println("2: Descargar las banderas (como imagen) de cada pais con el numero de casos en el nombre de la imagen, de forma NO concurrente (NO RECOMENDADO)");
			System.out.println("3: Datos de un pais concreto");
			System.out.println("4: Consultar top, de forma concurrente (RECOMENDADO)");
			System.out.println("5: Consultar top, de forma NO concurrente (NO RECOMENDADO)");
			System.out.println("6: Exit");
			System.out.println("");

			n = entrada.nextInt();
			entrada.nextLine();
			
			
			
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
						if ( ! (listaPaises[i].getCountryInfo().getIso3()==null) )   {
							out.write(listaPaises[i].getCountry()+", "+listaPaises[i].getCountryInfo().getIso3()+"\r\n");
						}
						else {
							out.write(listaPaises[i].getCountry()+", "+listaPaises[i].getCountry()+"\r\n");
						}
						//System.out.println(listaPaises[i].getCountry()+", "+listaPaises[i].getCountryInfo().getIso3());
					}
					out.flush();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// Manejar excepción
				e.printStackTrace();
			}
			
			Path path = Paths.get("imagenes");
			try {
				Files.createDirectories(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			switch (n) {

			case 0:
				// String url = "https://disease.sh/v3/covid-19/countries";
				try (BufferedReader in = new BufferedReader (new FileReader("fichPaises.txt"))) {
					String linea;
					while( (linea=in.readLine())!=null  ) {
						System.out.println(linea);
					}
		
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case 1:
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

			case 2:
	
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

			case 3:
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

				} catch (Exception e) {

					e.printStackTrace();
				}
				break;



			case 4:
				System.out.println("caso 4");
				long ssstart = System.currentTimeMillis();
				System.out.println("introduce top");
				int topp = entrada.nextInt();
				int lineass=cuentaLineas("fichPaises.txt");
				
				int ttamBloque=lineass/4;
				
				HiloPais hh1= new HiloPais(1, ttamBloque, "fichPaises.txt");
				hh1.start();
				HiloPais hh2= new HiloPais(ttamBloque+1, 2*ttamBloque, "fichPaises.txt");
				hh2.start();
				HiloPais hh3= new HiloPais(2*ttamBloque+1, 3*ttamBloque, "fichPaises.txt");
				hh3.start();
				HiloPais hh4= new HiloPais(3*ttamBloque+1, lineass, "fichPaises.txt");
				hh4.start();
				

				try {
					hh1.join();
					hh2.join();
					hh3.join();
					hh4.join();

					ArrayList<Pais> listaP1= hh1.getPaisesHilo();
					ArrayList<Pais> listaP2= hh2.getPaisesHilo();
					ArrayList<Pais> listaP3= hh3.getPaisesHilo();
					ArrayList<Pais> listaP4= hh4.getPaisesHilo();
					ArrayList<Pais> lp=new ArrayList<>();
					lp.addAll(listaP1);
					lp.addAll(listaP2);
					lp.addAll(listaP3);
					lp.addAll(listaP4);
				
					Collections.sort(lp);
					
					System.out.println("\n");
					for (int i = 0; i < Math.min(topp, lp.size()); i++) {
						System.out.println(lp.get(i).toString());
					}
					long tttiempo = System.currentTimeMillis() - ssstart;
					System.out.println("He tardado:" + tttiempo + " ms.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;

			case 5:
				System.out.println("introduce top");
				int top = entrada.nextInt();
				
				long sstart = System.currentTimeMillis();
				int lineas=cuentaLineas("fichPaises.txt");
				HiloPais h= new HiloPais(1, lineas, "fichPaises.txt");
				h.start();
				try {
					h.join();
					ArrayList<Pais> listaP= h.getPaisesHilo();
					Collections.sort(listaP);
					System.out.println("\n");
					for (int i = 0; i < Math.min(top, listaP.size()); i++) {
						System.out.println(listaP.get(i).toString());
					}
					long ttiempo = System.currentTimeMillis() - sstart;
					System.out.println("He tardado:" + ttiempo + " ms.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;

			case 6:
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

	
}