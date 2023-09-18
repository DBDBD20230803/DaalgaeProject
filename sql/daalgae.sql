USE daalgae;

DROP TABLE IF EXISTS `ATTACHMENT_FILE`;

CREATE TABLE `ATTACHMENT_FILE`
(
    `ATTACH_CODE_SEQ`    INTEGER AUTO_INCREMENT NOT NULL,
    `ATTACH_ORIGIN_NAME`    VARCHAR(100) NOT NULL,
    `ATTACH_DB_NAME`    VARCHAR(30) NOT NULL,
    `POST_CODE`    INTEGER NOT NULL,
    `ATTACH_THUMB_ADDR`    VARCHAR(500) NOT NULL,
    `ATTACH_ORIGIN_ADDR`    VARCHAR(500) NOT NULL,
    PRIMARY KEY ( `ATTACH_CODE_SEQ` )
);

DROP TABLE IF EXISTS `BOOKMARK_INFO`;

CREATE TABLE `BOOKMARK_INFO`
(
    `MEM_BOOKMARK_SEQ`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `MEM_BOOKMARK_SEQ`,`MEM_CODE` )
);


DROP TABLE IF EXISTS `DOG_GUM_PAY`;

CREATE TABLE `DOG_GUM_PAY`
(
    `DOG_GUM_PAY_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    `DOG_GUM_PAYMETHOD`    VARCHAR(50) NOT NULL,
    `DOG_GUM_PAY`    VARCHAR(30) NOT NULL,
    `DOG_GUM_PAY_DATE`    DATE NOT NULL,
    PRIMARY KEY ( `DOG_GUM_PAY_CODE` )
);


DROP TABLE IF EXISTS `DOG_GUM_USE`;

CREATE TABLE `DOG_GUM_USE`
(
    `DOG_GUM_USE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    `DOG_GUM_USE_DATE`    DATE NOT NULL,
    `DOG_GUM_USE_AMOUNT`    INTEGER NOT NULL,
    `WEBTOON_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `DOG_GUM_USE_CODE` )
);


DROP TABLE IF EXISTS `ENCYCLE_INFO`;

CREATE TABLE `ENCYCLE_INFO`
(
    `ENCYCLE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `ENCYCLE_TEXT`    VARCHAR(100) NOT NULL,
    `ENCYCLE_IMAGE`    VARCHAR(100) NOT NULL,
    `ENCYCLE_TITLE`    VARCHAR(100) NOT NULL,
    `ENCYCLE_CONTENT`    VARCHAR(100) NOT NULL,
    PRIMARY KEY ( `ENCYCLE_CODE` )
);


DROP TABLE IF EXISTS `ENCYCLE_SORT`;

CREATE TABLE `ENCYCLE_SORT`
(
    `ENCYCLE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_BOOKMARK_SEQ`    INTEGER NOT NULL ,
    `MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `ENCYCLE_CODE`,`MEM_BOOKMARK_SEQ`,`MEM_CODE` )
);


DROP TABLE IF EXISTS `MEM_INFO`;

CREATE TABLE `MEM_INFO`
(
    `MEM_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_ID`    VARCHAR(15) NOT NULL UNIQUE,
    `MEM_PWD`    VARCHAR(15) NOT NULL,
    `MEM_NAME`    VARCHAR(30) NOT NULL,
    `MEM_BIRTH`    DATE NOT NULL,
    `MEM_EMAIL`    VARCHAR(50) NOT NULL,
    `MEM_ADRS`    VARCHAR(100) NOT NULL,
    `MEM_ADRS_DETAIL`    VARCHAR(100),
    `MEM_WITHDRAWAL`    DATE,
--   탈퇴일자입니다. NULL일 시 탈퇴하지 않음을 나타냅니다.
    `MEM_DOG_GUM`    INTEGER(30) DEFAULT 0 NOT NULL,
    `MEM_ROLE`    VARCHAR(15) DEFAULT 'ROLE_MEMBER' NOT NULL,
--   권한코드입니다. 'ROLE_MEMBER', 'ROLE_ADMIN' 이 있습니다.
    `MEM_STATUS`    VARCHAR(2) DEFAULT 'N' NOT NULL,
--   회원 제제 상태입니다. 제제를 받으면 'Y'가 됩니다.
    `MAIL_AUTH`     INTEGER DEFAULT 0 NOT NULL,
--   이메일 인증시 `MAIL_AUTH` 값이 1이됨
    `MAIL_KEY`      VARCHAR(50),
--   `MAIL_KEY`에는 Tempkey.java에서 생성한 난수를 저장하는데 사용됨
    PRIMARY KEY ( `MEM_CODE` )
);


DROP TABLE IF EXISTS `PET_INFO`;

CREATE TABLE `PET_INFO`
(
    `PET_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `PET_NICK`    VARCHAR(50) NOT NULL,
    `PET_KIND`    VARCHAR(50) NOT NULL,
    `PET_BIRTH`    DATE NOT NULL,
    `PET_GENDER`    CHAR(1) NOT NULL,
    `PET_WEIGHT`    FLOAT NOT NULL,
    `PET_NEUTERED`    CHAR(1) NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    `PET_INTRODUCE`    VARCHAR(1000),
    PRIMARY KEY ( `PET_CODE` )
);


DROP TABLE IF EXISTS `POST_INFO`;

CREATE TABLE `POST_INFO`
(
    `POST_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `POST_SORT`    VARCHAR(30) NOT NULL,
    `POST_TITLE`    VARCHAR(30) NOT NULL,
    `POST_CONTENT`    VARCHAR(5000) NOT NULL,
    `POST_DATE`    DATE NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    `WEBTOON_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `POST_CODE` )
);


DROP TABLE IF EXISTS `POST_REPLY`;

CREATE TABLE `POST_REPLY`
(
    `POST_REPLY_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `POST_REPLY_CON`    VARCHAR(5000) NOT NULL,
    `POST_REPLY_WRITER`    VARCHAR(30) NOT NULL,
    `POST_REPLY_DATE`    DATE NOT NULL,
    `POST_CODE`    INTEGER,
    PRIMARY KEY ( `POST_REPLY_CODE` )
);


DROP TABLE IF EXISTS `POST_SORT`;

CREATE TABLE `POST_SORT`
(
    `POST_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_BOOKMARK_SEQ`    INTEGER NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `POST_CODE`,`MEM_BOOKMARK_SEQ`,`MEM_CODE` )
);


DROP TABLE IF EXISTS `TOUR_INFO`;

CREATE TABLE `TOUR_INFO`
(
    `TOUR_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `TOUR_TITLE`    VARCHAR(100) NOT NULL,
    `TOUR_ADDR`    VARCHAR(300) NOT NULL,
    `TOUR_TEL`    VARCHAR(30) NOT NULL,
    `TOUR_TIME`    VARCHAR(30) NOT NULL,
    `TOUR_PAGE`    VARCHAR(100) NOT NULL,
    `TOUR_INTRO`    VARCHAR(500) NOT NULL,
    `TOUR_COORDINATE`    VARCHAR(30) NOT NULL,
    PRIMARY KEY ( `TOUR_CODE` )
);


DROP TABLE IF EXISTS `TOUR_SORT`;

CREATE TABLE `TOUR_SORT`
(
    `TOUR_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_BOOKMARK_SEQ`    INTEGER NOT NULL,
    `MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `TOUR_CODE`,`MEM_BOOKMARK_SEQ`,`MEM_CODE` )
);


DROP TABLE IF EXISTS `USER_BAN`;

CREATE TABLE `USER_BAN`
(
    `USER_BAN_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `USER_BAN_DATE`    DATE NOT NULL,
    `USER_BAN_DUE`    DATE NOT NULL,
    `USER_BAN_REASON`    VARCHAR(4500) NOT NULL,
    PRIMARY KEY ( `USER_BAN_CODE` )
);


DROP TABLE IF EXISTS `USER_REPORT`;

CREATE TABLE `USER_REPORT`
(
    `REPORT_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `REPORT_DATE`    DATE NOT NULL,
    `MEMBER_ID`    VARCHAR(15) NOT NULL,
    `REPORT_MEM_CODE`    INTEGER NOT NULL,
    `REPORT_DEFAN_CODE`    INTEGER NOT NULL,
    `USER_BAN_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `REPORT_CODE` )
);


DROP TABLE IF EXISTS `WEBTOON_INFO`;

CREATE TABLE `WEBTOON_INFO`
(
    `WEBTOON_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `TOON_TITLE_ORIGIN`    VARCHAR(500) NOT NULL,
    `TOON_TITLE_DB`    VARCHAR(500) NOT NULL,
    `TOON_CONTENT_ORIGIN`    VARCHAR(500) NOT NULL,
    `TOON_CONTENT_DB`    VARCHAR(500) NOT NULL,
    `TOON_CONTENT_DIR`    VARCHAR(500) NOT NULL,
    `TOON_TITLE_DIR`    VARCHAR(500) NOT NULL,
    PRIMARY KEY ( `WEBTOON_CODE` )
);

# ALTER를 이용하여 TABLE을 만든 후에 FOREIGN KEY를 부여했습니다.
ALTER TABLE ATTACHMENT_FILE ADD FOREIGN KEY (POST_CODE) REFERENCES POST_INFO(POST_CODE);
ALTER TABLE BOOKMARK_INFO ADD FOREIGN KEY (MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE DOG_GUM_PAY ADD FOREIGN KEY (MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE DOG_GUM_USE ADD FOREIGN KEY (MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE ENCYCLE_SORT ADD FOREIGN KEY (MEM_CODE,MEM_BOOKMARK_SEQ) REFERENCES BOOKMARK_INFO(MEM_CODE,MEM_BOOKMARK_SEQ);
ALTER TABLE ENCYCLE_SORT ADD FOREIGN KEY (ENCYCLE_CODE) REFERENCES ENCYCLE_INFO(ENCYCLE_CODE);
ALTER TABLE PET_INFO ADD FOREIGN KEY (MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE POST_INFO ADD FOREIGN KEY (MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE POST_INFO ADD FOREIGN KEY (WEBTOON_CODE) REFERENCES WEBTOON_INFO(WEBTOON_CODE);
ALTER TABLE POST_REPLY ADD FOREIGN KEY (POST_CODE) REFERENCES POST_INFO(POST_CODE);
ALTER TABLE POST_SORT ADD FOREIGN KEY (`MEM_BOOKMARK_SEQ`,`MEM_CODE` ) REFERENCES BOOKMARK_INFO(MEM_BOOKMARK_SEQ, MEM_CODE);
ALTER TABLE POST_SORT ADD FOREIGN KEY (POST_CODE) REFERENCES POST_INFO(POST_CODE);
ALTER TABLE TOUR_SORT ADD FOREIGN KEY (`MEM_BOOKMARK_SEQ`,`MEM_CODE` ) REFERENCES BOOKMARK_INFO(MEM_BOOKMARK_SEQ, MEM_CODE);
ALTER TABLE TOUR_SORT ADD FOREIGN KEY (TOUR_CODE) REFERENCES TOUR_INFO(TOUR_CODE);
ALTER TABLE USER_REPORT ADD FOREIGN KEY (REPORT_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE USER_REPORT ADD FOREIGN KEY (REPORT_DEFAN_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE USER_REPORT ADD FOREIGN KEY (USER_BAN_CODE) REFERENCES USER_BAN(USER_BAN_CODE);

# 임시 admin계정과 user01 정보 insert문입니다.
INSERT INTO MEM_INFO (MEM_ID, MEM_PWD, MEM_NAME, MEM_BIRTH, MEM_EMAIL, MEM_ADRS, MEM_ADRS_DETAIL, MEM_ROLE, MAIL_AUTH)
VALUES ('admin', 'admin', 'admin', 20230918, 'daalgae@doctype.com', '서울', 'HI10class', 'ROLE_ADMIN', 1);
INSERT INTO MEM_INFO (MEM_ID, MEM_PWD, MEM_NAME, MEM_BIRTH, MEM_EMAIL, MEM_ADRS, MEM_ADRS_DETAIL, MEM_ROLE, MAIL_AUTH)
VALUES ('user01', 'user01', 'user01', 20230918, 'daalgae@doctype.com', '서울', 'HI10class', 'ROLE_USER', 1);

