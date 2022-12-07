package Lab1;

import java.util.ArrayList;

public class MemoryManager {
    
    private int memorySize;
    private ArrayList<Block> memoryList;
    private ArrayList<Job> jobList;

    public MemoryManager() {
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

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
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

    public double calculateFragmentation() {

        double largestFreeMemoryblock = 0;
        double totalFreeMemory = 0;

        for (Block block : memoryList) {
            if(block.getJob() == null) {
                double blockSize = (block.getEndAdress() + 1) - block.getStartAdress();
                if(blockSize > largestFreeMemoryblock) {
                    largestFreeMemoryblock = blockSize;
                }
                totalFreeMemory += blockSize;
            }
        }

        return (1 - (largestFreeMemoryblock / totalFreeMemory));
    }
}
