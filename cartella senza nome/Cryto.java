import java.nio.charset.StandardCharsets;

public class Cryto {

    public String Cesar(String data, int shift) {
        StringBuilder output = new StringBuilder(data.length());
        shift %= 26;
        for (char singleChar : data.toCharArray()) {
            int shiftedChar = (int) (singleChar + shift) % 256;
            output.append((char) shiftedChar);
        }
        return output.toString();
    }

    public String xorAlgo(String data, String key) {
       byte[] dataArray = data.getBytes(StandardCharsets.UTF_8);
       byte[] keyArray = key.getBytes(StandardCharsets.UTF_8);

       byte[] output = new byte[dataArray.length];

       for(int i = 0; i<dataArray.length; i++){
        output[i]=(byte)(dataArray[i]^keyArray[i % keyArray.length]);
        
       }
       return (new String(output, StandardCharsets.UTF_8).toString());

    }
}                                     