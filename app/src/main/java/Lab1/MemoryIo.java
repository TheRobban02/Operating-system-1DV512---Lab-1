package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import Lab1.MemoryManager;

public class MemoryIo {

    MemoryManager manager;

    public void Output() {

    }

    /**
   * Method to load the file savefile.data.
   */
  public void load() throws IOException {

    File file = new File("savefile.data");

    try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
      
      String line;
      while ((line = br.readLine()) != null) {
        
        String[] parts = line.split(";");

        // int id = Integer.parseInt(parts[1]);
        // int size = Integer.parseInt(parts[2]);

        // manager.addJob(new Job(parts[0], id, size));
        
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }

}
