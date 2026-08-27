# Automatización QC Frontend

Automatización de pruebas funcionales para [SauceDemo](https://www.saucedemo.com/) desarrollada como parte de la prueba técnica para Analista QC.

## Objetivo

Validar los principales flujos de usuario de SauceDemo mediante escenarios automatizados positivos y negativos, verificando la consistencia de la interfaz, las acciones realizadas y los resultados mostrados.

## Estado actual

La solución cuenta con **13 escenarios automatizados**, escritos en español:

| Módulo | Cobertura |
| --- | --- |
| Autenticación | Inicio de sesión exitoso, usuario bloqueado, credenciales inválidas y usuario vacío |
| Productos | Visualización del catálogo, cantidad de productos y producto esperado |
| Filtrado | Ordenamiento por precio y por nombre |
| Carrito | Agregar, consultar, eliminar productos, carrito vacío y campos obligatorios |
| Compra | Diligenciamiento de datos y confirmación final de compra |

SauceDemo no dispone de un campo de búsqueda textual. Por esta razón, el módulo de filtrado valida las opciones de ordenamiento disponibles en la aplicación.

## Tecnologías

- Java 17
- Gradle 9.6.0
- Selenium WebDriver
- Serenity BDD 4.2.34
- Serenity Cucumber 4.2.34
- Cucumber 7.20.1
- Gherkin
- JUnit 5.11.4
- Patrón Screenplay
- Chrome WebDriver con descarga automática

## Arquitectura del proyecto

```text
.
├── build.gradle
├── gradle
│   └── wrapper
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle
└── src
    ├── main
    │   └── java/com/qcautomation
    │       ├── configurations
    │       ├── interactions
    │       ├── models
    │       ├── questions
    │       ├── tasks
    │       └── utilities
    └── test
        ├── java/com/qcautomation
        │   ├── runners
        │   └── stepdefinitions
        └── resources
            ├── features/authentication
            ├── features/cart
            ├── features/products
            ├── features/search
            └── serenity.properties
```

### Organización Screenplay

- `configurations`: configuración general de la aplicación.
- `models`: modelos de datos utilizados por los escenarios.
- `tasks`: acciones de negocio ejecutadas por el actor.
- `interactions`: interacciones reutilizables de bajo nivel.
- `questions`: validaciones y consultas sobre el estado de la aplicación.
- `utilities`: utilidades transversales.
- `stepdefinitions`: conexión entre Gherkin y las acciones Screenplay.
- `runners`: puntos de entrada independientes por módulo.
- `features`: escenarios escritos en Gherkin.

## Requisitos

- Java 17 o superior.
- Conexión a internet.
- Google Chrome instalado.

El proyecto utiliza el Gradle Wrapper, por lo que no es necesario instalar Gradle localmente.

## Ejecución

### Windows

Ejecutar todos los escenarios:

```powershell
.\gradlew.bat clean test
```

Ejecutar las pruebas y generar el reporte HTML de Serenity:

```powershell
.\gradlew.bat clean test aggregate
Start-Process .\target\site\serenity\index.html
```

Ejecutar un módulo específico:

```powershell
.\gradlew.bat test --tests com.qcautomation.runners.AuthenticationTestSuite
.\gradlew.bat test --tests com.qcautomation.runners.ProductTestSuite
.\gradlew.bat test --tests com.qcautomation.runners.FilterTestSuite
.\gradlew.bat test --tests com.qcautomation.runners.CartTestSuite
```

### Linux o macOS

```bash
./gradlew clean test
```

## Reportes

Generar el reporte agregado de Serenity:

```powershell
.\gradlew.bat aggregate
```

El reporte se encuentra en:

```text
target/site/serenity/index.html
```

Los resultados técnicos de Gradle se generan en:

```text
build/reports/tests/test/index.html
```

## Configuración

La configuración de WebDriver se encuentra en `src/test/resources/serenity.properties`:

- Driver personalizado de Chrome con preferencias para evitar el aviso de contraseñas comprometidas.
- Descarga automática del controlador.
- Capturas de pantalla después de cada paso, incluidas las validaciones.
- Ejecución aislada de cada suite para evitar interferencias entre escenarios.

La URL base está centralizada en `AppConfiguration`. Las credenciales utilizadas corresponden a los usuarios públicos de prueba de SauceDemo y no son credenciales reales.

La URL puede sobrescribirse sin modificar el código:

```powershell
.\gradlew.bat test -Dbase.url=https://www.saucedemo.com/
```

También puede configurarse mediante la variable de entorno `SAUCEDEMO_BASE_URL`.

## Datos de prueba

| Usuario | Contraseña | Uso |
| --- | --- | --- |
| `standard_user` | `secret_sauce` | Escenarios exitosos |
| `locked_out_user` | `secret_sauce` | Escenario negativo de autenticación |

## Buenas prácticas aplicadas

- Separación entre código de automatización, definiciones de pasos y escenarios.
- Uso del patrón Screenplay.
- Reutilización de tareas, preguntas y modelos.
- Selectores basados en atributos `data-test`.
- Validaciones explícitas sobre el estado y contenido de la interfaz.
- Configuración centralizada.
- Escenarios independientes por módulo.
- Codificación UTF-8 para soportar documentación y escenarios en español.
- Exclusión de archivos generados mediante `.gitignore`.
- Configuración por defecto con posibilidad de sobrescritura por ambiente.
- Cobertura de errores funcionales de autenticación y checkout.

## Limitaciones conocidas

La automatización valida el flujo completo hasta la pantalla de confirmación `Thank you for your order!`.

## Funcionamiento del proyecto

La ejecución comienza en uno de los runners ubicados en `src/test/java/com/qcautomation/runners`. Cada runner selecciona el directorio de features correspondiente y registra el paquete de step definitions para que Cucumber conecte Gherkin con la automatización.

Antes de cada escenario, `Hooks` prepara el escenario Screenplay y crea el actor principal. Las step definitions expresan el comportamiento del usuario y delegan las acciones a las tareas del dominio. Las tareas, ubicadas en `src/main/java/com/qcautomation/tasks`, encapsulan acciones como iniciar sesión, ordenar productos, agregar o eliminar productos y diligenciar datos de compra.

Las preguntas, ubicadas en `src/main/java/com/qcautomation/questions`, consultan el estado de la interfaz y permiten validar títulos, mensajes, productos, precios y contenido del carrito. Los modelos representan datos compuestos, como credenciales y datos del comprador. Los selectores se basan principalmente en atributos `data-test` para reducir el acoplamiento con estilos visuales.

Serenity registra cada paso, evidencia los fallos y genera el reporte HTML. Gradle administra las dependencias, compila el proyecto, ejecuta JUnit y permite generar el reporte agregado mediante la tarea `aggregate`.
