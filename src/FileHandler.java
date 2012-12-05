import gnu.io.CommPortIdentifier;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;

public class FileHandler {
	FileInputStream fstream;
	DataInputStream in;
	BufferedReader br;
	String path = "";
	HashMap map;

	// list of files in current folder
	public HashMap getFileNames() throws Exception {
		map = new HashMap<String, String>();
		path = new java.io.File(".").getCanonicalPath();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		System.out.println("Current path: " + path);
		int nr = 0;
		for (File listOfFile : listOfFiles)
			// if its file add to map
			// exclude *.jar files and *.sh files and hidden files
			if (listOfFile.isFile()
					&& !(listOfFile.getName().startsWith(".") || (listOfFile
							.getName().endsWith(".sh")) || (listOfFile.getName().endsWith(".jar")) )) {
				map.put(nr + "", listOfFile.getName());
				nr++;
				// System.out.println(listOfFile.getName());
			}

		return map;
	}

	public HashMap getPortNames() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Enumeration portList = CommPortIdentifier.getPortIdentifiers();
			int nr = 0;
			while (portList.hasMoreElements()) {
				CommPortIdentifier portId = (CommPortIdentifier) portList
						.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					map.put(nr + "", portId.getName());
					// System.out.println(portId.getName());
				}
				nr++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public void init(String fileName) {
		try {

			fstream = new FileInputStream(path + "/" + fileName);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
		} catch (FileNotFoundException e) {
			System.out.println("No such file or directory: " + path + "/"
					+ fileName);
			System.exit(1);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String readFile() {

		String str = "";
		try {

			str = br.readLine();
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
		return str;
	}

	public void close() {
		try {
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
