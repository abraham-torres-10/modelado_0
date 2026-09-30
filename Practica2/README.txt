# practica_arbol_b

EQUIPO 67
Practica Arbol B

Lenguaje utilizado:
Java

Instrucciones de ejecución:

* Abrimos la terminal en la carpeta donde están los archivos
Para compilar ponemos:
javac Main.java ArbolB.java Nodo.java
java Main

Explicación casos prueba:

Inserción de elementos – se inserta una secuencia inicial de llaves (20, 40, 10, 30, 50, 60, 70, 5, 15, 25, 35, 45) y se imprime el árbol por niveles para ver su estructura tras dividirse y acomodar los nodos.
Búsqueda de elementos – buscar(35) debe confirmar que existe, mientras que buscar(99) debe indicar que no existe.
Eliminación de elementos – se eliminan algunas llaves específicas (25, 10, 70, 5) que provocan diferentes casos de eliminación (borrar en hojas, pedir prestado, etc.) y se imprime el árbol de nuevo para confirmar que los nodos se reacomodaron correctamente.
Búsquedas finales – se confirma que buscar(25) ya no existe pues fue eliminada, pero buscar(35) sigue existiendo.

Explicación representación de un nodo:

* En el código, un nodo se representa guardando una lista de claves (enteros) ordenadas y una lista de referencias a sus nodos hijos. También es capaz de identificar si es una hoja o si está desbordado. Todo esto se maneja con listas dinámicas de Java para facilitar agregar y remover elementos.

Explicación de qué significa m = 4 y por qué cada nodo admite máximo tres llaves:

* Que el orden sea m = 4 significa que la cantidad máxima de apuntadores o hijos que puede tener un nodo es de 4. Por las reglas de los árboles B, la cantidad máxima de llaves en un nodo siempre es el número de hijos menos 1 (m - 1). Entonces, 4 - 1 = 3, por lo que cada nodo admite máximo tres llaves.

Explicación de cómo se decide qué hijo seguir durante una búsqueda:

* Se compara la llave que estamos buscando con las llaves que están dentro del nodo actual para encontrar su índice correspondiente. Si la llave no se encuentra ahí y el nodo no es una hoja, se baja al hijo que está en ese mismo índice, así garantizamos irnos por la rama que tiene los valores que son menores que la siguiente llave y mayores que la anterior.

Explicación de qué ocurre cuando un nodo alcanza cuatro llaves:

* Como nuestro límite es tres, al momento de insertar una cuarta llave el nodo detecta que está desbordado. Lo que ocurre entonces es que el nodo se tiene que dividir obligatoriamente en dos nodos distintos para mantener el equilibrio.

Explicación de la convención de promoción usada en la práctica:

* Cuando el nodo se divide por tener cuatro llaves, la llave del medio (la llave promovida) se extrae del nodo y sube para insertarse directamente en el nodo padre. Si el nodo que se dividió y promovió la llave era la raíz, esa llave promovida se convierte en la nueva raíz del árbol, lo cual aumenta su altura.

Explicación breve de redistribución y fusión:

* Cuando se elimina una llave y un nodo se queda subocupado (vacío o por debajo del mínimo permitido), se hace una redistribución si algún nodo hermano tiene llaves de sobra para prestar; en este caso sube una llave del hermano al padre y baja la del padre al nodo subocupado.
* Si ningún hermano puede prestar llaves porque también están al límite, se hace una fusión, que consiste en unir al nodo afectado, a su hermano y a la llave separadora del padre en un solo nodo consolidado.

Respuesta a las preguntas marcadas en esta guía:

¿Porque una búsqueda no debe recorrer todos los hijos de un nodo?

Porque en un árbol B, las llaves dentro de un nodo están ordenadas. Esto permite que la búsqueda se realice de manera más eficiente, utilizando una comparación binaria en lugar de una búsqueda lineal. 
Al comparar la llave buscada con las llaves del nodo, se puede determinar rápidamente en qué hijo continuar la búsqueda, evitando recorrer todos los hijos.
