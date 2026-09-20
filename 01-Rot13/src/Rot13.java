public class Rot13 {
    //cabe recalcar que no tuve un primer commit en clase porque tuve un problema
    //con el github por haber hecho mal el clone, pero ahora mismo ya lo he solucionado y espero que no haya problemas
    static char[] mayusculas = {
        'A', 'À', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H',
        'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O',
        'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V',
        'W', 'X', 'Y', 'Z'
    };
    static char[] minusculas = {
        'a', 'à', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h',
        'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o',
        'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v',
        'w', 'x', 'y', 'z'
    };

    public static String xifraRot13(String cadena) {

        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {

            char letra = cadena.charAt(i);
            boolean encontrada = false;

            for (int j = 0; j < minusculas.length; j++) {

                if (letra == minusculas[j]) {

                    int nuevaPosi = (j + 13) % minusculas.length;
                    resultat += minusculas[nuevaPosi];
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {

                for (int j = 0; j < mayusculas.length; j++) {

                    if (letra == mayusculas[j]) {

                        int nuevaPosi = (j + 13) % mayusculas.length;
                        resultat += mayusculas[nuevaPosi];
                        encontrada = true;
                        break;
                    }
                }
            }

            if (!encontrada) {
                resultado += letra;
            }
        }
    return resultado;
    }

}