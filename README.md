# challenged_ml
# Repositorio con las aplicaciones challenged mercado libre

# Consideraciones
Se asume que el servicio no requiere ningun tipo de seguridad y será una api publica </br>
Para el registro de logs se hace de forma asincronica a travez de un gestor de mensajes, para este ejercicio se utiliza apache kafka</br>
Se crean 3 apis para la resoluciòn de la solución, esto para separar notoriamente las responsabilidades del servicio</br>
Para el api de consulta de Items el acceso a datos se hace a traves de JPA ya que es una forma sencilla si la aplicacion no requiere consultas complejas y hace uso de CRUDS sencillos y ejecutar el microservicio api-log-job

Para el api de registro de log y el servicio de Health se utiliza conexion a traves de JDBC debido a que este ultimo servicio debido al tipo de consulta realizada su rendimiento favorece mas cundo se utiliza sql nativo ya que se elimina una capa de interpretaciòn de HQL a SQL 

En la carpeta Scripts se encuentran los scripts de base de datos y la creaciòn de las imagenes de docker construidas para la solución onPremise

La solución  contempla 3 servicios con las siguietes responsabilidades

api-mercadolibre: Consulta items <br>
api-log-job: Registro de logs de las transacciones tomando los mensajes a travez de un topic de Kafka<br>
api-health: Consulta los logs de las transacciones<br>

# Solución Hibrida 

Se contempla una segunda solución hibrida con una base de datos en AWS para este caso, el registro de logs no se hace a traves de Apache Kafka esta responsabilidad la asume el servicio de api-mercadolibre, esta version es la que se tiene por default si se quiere hacer uso de la versión numero 1 tiene que cambiarse el parametro
kafka a S en el application.properties

Para esta se sacrifica un poco los tiempos de respuesta pero se mejora la disponibilidad al tener un servicio de alta disponibilidad en el cloud de AWS.

