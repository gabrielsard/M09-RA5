public class RotX {
        static char[] minusculas = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
        't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    static char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };


    //xifratRot hace que se xifren dependiendo de la cadena y la cantidad de saltos

    public static String xifraRotX(String cadena, int desplazamiento) {
        int longitud = minusculas.length; //variable con el length del código ez peacy 
        int desp = ((desplazamiento % longitud) + longitud) % longitud; 
        //tengo que limpiar el desplazamiento para que quede dentro de 0 y el length del abcedario 

        String resultado = "";

        //recorremos la cadena que nos dieron 
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            boolean encontrada = false;

            //ahora recorremos todo el char de las minúsculas a ver si se encuentra ahí
            for (int j = 0; j < minusculas.length; j++) {
                //si se encuentra pues cambiamos el boolean y metemos el carácter cifrado en resultado
                if (letra == minusculas[j]) {
                    resultado += minusculas[(j + desp) % longitud];
                    encontrada = true;
                    break;
                }
            }

            //si no es minuscula pues tiramos por las mayus
            if (!encontrada) {
                for (int j = 0; j < mayusculas.length; j++) {
                    if (letra == mayusculas[j]) {
                        resultado += mayusculas[(j + desp) % longitud];
                        encontrada = true;
                        break;
                    }
                }
            }

            //y si no es letra directamente lo ponemos en resultado porque lo más probable es que sea un caracter :p
            if (!encontrada) {
                resultado += letra;
            }
        }
        //no creo que haga falta explicar para que funciona esto xd
        return resultado;
    }

    //desxifratRot hace lo mismo pero hacia atrás

    public static String desxifraRotX(String cadena, int desplaçament) {
        int longitud = minusculas.length; //lo mismo, length de los abecedarios
        int desp = ((desplaçament % longitud) + longitud) % longitud;
        //volvemos a depurar el desplazamiento 

        // En vez de duplicar todo el bucle, reutilizamos lo que hicimos antes :p
        // Ok, soooo, en esta parte en vez de restar vamos a darle el número positivo para que de la vuelta
        // Por si el profesor lee esto, hago que de toda la vuelta porque anteriormente dije que no se podían pasar 
        // negativos, por lo cual esta es la forma que se me ocurrió, no sé si es eficiente, pero salió de mi cabeza
        return xifraRotX(cadena, longitud - desp);
    }

    //forcaBruta le das la cadena cifrada y va desifrando 1 por 1 

    

}