public class Main {

    public static void main(String[] args) {
        ArbolB arbol = new ArbolB();

        System.out.println(arbol.estaVacio());
        System.out.println(arbol.buscar(10));

        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(10);
        arbol.insertar(30);

        System.out.println(arbol.buscar(20));
        System.out.println(arbol.buscar(30));
        System.out.println(arbol.buscar(99));
    }
}