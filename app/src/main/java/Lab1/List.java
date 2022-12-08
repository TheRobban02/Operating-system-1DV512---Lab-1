package Lab1;

import java.util.ArrayList;

public class List {

    private int memorySize;
    private ArrayList<Block> memoryList;
    private ArrayList<Job> jobList;

    public List() {
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

    public void addFirstBlock() {
        memoryList.add(new Block(0, memorySize - 1));
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void removeBlock(Block block) {
        memoryList.remove(block);
    }

    public void addBlock(Block block) {
        memoryList.add(block);
    }

}
