public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte xifraAES(String msg, String clau) throws Exception{
        //obtener los bytes de el string

        //Genera IvParameterSpec

        //Genera gash

        //Encrypt

        //Combinar IV i part xifrada.

        //return iv+msgxifrat

        return 4;
    }

    public static byte desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{
        //Extru l'IV

        //Extreu la part xifrada

        //fer Hash de la clau

        //Desfrixat

        //return String desxifrat

        return 4;
    }

    public static void main(String[] args) {
        String msgs[] = {"Loren ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora illa òtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifratas = null;
            String desxifrat = "";
            try {
                bXifratas = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifratas, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifratas));
            System.out.println("DEC: " + desxifrat);
        }
    }
    
}
