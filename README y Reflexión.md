## README y Reflexión

### 1. ¿Qué significa programar de forma estructurada?
Es un paradigma de programación basado en organizar el código de manera clara y secuencial, utilizando bloques lógicos bien definidos y evitando saltos desordenados. Su objetivo es que el programa sea fácil de leer, probar y mantener.

### 2. ¿Cuáles son las tres estructuras fundamentales de control?
* Secuencia: Ejecución de instrucciones una tras otra en el orden en que están escritas. 
* Selección (o Condicional): Toma de decisiones según una condición lógica.
* Iteración (o Repetición): Repetición de un bloque de instrucciones mientras se cumpla una condición.

### 3. ¿Qué ventaja tuvo separar el programa en funciones?
Permitió ordenar la lógica en módulos. Al aislar la evaluación del alumno en la función `aprobado(name, prom)`, el método main queda más limpio y enfocado en la captura de datos. Además, facilita modificar el criterio de aprobación sin alterar el flujo de captura ni el cálculo del promedio.

### 4. ¿Qué función tendría que modificarse si la calificación mínima aprobatoria cambiara?
Habría que modificar únicamente la función `aprobado(String name, double prom)`, específicamente cambiando el valor en la condición `if (prom >= 6)`.

### 5. ¿Qué diferencia conceptual observa entre una función y un método?
- Función: Es un bloque de código independiente que recibe parámetros, realiza una tarea y devuelve (o no) un resultado. Existe de manera autónoma en lenguajes estructurados/funcionales.
- Método: Es una función que pertenece a una clase o interactúa con un objeto dentro del paradigma de Programación Orientada a Objetos (en Java, como `public static void aprobado(...)` dentro de la clase `modelado_0`).

### 6. ¿Qué representa un commit en Git?
Representa una "instantánea" (*snapshot*) del estado del proyecto en un momento determinado. Guarda los cambios realizados en los archivos junto con un mensaje explicativo, autor y un identificador único (hash), formando la base del historial de versiones.

### 7. ¿Por qué es preferible realizar varios commits pequeños en lugar de un solo commit al final?
Porque permite un control granular de los cambios. Si surge un error, es mucho más sencillo identificar la causa exacta y revertir únicamente el commit defectuoso sin perder el resto del avance. Además, documenta claramente la evolución del código paso a paso.
