import java.util.Scanner;

public class modelado_0{

    public static void aprobado(String name, double prom){
        if (prom>=6)
            System.out.println(name +  " tu calificación es: " + prom +" estás APROBADO");
        else 
            System.out.println(name + " tu calificación es: " + prom +" estás REPROBADO");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double promedio;
        String nombre;
        double calificacion = 0;
        double total = 0;
        boolean paso;

        System.out.println("Ingresa tu nombre");
        nombre= scanner.nextLine();

        for (int i = 0; i<3; i++ ){
            paso = false;
            System.out.println("Ingresa tu calificacion " + (i+1) );
            while (paso != true) {
                try {
                    calificacion = scanner.nextDouble();
                    if (calificacion >= 0 && calificacion<=10) {
                        paso = true; 
                    } else {
                        System.out.println("Calificación inválida, debe estar entre 0 y 10.");
                    }
                } catch (Exception e) {
                    System.out.println("Dato inválido. Intenta de nuevo.");
                    scanner.next();
                }
            }
            total += calificacion;
             
        }
        promedio = total/3;
        aprobado(nombre, promedio);
    }
}