package pruebas;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PrincipalDos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (BufferedInputStream in = new BufferedInputStream(new URL("https://disease.sh/assets/img/flags/fr.png").openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream("imagenes/im.png")) {
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				} catch (IOException e) {
				    // handle exception
				}





	}

}
