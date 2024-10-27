package myapp;

import java.io.*;

public class OpenFile {
    
    public void OpenFile1(String fileName) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(fileName);
        byte[] buff = new byte[1024];
        while ((fis.read(buff)) > 0) {
            // Read the file
        }
        System.out.printf(">>> Completed reading the file");
        fis.close();
    }

    public void OpenFile2(String fileName) throws MyAppExecution {

        int totalRead = 0;
        FileInputStream fis = null;

        try{
            fis = new FileInputStream(fileName);
            byte[] buff = new byte[1024];
            int read = 0;
            while ((read = fis.read(buff)) > 0) {
                // Read the file
                totalRead += read;
            }
            System.out.printf("Completed reading the file\n");
        } catch (FileNotFoundException ex) {
            throw new MyAppExecution("Cannot open file %s".formatted(fileName), ex);

        } catch (IOException ex) {
            MyAppExecution myAppEx = new MyAppExecution("Error while processing %s".formatted(fileName), ex);
            myAppEx.setTotalBytesRead(totalRead);
            throw myAppEx;

        } finally {
            if (null != fis)
                try { fis.close();
                } catch (Exception ex) {

                };
        }

    }

}
