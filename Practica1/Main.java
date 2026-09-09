public class Main {
    public static void main(String[] args) {
        practica_1.TablaHash tabla = new practica_1.TablaHash();

        //Comprobar busqueda en tabla vacia
        System.out.println("buscar(5) en tabla vacia -> " + tabla.buscar(5));

        //Comprobar que se insertan correctamente los elementos
        tabla.insertar(18, "Ana");
        tabla.insertar(10, "Luis");
        tabla.insertar(23, "Elena");
        tabla.imprimir();

        //Comprobamos que el metodo buscar funciona con elementos que si existen
        System.out.println("buscar(18) -> " + tabla.buscar(18));
        System.out.println("buscar(10) -> " + tabla.buscar(10));

        //Comprobamos una colision (10, 24 y 31 caen en el indice 3 porque M = 7)
        tabla.insertar(24, "Maria");
        tabla.insertar(31, "Carlos");
        tabla.imprimir();

        //Comprobar que el metodo eliminar funciona con un elemento de la colision (24 quedo en medio de la cadena)
        System.out.println("eliminar(24) -> " + tabla.eliminar(24));
        tabla.imprimir();
        System.out.println("buscar(10) -> " + tabla.buscar(10)); //debe seguir existiendo
        System.out.println("buscar(31) -> " + tabla.buscar(31)); //debe seguir existiendo
        System.out.println("buscar(24) -> " + tabla.buscar(24)); //ya no debe existir

        //Comprobar busqueda de una llave que no existe
        System.out.println("buscar(100) -> " + tabla.buscar(100));

        //Comprobar que insertar con una llave existente actualiza el valor
        System.out.println("buscar(18) antes -> " + tabla.buscar(18));
        tabla.insertar(18, "Ana Actualizada");
        System.out.println("buscar(18) despues -> " + tabla.buscar(18));


        //Pruebas obligatorias
        practica_1.TablaHash t2 = new practica_1.TablaHash();

        t2.insertar(18, "Ana");
        t2.insertar(10, "Luis");
        t2.insertar(24, "Maria");
        t2.insertar(31, "Carlos");
        t2.imprimir();
        System.out.println("buscar(24) -> " + t2.buscar(24));
        System.out.println("buscar(99) -> " + t2.buscar(99));
        System.out.println("eliminar(24) -> " + t2.eliminar(24));
        t2.imprimir();

        t2.insertar(20, "Nuevo"); //nota: el enunciado solo decia "20" suelto, se asumio como una insercion mas

        System.out.println("buscar(24) -> " + t2.buscar(24));
        System.out.println("buscar(31) -> " + t2.buscar(31));
    }
}
