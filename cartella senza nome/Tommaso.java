import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tommaso  {
    public static void main (String[] args) throws IOException {
        Scanner keyInput = new Scanner(System.in);
        String message;
        String method;
        String output;
        Scanner input = new Scanner(System.in);
      //  FileReader f = new FileReader("messagio_cifrato.txt");
       // BufferedReader b;
        //b=new BufferedReader(f);

        File file = new File("messaggio_cifrato");
        if (!file.exists()) {
            try{
                System.out.println(file.createNewFile());
            }catch (IOException e){
                System.out.println("ERRORE: "+e);
            }
            System.out.println("file creato");
        } else {
            System.out.println("file esistente");
        }
        
        

        Cryto cryto = new Cryto();

        System.out.println("Inserisci un messaggio da cifrare o decifrare:");
        message = keyInput.nextLine();
        
        do{
            System.out.println("Scegli il tipo di algoritmo: (C = cifrario di cesare; X = metodo XOR)");
            method = keyInput.nextLine();
            method = method.toLowerCase();
        } while(!(method.equals("c")  || method.equals("x")));

        switch (method) {
            case "c":
                System.out.print("Inserisci l'intervallo di cifratura: ");
                int shifter = keyInput.nextInt();
                keyInput.nextLine();

                output = cryto.Cesar(message, shifter);
                try{
                    FileWriter fw = new FileWriter(file);
                    fw.write(output);
                    fw.flush();
                    fw.close();
                }catch(IOException e){
                    System.out.println("ERRORE: " + e);
                }

                System.out.println("Messaggio trasformato: " + output);
                System.out.println("Messaggio originale: " + cryto.Cesar(output, (0-shifter)));

                
                break;
        
            case "x":
                System.out.print("Inserisci la chiave di cifratura: ");
                String key = keyInput.nextLine();

                String output2 = cryto.xorAlgo(message, key);

                // try{
                //     FileWriter fw = new FileWriter(file);
                //     fw.write(output);
                //     fw.flush();
                //     fw.close();
                // }catch(IOException e){
                //     System.out.println("ERRORE: " + e);
                // }

                System.out.println("Messaggio trasformato: " + output2);
                //System.out.println("Messaggio originale: " + cryto.xorAlgo(output, key));
                break;
        }
      /*  System.out.println("Inserisci un messaggio da cifrare o decifrare:");
       message = b.readLine();
        System.out.print("la chiave per decifrarlo: ");
                int shifter = keyInput.nextInt();
                keyInput.nextLine();

                output = cryto.Cesar(message, shifter);
                try{
                    FileWriter fw = new FileWriter(file);
                    fw.write(output);
                    fw.flush();
                    fw.close();
                }catch(IOException e){
                    System.out.println("ERRORE: " + e);
                }

                System.out.println("Messaggio trasformato: " + output);
                System.out.println("Messaggio originale: " + cryto.Cesar(output, (0-shifter)));

                
                */ 
    }
}