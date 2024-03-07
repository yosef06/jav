import crypto.*;

import iostring.ReadWrite;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static String path;

    public static void main(String[] args) throws IOException {
        inputPath();
        ReadWrite fileObj = new ReadWrite(path); // create the obj which contains the path

        String result = fileObj.readAll(); // read the content of the file
        label:
        while (true) {
            String res = inputMenu("Cosa vuoi fare [c=cripta/d=decripta/e=esci]: ");
            switch (res) {
                case "c":
                    result = algoSelect().crypt(result);
                    break;
                case "d":
                    result = algoSelect().decrypt(result);
                    break;
                case "e":
                    /*
                        A break with a label is not the same as a goto statement.
                        Java does not have a goto statement.

                        A label marks the statement that follows it.
                        You can use it to break out of that statement, and only out of that statement.
                        Control of flow will always transfer to the end of the labeled statement.

                        # source: https://stackoverflow.com/questions/28381212/how-to-use-labels-in-java
                     */
                    break label;
                default:
                    System.out.println("Selezione invalida!");
            }
            fileObj.write(result);
        }
    }

    private static CryptoInterface algoSelect() {
        CryptoInterface value;
        String res = inputMenu("Seleziona [c=algo. Cesare/d=algo. Cesare Migliorato/x=XOR cipher/a=Cripto. Sim./i=indietro]: ");

        value = switch (res) { // Versione nuova di uno switch con Java >= 13
            case "c" -> new Cesar();
            case "d" -> new CesarImproved();
            case "x" -> new XORCipher();
            case "a" -> new SymmetricKeyCipher();
            default -> new Cesar(0); // @FIXME: Non muta i dati ma comunque è computazionalmente intensivo
        };

        return value;
    }

    private static String inputMenu(String menuString) {
        Scanner console = new Scanner(System.in);
        String res;
        while (true) {
            System.out.print(menuString);
            res = console.nextLine();
            if (res.length() != 1) continue;

            boolean isNumeric = Character.isAlphabetic(res.charAt(0));
            if (!isNumeric) continue;

            break;
        }
        return res.toLowerCase();
    }

    private static void inputPath(){
        Scanner console = new Scanner(System.in);
        System.out.print("Path del file: ");
        path = console.nextLine();
    }
}
