package Lab1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class MemoryManager {
    
    private int memorySize;
    private ArrayList<Block> memoryList;
    private ArrayList<Job> jobList;

    public MemoryManager(int memorySize) {
        this.memorySize = memorySize - 1;
        this.jobList = new ArrayList<>();
        this.memoryList = new ArrayList<>();
    }

    public void addJob(Job job) {
        jobList.add(job);
    }
    
    public ArrayList<Job> getJobList() {
        return jobList;
    }

    public ArrayList<Block> getMemoryList() {
        return memoryList;
    }

    public void firstFit(Job job) {

        for (Block b : memoryList) {
            if(b.getJob() == null && b.getEndAdress()-b.getStartAdress() > job.getSize()) {
                b.setJob(job);
                b.setEndAdress(b.getStartAdress() + job.getSize() - 1);
                memoryList.add(new Block(b.getEndAdress() + 1, memorySize));
                break;
            }
        }
    }

    public void addFirstBlock() {
        memoryList.add(new Block(0, memorySize));
    }

    public void deAllocation(Job job) {

        for (int i = 0; i < memoryList.size(); i++) {
            Block block = memoryList.get(i);
            if(block.getJob() != null) {
                if(block.getJob().getId() == job.getId()) {
                    if(i == 0) { 
                        Block nextBlock = memoryList.get(i+1); //Slå ihop block om det är första platsen som är blocket
                        if(nextBlock.getJob() == null) {
                            nextBlock.setStartAdress(block.getStartAdress());
                            memoryList.remove(block);
                        } else {
                            block.setJob(null);
                        }
                        
                    } else {                    
                        Block previusBlock = memoryList.get(i-1); //Slå ihop blocken
                        if(previusBlock.getJob() == null) {
                            previusBlock.setEndAdress(block.getEndAdress()); 
                            memoryList.remove(block);
                        } else {
                            block.setJob(null);
                        }
                    }
                }
            }
        }
    }
}
