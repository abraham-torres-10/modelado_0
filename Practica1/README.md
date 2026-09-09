# practica_1
EQUIPO 67
Practica 1

Lenguaje utilizado: 
Java

Instrucciones de ejecución:
- Abrimos la terminal en la carpeta donde está el archivo
Para compila ponemos:
   javac practica_1.java
   java practica_1

Explicación casos prueba:

Búsqueda en tabla vacía – buscar(5) antes de insertar nada, debe dar null.
Inserción de elementos – se insertan 18, 10 y 23, y se imprime la tabla para verlos ubicados.
Búsqueda de elementos existentes – buscar(18) y buscar(10) deben regresar sus valores.
Manejo de colisiones – se insertan 24 y 31, que junto con 10 caen en el mismo índice (mod 7 = 3), formando una lista encadenada en ese bucket.
Eliminación en una colisión – se elimina 24, que quedó en medio de la cadena, y se confirma que 10 y 31 siguen existiendo mientras 24 ya no.
Búsqueda de llave inexistente – buscar(100) debe dar null.
Actualización de valor – se vuelve a insertar la llave 18 con otro valor, y se comprueba que lo reemplaza en vez de duplicar el nodo.

Al final se repite una secuencia fija de estas mismas operaciones (las "pruebas obligatorias") en una tabla nueva, para que se vea todo el flujo de una sola corrida.

Explicación función hash:
- Aqui se usa el método de la división

M = 7 es el tamaño del arreglo tabla que es el número de cubeta (este termino así fue utilizado en clase).
Dado un key (número entero), la función calcula key % M es decir, el residuo de dividir key entre 7.
Como el residuo de una división entre 7 siempre está entre 0 y 6 el resultado siempre cae dentro de un índice válido del arreglo (tabla[0] a tabla[6])

EJEMPLO
dispersion(10) → 10 % 7 = 3 → se guarda en tabla[3]
dispersion(17) → 17 % 7 = 3 → también en tabla[3] (colisión con el anterior)
dispersion(21) → 21 % 7 = 0 → se guarda en tabla[0]


Explicación manejo de colisiones:
- Las colisiones se manejaron con un encadenamiento, que se maneja con listas ligadas en cada posición de la tabla. Cuando una llave se inserta en una cubeta donde ya existe un elemento, lo que se hace es que el nuevo elemento se agrega al inicio de la lista correspondiente a esa cubeta, así se logra poder tener almacenados más de un elemento en una misma posición sin generar algún problema.

Explicación producción del mismo hash:
- Cuando dos llaves producen el mismo hash, es cuando ocurre una colisión, pues ambas se dirigen a la misma cubeta. Con la manera en que implementamos nuestro manejo de colisiones, ambas llaves se pueden insertar en la lista ligada de la cubeta.

Explicación cuando dos varias llaves caen en la misma cubeta:
- Estas se almacenan en la lista ligada correspondiente, no se genera ninguna colisión que afecte al programa, y se logra poder almacenar varias llaves en la misma cubeta.

Factor de carga obtenido:
- Como nuestra tabla cuenta con m = 7, y nuestro número de elementos durante las pruebas fue de n = 3, nuestro factor de carga fue de 3/7 = 0.4285
