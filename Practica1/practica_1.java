/**
 * Práctica 1.
 * Implementación de una tabla hash con encadenamiento separado
 * para el manejo de colisiones.
 */
public class practica_1 {

    /**
     * Tabla hash que almacena llaves de tipo int y valores de tipo String.
     * Las colisiones se resuelven mediante listas ligadas.
     */
    public static class TablaHash {

        /**
         * Nodo utilizado para almacenar cada elemento de la tabla.
         * Contiene una llave, su valor asociado y una referencia
         * al siguiente nodo de la lista.
         */
        private static class Nodo {
            private int key;
            private String value;
            private Nodo sig;

            /**
             * Construye un nodo con una llave y un valor.
             *
             * @param key llave del elemento
             * @param value valor asociado a la llave
             */
            public Nodo(int key, String value) {
                this.key = key;
                this.value = value;
                this.sig = null;
            }
        }

        /** Número de cubetas de la tabla hash. */
        private static final int M = 7;

        /** Arreglo que contiene las cubetas de la tabla hash. */
        private Nodo[] tabla;

        /** Número de elementos almacenados en la tabla. */
        private int n;

        /**
         * Construye una tabla hash vacía con M cubetas.
         */
        public TablaHash() {
            tabla = new Nodo[M];
            n = 0;
        }

        /**
         * Calcula la posición correspondiente a una llave
         * mediante el residuo de su división entre M.
         *
         * @param key llave de la que se desea obtener la posición
         * @return índice de la cubeta correspondiente
         */
        private int dispersion(int key) {
            return key % M;
        }

        /**
         * Inserta una llave y su valor asociado en la tabla.
         * Si la llave ya existe, actualiza su valor. Si ocurre
         * una colisión, el nuevo nodo se agrega al inicio de la
         * lista ligada de la cubeta correspondiente.
         *
         * @param key llave que se desea insertar
         * @param value valor asociado a la llave
         */
        public void insertar(int key, String value) {
            int a = dispersion(key);
            Nodo actual = tabla[a];

            // Recorre la cubeta para verificar si la llave ya existe.
            while (actual != null) {
                if (actual.key == key) {
                    actual.value = value;
                    return;
                }
                actual = actual.sig;
            }

            // Si la llave no existe, crea un nodo y lo agrega al inicio.
            Nodo nuebo = new Nodo(key, value);
            nuebo.sig = tabla[a];
            tabla[a] = nuebo;
            n++;
        }

        /**
         * Busca una llave dentro de la tabla hash.
         * Solamente recorre la lista ligada de la cubeta
         * correspondiente a la llave.
         *
         * @param key llave que se desea buscar
         * @return valor asociado a la llave, o null si no se encuentra
         */
        public String buscar(int key) {
            int a = dispersion(key);
            Nodo actual = tabla[a];

            // Recorre los elementos almacenados en la misma cubeta.
            while (actual != null) {
                if (actual.key == key) {
                    return actual.value;
                }
                actual = actual.sig;
            }

            return null;
        }

        /**
         * Elimina de la tabla el elemento que tenga la llave indicada.
         * Se mantienen referencias al nodo actual y al anterior para
         * poder retirar correctamente el nodo de la lista ligada.
         *
         * @param key llave del elemento que se desea eliminar
         * @return true si la llave fue encontrada y eliminada;
         *         false si la llave no se encuentra en la tabla
         */
        public boolean eliminar(int key) {
            int idx = dispersion(key);
            Nodo actual = tabla[idx];
            Nodo anterior = null;

            while (actual != null) {
                if (actual.key == key) {

                    // Si es el primer nodo, se cambia el inicio de la cubeta.
                    if (anterior == null) {
                        tabla[idx] = actual.sig;
                    } else {
                        // Si no es el primero, se enlaza el anterior
                        // con el siguiente del nodo eliminado.
                        anterior.sig = actual.sig;
                    }

                    n--;
                    return true;
                }

                anterior = actual;
                actual = actual.sig;
            }

            return false;
        }

        /**
         * Imprime el contenido completo de la tabla hash.
         * Para cada cubeta muestra las llaves y valores almacenados
         * siguiendo el orden de su lista ligada.
         */
        public void imprimir() {
            for (int i = 0; i < M; i++) {
                System.out.print(i + " -> ");
                Nodo actual = tabla[i];

                // Recorre e imprime todos los nodos de la cubeta.
                while (actual != null) {
                    System.out.print("(" + actual.key + "," 
                            + actual.value + ") -> ");
                    actual = actual.sig;
                }

                System.out.println("null");
            }
        }
    }
}
