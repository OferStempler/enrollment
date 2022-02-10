DROP TABLE IF EXISTS GRADE;

DROP TABLE IF EXISTS pupil_friends;
DROP TABLE IF EXISTS pupil;
DROP TABLE IF EXISTS school;


create table grade (id bigint generated by default as identity, course varchar(255), grade integer, pupil_id bigint, primary key (id));
create table pupil (id bigint generated by default as identity, lat double, lon double, school_id bigint, primary key (id));
create table pupil_friends (pupil_id bigint not null, pupil_friend_id bigint not null, primary key (pupil_id, pupil_friend_id));
create table school (school_id bigint generated by default as identity, lat double, lon double, max_number_of_pupils integer, minimum_gpa double, primary key (school_id));
alter table grade add constraint FKs3qqrrt8pxrexsxy3kbytg8k2 foreign key (pupil_id) references pupil;
alter table pupil add constraint FKt8jbuju8s1xselh88xr3dkft8 foreign key (school_id) references school;
alter table pupil_friends add constraint FKkr6sv4soywga7f8qdkwo76ui4 foreign key (pupil_friend_id) references pupil;
alter table pupil_friends add constraint FKeefaa0fn37qk2lww139sobu1n foreign key (pupil_id) references pupil;


INSERT INTO SCHOOL (lat, lon, max_number_of_pupils, minimum_gpa) VALUES
( -63.03238901390978, 14.913188059745586, 5, 90),
( -62.03238901390978, 19.913188059745586, 5, 86),
( -67.03238901390978, 18.913188059745586, 5, 40);

INSERT INTO pupil (lat, lon, school_id) VALUES
( -79.03238901390978, 38.913188059745586, 1),
( -73.03238901390978, 36.913188059745586, 1),
( -71.03238901390978, 35.913188059745586, 1),
( -77.03238901390978, 34.913188059745586, 2),
( -74.03238901390978, 31.913188059745586, 2);

INSERT INTO pupil_friends (pupil_id, pupil_friend_id) VALUES
(1,2),
(1,3),
(1,4);

INSERT INTO grade (course, grade, pupil_id) VALUES
('History', 95, 1),
('Math', 95, 1),
('Science', 95, 1),
('History', 95, 2),
('Math', 95, 2);

