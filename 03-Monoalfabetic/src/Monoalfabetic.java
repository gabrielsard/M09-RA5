import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] permutacion;

    public char[] permutaAlfabet(char[] alfabet) {

        //pasamos la lista a un arraylist
        List<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }

        Collections.shuffle(lista);

        //la volvemos a pasar a char :p
        char[] resultado = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    public String xifraMonoAlfa(String cadena) {
        String resultado = "";

        //recorremos caracteres
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

    public String desxifraMonoAlfa(String cadena) {
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




    public static void main(String[] args) {

        Monoalfabetic mono = new Monoalfabetic();

        mono.permutacion = mono.permutaAlfabet(mono.mayusculas);

        System.out.println("Alfabet original: " + new String(mono.mayusculas));
        System.out.println("Permutació:       " + new String(mono.permutacion));
        System.out.println();

        String[] tests = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge:");
        String[] testsXifrats = new String[tests.length];

        for (int i = 0; i < tests.length; i++) {
            testsXifrats[i] = mono.xifraMonoAlfa(tests[i]);
            System.out.println(tests[i] + " -> " + testsXifrats[i]);
        }

        System.out.println("\nDesxifratge:");
        for (int i = 0; i < testsXifrats.length; i++) {
            System.out.println(testsXifrats[i] + " -> " + mono.desxifraMonoAlfa(testsXifrats[i]));
        }
    }

}