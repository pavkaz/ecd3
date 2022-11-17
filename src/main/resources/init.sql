create database if not exists graduate_work;
use graduate_work;

create table if not exists teacher(
                                      code int not null unique auto_increment,
                                      full_name varchar(100) not null,
                                      degree varchar(100) not null,
                                      title varchar(100) not null,
                                      department varchar(100) not null,
                                      phone_number varchar(20) not null unique,
                                      email varchar(100) not null unique
);
create table if not exists theme(
                                    id int not null unique auto_increment,
                                    teacher_code int not null,
                                    name varchar(255) not null unique,
                                    foreign key (teacher_code) references teacher (code)
);
create table if not exists student(
                                      record_book_number int not null unique auto_increment,
                                      full_name varchar(100) not null,
                                      faculty varchar(100) not null,
                                      group_name varchar(100) not null,
                                      theme_id int not null unique,
                                      foreign key (theme_id) references theme (id)
);
create table if not exists marks(
                                    record_book_number int not null unique,
                                    state_exam_grade tinyint not null,
                                    thesis_grade tinyint not null,
                                    foreign key (record_book_number) references student (record_book_number)
);

insert into teacher(full_name, degree, title, department, phone_number, email) values('Grigoriev Grigoriy Grigorievich', 'Doctor of Science', 'Professor', 'Software systems', '+79001234567', 'professor@mail.ru');
insert into teacher(full_name, degree, title, department, phone_number, email) values('Dmitriev Dmitriy Dmitrievich', 'Candidate of Science', 'Docent', 'Software systems', '+78005553535', 'docent@gmail.com');
insert into teacher(full_name, degree, title, department, phone_number, email) values('Vasiliev Vasiliy Vasilievich', 'Candidate of Science', 'Academician', 'Biology', '+72286661337', 'academician@yandex.ru');

insert into theme(teacher_code, name) values(1, 'Service for optimization models based on recurrent algorithms');
insert into theme(teacher_code, name) values(1, 'Development of a program for automating accounting and pre-orders at enterprises in various fields of activity');
insert into theme(teacher_code, name) values(1, 'Matrix correction of improper linear programming problems');
insert into theme(teacher_code, name) values(1, 'Adequacy and objectivity of modeling information management system');
insert into theme(teacher_code, name) values(1, 'Characteristic features of modeling information transmission systems of particular importance');
insert into theme(teacher_code, name) values(2, 'Creation of a file manager by means of a programming environment');
insert into theme(teacher_code, name) values(2, 'Email billing system in Linux OS, based on Qmail mail service logs');
insert into theme(teacher_code, name) values(2, 'Characteristic features of the functioning of cluster systems');

insert into student(full_name, faculty, group_name, theme_id) values('Ivanov Ivan Ivanovich', '1', '1', 1);
insert into student(full_name, faculty, group_name, theme_id) values('Petrov Petr Petrovich', '1', '1', 2);
insert into student(full_name, faculty, group_name, theme_id) values('Sidorov Sidor Sidorovich', '1', '2', 3);
insert into student(full_name, faculty, group_name, theme_id) values('Nikolaev Nikolay Nikolaevich', '2', '3', 5);
insert into student(full_name, faculty, group_name, theme_id) values('Valeriev Valeriy Valerievich', '2', '4', 6);
insert into student(full_name, faculty, group_name, theme_id) values('Konstantiov Konstantin Konstantinovich', '3', '5', 7);

insert into marks(record_book_number, state_exam_grade, thesis_grade) values(1, 5, 5);
insert into marks(record_book_number, state_exam_grade, thesis_grade) values(2, 4, 5);
insert into marks(record_book_number, state_exam_grade, thesis_grade) values(3, 4, 3);
insert into marks(record_book_number, state_exam_grade, thesis_grade) values(4, 4, 4);
insert into marks(record_book_number, state_exam_grade, thesis_grade) values(5, 5, 4);
insert into marks(record_book_number, state_exam_grade, thesis_grade) values(6, 3, 2);