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

select *  from ITEM;


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

select * from item_children;
-----------------------------------------------TABLA LOG----------------------------------------------------------------
create table LOG(
id_log serial  primary key not null,
codigo_respuesta integer not null,
tiempo_ejecucion integer not null,
consumio_api varchar(1) not null,
tiempo_ejecucion_api integer,
fecha timestamp default current_date
)
;

select * from log;
