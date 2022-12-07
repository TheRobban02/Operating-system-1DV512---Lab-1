package Lab1;

public class Job {
    
    private int id;
    private int size;
    private String type;

    public Job (String type, int id, int size) {
        this.id = id;
        this.size = size;
        this.type = type;
    }

    public Job(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public Job(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

}
