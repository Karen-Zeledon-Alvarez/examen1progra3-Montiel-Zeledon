# Etapa 3 — Integración, pruebas y mejora

> Complete todas las secciones. Elimine las indicaciones entre `< >` al terminar.

## Integración



| Opción del menú | Funcionalidad | Estado |
|---|---|---|
| Permite pagar las multas y consultas cuantas multas pendientes hay y de quien | Cada vez que un prestamo tiene una multa se registra y por dia que pase se suman 200 | ✅  |

## Cambio de requerimiento

**Cambio solicitado por el docente:**

MULTAS POR ATRASO

**Clases modificadas o agregadas:**

| Clase | Capa | Cambio realizado |
|---|---|---|
| MultaPendienteException | Model | clase agregada nurva usar super() |
| PrestamoModel | modelado | se agregaron las variable double multa, boolean multa pagada y static final double MULTA_POR_DIA |
| PrestamoDAO | persistencia | double TotalMultasPendientes(int), boolean PagarMulta(int), List<> ListarUsuarioConMultas() |
| PrestamosController | Controller | string formatearMulta(PrestamoModel), void pagarMulta(), void mostrarDeudores() |

**Análisis:** ¿qué partes del diseño facilitaron el cambio? ¿qué fue necesario rehacer?

no fue necesario rehacer clases solo agregar funciones y variables, haber separado las clases hizo facil agregarle las nuevas funciones y solo se agrego una clase más pero la clase prestamo hizo todo el trabajo

## Decisiones de diseño relevantes

- Separar lo más posible las clases para hacer escalable el proyecto

## Pruebas y mejoras

- Pruebas: [`pruebas.md`](pruebas.md)
- Mejoras y defectos corregidos: [`mejoras.md`](mejoras.md)

## UML final

\docs\etapa_3\img\Diagrama ER de Biblioteca con Multas.png

## Aspectos pendientes

- ninguno

## Distribución del trabajo

| Integrante | Aportes principales |
|---|---|
| Mauricio Montiel | Controllers iniciales y Views iniciales Multas creacion de la base de datos |
| Karen Zeledon | Model final, DAO final controller final y view final |
