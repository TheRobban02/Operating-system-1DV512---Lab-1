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
        int counter = 0;

        for (Job job : mem.getJobList()) {
            if(job.getType().equals("A")) {
                mem.firstFit(job);
            } else if(job.getType().equals("D")) {
                mem.deAllocation(job);
            } else if(job.getType().equals("O")) {
                counter += 1;
                try {
                    memIO.writeToFile(mem, counter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            memIO.writeToFile(mem, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bestFit(MemoryManager mem, MemoryIO memIO) {
        
    }

}
