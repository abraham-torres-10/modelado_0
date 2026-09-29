import java.util.LinkedList;
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

    /** Se inserta en la hoja y si un nodo llega a 4 llaves 
    se divide y la llave promovida sube al padre
    */
    public boolean insertar(int x) {
        if (buscar(x)) {
            return false;
        }
        if (raiz == null) {
            raiz = new Nodo();
            raiz.claves.add(x);
            return true;
        }

        Nodo.ResultadoDivision division = insertarEn(raiz, x);
        if (division != null) {                               // la raíz se dividió: nueva raíz
            Nodo nuevaRaiz = new Nodo();
            nuevaRaiz.claves.add(division.llavePromovida);
            nuevaRaiz.hijos.add(division.izquierdo);
            nuevaRaiz.hijos.add(division.derecho);
            raiz = nuevaRaiz;
        }
        return true;
    }

    // Devuelve null si el nodo no se dividió, si se dividió lo que el padre debe recibir
    private Nodo.ResultadoDivision insertarEn(Nodo nodo, int x) {
        int i = nodo.indiceDe(x);
        if (nodo.esHoja()) {
            nodo.claves.add(i, x);                            // queda ordenada
        } else {
            Nodo.ResultadoDivision division = insertarEn(nodo.hijos.get(i), x);
            if (division != null) {                           // el hijo se divide
                nodo.claves.add(i, division.llavePromovida);
                nodo.hijos.set(i, division.izquierdo);
                nodo.hijos.add(i + 1, division.derecho);
            }
        }
        return nodo.estaDesbordado() ? nodo.dividir() : null;
    }

    // imprimir por niveles, se hace recorrido con una cola
    public String aTextoPorNiveles() {
        if (raiz == null) {
            return "(arbol vacio)";
        }
        StringBuilder texto = new StringBuilder();
        LinkedList<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            if (nivel > 0) {
                texto.append("\n");
            }
            texto.append("Nivel ").append(nivel).append(":");
            for (int k = 0; k < nodosEnNivel; k++) {
                Nodo nodo = cola.remove();
                texto.append(" ").append(nodo);
                cola.addAll(nodo.hijos);
            }
            nivel++;
        }
        return texto.toString();
    }

    public void imprimirPorNiveles() {
        System.out.println(aTextoPorNiveles());
    }
}