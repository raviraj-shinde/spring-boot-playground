use quizdb;

INSERT INTO tbl_questions 
(q_text, category, level, marks, optiona, optionb, optionc, optiond, correct_answer) 
VALUES
-- Java Questions
('Which keyword is used to inherit a class in Java?', 'Java', 'easy', 5, 'this', 'super', 'extends', 'implements', 'extends'),
('Which of these is not a Java feature?', 'Java', 'medium', 10, 'Object-Oriented', 'Platform Independent', 'Pointers', 'Multithreading', 'Pointers'),
('What is the default value of an int variable in Java?', 'Java', 'easy', 5, '0', 'null', 'undefined', '1', '0'),
('Which collection class allows you to store unique elements in Java?', 'Java', 'medium', 10, 'ArrayList', 'HashMap', 'HashSet', 'LinkedList', 'HashSet'),
('Which annotation is used to mark a Spring Boot main class?', 'Java', 'medium', 10, '@RestController', '@EnableAutoConfiguration', '@SpringBootApplication', '@Component', '@SpringBootApplication'),

-- Python Questions
('Which keyword is used to define a function in Python?', 'Python', 'easy', 5, 'func', 'function', 'def', 'lambda', 'def'),
('What is the output of len("Hello World") in Python?', 'Python', 'easy', 5, '10', '11', '12', 'None', '11'),
('Which of these is a Python immutable data type?', 'Python', 'medium', 10, 'List', 'Dictionary', 'Tuple', 'Set', 'Tuple'),
('What is the correct file extension for Python files?', 'Python', 'easy', 5, '.java', '.py', '.python', '.pyt', '.py'),
('Which library is used for data analysis in Python?', 'Python', 'medium', 10, 'numpy', 'pandas', 'matplotlib', 'scikit-learn', 'pandas');


drop table tbl_questions;
drop table tbl_quiz;
drop table tbl_quiz_questions;

select * from tbl_questions;
select * from  tbl_quiz;
select * from tbl_quiz_questions;