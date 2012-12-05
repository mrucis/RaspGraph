import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class RaspGraph {

	public static void main(String[] args) {
		try {
			SerialPortHandler serial = new SerialPortHandler();

			//console input
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));			
			String consolInput = null;
			//Contains serial port names
			HashMap mapSerial = serial.getPortNames();			
			if (mapSerial.isEmpty()) {
				System.out.println	("No Serial ports found!");
				return;
			} else if (mapSerial.size() == 1) {
				//connect to only port available
				serial.connect(mapSerial.get("0") + "");
				System.out.println("Connected to:"+mapSerial.get("0"));
				
			}else{
				// if more than one serial port available
				// ask user to input map number to get name
				System.out.println("Available ports:");
				//System.out.println(formater(mapSerial));
				formater(mapSerial);
				System.out.println("Enter number:");
				consolInput = reader.readLine();
				serial.connect(mapSerial.get(consolInput) + "");
				System.out.println("Connected to:"+mapSerial.get(consolInput));
			}
			
			//File processing part:
			HashMap mapFiles = serial.getPortNames();
			FileHandler file = new FileHandler();
			mapFiles = file.getFileNames();
			if(mapFiles.isEmpty()){
				System.out.println	("No Files found!");
			}else{
				System.out.println("Available files:");				
				formater(mapFiles);
				System.out.println("Enter number:");
				consolInput = reader.readLine();
				file.init(mapFiles.get(consolInput) + "");
				System.out.println("Selected file:"+mapFiles.get(consolInput));
			}
			
			//Store read valu from serial port
			String serialRead = "";
			// TODO enter key to quit loop
			//infinity loop
			while (1==1) {				
				serialRead=serial.read().trim();
				System.out.println(serialRead);
				if("READY".equals(serialRead)){
					//send commands to printing command to serial port
					serial.write(file.readFile());					
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	//make hashmap outprint more readable
	static private void formater(HashMap map){
	    Iterator iterator = map.keySet().iterator();  
	    //make sorted list
	    Set<String> sortedKeys = new TreeSet<String>();
	    sortedKeys.addAll(map.keySet());

	    for(String key: sortedKeys){
	    	 System.out.println(key  + " : " + map.get(key));
	    }
	    
	}

}




