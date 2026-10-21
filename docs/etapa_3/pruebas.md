# Pruebas documentadas

> Mínimo diez pruebas: al menos cuatro exitosas, cuatro de error y la operación de negocio principal en ambos sentidos (éxito y rechazo).

## Tipo de pruebas

- [ X ] Manuales con evidencia
- [ ] Automatizadas con JUnit 5 (`mvn test`) — clases: `<NombreTest>`

## Casos de prueba

| ID | Tipo (éxito / error) | Descripción | Datos de entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|---|---|
| P-01 | error | no permite registrar una fecha limite menor o igual al dia del prestamo | fecha limite 21/10/2026, fecha prestamo 21/10/2026 |  que nos de error al registrar el prestamo | no permite registrar el prestamo y muestra mensaje de error |  ❌ |
| P-02 | error | no permite que en id de usuario se ingresen letras solo numeros | Id Usuario 87766a | | no permite registrar el prestamo y muestra mensaje de error | ❌  |
| P-03 | error | al tratar de registrar un correo invalido sin el @ | kmercedesgmail.com | no se registre | no se registra y muestra mensaje de error | ❌ |
| P-04 | error | no permite registrar un numero de cedula menor a los 9 digitos | identificacion a33445 | no permita el registro | no permitio el registro y mostro mensaje de error | ❌ |
| P-05 | error OPERACION PRINCIPAL | NO SE PERMITE HACER UN PRESTAMO SI EL USUARIO ESTA INACTIVO | Usuario inactivo | no permite registrar el prestamo | no se registro el prestamo y mostro un mensaje de error |  ❌  |
| P-06 | exito | permite registrar los usuario correctamente | identificacion 788765548 nombre jesi correo jesi@gmail.com usuario inactivo | registre correctamente al usuario aunque este inactivo | se registro correctamente el usuario y mostro mensaje de exito | ✅ |
| P-07 | exito | permite modificar el estado inactivo del usuario y viceversa | Usuario activo ✅ | se permitan las modificaciones del estado | se modifico correctamente el estado y mostro mensaje de exito |  ✅  |
| P-08 | exito | se pueden agregar correctamente los ejemplares o copias de libros | libro duolingo codigo del ejemplar duo45 estado disponible | se registre con exito | se registro correctamente y mostro mensaje de exito | ✅  |
| P-09 | exito OPERACION PRINCIPAL | se pueden pagar las multas pendientes | se paga la multa | deja de salir en la lista de deudores | se registro correctamente y mostro mensaje de exito | ✅  |
| P-10 | exito | se pueden eliminar los prestamos | se selecciono de la tabla y se dio eliminar | se elimine de los prestamos | se registro correctamente la eliminacion y mostro mensaje de exito |  ✅ |

## Evidencias

<Salida de consola, salida de `mvn test` o capturas en `docs/etapa_3/img/`.>

```Successful Connection
Loan Successfully Added
Example Successfully added.
Example Successfully added.
Example Successfully added.
Loan Successfully Added
WARNING: row index is bigger than sorter's row count. Most likely this is a wrong sorter usage.
User successfully added.
User successfully added.
Example Successfully added.
User successfully added.
Loan Successfully Added
```

## Resumen

| Total | Exitosas | Fallidas |
|---:|---:|---:|
| 10 | 5  | 5 |
