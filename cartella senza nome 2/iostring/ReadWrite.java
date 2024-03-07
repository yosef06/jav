package iostring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadWrite {

    private final File myFile;

    public ReadWrite(String path) {
        this.myFile = new File(path);
    }

    public String readAll() throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        Scanner myReader = new Scanner(this.myFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            str.append(data);
        }
        myReader.close();
        return str.toString();
    }

    public void write(String data) throws IOException {
        FileWriter myWriter = new FileWriter(this.myFile);
        myWriter.write(data);
        myWriter.close();
    }
}


