import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] permutacion;
    Random random;

    private void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }

    private void permutaAlfabet() {
        List<Character> lista = new ArrayList<>();
        for (char c : mayusculas) lista.add(c);
        Collections.shuffle(lista, random);

        char[] resultado = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) resultado[i] = lista.get(i);
        permutacion = resultado;
    }

    private long convertirClau(String clau, boolean esXifrat) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            if (esXifrat) {
                throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
            } else {
                throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
            }
        }
    }

    private String xifraPoliAlfa(String msg) {
        String resultado = "";

        for (int i = 0; i < msg.length(); i++) {
            char letra = msg.charAt(i);
            permutaAlfabet();

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

    private String desxifraPoliAlfa(String msgXifrat) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong = convertirClau(clau, true);
        initRandom(clauLong);
        String resultat = xifraPoliAlfa(msg);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauLong = convertirClau(clau, false);
        initRandom(clauLong);
        String cadenaXifrada = new String(xifrat.getBytes());
        return desxifraPoliAlfa(cadenaXifrada);
    }
}