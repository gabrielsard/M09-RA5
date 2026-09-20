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
                    resultado += minusculas[nuevaPosi];
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {

                for (int j = 0; j < mayusculas.length; j++) {

                    if (letra == mayusculas[j]) {

                        int nuevaPosi = (j + 13) % mayusculas.length;
                        resultado += mayusculas[nuevaPosi];
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

    //también me compliqué mucho porque el archivo que salía en github era otro y no este que tenía el código
    //por eso la diferencia de tiempo de los commis (además de que se me olvidó agregar la clave ssh)

    public static String desxifraRot13(String cadena) {

        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {

            char letra = cadena.charAt(i);
            boolean encontrada = false;

            for (int j = 0; j < minusculas.length; j++) {

                if (letra == minusculas[j]) {

                    int nuevaPosi = (j - 13 + minusculas.length) % minusculas.length;
                    resultado += minusculas[nuevaPosi];
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {

                for (int j = 0; j < mayusculas.length; j++) {

                    if (letra == mayusculas[j]) {

                        int nuevaPosi = (j - 13 + mayusculas.length) % mayusculas.length;
                        resultado += mayusculas[nuevaPosi];
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

    public static void main(String[] args) {

        String[] mensajes = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        String[] mensajesCifrados = new String[mensajes.length];

        System.out.println("\nCifrado");
        System.out.println("-------");

        for (int i = 0; i < mensajes.length; i++) {

            mensajesCifrados[i] = xifraRot13(mensajes[i]);

            System.out.println(mensajes[i] + " => " + mensajesCifrados[i]);
        }

        System.out.println("\nDescifrado");
        System.out.println("----------");

        for (String mensaje : mensajesCifrados) {

            System.out.println(mensaje + " => " + desxifraRot13(mensaje));
        }
    }

}