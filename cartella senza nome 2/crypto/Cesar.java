package crypto;

public class Cesar implements CryptoInterface {

    private final int shift;

    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


    public Cesar(int shift) {
        this.shift = shift;
        System.out.println(this instanceof CryptoInterface);
        shift = shift%52;
    }

    public Cesar() {
        this(6);
    }

    /**
     * The Cesar method is used
     *
     * @param data provide an unencrypted string
     * @return return an encrypted string
     */
    @Override
    public String crypt(String data) {
        char[] sentenceArray = data.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<sentenceArray.length; ++i){

            boolean flag = false;
            for (int j=0; j<alphabet.length; ++j){
                if (sentenceArray[i]==alphabet[j]){

                    sb.append((char) alphabet[(j+shift)%52]);
                    flag = true;
                    break;
                }
            }
            if (!flag){ //special character
                sb.append((char) sentenceArray[i]);
            }
        }

        return sb.toString();

    }
    

    /**
     * The Cesar method is used
     *
     * @param data provide an encrypted string
     * @return return an unencrypted string
     */
    @Override
    public String decrypt(String data) {
        char[] sentenceArray = data.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<sentenceArray.length; ++i){

            boolean flag = false;
            for (int j=0; j<alphabet.length; ++j){
                if (sentenceArray[i]==alphabet[j]){
                    if ((j-shift)<0){
                        sb.append((char) alphabet[52+(j-shift)]);
                    }else{
                        sb.append((char) alphabet[(j-shift)%52]);
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag){ //special character
                sb.append((char) sentenceArray[i]);
            }
            
        }
        return sb.toString();
       

    }
    
}


