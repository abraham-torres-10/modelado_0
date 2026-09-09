
void main() {
        //Crear la tabla    
        TablaHash tabla = new TablaHash();

        //------Pruebas Obligatorias-------

        //Tabla vacia 
        tabla.buscar(10);
           
        //Incersion basica 
        tabla.insertar(18, "Ana");
        tabla.insertar(10, "Luis");
        tabla.insertar(23, "Elena");

        tabla.buscar(18);
        tabla.buscar(10);
        tabla.buscar(23);

        //Colisiones 
        tabla.insertar(24, "Maria");
        tabla.insertar(31, "Carlos");

        tabla.buscar(10);
        tabla.buscar(24);
        tabla.buscar(31);

        //eliminacion con colision

        tabla.eliminar(24);

        tabla.buscar(24);
        tabla.buscar(10);
        tabla.buscar(31);

        //Llave inexistente 

        tabla.eliminar(999);

        //Actualizacion de una llave existente 
        
    }