public class Main {

    public static void main(String[] args) {
        ArbolB arbol = new ArbolB();

        int[] llaves = {20, 40, 10, 30, 50, 60, 70, 5, 15, 25, 35, 45};

        for (int llave : llaves) {
            arbol.insertar(llave);
        }

        System.out.println("Arbol despues de insertar");
        arbol.imprimirPorNiveles();
        System.out.println();
        mostrarBusqueda(arbol, 35);
        mostrarBusqueda(arbol, 99);
        System.out.println();
        int[] llavesAEliminar = {25, 10, 70, 5};
        for (int llave : llavesAEliminar) {
            arbol.eliminar(llave);
            System.out.println("eliminar(" + llave + ")");
        }
        System.out.println();
        System.out.println("Arbol despues de eliminar");
        arbol.imprimirPorNiveles();
        System.out.println();
        System.out.println("Busquedas finales");
        mostrarBusqueda(arbol, 25);
        mostrarBusqueda(arbol, 35);
    }

    private static void mostrarBusqueda(ArbolB arbol, int x) {
        if (arbol.buscar(x)) {
            System.out.println("buscar(" + x + ") -> Existe");
        } else {
            System.out.println("buscar(" + x + ") -> No existe");
        }
    }
}