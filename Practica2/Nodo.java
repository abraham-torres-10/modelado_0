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
}