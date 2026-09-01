import java.util.Scanner;

/**
 * Programa que solicita el nombre de un alumno y tres calificaciones,
 * calcula el promedio y muestra si el alumno está aprobado o reprobado.
 */
public class modelado_0 {

    /**
     * Muestra en pantalla si el alumno aprobó o reprobó según su promedio.
     *
     * @param name nombre del alumno
     * @param prom promedio de calificaciones del alumno
     */
    public static void aprobado(String name, double prom) {
        if (prom >= 6)
            System.out.println(name + " tu calificación es: " + prom + " estás APROBADO");
        else
            System.out.println(name + " tu calificación es: " + prom + " estás REPROBADO");
    }

    /**
     * Método principal del programa.
     * Pide el nombre del alumno y tres calificaciones válidas (entre 0 y 10),
     * calcula el promedio y llama al método {@link #aprobado(String, double)}
     * para mostrar el resultado final.
     *
     * @param args argumentos de línea de comandos (no se usan aquí)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double promedio;      // Promedio final de las 3 calificaciones
        String nombre;        // Nombre del alumno
        double calificacion = 0; // Calificación capturada en cada vuelta del ciclo
        double total = 0;     // Suma acumulada de las calificaciones
        boolean paso;         // Indica si la calificación ingresada es válida

        System.out.println("Ingresa tu nombre");
        nombre = scanner.nextLine();

        // Ciclo que pide 3 calificaciones, validando que sean correctas
        for (int i = 0; i < 3; i++) {
            paso = false;
            System.out.println("Ingresa tu calificacion " + (i + 1));

            // Se repite hasta que el usuario ingrese un número válido entre 0 y 10
            while (paso != true) {
                try {
                    calificacion = scanner.nextDouble();
                    if (calificacion >= 0 && calificacion <= 10) {
                        paso = true; // Calificación válida, se sale del ciclo while
                    } else {
                        System.out.println("Calificación inválida, debe estar entre 0 y 10.");
                    }
                } catch (Exception e) {
                    // Se captura si el usuario ingresa algo que no es un número
                    System.out.println("Dato inválido. Intenta de nuevo.");
                    scanner.next(); // Limpia el dato inválido del scanner
                }
            }
            total += calificacion; // Se suma la calificación válida al total
        }

        promedio = total / 3; // Se calcula el promedio de las 3 calificaciones
        aprobado(nombre, promedio); // Se muestra el resultado final
    }
}
