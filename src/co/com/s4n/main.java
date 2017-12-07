package co.com.s4n;

import co.com.s4n.thread.DronThread;
import co.com.s4n.util.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author PABLO
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
    	DronThread dronThread;
    	Stream<String> stream;
    	
    	for (int i = 1; i <= Constantes.DRONES; i++) {
    		stream = Files.lines(Paths.get("Files/inFiles/in"+(i<10?"0"+i:i)+".txt"));
    		dronThread = new DronThread(stream,i);
    		dronThread.start();
		}
    }       
}
