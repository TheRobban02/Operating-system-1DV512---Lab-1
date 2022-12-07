package Lab1;

public class Block {
    
    private int startAdress;
    private int endAdress;
    private Job job;

    public Block(int startAdress, int endAdress, Job job) {
        this.startAdress = startAdress;
        this.endAdress = endAdress;
        this.job = job;
    }

    public Block(int startAdress, int endAdress) {
        this.startAdress = startAdress;
        this.endAdress = endAdress;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public int getStartAdress() {
        return startAdress;
    }

    public int getEndAdress() {
        return endAdress;
    }

    public void setEndAdress(int endAdress) {
        this.endAdress = endAdress;
    }

    public void setStartAdress(int startAdress) {
        this.startAdress = startAdress;
    }
}
