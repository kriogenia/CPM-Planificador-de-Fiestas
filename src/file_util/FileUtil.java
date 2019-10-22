package file_util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase abstracta que gesstiona los m�todos de escritura de ficheros
 * @author Ricardo Soto Est�vez
 */
public abstract class FileUtil {

	/**
	 * Escribe la cadena introducida en un nuevo fichero
	 * @param filePath	Ruta del fichero
	 * @param toPrint	Cadena a imprimir
	 */
	public static void writeFile(String filePath, String toPrint) {
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(toPrint);
            bw.newLine();
            bw.close();	}
		catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();	}
	}
	
	/**
	 * A�ade una cadena a un fichero existente
	 * @param filePath	ruta del fichero
	 * @param toAdd		cadena a a�adir
	 */
	public static void addToFile(String filePath, String toAdd) {
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            bw.write(toAdd);
            bw.newLine();
            bw.close();	} 
		catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();	}
	}
	
}