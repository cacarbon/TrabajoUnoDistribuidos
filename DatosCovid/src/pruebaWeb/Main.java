package pruebaWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.google.gson.Gson;
class Main {
	public static void main(String[] args) {
		String url = "https://disease.sh/v3/covid-19/countries/Poland";
		String respuesta = "";
		try {
			respuesta = peticionHttpGet(url);
			System.out.println("La respuesta es:\n" + respuesta);
			Gson gson = new Gson();
			
//			String cadena3=respuesta.substring(1, respuesta.length()-1);
//			String cadena4="["+cadena3+"]";
			
			// Para que no haya conflicto con la palabra reservada long
			String copiaArreglada=respuesta.replace("long", "longi");
			
			 Pais pais = gson.fromJson(copiaArreglada, Pais.class);
			//Properties properties = gson.fromJson(respuesta, Properties.class);
			System.out.println(pais.toString());
			System.out.println("prueba");
		} catch (Exception e) {
			// Manejar excepción
			e.printStackTrace();
		}
	}

	public static String peticionHttpGet(String urlParaVisitar) throws Exception {
		// Esto es lo que vamos a devolver
		String resultado ="";
		// Crear un objeto de tipo URL
		URL url = new URL(urlParaVisitar);

		// Abrir la conexión e indicar que será de tipo GET
		//HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		URLConnection conexion =  url.openConnection();
		//conexion.setRequestMethod("GET");
		// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado=resultado+linea;
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder
		return resultado;
	}
}