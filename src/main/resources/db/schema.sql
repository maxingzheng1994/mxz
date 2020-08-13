create table if not exists PEOPLE_2 (
                                    USE_ID int not null primary key auto_increment,
                                    USE_NAME varchar(100),
                                    USE_SEX varchar(1),
                                    USE_AGE NUMBER(3),
                                    USE_ID_NO VARCHAR(18),
                                    USE_PHONE_NUM VARCHAR(11),
                                    USE_EMAIL VARCHAR(100),
                                    CREATE_TIME DATE,
                                    MODIFY_TIME DATE,
                                    USE_STATE VARCHAR(1));



create table  if not exists ARTICLE (
    id int not null primary key auto_increment,
    name varchar(100),
    path varchar(400)
)