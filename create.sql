CREATE TABLE courses(
	course_code TEXT NOT NULL,
    AM TEXT NOT NULL,
	department TEXT NOT NULL,
	semester TEXT NOT NULL,
	primary key ( course_code )
);

insert into courses(course_code,AM,department,semester) values ('computermaths','c37bf859faf392800d739a41fe5af151','informatics','1');
insert into courses(course_code,AM,department,semester) values ('programming','c37bf859faf392800d739a41fe5af151','informatics','1');
insert into courses(course_code,AM,department,semester) values ('datastructures','2ad55c21c5505af3752b1412e7f2c882','informatics','2');
insert into courses(course_code,AM,department,semester) values ('algebra','2ad55c21c5505af3752b1412e7f2c882','informatics','2');
insert into courses(course_code,AM,department,semester) values ('operatingsystems','4eb269bdf2d3fb361eb837c219d26eac','informatics','3');
insert into courses(course_code,AM,department,semester) values ('compilers','4eb269bdf2d3fb361eb837c219d26eac','informatics','3');
insert into courses(course_code,AM,department,semester) values ('algorithms','none','informatics','4');
insert into courses(course_code,AM,department,semester) values ('networks','none','informatics','4');
insert into courses(course_code,AM,department,semester) values ('cryptography','none','informatics','5');
insert into courses(course_code,AM,department,semester) values ('databases','none','informatics','5');
insert into courses(course_code,AM,department,semester) values ('logistics','none','economics','1');
insert into courses(course_code,AM,department,semester) values ('politicaleconomy','none','economics','2');
insert into courses(course_code,AM,department,semester) values ('statistics','none','economics','3');
insert into courses(course_code,AM,department,semester) values ('macroeconomics','none','economics','4');
insert into courses(course_code,AM,department,semester) values ('oceanography','none','shipping','1');
insert into courses(course_code,AM,department,semester) values ('shiptechnology','none','shipping','2');


CREATE TABLE grades(
	uname TEXT NOT NULL,
	AM TEXT NOT NULL,
    department TEXT NOT NULL,
	course_code TEXT NOT NULL,
	grade TEXT NOT NULL
);

insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','computermaths','10');
insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','programming','10');
insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','datastructures','5');
insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','algebra','10');
insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','operatingsystems','none');
insert into grades(uname,AM,department,course_code,grade) values ('pit','f190ce9ac8445d249747cab7be43f7d5','informatics','compilers','7');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','computermaths','none');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','programming','none');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','datastructures','8');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','algebra','9');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','operatingsystems','none');
insert into grades(uname,AM,department,course_code,grade) values ('nik','be041b21f66931f5a1d24e1e19a78539','informatics','compilers','none');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','computermaths','10');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','programming','none');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','datastructures','none');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','algebra','none');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','operatingsystems','none');
insert into grades(uname,AM,department,course_code,grade) values ('anma','1c104b9c0accfca52ef21728eaf01453','informatics','compilers','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','computermaths','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','programming','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','datastructures','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','algebra','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','operatingsystems','none');
insert into grades(uname,AM,department,course_code,grade) values ('dio','d10906c3dac1172d4f60bd41f224ae75','informatics','compilers','none');


CREATE TABLE professors(
	uname TEXT NOT NULL,
	fname TEXT NOT NULL,
	sname TEXT NOT NULL,
	AM TEXT NOT NULL,
	primary key ( AM )
);

insert into professors(uname,fname,sname,AM) values ('mikeb','Mike','Black','c37bf859faf392800d739a41fe5af151');-- AM is 98765
insert into professors(uname,fname,sname,AM) values ('jj','James','Johnson','2ad55c21c5505af3752b1412e7f2c882');-- AM is 98766
insert into professors(uname,fname,sname,AM) values ('gg','George','Gordon','4eb269bdf2d3fb361eb837c219d26eac');-- AM is 98767

CREATE TABLE secretaries(
	uname TEXT NOT NULL,
	fname TEXT NOT NULL,
	sname TEXT NOT NULL,
	AM TEXT NOT NULL,
	primary key ( AM ) 
);

insert into secretaries(uname,fname,sname,AM) values ('admin','Adam','White','d3eb9a9233e52948740d7eb8c3062d14');-- AM is 99999

CREATE TABLE students(
	uname TEXT NOT NULL,
	fname TEXT NOT NULL,
	sname TEXT NOT NULL,
	department TEXT NOT NULL,
	AM TEXT NOT NULL,
	primary key ( AM ) 
);

insert into students(uname,fname,sname,department,AM) values ('pit','Panagiotis','Prattis','informatics','f190ce9ac8445d249747cab7be43f7d5');-- AM is 12341
insert into students(uname,fname,sname,department,AM) values ('nik','Nikos','Kontopoulos','informatics','be041b21f66931f5a1d24e1e19a78539');-- AM is 12342
insert into students(uname,fname,sname,department,AM) values ('anma','Annamaria','Tzimourta','informatics','1c104b9c0accfca52ef21728eaf01453');-- AM is 12343
insert into students(uname,fname,sname,department,AM) values ('dio','Dionusis','Tsirigotis','informatics','d10906c3dac1172d4f60bd41f224ae75');-- AM is 12344
insert into students(uname,fname,sname,department,AM) values ('kris','Chris','Adams','shipping','68d5b0923a8bb1387adfb044e105d512');-- AM is 23451
insert into students(uname,fname,sname,department,AM) values ('kost','Kostas','Brown','economics','ca35d8d4087914717010fdb91b14ac22');-- AM is 34561


/*CREATE TABLE usercounter(
	counter INTEGER NOT NULL
);

insert into usercounter(counter) values (12);*/