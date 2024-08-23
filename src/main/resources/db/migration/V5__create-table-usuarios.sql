create table usuarios(

    id SERIAL not null ,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)

);