select * from student as s where faculty = 1 order by full_name;

select count(*), faculty from student group by faculty order by count(*);

select distinct state_exam_grade from marks;

select degree, title from teacher where code in (1, 2, 3, 4);

select name from theme where teacher_code between 2 and 3;

select name from theme where name like '%of%';

select * from student where student.theme_id is null;

select state_exam_grade, count(*) as count, sum(state_exam_grade), avg(thesis_grade), max(thesis_grade), min(thesis_grade)
from marks group by state_exam_grade having count between 2 and 3;

select t.degree, t.title, th.name from teacher as t inner join theme th on t.code = th.teacher_code;

select * from teacher t left outer join theme th on t.code = th.teacher_code inner join student s on th.id = s.theme_id
where t.title in ('Professor', 'Docent');

(select full_name, theme_id from student where group_name = 1)
union
(select full_name, theme_id from student where faculty between 1 and 2);

insert into teacher(full_name, degree, title, department, phone_number, email)
values ('Anatoliev Anatoliy Anatolievich', 'Candidate of Science', 'Academician', 'Philosophy', '+79876543210', 'philosopher@proton.mail');

select * from teacher;

update marks set state_exam_grade = 2 where record_book_number = 3;

delete from teacher where full_name = 'Anatoliev Anatoliy Anatolievich';