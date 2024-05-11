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
