package crypto;

public class XORCipher implements CryptoInterface {
    /*
    * The XOR operator is extremely common as a component in more complex ciphers.
    * By itself, using a constant repeating key, a simple XOR cipher can trivially be broken using frequency analysis.
    * If the content of any message can be guessed or otherwise known then the key can be revealed.
    * Its primary merit is that it is simple to implement, and that the XOR operation is computationally inexpensive.
    *
    * # source: https://en.wikipedia.org/wiki/XOR_cipher
    */

    private final String key;

    public XORCipher(String key) {
        this.key = key;
    }

    public XORCipher() {
        this("my_secret");
    }


    /**
     * Use XOR algo to cipher data.
     *
     * @param data provide an unencrypted string
     * @return return an encrypted string
     */
    @Override
    public String crypt(String data) {

        byte[] keyArray = key.getBytes();
        byte[] byteArray = data.getBytes();

        byte[] crypSentenceArray = new byte[byteArray.length];

        for (int i=0; i<byteArray.length; ++i){
            crypSentenceArray[i]=(byte)(byteArray[i] ^ keyArray[i % keyArray.length]);
            //crypSentenceArray[i]=(byte)(crypSentenceArray[i] ^ keyArray[i % keyArray.length]);
        }
        return (new String(crypSentenceArray));
    }
    /**
     * Use XOR algo to decipher data.
     *
     * @param data provide an encrypted string
     * @return return an unencrypted string
     */
    @Override
    public String decrypt(String data) {
        byte[] keyArray = key.getBytes();
        byte[] byteArray = data.getBytes();

        byte[] crypSentenceArray = new byte[byteArray.length];

        for (int i=0; i<byteArray.length; ++i){
            crypSentenceArray[i]=(byte)(byteArray[i] ^ keyArray[i % keyArray.length]);
            //crypSentenceArray[i]=(byte)(crypSentenceArray[i] ^ keyArray[i % keyArray.length]);
        }
        return (new String(crypSentenceArray));
    }
}
