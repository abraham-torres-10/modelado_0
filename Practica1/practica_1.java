public class practica_1 {

    public static class TablaHash {

        private static class Nodo {
            private int key;
            private String value;
            private Nodo sig;

            public Nodo(int key, String value) {
                this.key = key;
                this.value = value;
                this.sig = null;
            }
        }

        private static final int M = 7; // esto esta pq en la practica dice que m es 7 jaja
        private Nodo[] tabla;
        private int n;

        public TablaHash() {
            tabla = new Nodo[M];
            n = 0;
        }

        private int dispersion(int key) {
            return key % M;
        }

        public void insertar(int key, String value) {
            int a = dispersion(key);
            Nodo actual = tabla[a];

            while (actual != null) {
                if (actual.key == key) {      // esto es en el caso de que tengan la misma llave, simplemente actualiza el valor
                    actual.value = value;
                    return;
                }
                actual = actual.sig;
            }
            Nodo nuebo = new Nodo(key, value);
            nuebo.sig = tabla[a];
            tabla[a] = nuebo;
            n++;
        }

        public String buscar(int key) {
            int a = dispersion(key);
            Nodo actual = tabla[a];

            while (actual != null) {
                if (actual.key == key)
                    return actual.value;   // si esta
                actual = actual.sig;       // FIX: antes decia "actual.sig = actual;" y eso generaba un bucle infinito
            }
            return null; // no esta la llave
        }

        public boolean eliminar(int key) {
            int idx = dispersion(key);
            Nodo actual = tabla[idx];
            Nodo anterior = null;

            while (actual != null) {
                if (actual.key == key) {
                    if (anterior == null) {
                        tabla[idx] = actual.sig;
                    } else {
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

        public void imprimir() {
            for (int i = 0; i < M; i++) {
                System.out.print(i + " -> ");
                Nodo actual = tabla[i];
                while (actual != null) {
                    System.out.print("(" + actual.key + "," + actual.value + ") -> ");
                    actual = actual.sig;
                }
                System.out.println("null");
            }
        }
    }
}
