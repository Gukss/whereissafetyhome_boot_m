create database if not exists ssafywebpjt;

use ssafywebpjt;

create table if not exists member(
	member_no int auto_increment primary key,
    id varchar(20) unique not null,
    pw varchar(20),
    name varchar(20),
    email varchar(100),
    phone varchar(20)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

create table if not exists interest_area(
	interest_area_no int not null auto_increment primary key,
    member_no int,
    sidoName varchar(30),
    gugunName varchar(30),
    dongName varchar(30),
    
    foreign key (member_no)
    references member(member_no) on update cascade on delete restrict
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `friends_board` (
  `friends_article_no` INT NOT NULL AUTO_INCREMENT,
  `member_no` int NULL DEFAULT NULL,
  `member_name` varchar(20) not null,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `content` text NULL DEFAULT NULL,
  `views` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`friends_article_no`),
  INDEX `friends_board_to_member_member_no_fk` (`member_no` ASC) VISIBLE,
  CONSTRAINT `friends_board_to_member_member_no_fk`
    FOREIGN KEY (`member_no`)
    REFERENCES `ssafywebpjt`.`member` (`member_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `ssafywebpjt`.`friends_board_comment` (
  `friends_board_comment_no` INT NOT NULL AUTO_INCREMENT,
  `member_no` int NULL DEFAULT NULL,
  `member_name` varchar(20) not null,
  `comment_text` VARCHAR(500) NULL DEFAULT NULL,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `friends_article_no` INT NULL DEFAULT NULL,
  PRIMARY KEY (`friends_board_comment_no`),
  INDEX `friends_board_comment_to_friends_board_friends_article_no_fk` (`friends_board_comment_no` ASC) VISIBLE,
  INDEX `comment_to_member_fk_nox` (`member_no` ASC) VISIBLE,
  CONSTRAINT `friends_board_comment_to_friends_article_no_fk`
    FOREIGN KEY (`friends_article_no`)
    REFERENCES `ssafywebpjt`.`friends_board` (`friends_article_no`),
  CONSTRAINT `friends_board_comment_to_member_member_no_fk`
    FOREIGN KEY (`member_no`)
    REFERENCES `ssafywebpjt`.`member` (`member_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


insert into member(id,pw,name,email, phone) values('ssafy','ssafy','ssafy','ssafy','ssafy');
insert into member(id,pw,name,email, phone) values('gukss','gukss','gukss','gukss','gukss');

insert into interest_area(member_no, sidoName,gugunName,dongName) values(1 ,'서울특별시','종로구','보문1동');
insert into interest_area(member_no,sidoName,gugunName,dongName) values(1,'서울특별시','종로구','보문2동');

insert into friends_board(member_no, member_id, title, content, views) values(1, 'ssafy', '유성온천역에서', '7시에 만나요', 100);
insert into friends_board(member_no, member_id, title, content, views) values(2, 'gukss', '이사왔어요', '안녕하세요', 10);

insert into friends_board_comment(member_no, member_id, comment_text, friends_article_no) values(1,'ssafy', '핫도그', 1);
insert into friends_board_comment(member_no, member_id, comment_text, friends_article_no) values(1,'ssafy', '닭꼬치', 1);
insert into friends_board_comment(member_no, member_id, comment_text, friends_article_no) values(2,'gukss', '커피', 1);

select * from friends_board;
select * from friends_board_comment;

-- 프렌즈 보드 리스트 -- 
select fb.friends_article_no, fb.member_no, fb.title, fb.content, fb.views, fb.register_time, m.id from friends_board fb join member m on fb.member_no = m.member_no ; 

-- 프렌즈 보드 게시글 --
select * from friends_board where member_no = 1;

-- 프렌즈 보드 게시글 댓글 --
select * from friends_board_comment where friends_article_no = 1;

-- 가로등 테이블 --
CREATE TABLE IF NOT EXISTS `street_lamp` (
		street_lamp_no int auto_increment,
        street_lamp_name varchar(100),
        lat varchar(30),
        lng varchar(30)
);

commit;