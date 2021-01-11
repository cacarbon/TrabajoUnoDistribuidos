package pruebaWeb;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.google.gson.Gson;

public class HiloPais extends Thread {

	private int lineaOrigen;
	private int lineaFinal;
	private String fich;
	private ArrayList <Pais> paisesHilo;
	private ArrayList <String> stringPaisesHilo;
	private String unicoSstringPaisesHilo;
	private int numCasosSuma;
	
	
	public HiloPais(int lineaOrigen, int lineaFinal, String fich) {
		super();
		this.lineaOrigen = lineaOrigen;
		this.lineaFinal = lineaFinal;
		this.fich = fich;
		this.paisesHilo = new ArrayList<>();
		this.stringPaisesHilo= new ArrayList<>();
		this.unicoSstringPaisesHilo="";
		this.numCasosSuma=0;
	}

	public int getNumCasosSuma() {
		return numCasosSuma;
	}

	public void setNumCasosSuma(int numCasosSuma) {
		this.numCasosSuma = numCasosSuma;
	}

	public void run() {
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fich)));){
			String linea;
			int contador=1;
			while ((linea = in.readLine()) != null &&contador<lineaOrigen) {
				contador++;
			}
			this.unicoSstringPaisesHilo=this.unicoSstringPaisesHilo+linea.substring(linea.indexOf(",")+2)+"%2C%20";
			this.stringPaisesHilo.add(linea.substring(linea.indexOf(",")+1));
			while ((linea = in.readLine()) != null &&contador<lineaFinal) {
				this.unicoSstringPaisesHilo=this.unicoSstringPaisesHilo+linea.substring(linea.indexOf(",")+2)+"%2C%20";
				this.stringPaisesHilo.add(linea.substring(linea.indexOf(",")+1));
				contador++;
			}
			
			
			try {
				String respuesta0 = peticionHttpGet("https://disease.sh/v3/covid-19/countries/"+this.unicoSstringPaisesHilo);
				Gson gson = new Gson();
				// Para que no haya conflicto con la palabra reservada long
				String copiaArreglada = respuesta0.replace("long", "longi");
				Collections.addAll(this.paisesHilo, gson.fromJson(copiaArreglada, Pais[].class));
				for (int i = 0; i < this.paisesHilo.size(); i++) {
					try (BufferedInputStream inn = new BufferedInputStream(new URL(this.paisesHilo.get(i).getCountryInfo().getFlag()).openStream());
							  FileOutputStream fileOutputStream = new FileOutputStream("imagenes/"+this.paisesHilo.get(i).getCases()+"-"+this.paisesHilo.get(i).getCountry()+".png")) {
							    byte dataBuffer[] = new byte[1024];
							    int bytesRead;
							    while ((bytesRead = inn.read(dataBuffer, 0, 1024)) != -1) {
							        fileOutputStream.write(dataBuffer, 0, bytesRead);
							    }
							} catch (IOException e) {
							    // handle exception
							}
				}
				//Collections.sort(this.paisesHilo);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				

			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Pais> getPaisesHilo() {
		return paisesHilo;
	}

	public void setPaisesHilo(ArrayList<Pais> paisesHilo) {
		this.paisesHilo = paisesHilo;
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
	
}