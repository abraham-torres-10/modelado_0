public class ArbolB {

    private Nodo raiz;

    public ArbolB() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // en cada nodo se elige un solo hijo y se baja
    public boolean buscar(int x) {
        Nodo actual = raiz;
        while (actual != null) {
            int i = actual.indiceDe(x);
            if (i < actual.cantidadClaves() && actual.claves.get(i) == x) {
                return true;
            }
            if (actual.esHoja()) {
                return false;
            }
            actual = actual.hijos.get(i);
        }
        return false;
    }
}