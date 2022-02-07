package de.fx.spring.resources;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *This class can control a text-file.
 *If you inherit the class or create an object of the class, 
 *you only have to specify the path of the text-file. 
 *If the file doesn't exists it gets created automatically.
 *
 * @author David Baumer
 * 
 * @version 1.1
 */

public class TFController {



	private String file;
	private List<Object> list;
	private PrintWriter pWriter;
	private boolean firstIndexNull = false;

	/**
	 *@param file
	 *initialize list
	 *calls read method
	 */
	public TFController(String file) {
		this.file = file;
		list = new ArrayList<>();
		read();
	}





	/**
	 * 
	 * Writes value in the list and the file.
	 * If the first Object of the {@code ArrayList} equals null, 
	 * the Object get deleted and replaced by the second Object of the {@code ArrayList}.
	 * If the first object isn't null then it is checked 
	 * whether the value already exists
	 * The file-writer is only created when it is needed,
	 * otherwise errors will occur.
	 *
	 * @param value
	 * @throws IOExceptioin
	 */
	public void write(Object... value) {
		pWriter = null;

		try {
			if(list.get(0).equals(null)) {
			}
		}catch(Exception ex) {
			firstIndexNull = true;
			read();
		}

		for(Object o : value) {
			if(firstIndexNull == true) {

				list.add(o);
				list.set(0, list.get(1));
				list.remove(1);
				firstIndexNull = false;
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
					pWriter.println(list.get(0));
					pWriter.flush();
					pWriter.close();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}else if(firstIndexNull==false) {

				list.add(o);
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));

					for(int i=0;i<list.size();i++) {
						pWriter.println(list.get(i));
					}

					pWriter.flush();
					pWriter.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}


		}
	}




	/**
	 * Reads the data from the file and add it to the {@code ArrayList}
	 * And create the Path if the chosen Path doesn't exist
	 * 
	 * @throws NullPointerException if no path is chosen 
	 * @throws FileNotFoundException if the File doesen't exist
	 * @throws IOException
	 */
	public void read() {
		list.removeAll(list);
		String s;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			s= in.readLine();
			list.add(s);
			while(s!=null) {
				s= in.readLine();
				if(s!=null) {
					list.add(s);	
				}
			}
			in.close();
		}catch(FileNotFoundException e1){
			try {
				if(getPath().exists() == false) {
					getPath().mkdirs();
					System.out.println("The Path was created");
				}
			}	catch(NullPointerException|StringIndexOutOfBoundsException ex) {
				System.out.println("No complete path\r\n"
						+ "The file may be created in the current directory");
			}
			if(new File(file).exists() == false) {
				try {
					new File(file).createNewFile();
					System.out.println("The File was created");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e2) {
			e2.printStackTrace();
		}
	}



	/**
	 * 
	 * If s equals to a value in the file, 
	 * the value is deleted from the file and the {@code ArrayList}
	 * @param s
	 * @throws Exception
	 */

	public void deleteValue(String... value) {
		try {
			if(list.get(0).equals(null)) {

			}else {
				boolean exist = false;
				for(String s : value) {
					for(int i =0; i<list.size();i++) {
						if(list.get(i).equals(s)) {
							list.remove(list.get(i));
							writeByDelete();
							exist = true;
							break;
						}
					}
					if(!exist) {
					}
				}
			}
		}catch(Exception e) {
		}
	}






	/**
	 * 
	 * 
	 * deletes the value from the {@code ArrayList} 
	 * and the file which are on the position of 
	 * @param row
	 * @throws Exception
	 */
	public void deleteFromRow(int... row) {
		try {
			if(list.get(0).equals(null)) {
			}else {
				int count = 0;
				for(int in : row) {
					if(count>=1) {
						if(in-count>=list.size()) {
						}else {
							list.remove((in-count)-1);
							writeByDelete();
						}
					}else {
						if(in>=list.size()) {
						}else {
							list.remove(in-1);
							writeByDelete();
						}
					}
					count++;
				}
			}
		}catch(Exception e) {

		}
	}


	/**
	 * Delete all the data from the {@code ArrayList}
	 * and the File
	 * @throws Exception
	 */
	public void deleteAll() {
		try {
			if(list.get(0).equals(null)) {

			}else {

				list.removeAll(list);
				writeByDelete();

			}

		}catch(Exception e) {

		}
	}


	/**
	 * 
	 * 
	 * If a value get deleted this method get called 
	 * and write the new {@code ArrayList} to the file
	 * @throws IOException
	 */
	private void writeByDelete() throws IOException {
		pWriter = null;

		pWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(int i=0;i<list.size();i++) {		
			pWriter.println(list.get(i));
		}
		pWriter.flush();
		pWriter.close();
	}



	/**
	 * Checks return the chosen path to check if
	 * the chosen path exists
	 * @return file 
	 */
	private File getPath() {
		File path = null;
		for(int i=0; i<file.length();i++) {
			if(file.substring(i,i+1).equals(".")) {
				String p =file.substring(0,i);
				boolean there = false;
				while(!there) {
					i--;
					if(p.substring(i,i+1).equals("\\")) {
						path = new File(p.substring(0,i));
						there = true;
					}
				}
				break;
			}
		}
		return path;
	}

	/**
	 * @return the list
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
		read();
	}

	/**
	 * @return the list
	 */
	public List<Object> getList() {
		return list;
	}
	
}
