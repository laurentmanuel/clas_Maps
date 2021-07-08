create database tourAdvisor;

use tourAdvisor;

create table point(
	id_point int auto_increment primary key not null,
	lat_point double,
	lon_point double
);