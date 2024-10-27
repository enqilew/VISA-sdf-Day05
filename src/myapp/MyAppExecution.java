package myapp;

public class MyAppExecution extends Exception {

    private int totalBytesRead = 0;

    public int getTotalBytesRead() {
        return totalBytesRead;
    }

    public void setTotalBytesRead(int totalBytesRead) {
        this.totalBytesRead = totalBytesRead;
    }
    
    // Overriding the constructor
    public MyAppExecution(){

    }

    public MyAppExecution(String msg) {
        // Call the superclass in the constructor
        // The first line
        super(msg);
    }

    public MyAppExecution(String msg, Throwable t) {
        super(msg, t);
    }
}
