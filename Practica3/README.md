# Equipo 67. Práctica 3: Refactorización y Diseño Modular

Esta carpeta contiene la Práctica 3, aplicando los patrones de diseño **Composite**, **Factory Method** y **Adapter** para eliminar acoplamiento, duplicación y rigidez en un sistema de gestión de archivos, carpetas y notificaciones.

---

## Requisitos

* **Java:** JDK 21 (o superior)

---

## Compilación y Ejecución

### Ubicarse en el directorio del proyecto
Abre tu terminal y navega hasta la carpeta exacta donde se encuentra descargado el archivo `Main.java`. Utiliza el comando `cd`:
```
cd ruta/a/la/carpeta/del/proyecto
```

#### Compilar y Ejecutar el programa principal:
```
javac Main.java
java Main
```

**Salida esperada:**
```
--- EJECUTANDO PRUEBAS UNITARIAS---
OK: Factory crea ArchivoPDF
OK: Factory crea ArchivoTexto
--Casos de Prueba--
OK: Carpeta vacia
OK: Carpeta con PDF 120
OK: Carpeta con PDF 120 y texto 80
OK: Ejemplo completo con subcarpeta de 50
OK: Carpeta con archivo de tamanio 0

--- PROGRAMA PRINCIPAL ---
250
Para: profesor@universidad.edu
Tamanio total: 250

```

## Patrones de Diseño Aplicados

1. **Composite:** Mediante la interfaz `Elemento`, se tratan archivos (`Archivo`) y agrupaciones (`Carpeta`) de la misma forma, lo que permite calcular el tamaño sin tener que "condicionarlo" por el tipo.
2. **Factory Method:** Se delega la instanciación de archivos a creadores especializados (`CreadorArchivo`, `CreadorPDF`, `CreadorTexto`), eliminando los `if/else` basados en el texto del tipo de archivo.
3. **Adapter:** Se define la interfaz de dominio `Notificador` y la clase adaptadora `AdaptadorCorreo` para encapsular la llamada a `CorreoLegacy.send_email`, para no depender directamente de cómo está programado el servicio de correo.
