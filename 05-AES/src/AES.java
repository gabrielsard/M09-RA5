import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes("UTF-8");

        iv = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec clauSpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clauSpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bMsg);

        // Combinamos IV + mensaje cifrado en un solo array (el IV va al principio)
        byte[] resultado = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultado, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultado, iv.length, msgXifrat.length);

        return resultado;
    }


    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] ivExtret = extreureIv(bIvIMsgXifrat);
        byte[] msgXifrat = getBytesXifrats(bIvIMsgXifrat);

        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        SecretKeySpec clauSpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clauSpec, ivSpec);
        byte[] bMsg = cipher.doFinal(msgXifrat);

        return new String(bMsg, "UTF-8");
    }

    private static byte[] generaIv() {
        byte[] nouIv = new byte[MIDA_IV];
        new SecureRandom().nextBytes(nouIv);
        return nouIv;
    }

    private static SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(clau.getBytes("UTF-8"));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private static byte[] extreureIv(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    }

    private static byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora illa òtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}