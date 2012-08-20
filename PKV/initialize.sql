CREATE TABLE USERS(
        ID integer primary key AUTO_INCREMENT,
        LOGIN VARCHAR(255),
        PASSWORD VARCHAR(255),
        LASTNAME VARCHAR(255),
        FIRSTNAME VARCHAR(255)
);

CREATE TABLE TOPIC_BASED_EXAMS(
        ID integer primary key AUTO_INCREMENT,
        NAME VARCHAR(255),
        META_INFO VARCHAR(100),
        YEAR INTEGER,
        MONTH INTEGER,
        CREATED_DATE DATE
);

CREATE TABLE TBE_TOPICS(
	ID integer primary key AUTO_INCREMENT,
	NAME VARCHAR(255),
        TOPIC_BASED_EXAM_ID integer references TOPIC_BASED_EXAMS(ID)
);


CREATE TABLE TBE_QUESTIONS
(
	ID integer primary key AUTO_INCREMENT,
	Name varchar(5000),
	POINT INTEGER,
	CreatedYear integer,
	CreatedDate Date,
	TBE_TOPIC_ID integer references TBE_TOPICS(ID)
);
