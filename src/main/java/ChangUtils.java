import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class ChangUtils {
    public String streamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        for (int length; (length = inputStream.read(buffer)) != -1; )
            result.write(buffer, 0, length);
        inputStream.close();
        return result.toString(StandardCharsets.UTF_8.name());
    }
    public void streamToStringUTF_8(OutputStream outputStream, String toWrite) throws IOException {
        outputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    public int getDaysBetween(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        period.normalized(); // 1달 1일 == 32일, 31일 둘 다 가능
        return (int) ChronoUnit.DAYS.between(startDate, endDate); // only  32일

    }
    void wrapNullCallback(Object object, Runnable callbackFunction) {
        if(object == null) callbackFunction.run();
    }

    private enum Crypto {
        Algorithm("AES/CBC/PKCS5Padding"),
        Key("F130C02F55C5C7AABFE51907E4065A7426B52B6".substring(0, 32)),
        IV(Crypto.Key.value.substring(0, 16));

        private String value;
        Crypto(String value)
        {
            this.value = value;
        }
        String get()
        {
            return this.value;
        }

        public String encryptText(String text) throws Exception {
            Cipher cipher = Cipher.getInstance(Crypto.Algorithm.value);
            SecretKeySpec keySpec = new SecretKeySpec(Crypto.Key.value.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(Crypto.IV.value.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        }

        public String decryptText(String cipherText) throws Exception {
            Cipher cipher = Cipher.getInstance(Crypto.Algorithm.value);
            SecretKeySpec keySpec = new SecretKeySpec(Crypto.Key.value.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(Crypto.IV.value.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        }
    }
}
