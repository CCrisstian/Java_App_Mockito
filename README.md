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