import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] permutacion;

    public XifradorMonoalfabetic() {
        permutacion = permutaAlfabet();
    }

    private char[] permutaAlfabet() {
        List<Character> lista = new ArrayList<>();
        for (char c : mayusculas) {
            lista.add(c);
        }

        Collections.shuffle(lista);

        char[] resultado = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    private void comprovaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    private String xifraMonoAlfa(String cadena) {
        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

            boolean esMinuscula = Character.isLowerCase(letra);
            char letraMayus;
            if (esMinuscula) {
                letraMayus = Character.toUpperCase(letra);
            } else {
                letraMayus = letra;
            }

            boolean encontrada = false;
            for (int j = 0; j < mayusculas.length; j++) {
                if (letraMayus == mayusculas[j]) {
                    char cifrada = permutacion[j];

                    if (esMinuscula) {
                        resultado += Character.toLowerCase(cifrada);
                    } else {
                        resultado += cifrada;
                    }

                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                resultado += letra;
            }
        }
        return resultado;
    }

    private String desxifraMonoAlfa(String cadena) {
        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

            boolean esMinuscula = Character.isLowerCase(letra);
            char letraMayus;
            if (esMinuscula) {
                letraMayus = Character.toUpperCase(letra);
            } else {
                letraMayus = letra;
            }

            boolean encontrada = false;
            for (int j = 0; j < permutacion.length; j++) {
                if (letraMayus == permutacion[j]) {
                    char original = mayusculas[j];

                    if (esMinuscula) {
                        resultado += Character.toLowerCase(original);
                    } else {
                        resultado += original;
                    }

                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                resultado += letra;
            }
        }
        return resultado;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        String resultat = xifraMonoAlfa(msg);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        String cadenaXifrada = new String(xifrat.getBytes());
        return desxifraMonoAlfa(cadenaXifrada);
    }
}