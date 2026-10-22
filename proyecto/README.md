# README!

**Curso:** Ingeniería en Sistemas de Información

**Integrantes:**

* Karen Zeledón Alvares
* Mauricio Daniel Montiel Valencia

**Caso Asignado:** Administración de Biblioteca

---

## 1. DESCRIPCIÓN

Permite crear usuarios(Nombre, Correo, ID) en estado activo o inactivo, también 
permite añadir libros a la biblioteca, como a su vez brindar ejemplares con su 
respectivo código de identificación, y además permite crear préstamos de libros
(solo si el usuario está activo).

Las opciones de eliminar, modificar y consultar están disponibles para los
préstamos, y también para la creación de ejemplares y usuarios.

---

## 2. FUNCIONALIDADES IMPLEMENTADAS

Se mencionan de manera resumida junto a la descripción, por lo que no es
pertinente repetirlas. Aun así, se hara algo de mención a las funcionalidades
de cada pantalla:

* Library: ventana principal, muestra los libros y los
  busca por ISBN o título.
* Add Book: registra libros.
* Book Copies: administra los ejemplares de cada libro.
* Prestamo: administra los préstamos, las multas y los deudores.
* Create User: administra los usuarios.

---

## 3. REGLAS DE NEGOCIO IMPLEMENTADAS

### Usuarios:

* La identificación debe tener exactamente 9 dígitos numéricos.
* No se pueden registrar dos usuarios con la misma identificación.
* Identificación, nombre y correo son obligatorios, y el correo debe tener
  un formato válido.
* Un usuario puede estar Activo o Inactivo (por defecto se crea Activo).
* La identificación no se puede cambiar al modificar un usuario.

### Libros:

* Cada libro se identifica por su ISBN (Book Code), y además tiene
  título, autor y año.
* El año solo acepta números.
* Para administrar ejemplares primero debe existir al menos un libro.

### Ejemplares:

* Cada ejemplar tiene un código único y pertenece a un libro.
* No se pueden registrar dos ejemplares con el mismo código.
* El estado de un ejemplar puede ser Disponible, Prestado o Dañado.
* El código no se puede cambiar al modificar un ejemplar.
* Antes de eliminar un ejemplar se pide confirmación, y si está asociado
  a un préstamo el sistema avisa que no se pudo eliminar.

### Préstamos:

* El número de préstamo se genera automáticamente.
* Solo se pueden prestar ejemplares en estado Disponible.
* El usuario debe existir y estar Activo para solicitar un préstamo.
* La fecha del préstamo y la fecha límite son obligatorias.
* La fecha límite no puede ser anterior a la fecha del préstamo.
* La fecha de devolución es opcional, pero no puede ser anterior a la
  fecha del préstamo.
* Si el préstamo se registra en estado Activo, el ejemplar pasa
  automáticamente a estado Prestado.

### Multas:

* Si el libro se devuelve después de la fecha límite se cobra una multa
  de ₡200 por cada día de atraso.
* Si se devuelve a tiempo, o todavía no se ha devuelto, la multa es ₡0.
* Un usuario con multas pendientes no puede solicitar un nuevo préstamo
  hasta pagarlas (botón "Pagar multa").
* El botón "Ver deudores" muestra los usuarios con multas pendientes y el
  total que debe cada uno.
* Al modificar un préstamo la multa se recalcula, y solo se mantiene como
  pagada si el monto no cambió y ya estaba pagada.

---

## 4. ARQUITECTURA Y ESTRUCTURA DEL PROYECTO

El proyecto usa el patrón MVC (Modelo - Vista - Controlador), junto con una
capa de persistencia DAO que es la única que habla con la base de datos.

```
Vista  <---->  Controlador  ---->  DAO  ---->  Base de datos (MySQL)
                    |
                  Modelo (los datos viajan entre las capas)
```

Estructura de paquetes:

```
Main/               Main.java
View/               Pantallas hechas con Java Swing
Controller/         Eventos de los botones y lógica de cada pantalla
Model/              Clases que representan los datos
Persistence/        DAO: consultas SQL
DataBaseConnection/ Conexión a la base de datos
Validations/        Validaciones de texto
```

Base de datos (relaciones):

```
libro (isbn, titulo, autor, anio)
    1 libro -> muchos ejemplares

ejemplar (codigo, libro_isbn, estado)
    1 ejemplar -> muchos préstamos

usuario (identificacion, nombre, correo, activo)
    1 usuario -> muchos préstamos

prestamo (id, ejemplar_codigo, usuario_id, estado, fecha_prestamo,
          fecha_limite, fecha_devolucion, multa, multa_pagada)
```

---

## 5. CONFIGURACIÓN DE LA BASE DE DATOS Y PASOS PARA EJECUTAR

Ya está configurada, y no es necesario ningún paso para ejecutar la BD, solo
tener el programa y correrlo. Aunque, por supuesto, conectado a una red de 
internet. 

---

## 6. CÓMO EJECUTAR LAS PRUEBAS

Iniciar el proyecto: F6, sino Shift + F6, si el último falla, entonces
Shift + Fn + F6.

El programa es fácil de entender por lo que no hace falta mucha explicación,
salvo por 2 botones presentes en la creación de usuarios, ejemplares y
préstamos, los cuales son "Modificar" y "Consultar":

* **Modificar:** selecciona una fila, este te agregará los datos
  automáticamente en las líneas de texto, tras ello cambia lo
  que quieras, luego de realizar los cambios, presiona
  nuevamente el botón "Modificar".

* **Consultar:** puedes agregar el número de identificación de x objeto de la
  ventana y luego darle al botón con la finalidad de buscarlo
  más rápido, aunque también puedes buscarlo mediante la tabla.

---

## 7. TECNOLOGÍAS USADAS

* Apache NetBeans IDE 28
* JDK: javac 25.0.1
* Java Swing (interfaz gráfica)
* JDBC con MySQL
* JDateChooser (JCalendar) para escoger las fechas

---

## 8. ENLACE DE VIDEO

Enlace de Video: https://youtu.be/umVaKC1dGR0
