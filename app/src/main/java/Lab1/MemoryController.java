package Lab1;

import java.io.IOException;

public class MemoryController {
    
    public void firstFit(MemoryManager mem, MemoryIO memIO) {
        
        try {
            memIO.load(mem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mem.addFirstBlock();

        for (Job job : mem.getJobList()) {
            if(job.getType().equals("A")) {
                mem.firstFit(job);
            } else if(job.getType().equals("D")) {
                mem.deAllocation(job);
            }
        }

        System.out.println("First fit: ");
        //TEMPORARY
        System.out.println("Allocated blocks: ");
        for (Block block : mem.getMemoryList()) { //Allocated blocks

            if(block.getJob() != null) {
                System.out.println(block.getJob().getId() + ";" + block.getStartAdress() + ";" + block.getEndAdress());
            }
        }

        System.out.println("Free Blocks: ");
        for (Block block : mem.getMemoryList()) { //Empty blocks
  
            if(block.getJob() == null) {
                System.out.println(block.getStartAdress() + ";" + block.getEndAdress());
            }
        }

        System.out.printf("Fragmentation %.6f%n", mem.calculateFragmentation());
        //TEMPORARY

        try {
            memIO.writeToFile(mem);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void bestFit(MemoryManager mem, MemoryIO memIO) {
        
        try {
            memIO.load(mem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mem.addFirstBlock();

        for (Job job : mem.getJobList()) {
            if(job.getType().equals("A")) {
                mem.bestFit(job);
            } else if(job.getType().equals("D")) {
                mem.deAllocation(job);
            }
        }

        System.out.println("Best Fit: ");
        //TEMPORARY
        System.out.println("Allocated blocks: ");
        for (Block block : mem.getMemoryList()) { //Allocated blocks

            if(block.getJob() != null) {
                System.out.println(block.getJob().getId() + ";" + block.getStartAdress() + ";" + block.getEndAdress());
            }
        }

        System.out.println("Free Blocks: ");
        for (Block block : mem.getMemoryList()) { //Empty blocks
  
            if(block.getJob() == null) {
                System.out.println(block.getStartAdress() + ";" + block.getEndAdress());
            }
        }

        System.out.printf("Fragmentation %.6f%n", mem.calculateFragmentation());
        //TEMPORARY

    }

}
