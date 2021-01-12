

drop table if exists users;

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   id                   varchar(36)                    null,
   username             varchar(5)                    null,
   password             varchar(32)                    null,
   create_date          dateTime                       null,
   phone                varchar(32)                    null
);
