public class Main {

    public static void main(String[] args) {
        ArbolB arbol = new ArbolB();

        int[] llaves = {20, 40, 10, 30, 50, 60, 70, 5, 15, 25, 35, 45};

        for (int llave : llaves) {
            arbol.insertar(llave);
        }

        arbol.imprimirPorNiveles();

        System.out.println(arbol.buscar(35));
        System.out.println(arbol.buscar(99));
    }
}