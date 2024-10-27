package myapp;

//import java.io.FileNotFoundException;
//import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {

        OpenFile f = new OpenFile();

        try {
            //f.openfile1(args[0]);
            f.OpenFile2(args[0]);
            System.out.println("Completed!");
        
        } catch (MyAppExecution ex) {
            System.err.println("ex.getMessage()");
            System.err.println("ex.getTotalBytesRead()");
            System.out.println("----------------------");
            ex.printStackTrace();
        
        //} catch (FileNotFoundException ex) {
            // Exception handlers
        //    System.out.printf("Your file does not exists: %s\n", args[0]);
        
        //} catch (IOException ex) {
            //System.err.printf("Cannot read the file");
        
        } finally {
            System.out.printf("\nI am in the finally block\n");
        }
        
    }

}
