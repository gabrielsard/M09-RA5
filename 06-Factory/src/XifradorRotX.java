public class XifradorRotX implements Xifrador {

    char[] mayusculas = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    char[] minusculas = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
        't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int desplazamiento = Integer.parseInt(clau);
            if (desplazamiento < 0 || desplazamiento > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desplazamiento;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    private String xifraRotX(String cadena, int desplazamiento) {
        int longitud = minusculas.length;
        int desp = ((desplazamiento % longitud) + longitud) % longitud;

        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            boolean encontrada = false;

            for (int j = 0; j < minusculas.length; j++) {
                if (letra == minusculas[j]) {
                    resultado += minusculas[(j + desp) % longitud];
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                for (int j = 0; j < mayusculas.length; j++) {
                    if (letra == mayusculas[j]) {
                        resultado += mayusculas[(j + desp) % longitud];
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

    private String desxifraRotX(String cadena, int desplazamiento) {
        int longitud = minusculas.length;
        int desp = ((desplazamiento % longitud) + longitud) % longitud;
        return xifraRotX(cadena, longitud - desp);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplazamiento = validaClau(clau);
        String resultat = xifraRotX(msg, desplazamiento);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplazamiento = validaClau(clau);
        String cadenaXifrada = new String(xifrat.getBytes());
        return desxifraRotX(cadenaXifrada, desplazamiento);
    }
}