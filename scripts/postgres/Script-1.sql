--Se asume que todos los campos son obligatorios menos start time y stop time
--Se asume que el item_id es unico
create table item(
 item_id varchar(30) not null,
 title varchar(100) not null,
 category_id varchar(100) not null,
 price float not null,
 start_time timestamp ,
 stop_time timestamp
);

ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ("item_id");


select *
from item;

create table item_children(
 id serial primary key not null,	
 item_id varchar(30) not null,
 item_children_id varchar(30) not null,
 stop_time timestamp not null
);


ALTER TABLE item_children 
ADD CONSTRAINT item_id_fk 
FOREIGN KEY (item_id) 
REFERENCES item(item_id) 

delete from item_children ;
select * from item_children;


-----------------------------------------------TABLA LOG----------------------------------------------------------------
create table LOG(
id_log serial  primary key not null,
codigo_respuesta integer not null,
tiempo_ejecucion integer not null,
consumio_api integer not null,
tiempo_ejecucion_api integer,
fecha timestamp default current_date
)
;

select * from log;


select  TO_CHAR(fecha,'YYYY-MM-DD HH24-MI') fecha, 		avg(l.tiempo_ejecucion) promedio_respuesta, 		count(1) total_peticiones, 		avg(l.tiempo_ejecucion_api) promedio_respuesta_api, 		sum(l.consumio_api) cantidad_apis 
from log l 
where l.fecha >= current_date -2   group by TO_CHAR(fecha,'YYYY-MM-DD HH24-MI')  order by fecha


select  CODIGO_RESPUESTA ,		COUNT(1) CONTADOR 
from log l where to_char(L.FECHA,'YYYY-MM-DD HH24-MI') = '2020-08-08 21-24' 
group by CODIGO_RESPUESTA;
