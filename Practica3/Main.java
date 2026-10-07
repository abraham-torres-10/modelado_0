import java.util.ArrayList;
import java.util.List;

// 1. PATRON COMPOSITE: Elemento, Archivo y Carpeta

/**
 * Interfaz comun para hojas (Archivo) y compuestos (Carpeta).
 * Permite tratar archivos y carpetas de forma uniforme sin condicionales de tipo.
 */
interface Elemento {
    int obtenerTamanio();
}

abstract class Archivo implements Elemento {
    String nombre;
    int tamanio;

    Archivo(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }
    @Override
    public int obtenerTamanio() {
        return this.tamanio;
    }
}

class ArchivoPDF extends Archivo {
    ArchivoPDF(String nombre, int tamanio) {
        super(nombre, tamanio);
    }
}

class ArchivoTexto extends Archivo {
    ArchivoTexto(String nombre, int tamanio) {
        super(nombre, tamanio);
    }
}

/**
 * Clase compuesta del patron Composite.
 * Contiene una lista uniforme de Elemento, pueden ser Archivo u otras Carpetas.
 */
class Carpeta implements Elemento {
    String nombre;
    List<Elemento> elementos = new ArrayList<>();

    Carpeta(String nombre) {
        this.nombre = nombre;
    }
    void agregar(Elemento elemento) {
        elementos.add(elemento);
    }

    @Override
    public int obtenerTamanio() {
        int total = 0;
        for (Elemento elemento : elementos) {
            total += elemento.obtenerTamanio();
        }
        return total;
    }
}

// 2. PATRON FACTORY METHOD: Creadores de Archivos

abstract class CreadorArchivo {
    abstract Archivo crearArchivo(String nombre, int tamanio);
}

class CreadorPDF extends CreadorArchivo {
    @Override
    Archivo crearArchivo(String nombre, int tamanio) {
        return new ArchivoPDF(nombre, tamanio);
    }
}

class CreadorTexto extends CreadorArchivo {
    @Override
    Archivo crearArchivo(String nombre, int tamanio) {
        return new ArchivoTexto(nombre, tamanio);
    }
}

// 3. PATRON ADAPTER: Integracion de Correo

class CorreoLegacy {
    void send_email(String to, String body) {
        System.out.println("Para: " + to);
        System.out.println(body);
    }
}

/**
 * Interfaz esperada para envio de notificaciones.
*/
interface Notificador {
    void enviar(String destino, String mensaje);
}

/**
 * Adaptador que implementa Notificador y delega la llamada a CorreoLegacy.
*/
class AdaptadorCorreo implements Notificador {
    private CorreoLegacy correo;

    AdaptadorCorreo(CorreoLegacy correo) {
        this.correo = correo;
    }

    @Override
    public void enviar(String destino, String mensaje) {
        correo.send_email(destino, mensaje);
    }
}

// PROGRAMA PRINCIPAL Y PRUEBAS

public class Main {

    static void enviarResultado(Carpeta carpeta, String destino, Notificador notificador) {
        int tamanio = carpeta.obtenerTamanio();
        notificador.enviar(destino, "Tamanio total: " + tamanio);
    }


    static void comprobar(String nombre, int esperado, int obtenido) {
        if (esperado == obtenido) {
            System.out.println("OK: " + nombre);
        } else {
            System.out.println("FALLO: " + nombre
                    + " | esperado=" + esperado
                    + " | obtenido=" + obtenido);
        }
    }

    public static void ejecutarPruebas() {
        System.out.println("--- EJECUTANDO PRUEBAS UNITARIAS---");
        CreadorArchivo creadorPDF = new CreadorPDF();
        CreadorArchivo creadorTexto = new CreadorTexto();

        //Pruebas para evaluar el comportamiento de Factory Method
        Archivo pdf = creadorPDF.crearArchivo("prueba.pdf", 100);

        if (pdf instanceof ArchivoPDF) {
            System.out.println("OK: Factory crea ArchivoPDF");
        } else {
            System.out.println("FALLO: Factory no crea ArchivoPDF");
        }
        
        Archivo texto = creadorTexto.crearArchivo("prueba.txt", 100);

        if (texto instanceof ArchivoTexto) {
            System.out.println("OK: Factory crea ArchivoTexto");
        } else {
            System.out.println("FALLO: Factory no crea ArchivoTexto");
        }

        System.out.println("--Casos de Prueba--");

        // Caso 1: Carpeta sin archivos ni subcarpetas (Esperado: 0)
        Carpeta vacia = new Carpeta("Vacia");
        comprobar("Carpeta vacia", 0, vacia.obtenerTamanio());

        // Caso 2: Carpeta con un PDF de 120 (Esperado: 120)
        Carpeta conPDF = new Carpeta("ConPDF");
        conPDF.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        comprobar("Carpeta con PDF 120", 120, conPDF.obtenerTamanio());

        // Caso 3: Carpeta con PDF de 120 y texto de 80 (Esperado: 200)
        Carpeta conDos = new Carpeta("ConDos");
        conDos.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        conDos.agregar(creadorTexto.crearArchivo("notas.txt", 80));
        comprobar("Carpeta con PDF 120 y texto 80", 200, conDos.obtenerTamanio());

        // Caso 4: Ejemplo completo con subcarpeta de 50 (Esperado: 250)
        Carpeta padre = new Carpeta("Padre");
        padre.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        padre.agregar(creadorTexto.crearArchivo("notas.txt", 80));
        Carpeta hija = new Carpeta("Hija");
        hija.agregar(creadorTexto.crearArchivo("ejemplo.txt", 50));
        padre.agregar(hija);
        comprobar("Ejemplo completo con subcarpeta de 50", 250, padre.obtenerTamanio());

        // Caso 5: Carpeta con un archivo de tamaño 0 (Esperado: 0)
        Carpeta conCero = new Carpeta("ConCero");
        conCero.agregar(creadorTexto.crearArchivo("vacio.txt", 0));
        comprobar("Carpeta con archivo de tamanio 0", 0, conCero.obtenerTamanio());

    }

    public static void main(String[] args) {
        //Ejecución de Pruebas unitarias
        ejecutarPruebas();
        
        System.out.println("\n--- PROGRAMA PRINCIPAL ---");

        // Flujo principal refactorizado usando Factory Method, Composite y Adapter
        CreadorArchivo creadorPDF = new CreadorPDF();
        CreadorArchivo creadorTexto = new CreadorTexto();

        Carpeta clase = new Carpeta("MyP");
        clase.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        clase.agregar(creadorTexto.crearArchivo("notas.txt", 80));

        Carpeta ejemplos = new Carpeta("Ejemplos");
        ejemplos.agregar(creadorTexto.crearArchivo("ejemplo.txt", 50));
        clase.agregar(ejemplos);

        System.out.println(clase.obtenerTamanio());

        //Envío de notificación mediante el Adapter

        CorreoLegacy correoLegacy = new CorreoLegacy();
        Notificador notificador = new AdaptadorCorreo(correoLegacy);
        enviarResultado(clase, "profesor@universidad.edu", notificador);
    }
}