package crypto;

public class CesarImproved extends Cesar {
    public static void main(String[] args){
        for (int i=0; i<256; ++i){
            System.out.println(i+" - "+ (char) i);
        }

        //A 65 Z 90
        //a 97 z 122

        String sentence = "Ciao, come stai?";

        char[] sentenceArray = sentence.toCharArray();

        for (int i=0; i<sentenceArray.length; ++i){
            System.out.println(i+" - "+ (int) sentenceArray[i]);
        }

        int key = 3;
        key %= 26;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<sentenceArray.length; ++i){
            int t = 0;
            if (sentenceArray[i]>= 65 && sentenceArray[i]<= 90){ //carattere maiuscolo
                t = (sentenceArray[i] + key - 65 )%26 + 65;
            }else if (sentenceArray[i]>= 97 && sentenceArray[i]<= 122){ //carattere minuscolo
                t = (sentenceArray[i] + key - 97)%26 + 97;
            }else { //carattere speciale
                t = sentenceArray[i];
            }
            System.out.println((char) t);
            sb.append((char) t);
        }

        System.out.println(sb.toString());

    }
}

