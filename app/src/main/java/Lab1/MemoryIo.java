package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class MemoryIO {

  public void writeToFile(MemoryManager mem, int counter) throws IOException {
    String strCounter;
    if(counter == 0) {
      strCounter = "";
    } else {
      strCounter = Integer.toString(counter);
    }
    FileWriter writer = new FileWriter("app/src/main/resources/Scenario1" + ".out" + strCounter, StandardCharsets.UTF_8);
    try {
      writer.write("First fit\n");
      writer.write("Allocated blocks\n");
      for (Block block : mem.getMemoryList()) { // Allocated blocks
        if (block.getJob() != null) {
          writer.write(block.getJob().getId() + ";" + block.getStartAdress() + ";" + block.getEndAdress() + "\n");
        }
      }
      writer.write("Free blocks\n");
      for (Block block : mem.getMemoryList()) { // Allocated blocks
        if (block.getJob() == null) {
          writer.write(block.getStartAdress() + ";" + block.getEndAdress() + "\n");
        }
      }

      writer.write("Fragmentation\n");
      double roundedFrag = roundAvoid(mem.calculateFragmentation(), 6);
      writer.write(roundedFrag+"");

    } finally {
      writer.close();
    }

  }

  public double roundAvoid(double value, int places) {
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
  }

  public void load(MemoryManager mem) throws IOException {

    File file = new File("app/src/main/resources/Scenario1.in");

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
