create database project;

use mvc;
select * from member;

use project;

show tables;

drop database project;

drop table member;
drop table rv_board;
drop table member_grade;
drop table message;
drop table chat;
drop table alert;
drop table restriction;
drop table reply;
drop table location;
drop table admin_info;
drop table noti_board;
drop table sendmessage;
drop table receivemessage;

create table member (
   member_id varchar(50) primary key,
    member_password varchar(30) not null,
    member_name varchar(50) not null,
    member_birthday varchar(50) not null,
    member_gender varchar(1) not null,
    member_phone varchar(30) not null,
    member_trip_exp int not null,
    member_register_date datetime,
    -- 패스워드 수정 날짜
    member_updatePassword_date datetime,
    -- 연락처 수정 날짜
    member_updatePhoneNum_date datetime,
    -- 이메일 수정 날짜
    member_updateEmail_date datetime,
    -- 주소 수정 날짜
    member_updateAddress_date datetime,
    -- 프로필사진 수정 날짜
    member_updateProfile_date datetime,
    member_profile_pic varchar(300),
    -- 이메일 추가
    member_email varchar(300) not null,
    -- 우편번호 추가
    member_Zip_code varchar(100) not null,
    -- 도로명 주소 추가
    member_Street_name_address varchar(300) not null,
    -- 상세 주소 추가
    member_Detailed_Address varchar(300),
    member_type int,
     constraint member_member_gender_CK check (member_gender in ('M','F','남','여'))
);

create table rv_board_favorite(
rv_board_index int not null,
member_id varchar(50) not null

);

select * from member where member_id ='123' and member_birthday = '2018-11-01' and member_phone = '01012311231' and member_email = '123@mail.com';

update member set member_password='5' , member_phone='5' , member_profile_pic='6' where member_id ='1231';
select * from member;
select * from location;

select * from member where member_phone = '01012311231';
truncate member;
drop table member;
desc member;


create table rv_board_android(
   rv_board_index int auto_increment unique,
    rv_board_post_date datetime,
    member_id varchar(50) not null,
    rv_board_title varchar(100) not null,
    rv_board_content varchar(500) not null,
    rv_board_location varchar(100) not null,
    rv_board_picture varchar(300),
    member_profile varchar(500),
    rv_board_comments int,
    rv_board_recommend int,
    rv_board_heart int,
    rv_board_count int,
    constraint rv_board_android_member_id_FK foreign key (member_id) references member (member_id)
);
select * from rv_board_android;

drop table rv_board;

select * from member where member_id = '123' and member_password ='123' and member_email='123@mail.com';

desc rv_board;

create table member_grade(
   member_id varchar(50) not null,
   member_grade varchar(20),
    constraint member_grade_member_id_FK foreign key (member_id) references member (member_id)
);
drop table member_grade;
desc member_grade;


create table receiveMessage(
   -- 삭제(구분) 할 때 필요
    message_id int auto_increment unique,
   member_id varchar(50) not null,
    -- 받는 사람 추가
    member_receiver varchar(100) not null,
    message_title varchar(500),
    message_content varchar(500) not null,
    -- 그림 안보내는 경우도 존재
    message_picture varchar(500),
    -- 회원가입할때 프로필 사진 첨부 안할 경우도 있으므로 not null 제거
    message_profil_pic varchar(500),
    message_send_date datetime,
    constraint message_member_id_FK foreign key (member_id) references member (member_id)    
);

create table sendMessage(
   -- 삭제(구분) 할 때 필요
    message_id int auto_increment unique,
   member_id varchar(50) not null,
    -- 받는 사람 추가
    member_receiver varchar(100) not null,
    message_title varchar(500),
    message_content varchar(500) not null,
    -- 그림 안보내는 경우도 존재
    message_picture varchar(500),
    -- 회원가입할때 프로필 사진 첨부 안할 경우도 있으므로 not null 제거
    message_profil_pic varchar(500),
    message_send_date datetime
);
select * from receiveMessage;
select * from sendMessage;
desc message;
drop table receiveMessage;
drop table sendMessage;


create table reportTypeAll(
rv_board_index int,
rv_board_title varchar(100),
rv_board_content varchar(500),
rv_board_picture varchar(300),

alert_index int,
alert_reason varchar(500),

message_id int,
message_title varchar(500),
message_content varchar(500),
message_picture varchar(500),

report_reason varchar(300) not null,
report_member_id varchar(50) not null,
reporter_member_id varchar(50) not null,
report_date datetime not null
);
select * from reportTypeAll;
drop table reportTypeAll;
 
set sql_safe_updates = 0;

create table alert(
   alert_index int auto_increment primary key,
    rv_board_index int not null,
    member_id varchar(50) not null,
    member_profil_pic varchar(500),
    alert_reason varchar(500) not null,
    alert_date datetime
    
);
drop table alert;

select * from alert;


create table chat(
   member_id varchar(50) not null,
    chat_content varchar(500) not null,
    chat_picture varchar(500) not null,
    chat_profil_pic varchar(500) not null,
    chat_send_date datetime,
    constraint chat_member_id_FK foreign key (member_id) references member (member_id)    
);

create table restriction(
   restriction_word varchar(50) not null
);


drop table location;

create table admin_info(
   member_id varchar(50) not null,
    admin_type int not null,
    constraint admin_member_id_FK foreign key (member_id) references member (member_id)    
);

load data local infile 'C:/Program Files/MySQL/MySQL Workbench 8.0 CE/National_DB.txt' into table location;

show variables like 'local_infile';

set global local_infile = 1;

create table content (
   content_id int auto_increment primary key,
    content_title varchar(50) not null,
    content_content varchar(1000)
);


create view contentView
as select content_title,content_content
from content;

drop table member_relation;

create table follow(
   follower_id varchar(50) not null,
    followed_id varchar(50) not null,
    relationship varchar(20)
);

delete from follow where follower_id = 'naka' and followed_id = 'kima';

drop table follow;
use project;
select * from follow;

insert into follow values('%', '%', 'test');
delete from follow where follower_id = 'lang' and followed_id = 'naka';
set sql_safe_updates = 0;

select * from member;

select * from follow;

show tables;

select count(*) from follow where follower_id = 'naka';

select f1.follower_id, member.member_profile_pic, f1.followed_id, member.member_profile_pic from member
inner join follow f1 on member.member_id = f1.followed_id
inner join follow f2 on member.member_id = f2.follower_id;

create view followPic as select follow.follower_id as follower_id, m1.member_profile_pic as pic1, 
follow.followed_id as followed_id, m2.member_profile_pic as pic2 
from follow inner join member m1 on follow.follower_id = m1.member_id
inner join member m2 on follow.followed_id = m2.member_id;

select * from followPic;

drop view followMemberPic;

select * from followMemberPic;

create table introduction_view(
   member_id varchar(50) primary key,
    introduction varchar(500),
    constraint introduction_member_id_FK foreign key (member_id) references member (member_id)
);

insert into introduction_view (member_id) select member_id from member group by member_id;

select * from introduction_view;

create view introduction as select member.member_id, introduction_view.introduction 
from member inner join introduction_view on member.member_id = introduction_view.member_id;

select * from introduction;

update introduction set introduction = '나는 무엇이냐' where member_id = '1234';

create view memberIntroAll as select m.member_id, m.member_password, m.member_name, m.member_birthday, m.member_gender, m.member_phone, m.member_trip_exp, m.member_register_date, m.member_profile_pic, m.member_email, m.member_Zip_code,m.member_Street_name_address, m.member_Detailed_Address, m.member_type, introduction_view.introduction
from member m inner join  introduction_view on m.member_id = introduction_view.member_id;

use project;
select * from memberIntroAll;

drop view memberIntroAll;
desc memberIntroAll;

select member_id from member;

select * from follow;

select * from follow
where follower_id = '1234';

select * from member
where member_id != 'kima' and member_id like '%a%';


select *
from member m inner join follow f on m.member_id = f.follower_id
where member_id != 'kima';

select *
from member m inner join follow f on m.member_id = f.follower_id
where follower_id = 'kima' and member_id like '%a%';

select m1.member_id, f.followed_id, ifnull(f.follower_id, 'NONE'), if(f.follower_id = 'kima', 1, 0) as isFollowed
from member m1 left outer join follow f on m1.member_id = f.followed_id 
where m1.member_id like '%a%';

select * from follow where follower_id = 'kima';

use project;
 -- 공지사항
create table notice(
   notice_id int auto_increment primary key,
   member_id varchar(50) not null,
    notice_title varchar(500) not null,
    notice_content varchar(5000) not null,
    notice_img varchar(1000),
    notice_date datetime,
    constraint notice_member_id_FK foreign key (member_id) references member (member_id)
);

desc notice;
select * from notice;

drop table notice;
select * from member;
select * from notice;


delete from notice
      where notice_id in (27);

select * from notice
      where notice_id = 11;

insert into notice
      ( notice_title, notice_content,notice_img, notice_date,member_id )
      values ('1234', '5678', 'aa', now(), '1234');

set sql_safe_updates = 0;


   -- 질문게시판
create table question(
   question_id int auto_increment primary key,
   member_id varchar(50) not null,
    question_title varchar(500) not null,
    question_content varchar(5000) not null,
    question_date datetime,
    constraint question_member_id_FK foreign key (member_id) references member (member_id)
);

create table question_reply(
    reply_index int auto_increment unique,
    question_id int not null,
    member_id varchar(50) not null,
    reply_content varchar(100) not null,
    reply_post_date datetime not null,
    reply_modify_date datetime,
    constraint question_reply_member_id_FK foreign key (member_id) references member (member_id),
    constraint question_reply_question_id_FK foreign key (question_id) references question (question_id)
);




insert into question
      ( question_title, question_content, question_date, member_id )
      values ('1234', '5678', now(), '1234');
insert into question
      ( question_title, question_content, question_date, member_id )
      values ('2345', '5678', now(), '1234'); 

select * from question where member_id = '2345';


drop table question;

select * from question;

select count(*) from question;

select * from member;
update member set member_profile_pic ='/JS/android/Member/Profile/profile_images/Kirby.jpg' where member_id = 'Kirby';

select * from location;

drop table location;


SHOW ENGINE innodb STATUS;


-- 지금 임의로 맞춰논상태이고 이걸 쓸려면 province(서울 대전 대구 등등 8도), district(구 단위로 쪼개야함)
-- 회원가입에 주소값을 가져와서 사용할경우 location 안써도됨
create table location(
   location_id int auto_increment  primary key not null,
   province varchar(100) not null,
   district varchar(100) not null
);


-- 추가 해야함
insert into location (province, district) values ('aa','bb');

select * from rv_board;
delete  from rv_board where rv_board_index = 5;

drop table rv_board;
drop table reply;
drop table noti_board;
drop table rv_board_like;
drop table rv_board_view;

select * from rv_board;   
create table rv_board (
    rv_board_index int auto_increment unique,
    member_id varchar(50) not null,
    rv_board_title varchar(500) not null,
    rv_board_content varchar(500) not null,
   rv_board_picture varchar(300),
    rv_board_Street_name_address varchar(500) not null,
    rv_board_post_date datetime,
    rv_board_modify_date datetime,
    constraint rv_board_member_id_FK foreign key (member_id) references member (member_id)
   
);

   insert into 
      rv_Board
      ( member_id, rv_board_title, rv_board_content, rv_board_Street_name_address, rv_board_picture, rv_board_post_date)
      values ('1234', 'as', '1', 'asd', 'asd', now());

 -- 게시물 주소값 검색하기
select * from rv_board
where rv_board_Street_name_address like '%해운대%';


select * from rv_board
      where rv_board_Street_name_address like '%%%'
      order by rv_board_post_date desc;


select * from rv_board
where rv_board_Street_name_address like '%%';


select * from rv_board
where rv_board_Street_name_address like    '%ㄴㄴ%'
order by rv_board_post_date desc;


create table reply (
   rv_board_index int not null,
    reply_index int auto_increment unique,
    member_id varchar(50) not null,
    reply_content varchar(100) not null,
    reply_post_date datetime not null,
    reply_modify_date datetime,
    constraint reply_member_id_FK foreign key (member_id) references member (member_id),
    constraint reply_rv_board_index_FK foreign key (rv_board_index) references rv_board (rv_board_index)
);

create table noti_board(
   member_id varchar(50) not null,
   noti_board_post_date datetime,
    noti_board_modify_date datetime,
    noti_board_title varchar(100) not null,
    noti_board_content varchar(500) not null,
    constraint noti_board_member_id_FK foreign key (member_id) references member (member_id)
);

create table rv_board_like (
   member_id varchar(50) not null,
    rv_board_index int not null,
    constraint rv_board_like_member_id_FK foreign key (member_id) references member (member_id),
    constraint rv_board_like_rv_board_index_FK foreign key (rv_board_index) references rv_board (rv_board_index)
);



select * from  rv_board   where  member_id = '1234' order by  rv_board_index desc;


insert into member values ('a','a','a','a','a','a',1,now(),'a','a','a','a','a',1);
insert into location values ('b','b');
insert into rv_board values (1,now(),now(),'a','c','b','c');
insert into rv_board values (2,now(),now(),'a','c2','b','c2');
insert into rv_board values (3,now(),now(),'a','c3','b','c3');
insert into reply values (1,1,'a','d',now(),null);
insert into noti_board values ('a',now(),now(),'d','d');
insert into rv_board_like values ('a',1);

select * from alert;
Create view rv_board_view
as
select b.rv_board_index, b.member_id as article_writer, b.rv_board_content,
  b.rv_board_post_date, b.rv_board_modify_date, 
  b.location_id, b.rv_board_picture,
  r.member_id as reply_writer, r.reply_content, 
  r.reply_post_date, r.reply_modify_date
   from rv_board b
 join reply r on b.rv_board_index = r.rv_board_index;


select * from member;
select * from member;
select * from rv_board_view;
drop view bast_board;
desc rv_board;

 -- 베스트 게시물(현재는 최근 게시물 탑3)
create view bast_board as select m.member_id, m.member_profile_pic, i.introduction, b.rv_board_index, b.rv_board_picture
from member m inner join introduction_view i on m.member_id = i.member_id inner join  rv_board b on i.member_id = b.member_id;

select * from bast_board order by rv_board_index desc LIMIT 3;