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

        private int factorCarga(int m){
            return n/m;
        }
    }
}