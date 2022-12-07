package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MemoryIO {

  public void writeToFile(MemoryManager mem) throws IOException {
    FileWriter writer = new FileWriter("savefile.data", StandardCharsets.UTF_8);
    try {
      writer.write("First fit\n");
      for (Block block : mem.getMemoryList()) { //Allocated blocks

        if(block.getJob() != null) {
            writer.write("Allocated blocks\n" + block.getJob().getId() + ";" + block.getStartAdress() + ";" + block.getEndAdress()+"\n");
        } else if (block.getJob() == null) {
          writer.write("Free blocks\n" + block.getStartAdress() + ";" + block.getEndAdress());
        }

    }
    } finally {
      writer.close();
    }

  }

  public void load(MemoryManager mem) throws IOException {

    File file = new File("app/src/test/resources/scenario1.in");

    try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {

      String line;
      int count = 0;

      while ((line = br.readLine()) != null) {

        String[] parts = line.split(";");

        if (count == 0) {
          int memorySize = Integer.parseInt(parts[0]);
          mem.setMemorySize(memorySize);
        } else if (parts.length == 3) {

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

    } catch (FileNotFoundException e) {
    }
  }

}
