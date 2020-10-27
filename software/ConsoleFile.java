import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import java.util.*;

public class ConsoleFile{
	
	/*public static String[] read(String filename){

		FileWriter fw = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter("C:\\archivos\\estudiantes.txt");
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for (Estudiante estudiante : lista) {
				//escribe los datos en el archivo
				bfwriter.write(estudiante.getCedula() + "," + estudiante.getNombres() + "," + estudiante.getApellidos()
						+ "," + estudiante.getTelefono() + "," + estudiante.getDireccion() + "\n");
			}
			//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}*/

	public static String[] arrayListToStrings(ArrayList<String> al)
	{
		String []out = new String[al.size()];
		int count = 0;
		for (String token : al) {
			out[count++] = token;
			//System.out.print(token);
		}//System.out.println(); 
		return out;
	}

	public static String[] processLine(String line)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(line,";");

		while (st.hasMoreTokens()){
            tokens.add(st.nextToken()); 
		} 

		return arrayListToStrings(tokens);
	}

	public static void imprimir(String cadena)
	{
		System.out.println(cadena);
	}

	public static String buscarExpresion(String linea,String patron)
	{
      	Pattern r; 
      	Matcher m;

      	r = Pattern.compile(patron);
		m = r.matcher(linea);
		if (m.find( )) {
			return m.group(0);
		}
		return null;
	}

	public static String filtrar(String temp)
	{
		return temp.replace("[","").replace("]","").replace("(","").replace(")","");
	}

	public static void leer(String ruta)
	{
		File file;
		BufferedReader br;
		int contador = 0;
		String st, linea1,linea2; 

		try{
			file = new File(ruta);
			br = new BufferedReader(new FileReader(file)); 

			while ((st = br.readLine()) != null)
			{ 
				if(st.length()>0 && Character.isDigit(st.charAt(0)))
				{
					linea1 = buscarExpresion(st,"\\[[^\\[]*\\]");// \\[[^\\[]*\\]
					if(linea1!=null)
					{
						linea2 = buscarExpresion(st,"\\([^)]*\\)");//\\([^)]*\\)
						if(linea2!=null)
						{
							imprimir(filtrar(linea1));
							imprimir(filtrar(linea2));
						}
					}
				}
				//imprimir(""+Arrays.toString(resultado));
			} 
		}
		catch(Exception e)
		{
			imprimir("Excepcion leer:"+e);
		}
	}

	//Crea el archivo en disco, retorna la lista de estudiantes
	//Lee cadenas con salto de linea al final
	public static String[] read(String file_name){
		// crea el flujo para leer desde el archivo
		File file = new File(file_name);
		ArrayList<String> out = new ArrayList<String>(); 
		Scanner scanner;
		//print(file_name);
		try {
			//se pasa el flujo al objeto scanner
			scanner = new Scanner(file);
			imprimir(""+file.getName()+" "+scanner.hasNextLine());
			while (scanner.hasNextLine()) {
				// el objeto scanner lee linea a linea desde el archivo				
				out.add(scanner.nextLine());				
			}
			//se cierra el ojeto scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Error no se encuentra el archivo "+file_name);
		}
		return arrayListToStrings(out);
	}

	public static String[] stringToArray(String str){
		StringTokenizer st = new StringTokenizer(str,";");
		String[] out = new String[st.countTokens()];
		int count = 0;
		while(st.hasMoreTokens()){
			out[count++] = st.nextToken();
			//System.out.print(token);
		}//System.out.println(); 
		return out;
	}

	public static String getRuta(String ruta)
	{
		return new File(ruta).getPath();
	}


	public static String[] listarCanciones(String ruta)
	{
		String[] canciones = listarCarpetas(ruta);
		//System.out.println(Arrays.toString(canciones));
		leer(canciones[0]);
		return null;
		//return leer(canciones[0]);
	}

	public static String[] listarCarpetas(String ruta)
	{
		ArrayList<String> carpetas; 
		File directoryPath;
		String files[];

		carpetas = new ArrayList<String>();
		
		try{
			directoryPath = new File(ruta);
			//List of all files and directories
			String contents[] = directoryPath.list();
			//System.out.println("List of files and directories in the specified directory:");
			for(int i=0; i<contents.length; i++) {
				 //System.out.println(contents[i]+" "+new File(ruta+contents[i]).isDirectory());
				 if(new File(ruta+contents[i]).isDirectory())
				 {

				 	if(!contents[i].equals(".git") && !contents[i].equals("software"))
				 	{
					 	files = new File(ruta+contents[i]).list();	
				 		carpetas.add(ruta+contents[i]+"/"+files[0]);
				 	}
				 }
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return arrayListToStrings(carpetas);
	}
}
