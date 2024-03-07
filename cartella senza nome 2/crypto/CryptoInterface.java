package crypto;

public interface CryptoInterface {


    /**
     * @param data provide an unencrypted string
     * @return return an encrypted string
     */
    String crypt(String data);

    /**
     * @param data provide an encrypted string
     * @return return an unencrypted string
     */
    String decrypt(String data);

}
