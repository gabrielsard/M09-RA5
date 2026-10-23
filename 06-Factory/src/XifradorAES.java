import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;

    private byte[] iv = new byte[MIDA_IV];

    private byte[] generaIv() {
        byte[] nouIv = new byte[MIDA_IV];
        new SecureRandom().nextBytes(nouIv);
        return nouIv;
    }

    private SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(clau.getBytes("UTF-8"));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private byte[] extreureIv(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    }

    private byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
    }

    private byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes("UTF-8");

        iv = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec clauSpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clauSpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bMsg);

        byte[] resultat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);

        return resultat;
    }

    private String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] ivExtret = extreureIv(bIvIMsgXifrat);
        byte[] msgXifrat = getBytesXifrats(bIvIMsgXifrat);

        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        SecretKeySpec clauSpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clauSpec, ivSpec);
        byte[] bMsg = cipher.doFinal(msgXifrat);

        return new String(bMsg, "UTF-8");
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] resultat = xifraAES(msg, clau);
            return new TextXifrat(resultat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}