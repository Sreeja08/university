create table if not exists professor(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    department TEXT
);

create table if not exists course(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    credits INT,
    professorId INT,
    FOREIGN KEY(professorId) REFERENCES professor(id)
);

create table if not exists student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    email TEXT
);

create table if not exists course_student(
    courseId INT,
    studentId INT,
    PRIMARY KEY(courseId,studentId),
    FOREIGN KEY(courseId) REFERENCES course(id),
    FOREIGN KEY(studentId) REFERENCES student(id)
);