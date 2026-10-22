# Mejoras

Este documento resume los cambios realizados al proyecto, separados en dos categorías: **corrección de defectos** y **mejora de calidad**.

---

## Corrección de defectos

### 1. Ventana principal duplicada al volver desde "Add Book"

**Problema**

Se repetía la ventana *Main* al presionar el botón **Return** de la ventana *Add Book*.

**Causa**

El código de la ventana *Add Book* crea una nueva ventana principal al presionar el botón. Como la ventana *Main* original nunca se cerraba, aparecían dos a la vez.

**Solución**

Se agregó un `.dispose()` en el momento en que se decide abrir la ventana *Add Book*, cerrando así la ventana *Main*.

```java
// Al abrir "Add Book", se cierra la ventana Main
this.dispose();
```

> **Nota:** solo se modificó esta ventana, ya que los demás accesos estaban correctamente programados.

---

### 2. El proyecto finalizaba al presionar la "X" de algunas ventanas

**Problema**

Al darle a la **X** de algunas ventanas, se finalizaba todo el proyecto.

**Causa**

La causa era la instrucción `setDefaultCloseOperation(EXIT_ON_CLOSE);`, que hace que la aplicación finalice por completo tras presionar la X.

**Solución**

La solución más sencilla por la que se optó fue cambiar esa línea de código por `DISPOSE_ON_CLOSE`, de modo que solo se cierre la ventana actual.

```java
// Antes
setDefaultCloseOperation(EXIT_ON_CLOSE);

// Después
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
```

---

## Mejora de calidad

### 3. Mensaje de error al dejar campos vacíos en "Add Book"

**Mejora**

Se mejoraron los mensajes de error para el usuario en la ventana *Add Book*, ya que en las demás ventanas ya estaba implementado.

**Situación anterior**

Cuando se dejaban campos vacíos, el código no permitía agregar el libro, pero el programa no mostraba ningún mensaje que explicara el motivo. El usuario presionaba el botón y no ocurría nada, sin saber qué estaba haciendo mal.

**Cambio realizado**

Se agregó un `JOptionPane` de tipo **Validation Error**, con la finalidad de notificarle al usuario que la razón por la que no se le permitió agregar el libro fue simplemente la falta de información en los campos correspondientes.

```java
// Aviso cuando falta información en los campos
JOptionPane.showMessageDialog(ABView, "Please, fill all gaps first.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
```

**Resultado**

Ahora *Add Book* se comporta igual que las demás ventanas del proyecto: si falta información, el usuario recibe una explicación clara en lugar de un silencio.

---

## Resumen

| # | Categoría | Cambio | Causa o motivo | Solución |
|---|-----------|--------|----------------|----------|
| 1 | Defecto | Ventana *Main* duplicada | *Add Book* crea una nueva *Main* sin cerrar la anterior | `.dispose()` al abrir *Add Book* |
| 2 | Defecto | La aplicación se cerraba con la X | `EXIT_ON_CLOSE` | `JFrame.DISPOSE_ON_CLOSE` |
| 3 | Calidad | Sin mensaje al dejar campos vacíos en *Add Book* | El código bloqueaba el guardado sin avisar al usuario | `JOptionPane` de tipo *Validation Error* |
