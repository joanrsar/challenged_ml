# challenged_ml
Repositorio con las aplicaciones challenged mercado libre


Se asume que el servicio no requiere ningun tipo de seguridad y va ser una api publica
Para el registro de logs se hace de forma asincronica a travez de un gestor de mensajes, para este ejercicio se utiliza apache kafka

Se crean 3 apis para la resoluciòn de la solución, esto para definir notoriamente las responsabilidades del servicio

api-mercadolibre: Consulta items
api-log-job: Registro de logs de las transacciones tomando los mensajes a travez de un topic de Kafka
api-health: Consulta los logs de las transacciones 

Para el api de consulta de Items el acceso a datos se hace a traves de JPA ya que es una forma sencilla si la aplicacion no
requiere consultas complejas y hace uso de CRUDS sencillos

Para el api de registro de log y el servicio de Health se utiliza conexion a traves de JDBC debido a que este ultimo servicio
debido al tipo de consulta realizada su rendimiento favorece mas cundo se utiliza sql nativo ya que se elimina una capa
de interpretaciòn de HQL a SQL 

En la carpeta Scripts se encuentran los scripts de base de datos y la creaciòn de las imagenes de docker construidas para la soluciòn
