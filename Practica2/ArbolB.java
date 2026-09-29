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

    //  Aquí se borra y después se revisa si algún nodo quedó vacío
    public boolean eliminar(int x) {
        if (!buscar(x)) {
            return false;
        }
        eliminarEn(raiz, x);
        if (raiz.cantidadClaves() == 0) {                     // caso especial de la raíz
            raiz = raiz.esHoja() ? null : raiz.hijos.get(0);  // la altura baja en uno
        }
        return true;
    }

    private void eliminarEn(Nodo nodo, int x) {
        int i = nodo.indiceDe(x);

        if (nodo.esHoja()) {                                  // caso basico, borrar de la hoja
            nodo.claves.remove(i);
            return;
        }

        boolean estaAqui = i < nodo.cantidadClaves() && nodo.claves.get(i) == x;
        int hijoARevisar = i;
        if (!estaAqui) {
            eliminarEn(nodo.hijos.get(i), x);
        } else {
            Nodo izquierdo = nodo.hijos.get(i);
            Nodo derecho = nodo.hijos.get(i + 1);
            if (izquierdo.puedePrestar()) {
                int predecesor = maximo(izquierdo);
                nodo.claves.set(i, predecesor);
                eliminarEn(izquierdo, predecesor);
            } else if (derecho.puedePrestar()) {
                int sucesor = minimo(derecho);
                nodo.claves.set(i, sucesor);
                eliminarEn(derecho, sucesor);
                hijoARevisar = i + 1;
            } else {
                fusionar(nodo, i);
                eliminarEn(nodo.hijos.get(i), x);
            }
        }
        if (nodo.hijos.get(hijoARevisar).estaSubocupado()) {
            repararSubocupacion(nodo, hijoARevisar);
        }
    }

    // Arregla un hijo que se quedó sin llaves, se deberá redistribuir o fusionar
    private void repararSubocupacion(Nodo padre, int i) {
        boolean hayIzquierdo = i > 0;
        boolean hayDerecho = i < padre.hijos.size() - 1;
        if (hayIzquierdo && padre.hijos.get(i - 1).puedePrestar()) {
            redistribuirDesdeIzquierdo(padre, i);
        } else if (hayDerecho && padre.hijos.get(i + 1).puedePrestar()) {
            redistribuirDesdeDerecho(padre, i);
        } else if (hayIzquierdo) {
            fusionar(padre, i - 1);
        } else {
            fusionar(padre, i);
        }
    }

    // La llave pasa por el padre: hermano, padre, nodo vacío
    private void redistribuirDesdeIzquierdo(Nodo padre, int i) {
        Nodo hijo = padre.hijos.get(i);
        Nodo hermano = padre.hijos.get(i - 1);
        hijo.claves.add(0, padre.claves.get(i - 1));                          // baja el separador
        padre.claves.set(i - 1, hermano.claves.remove(hermano.claves.size() - 1)); // sube la del hermano

        if (!hermano.esHoja()) {                                              // mover también el hijo
            hijo.hijos.add(0, hermano.hijos.remove(hermano.hijos.size() - 1));
        }
    }

    private void redistribuirDesdeDerecho(Nodo padre, int i) {
        Nodo hijo = padre.hijos.get(i);
        Nodo hermano = padre.hijos.get(i + 1);
        hijo.claves.add(padre.claves.get(i));                                 // baja el separador
        padre.claves.set(i, hermano.claves.remove(0));                        // sube la del hermano
        if (!hermano.esHoja()) {
            hijo.hijos.add(hermano.hijos.remove(0));
        }
    }

    // Une hijo[j] + llave separadora del padre + hijo[j+1] en un solo nodo
    private void fusionar(Nodo padre, int j) {
        Nodo izquierdo = padre.hijos.get(j);
        Nodo derecho = padre.hijos.get(j + 1);
        izquierdo.claves.add(padre.claves.remove(j));
        izquierdo.claves.addAll(derecho.claves);
        izquierdo.hijos.addAll(derecho.hijos);
        padre.hijos.remove(j + 1);
    }

    private int maximo(Nodo nodo) {                           // mayor llave del subárbol
        while (!nodo.esHoja()) {
            nodo = nodo.hijos.get(nodo.hijos.size() - 1);
        }
        return nodo.claves.get(nodo.claves.size() - 1);
    }

    private int minimo(Nodo nodo) {                           // menor llave del subárbol
        while (!nodo.esHoja()) {
            nodo = nodo.hijos.get(0);
        }
        return nodo.claves.get(0);
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