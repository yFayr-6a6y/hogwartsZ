-- 1. Студенты возраста от 10 до 20 лет
SELECT * FROM student WHERE age BETWEEN 10 AND 20;

-- 2. Только имена студентов
SELECT name FROM student;

-- 3. Студенты, у которых в имени есть буква "о" (без учёта регистра)
SELECT * FROM student WHERE LOWER(name) LIKE '%о%';

-- 4. Студенты, у которых возраст меньше id
SELECT * FROM student WHERE age < id;

-- 5. Все студенты, отсортированные по возрасту
SELECT * FROM student ORDER BY age ASC;