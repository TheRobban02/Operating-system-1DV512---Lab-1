package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MemoryIO {
    
    public void Output() {

    }

  /**
   * Method to load the file savefile.data.
   */
  public void load(MemoryManager mem) throws IOException {
    
    File file = new File("app/src/test/resources/scenario1.in");

    try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
      
      String line;
      int count = 0;

      while ((line = br.readLine()) != null) {
        
        String[] parts = line.split(";");

        if(count == 0) {
          int memorySize = Integer.parseInt(parts[0]);
          mem.setMemorySize(memorySize - 1);
        } else if(parts.length == 3) {
          
          String type = parts[0];
          int id = Integer.parseInt(parts[1]);
          int size = Integer.parseInt(parts[2]);
          
          mem.addJob(new Job(type, id, size));
        } else if (parts.length == 2) {

          String type = parts[0];
          int id = Integer.parseInt(parts[1]);
          mem.addJob(new Job(type, id));

        } else {

          String type = parts[0];
          mem.addJob(new Job(type));

        }

        count += 1;
        
      }

      br.close();
  
    } catch (FileNotFoundException e) {}
  }

}
