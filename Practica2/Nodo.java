import java.util.ArrayList;
import java.util.List;

public class Nodo {

    public static final int MAX_CLAVES = 3;
    public static final int MIN_CLAVES = 1;

    final List<Integer> claves = new ArrayList<>();
    final List<Nodo> hijos = new ArrayList<>();

    // Devuelve true si no tiene hijos
    public boolean esHoja() {
        return hijos.isEmpty();
    }

    // Cantidad de llaves del nodo
    public int cantidadClaves() {
        return claves.size();
    }

    // Tiene más llaves de las permitidas
    public boolean estaDesbordado() {
        return claves.size() > MAX_CLAVES;
    }

    /**  Primera posición i con x <= claves[i], si no hay entonces cantidadClaves()
     * Sirve para buscar la llave o para elegir el hijo por el que bajar */
    public int indiceDe(int x) {
        int i = 0;
        while (i < claves.size() && x > claves.get(i)) {
            i++;
        }
        return i;
    }

    /** Divide un nodo con 4 llaves, aqui sube la tercera, 
     * la izquierda queda con [k1, k2] y la derecha con [k4], 
     * si es interno entonces también reparte los hijos */
    public ResultadoDivision dividir() {
        if (claves.size() != MAX_CLAVES + 1) {
            throw new IllegalStateException("Solo se puede dividir un nodo con 4 llaves");
        }

        int llavePromovida = claves.get(2);
        Nodo izquierdo = new Nodo();
        Nodo derecho = new Nodo();

        izquierdo.claves.addAll(claves.subList(0, 2));
        derecho.claves.add(claves.get(3));

        if (!esHoja()) {
            izquierdo.hijos.addAll(hijos.subList(0, 3));
            derecho.hijos.addAll(hijos.subList(3, 5));
        }

        return new ResultadoDivision(llavePromovida, izquierdo, derecho);
    }

    // Información que recibe el padre después del split
    public static final class ResultadoDivision {
        public final int llavePromovida;
        public final Nodo izquierdo;
        public final Nodo derecho;

        public ResultadoDivision(int llavePromovida, Nodo izquierdo, Nodo derecho) {
            this.llavePromovida = llavePromovida;
            this.izquierdo = izquierdo;
            this.derecho = derecho;
        }
    }
    
    // Aqui se observa el formato [10 | 20 | 40]
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        for (int i = 0; i < claves.size(); i++) {
            if (i > 0) {
                texto.append(" | ");
            }
            texto.append(claves.get(i));
        }
        texto.append("]");
        return texto.toString();
    }
}