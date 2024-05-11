<h1 align="center">Unit Testing: Mockito </h1>
<p><b>"Unit Testing"</b> es una práctica de desarrollo de software en la que se escriben pruebas automáticas para validar el funcionamiento de unidades individuales de código, como funciones, métodos o clases, de forma aislada. Esto ayuda a detectar y corregir errores de manera temprana en el ciclo de desarrollo, mejorando la calidad del software.</p>
<p>Por otro lado, <b>"Mockito"</b> es un marco de prueba (framework) de Java utilizado específicamente para pruebas unitarias. Se centra en la creación de objetos simulados, también conocidos como <b>"mocks"</b>, que pueden ser utilizados para simular el comportamiento de componentes del sistema que interactúan con la unidad de código que está siendo probada. Esto permite aislar la unidad de código bajo prueba y verificar su comportamiento de manera controlada.</p>
<h2>Mockito </h2>
<p><b>Mockito</b> es una biblioteca de Java que se utiliza para crear objetos simulados (llamados <b>"mocks"</b>) en pruebas unitarias. Estos mocks son objetos que imitan el comportamiento de objetos reales dentro de un sistema, pero de una manera controlada y predefinida. Esto es útil para aislar la unidad de código que se está probando, permitiendo así enfocarse en las interacciones entre esa unidad y otros componentes del sistema.</p>
<p>Algunos conceptos clave relacionados con <b>Mockito</b>:</p>

-  <b>Mock Objects</b>: Son objetos simulados que se crean utilizando Mockito. Estos mocks imitan el comportamiento de objetos reales pero pueden ser programados para devolver valores específicos, lanzar excepciones o simplemente registrar las interacciones que tienen con ellos.
  
-  <b>Stubbing</b>: Es el proceso de configurar un mock para que devuelva valores específicos cuando se invocan sus métodos.

-  <b>Verifying</b>: Mockito proporciona métodos para verificar que se han realizado ciertas interacciones con un mock durante una prueba. Esto incluye verificar si se han llamado a ciertos métodos, con qué argumentos y cuántas veces.

-  <b>Matchers</b>: Mockito ofrece una amplia gama de matchers (correspondencias) para simplificar la escritura de pruebas. Estos matchers se utilizan para especificar condiciones sobre los argumentos de los métodos invocados en los mocks durante las pruebas.

-  <b>Capturing</b>: Mockito también permite capturar los argumentos que se pasan a un mock durante una prueba. Esto es útil cuando se desea verificar los valores de los argumentos que se pasan a un método de un mock.

<h1 align="center">Mockito: 'when()', 'thenReturn()' y 'verify()'</h1>
<p>Estos métodos son fundamentales en Mockito para configurar mocks, definir comportamientos y verificar interacciones durante las pruebas unitarias. Permiten escribir pruebas más concisas y efectivas al enfocarse en el comportamiento específico de los componentes bajo prueba.</p>
<h3>'when()' - Configuración de Comportamiento:</h3>
<p>El método 'when()' se utiliza para configurar el comportamiento esperado de un mock cuando se llama a un método específico. Permite especificar qué debe devolver el mock cuando se llame a un método particular con ciertos argumentos.</p>

```java
// Ejemplo de uso de when() para configurar un comportamiento
when(mockedObject.method()).thenReturn(result);
```
-  `mockedObject`.`method()`: Representa la llamada al método en el mock que queremos configurar.
-  `thenReturn``(result)`: Indica qué valor debe devolver el método cuando se llama en el mock. `result` puede ser un valor específico, una excepción lanzada, o incluso puede ser configurado para llamar a un método.

<h3>'thenReturn()' - Especificación de Valor de Retorno:</h3>
<p>El método 'thenReturn()' se utiliza en combinación con 'when()' para especificar qué valor debe devolver un método cuando se llama en un mock.</p>

```java
// Ejemplo de uso de thenReturn() para especificar un valor de retorno
when(mockedObject.method()).thenReturn(result);
```
-  `result`: El valor que se devuelve cuando se llama al método en el mock.

<h3>'verify()' - Verificación de Interacciones:</h3>
<p>El método 'verify()' se utiliza para verificar que se haya realizado una interacción específica con un mock. Permite asegurarse de que ciertos métodos hayan sido llamados con ciertos argumentos durante la ejecución de las pruebas.</p>

```java
// Ejemplo de uso de verify() para verificar una interacción
verify(mockedObject, times(2)).method(argument);
```
-  `mockedObject`: El mock sobre el que se está realizando la verificación.
-  `method`(argument): El método y sus argumentos cuya llamada se está verificando.
-  `times`(2): Opcionalmente, puedes especificar cuántas veces debe haberse llamado el método con los argumentos específicos.

<h1 align="center">'@Mock', '@InjectMock' y '@ExtendWith'</h1>
<p>La inyección de dependencia (DI) es un patrón de diseño que se utiliza para gestionar las dependencias entre objetos en una aplicación. La idea fundamental de la inyección de dependencia es desacoplar las clases dependientes de las clases que utilizan, permitiendo así una mayor flexibilidad, reutilización y pruebas unitarias más fáciles.</p>
<p>Las anotaciones específicas de Mockito que se utilizan comúnmente para realizar inyección de dependencia en pruebas unitarias:</p>

-  `@Mock`: Esta anotación se utiliza para marcar un campo como un mock. Mockito creará automáticamente un mock para el tipo de campo marcado con `@Mock` y lo inyectará en la instancia de la clase de prueba. Por lo general, se utiliza para inyectar dependencias simuladas en la clase de prueba.
-  `@InjectMocks`: Esta anotación se utiliza para marcar un campo en la clase de prueba que va a ser instanciado y que contiene las dependencias reales, y donde se inyectarán automáticamente los `mocks`. Mockito intentará inyectar automáticamente los mocks creados con `@Mock` en los campos marcados con `@InjectMocks`.
-  `@ExtendWith(MockitoExtension.class)`: Esta anotación se utiliza para habilitar el uso de las anotaciones `@Mock` y `@InjectMocks` en las clases de prueba. La clase `MockitoExtension` es una extensión de JUnit 5 proporcionada por Mockito que permite la integración de Mockito con las pruebas de JUnit 5.

<p>Ejemplo de cómo se utilizan estas anotaciones en una clase de prueba:</p>

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private Dependency dependencyMock;

    @InjectMocks
    private MyClass myClass;

    @Test
    void testSomething() {
        // Configuración de comportamiento del mock
        when(dependencyMock.someMethod()).thenReturn("mocked result");

        // Llamada al método de la clase bajo prueba
        String result = myClass.doSomething();

        // Verificación del resultado
        assertEquals("expected result", result);
    }
}
```

En este ejemplo:

-  `Dependency` es una dependencia simulada (un mock).
-  `MyClass` es la clase que estamos probando.
-  `@Mock` se utiliza para crear un mock de `Dependency` y se inyecta en el campo dependencyMock.
-  `@InjectMocks` se utiliza para instanciar `MyClass` y automáticamente inyectar el mock de Dependency en su campo correspondiente.
-  `@ExtendWith(MockitoExtension.class)` se utiliza para habilitar el uso de las anotaciones de Mockito en la clase de prueba.

<h1 align="centet">Mockito: 'Answer'</h1>
<p>La clase <b>'Answer'</b> en Mockito de Java es una interfaz funcional que se utiliza para proporcionar una implementación personalizada de un método cuando se invoca en un mock. Esta interfaz se utiliza junto con el método doAnswer() de Mockito para especificar acciones que deben realizarse cuando se llama a un método en un mock.</p>
<p>La interfaz <b>Answer</b> tiene un solo método abstracto llamado <b>answer()</b>, que toma un objeto de tipo InvocationOnMock como argumento y devuelve un valor. Este método se invoca cada vez que se llama al método correspondiente en el mock, y se utiliza para proporcionar la lógica personalizada que se debe ejecutar en lugar del comportamiento predeterminado del mock.</p>

Método `answer()` de la interfaz `Answer`:
```java
interface Answer<T> {
    T answer(InvocationOnMock invocation) throws Throwable;
}
```
El parámetro `invocation` de tipo `InvocationOnMock` contiene información sobre la invocación del método, como los argumentos pasados, el objeto en el que se llamó el método y otros detalles.

Ejemplo de cómo utilizar `Answer` en Mockito:
```java
when(repository.guardar(any(Examen.class))).then(new Answer<Examen>() {
  Long secuencia = 8L;
  @Override
  public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
    Examen examen = invocationOnMock.getArgument(0);
    examen.setId(secuencia++);
    return examen;
  }
});
```

-  `when`(repository.guardar(any(Examen.class))): Este método `when()` configura el comportamiento del `mock` repository cuando se llama al método guardar() con cualquier instancia de la clase Examen.
-  `.then`(new `Answer`<Examen>() { ... }): Después de especificar la llamada al método y los argumentos en `when()`, se especifica lo que debe devolver el mock usando `.then()`. Aquí, se crea una instancia de `Answer` y se proporciona una implementación personalizada para el método `answer()`.
-  Long secuencia = 8L;: Esto inicializa una variable secuencia con el valor 8L. Esta variable se utilizará para asignar un ID único a cada examen que se guarde en el `mock` repository.
-  @Override public Examen answer(`InvocationOnMock` invocationOnMock) throws Throwable { ... }: Esta es la implementación del método `answer()` de la interfaz `Answer`. Este método se invocará cada vez que se llame al método guardar() en el `mock` repository. Toma un objeto `InvocationOnMock` como argumento y devuelve un objeto Examen.
-  Examen examen = `invocationOnMock`.getArgument(0);: Dentro del método `answer()`, se obtiene el primer argumento pasado al método guardar() usando el método getArgument(0) del objeto `InvocationOnMock`. Este argumento es la instancia de Examen que se pasa al método guardar().
-  examen.setId(secuencia++);: Se asigna un ID único al examen utilizando la variable secuencia y luego se incrementa para la próxima asignación.
-  return examen;: Finalmente, se devuelve el objeto Examen modificado, que ahora tiene un ID único asignado.

En resumen, este bloque de código configura el `mock` repository para que, cuando se llame a su método guardar() con cualquier instancia de Examen, devuelva una instancia de Examen con un ID único asignado. Esto se logra utilizando una implementación personalizada de la interfaz `Answer`, que genera un ID único cada vez que se llama al método guardar().

<h3>'InvocationOnMock'</h3>
<p>InvocationOnMock es una clase en Mockito que encapsula información sobre la invocación de un método en un mock. Cuando un método de un mock es invocado durante una prueba unitaria, Mockito crea un objeto InvocationOnMock para representar esa invocación.</p>

Algunos de los métodos más comúnmente utilizados de `InvocationOnMock son`:
-  `getMethod()`: Devuelve el objeto `Method` que representa el método que fue invocado en el mock.
-  `getArguments()`: Devuelve un array de objetos que representan los argumentos pasados al método durante la invocación.
-  `getArgument(int index)`: Devuelve el argumento en la posición especificada por `index`.
-  `getMock()`: Devuelve el mock en el que se realizó la invocación.
-  `getMockingDetails()`: Devuelve detalles sobre el mock, como interfaces implementadas y configuraciones.
-  `getSequenceNumber()`: Devuelve el número de secuencia de la invocación. Las invocaciones consecutivas tendrán números de secuencia consecutivos.

<p>InvocationOnMock proporciona a los desarrolladores acceso a información detallada sobre la invocación del método, lo que puede ser útil para realizar acciones condicionales o personalizadas en respuesta a las invocaciones de los métodos de los mocks durante las pruebas unitarias.</p>

```java
when(repository.guardar(any(Examen.class))).then(new Answer<Examen>() {
  Long secuencia = 8L;
  @Override
  public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
    Examen examen = invocationOnMock.getArgument(0);
    examen.setId(secuencia++);
    return examen;
  }
});
```
En este bloque de código, `InvocationOnMock` se utiliza para acceder a los argumentos pasados al método guardar() del `mock` repository.

-  Cuando se llama al método guardar() del `mock` repository con cualquier instancia de la clase Examen (especificada por any(Examen.class)), Mockito captura esa invocación.

-  Luego, se crea una instancia de `Answer`<Examen> que proporciona una implementación personalizada para el método `answer()`. Esta implementación personalizada se ejecutará cuando se llame al método guardar() en el `mock` repository.

-  Dentro del método `answer()`:
  - `invocationOnMock` `.getArgument`(0) obtiene el primer argumento pasado al método guardar(), que es una instancia de la clase Examen.
  -  Luego, se accede al método setId() de la instancia de Examen para asignar un ID único a ese examen. Este ID único se genera utilizando una variable de secuencia (secuencia) que se incrementa con cada llamada al método guardar().
  -  Finalmente, el examen modificado se devuelve como resultado de la invocación del método guardar().

En resumen, `InvocationOnMock` permite acceder a los argumentos pasados a un método de un mock durante una invocación. En este caso específico, se utiliza para personalizar la respuesta del método guardar() del mock repository asignando un ID único a cada examen que se guarda.

<h1>BDD (Desarrollo Guiado por Comportamiento)</h1>
<p>Dentro del contexto de las pruebas unitarias en Java, "Given", "When" y "Then" son términos comúnmente asociados con el patrón de escritura de pruebas conocido como BDD (Desarrollo Guiado por Comportamiento, por sus siglas en inglés), aunque también se pueden aplicar en el marco de pruebas tradicionales como JUnit.</p>

-  <b>Given (Dado)</b>: Esta sección describe el estado inicial o las precondiciones necesarias para ejecutar la prueba. Aquí se establece el entorno de la prueba, incluyendo la configuración de objetos, datos de entrada, o cualquier otro contexto necesario para ejecutar el escenario de prueba.

-  <b>When (Cuando)</b>: En esta sección se describe la acción o el evento que se está probando. Representa la operación específica que se va a ejecutar en el sistema bajo prueba.

-  <b>Then (Entonces)</b>: Esta sección especifica el resultado esperado o el comportamiento que se debe observar después de ejecutar la acción descrita en la sección "When". Aquí se verifica que el sistema bajo prueba se comporte como se espera según el escenario de prueba.

<p>Estas secciones proporcionan una estructura clara y legible para escribir y comprender pruebas unitarias. Al utilizar estos términos, las pruebas se pueden diseñar de una manera más orientada al comportamiento y centrada en las necesidades del usuario, lo que ayuda a garantizar que las pruebas estén alineadas con los requisitos y comportamientos esperados del sistema. Además, esta estructura facilita la comunicación entre los miembros del equipo, ya que las pruebas se vuelven más legibles y comprensibles tanto para los desarrolladores como para otras partes interesadas en el proyecto.</p>
