import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {

    public static SecretKeySpec hmacKey(String algo, byte[] keyBytes) {
        SecretKeySpec key = new SecretKeySpec(keyBytes, algo);
        // String keyStr = key.toString();
        return key;
    }

    public static String hmacDigest(String msg, byte[] keyBytes, String algo) {

        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, algo);
            Mac mac = Mac.getInstance(algo);//"HmacSHA256"

            mac.init(key);
            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return digest;
    }
}