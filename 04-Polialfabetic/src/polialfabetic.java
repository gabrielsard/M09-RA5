import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static long clauSecreta = 192939495969798L;
    private static Random random;

    static char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    static char[] permutacion;

    public static void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }

    public static void permutaAlfabet() {
        List<Character> lista = new ArrayList<>();
        for (char c : mayusculas) lista.add(c);
        Collections.shuffle(lista, random);

        char[] resultado = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) resultado[i] = lista.get(i);
        permutacion = resultado;
    }

    public static String xifraPoliAlfa(String msg) {
        String resultado = "";

        for (int i = 0; i < msg.length(); i++) {
            char letra = msg.charAt(i);
            permutaAlfabet(); // nueva permutación por cada letra

            boolean min = Character.isLowerCase(letra);
            char letraMayus = min ? Character.toUpperCase(letra) : letra;

            boolean encontrada = false;
            for (int j = 0; j < mayusculas.length; j++) {
                if (letraMayus == mayusculas[j]) {
                    char cifrada = permutacion[j];
                    resultado += min ? Character.toLowerCase(cifrada) : cifrada;
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) resultado += letra;
        }
        return resultado;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String resultado = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            char letra = msgXifrat.charAt(i);
            permutaAlfabet();

            boolean min = Character.isLowerCase(letra);
            char letraMayus = min ? Character.toUpperCase(letra) : letra;

            boolean encontrada = false;
            for (int j = 0; j < permutacion.length; j++) {
                if (letraMayus == permutacion[j]) {
                    char original = mayusculas[j];
                    resultado += min ? Character.toLowerCase(original) : original;
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) resultado += letra;
        }
        return resultado;
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixi, Perímetre", 
                        "Test 02 Taüll, DÍA, año", 
                        "Test 03 Peça, Òrrius Bòvilla"
                    };
        
        String msgsXifrats[]= new String[msgs.length];

        System.out.println("Xifratge:\n--------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}