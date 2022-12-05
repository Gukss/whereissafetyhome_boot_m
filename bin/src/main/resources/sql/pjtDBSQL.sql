create database if not exists ssafywebpjt;

use ssafywebpjt;

create table if not exists member(
    id varchar(20) primary key,
    pw varchar(20),
    name varchar(20),
    email varchar(100),
    phone varchar(20)
);

create table if not exists interest_area(
	id int not null auto_increment primary key,
    member_id varchar(20),
    sidoName varchar(30),
    gugunName varchar(30),
    dongName varchar(30),
    
    foreign key (member_id)
    references member(id) on update cascade on delete restrict
);


insert into member(id,pw,name,email, phone) values('ssafy','ssafy','ssafy','ssafy','ssafy');

insert into interest_area(member_id,sidoName,gugunName,dongName) values('ssafy','서울특별시','종로구','보문1동');
insert into interest_area(member_id,sidoName,gugunName,dongName) values('ssafy','서울특별시','종로구','보문2동');


