USE daalgae;

DROP TABLE IF EXISTS `ATTACHMENT_FILE`;

CREATE TABLE `ATTACHMENT_FILE`
(
    `ATTACH_CODE_SEQ`    INTEGER AUTO_INCREMENT NOT NULL,
    `ATTACH_ORIGIN_NAME`    VARCHAR(100) NOT NULL,
    `ATTACH_DB_NAME`    VARCHAR(30) NOT NULL,
    `ATTACH_THUMB_ADDR`    VARCHAR(500) NOT NULL,
    `ATTACH_ORIGIN_ADDR`    VARCHAR(500) NOT NULL,
    `REF_POST_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `ATTACH_CODE_SEQ` )
);

DROP TABLE IF EXISTS `BOOKMARK_INFO`;

CREATE TABLE `BOOKMARK_INFO`
(
    `MEM_BOOKMARK_SEQ`    INTEGER AUTO_INCREMENT NOT NULL,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` )
);


DROP TABLE IF EXISTS `DOG_GUM_PAY`;

CREATE TABLE `DOG_GUM_PAY`
(
    `DOG_GUM_PAY_TID`    VARCHAR(255) NOT NULL,
    `DOG_GUM_PAY_METHOD`    VARCHAR(50) NOT NULL,
    `DOG_GUM_PAY`    VARCHAR(30) NOT NULL,
    `DOG_GUM_PAY_DATE`    DATE NOT NULL,
    `DOG_ITEM_NAME`     VARCHAR(200) NOT NULL,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `DOG_GUM_PAY_TID` )
);


DROP TABLE IF EXISTS `DOG_GUM_USE`;

CREATE TABLE `DOG_GUM_USE`
(
    `DOG_GUM_USE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `DOG_GUM_USE_DATE`    DATE NOT NULL,
    `DOG_GUM_USE_AMOUNT`    INTEGER NOT NULL,
    `REF_POST_CODE`    INTEGER NOT NULL,
    `REF_MEM_CODE`    INTEGER,
    PRIMARY KEY ( `DOG_GUM_USE_CODE` )
);


DROP TABLE IF EXISTS `ENCYCLE_INFO`;

CREATE TABLE `ENCYCLE_INFO`
(
    `ENCYCLE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `ENCYCLE_ORDER`    VARCHAR(100) NOT NULL,
    `ENCYCLE_IMAGE`    VARCHAR(100) NOT NULL,
    `ENCYCLE_TITLE`    VARCHAR(100) NOT NULL,
    `ENCYCLE_CONTENT`    VARCHAR(3000) NOT NULL,
    PRIMARY KEY ( `ENCYCLE_CODE` )
);


DROP TABLE IF EXISTS `MEM_INFO`;


CREATE TABLE `MEM_INFO`
(
    `MEM_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `MEM_ID`    VARCHAR(15) NOT NULL UNIQUE,
    `MEM_PWD`    VARCHAR(500) NOT NULL,
    `MEM_NAME`    VARCHAR(30) NOT NULL,
    `MEM_BIRTH`    DATE NOT NULL,
    `MEM_EMAIL`    VARCHAR(50) NOT NULL,
    `MEM_ADRS`    VARCHAR(100) NOT NULL,
    `MEM_ADRS_DETAIL`    VARCHAR(100),
    `MEM_WITHDRAWAL`    DATE,
--   탈퇴일자입니다. NULL일 시 탈퇴하지 않음을 나타냅니다.
    `MEM_DOG_GUM` INTEGER NOT NULL DEFAULT 0,
    `MEM_ROLE`    VARCHAR(15) DEFAULT 'ROLE_USER' NOT NULL,
--   권한코드입니다. 'ROLE_MEMBER', 'ROLE_ADMIN' 이 있습니다.
    `MAIL_AUTH`     INTEGER DEFAULT 0 NOT NULL,
--   이메일 인증시 `MAIL_AUTH` 값이 1이됨
    `MAIL_KEY`      VARCHAR(50),
--   `MAIL_KEY`에는 Tempkey.java에서 생성한 난수를 저장하는데 사용됨
    `BAN_PERIOD`    DATE,
    PRIMARY KEY ( `MEM_CODE` )
);

DROP TABLE IF EXISTS `POST_INFO`;

CREATE TABLE `POST_INFO`
(
    `POST_CODE` INTEGER AUTO_INCREMENT NOT NULL,
    `POST_TYPE` VARCHAR(10) NOT NULL CHECK(POST_TYPE IN ('공지', '자유', '자랑', '웹툰')),
    `POST_SORT` VARCHAR(30) NOT NULL CHECK(POST_SORT IN ('공지', '이벤트', '자유', '먹거리', '용품', '정보' ,'자랑', '웹툰')),
    `POST_TITLE` VARCHAR(30) NOT NULL,
#     POST_TITLE VARCHAR 100으로 늘리기
    `POST_CONTENT` VARCHAR(5000) NOT NULL,
    `POST_DATE` DATE NOT NULL,
    `POST_COUNT` INTEGER DEFAULT 0,
    `REF_POST_WRITER` INTEGER NOT NULL,
    PRIMARY KEY (`POST_CODE`)
);

DROP TABLE IF EXISTS `PET_INFO`;

CREATE TABLE `PET_INFO`
(
    `PET_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `PET_NICK`    VARCHAR(50) NOT NULL,
    `PET_KIND`    VARCHAR(50) NOT NULL,
    `PET_BIRTH`    DATE NOT NULL,
    `PET_GENDER`    VARCHAR(50) NOT NULL,
    `PET_WEIGHT`    FLOAT NOT NULL,
    `PET_NEUTERED`    CHAR(1) NOT NULL,
    `PET_INTRODUCE`    VARCHAR(1000),
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `PET_CODE` )
);

DROP TABLE IF EXISTS `POST_REPLY`;

CREATE TABLE `POST_REPLY`
(
    `POST_REPLY_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `POST_REPLY_CONTENT`    VARCHAR(5000) NOT NULL,
    `POST_REPLY_DATE`    DATE NOT NULL,
    `REF_POST_CODE`    INTEGER NOT NULL,
    `REF_POST_REPLY_WRITER`    INTEGER NOT NULL,
    PRIMARY KEY ( `POST_REPLY_CODE` )
);

DROP TABLE IF EXISTS `TOUR_INFO`;


CREATE TABLE `TOUR_INFO`
(
    `TOUR_CODE` INTEGER AUTO_INCREMENT NOT NULL,
    `TOUR_PHOTO` VARCHAR(300) NOT NULL,
    `TOUR_TITLE` VARCHAR(100) NOT NULL,
    `ADDR` VARCHAR(300) NOT NULL,
    `TEL` VARCHAR(30) NOT NULL,
    `MAPX` VARCHAR(30) NOT NULL,
    `MAPY` VARCHAR(30) NOT NULL,
    `TOUR_CATEGORY` VARCHAR(30) NOT NULL,
    `TIME` VARCHAR(50) NOT NULL,
    `PAGE` VARCHAR(500) NOT NULL,
    `FACILITIES` VARCHAR(300) NOT NULL,
    `SUPPLIES` VARCHAR(300) NOT NULL,
    `POLICY` VARCHAR(3000) NOT NULL,
    `NOTICE` VARCHAR(3000) NOT NULL,
    `INTRO` VARCHAR(7000) NOT NULL,
    PRIMARY KEY ( `TOUR_CODE` )
);

DROP TABLE IF EXISTS `ENCYCLE_SORT`;

CREATE TABLE `ENCYCLE_SORT`
(
    `REF_ENCYCLE_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `REF_MEM_BOOKMARK_SEQ`    INTEGER NOT NULL ,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `REF_ENCYCLE_CODE`,`REF_MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` )
);


DROP TABLE IF EXISTS `POST_SORT`;

CREATE TABLE `POST_SORT`
(
    `REF_POST_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `REF_MEM_BOOKMARK_SEQ`    INTEGER NOT NULL,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `REF_POST_CODE`,`REF_MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` )
);


DROP TABLE IF EXISTS `TOUR_SORT`;

CREATE TABLE `TOUR_SORT`
(
    `REF_TOUR_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `REF_MEM_BOOKMARK_SEQ`    INTEGER NOT NULL,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `REF_TOUR_CODE`,`REF_MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` )
);

DROP TABLE IF EXISTS `USER_REPORT`;

CREATE TABLE `USER_REPORT`
(
    `REPORT_CODE`    INTEGER AUTO_INCREMENT NOT NULL,
    `REPORT_DATE`    DATE NOT NULL,
    `REF_REPORT_MEM_CODE`    INTEGER NOT NULL,
    `REF_REPORT_DEFAN_CODE`    INTEGER NOT NULL,
    `REPORT_REASON`     VARCHAR(200) NOT NULL,
    PRIMARY KEY ( `REPORT_CODE` )
);

DROP TABLE IF EXISTS 'USER_PROFILE';

CREATE TABLE 'USER_PROFILE'(
    `PROFILE_CODE` INTEGER AUTO_INCREMENT NOT NULL UNIQUE ,
    `PROFILE_ORIGIN_NAME`    VARCHAR(100) NOT NULL,
    `PROFILE_DB_NAME`    VARCHAR(100) NOT NULL,
    `PROFILE_THUMB_ADDR`    VARCHAR(500) NOT NULL,
    `PROFILE_ORIGIN_ADDR`    VARCHAR(500) NOT NULL,
    `REF_MEM_CODE`    INTEGER NOT NULL,
    PRIMARY KEY ( `PROFILE_CODE` )
);
ALTER TABLE USER_PROFILE ADD FOREIGN KEY (REF_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);

# ALTER를 이용하여 TABLE을 만든 후에 FOREIGN KEY를 부여했습니다.

ALTER TABLE ATTACHMENT_FILE ADD FOREIGN KEY (REF_POST_CODE) REFERENCES POST_INFO(POST_CODE) ON DELETE CASCADE;

ALTER TABLE BOOKMARK_INFO ADD FOREIGN KEY (REF_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE DOG_GUM_PAY ADD FOREIGN KEY (REF_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE DOG_GUM_USE ADD FOREIGN KEY (REF_POST_CODE) REFERENCES POST_INFO(POST_CODE);
ALTER TABLE DOG_GUM_USE ADD FOREIGN KEY (REF_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE POST_INFO ADD FOREIGN KEY (REF_POST_WRITER) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE PET_INFO ADD FOREIGN KEY (REF_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE POST_REPLY ADD FOREIGN KEY (REF_POST_CODE) REFERENCES POST_INFO(POST_CODE) ON DELETE CASCADE;
ALTER TABLE POST_REPLY ADD FOREIGN KEY (REF_POST_REPLY_WRITER) REFERENCES MEM_INFO(MEM_CODE);

ALTER TABLE ENCYCLE_SORT ADD FOREIGN KEY (REF_MEM_BOOKMARK_SEQ, REF_MEM_CODE) REFERENCES BOOKMARK_INFO(MEM_BOOKMARK_SEQ, REF_MEM_CODE);
ALTER TABLE ENCYCLE_SORT ADD FOREIGN KEY (REF_ENCYCLE_CODE) REFERENCES ENCYCLE_INFO(ENCYCLE_CODE);

ALTER TABLE POST_SORT ADD FOREIGN KEY (`REF_MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` ) REFERENCES BOOKMARK_INFO(MEM_BOOKMARK_SEQ, REF_MEM_CODE);
ALTER TABLE POST_SORT ADD FOREIGN KEY (REF_POST_CODE) REFERENCES POST_INFO(POST_CODE);

ALTER TABLE TOUR_SORT ADD FOREIGN KEY (`REF_MEM_BOOKMARK_SEQ`,`REF_MEM_CODE` ) REFERENCES BOOKMARK_INFO(MEM_BOOKMARK_SEQ, REF_MEM_CODE);
ALTER TABLE TOUR_SORT ADD FOREIGN KEY (REF_TOUR_CODE) REFERENCES TOUR_INFO(TOUR_CODE);

ALTER TABLE USER_REPORT ADD FOREIGN KEY (REF_REPORT_MEM_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE USER_REPORT ADD FOREIGN KEY (REF_REPORT_DEFAN_CODE) REFERENCES MEM_INFO(MEM_CODE);
ALTER TABLE USER_REPORT ADD FOREIGN KEY (REF_USER_BAN_CODE) REFERENCES USER_BAN(USER_BAN_CODE);

/* 월요일에 반영 해야할 사항들 */
ALTER TABLE POST_INFO MODIFY POST_DATE DATE DEFAULT (CURRENT_DATE);
ALTER TABLE POST_REPLY MODIFY POST_REPLY_DATE DATE DEFAULT (CURRENT_DATE);
ALTER TABLE USER_REPORT MODIFY REPORT_DATE DATE DEFAULT (CURRENT_DATE);
ALTER TABLE ATTACHMENT_FILE MODIFY ATTACH_DB_NAME VARCHAR(100) NOT NULL;
ALTER TABLE POST_INFO MODIFY POST_SORT VARCHAR(30) NOT NULL CHECK(POST_SORT IN ('공지', '이벤트', '자유', '먹거리', '용품', '정보', '웹툰', '자랑'));
ALTER TABLE MEM_INFO ADD COLUMN BAN_PERIOD DATE;


INSERT INTO daalgae.encycle_info (ENCYCLE_CODE, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT, ENCYCLE_ORDER) VALUES (1, '/images/knowAfterAdopt2.png', '작고 약한 이빨', '<br>
- 성견이 되었어도, 강아지 생후 2세까지는 강아지 사춘기(개춘기)라고 불리는 시기입니다. 이 시기에는 강아지가 에너지가 넘쳐서 각종 사고를 칠 수 있어요.<br><br>

- 따라서, 강아지가 에너지를 모두 발산할 수 있도록 산책과 놀이를 충분히 시켜주는 게 매우 중요합니다. 또한, 앉아, 기다려, 켄넬 훈련 같은 기본 훈련을 시키는 게 좋은데요.<br>
이런 훈련들은 강아지의 흥분, 습관성 짖음 그리고 공격성 같은 행동을 고치는 데 도움이 됩니다.<br><br>

- 사춘기에 물건을 마구 물어뜯는 강아지도 많아요. 이 시기에도 이갈이 시기와 마찬가지로 씹기 좋은 장난감을 주는 게 좋습니다.<br>
단, 영구치가 난 상태이기 때문에 조금 더 튼튼한 장난감을 주는 게 좋습니다.<br><br>

- 사춘기 강아지는 에너지가 넘치기 때문에 활동량을 늘려줘야 하는데요. 하지만, 아직 골격이 완벽한 상태는 아니라 너무 강도 높은 격한 운동을 할 경우 관절에 무리가 갈 수 있습니다.<br>
따라서, 노즈워크 같은 머리를 써야 하는 놀이를 해주면 좋습니다.<br><br><br>', '1-1');

INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('1-1-1', 'none', '꼭꼭! 체크리스트', '<br>
[거주 형태]<br>
- 혼자 살 경우<br>
강아지와 실질적으로 함께할 수 있는 시간은 어느 정도인가요?<br>
정기적인 산책이나 놀이를 함께할 수 있나요?<br>
결혼을 앞둔 경우, 배우자가 강아지 털에 민감 하지는 않나요? 상대방의 집에서 반려견을 반대하지는 않나요?<br><br>

- 가족과 살 경우<br>
반려인 외에 강아지를 돌볼 가족 구성원이 있나요?<br>
가족들이 강아지를 키우고 훈련하는 데 동참 해 줄 수 있나요?<br>
다른 식구에게 안일하게 책임을 전가할 여지를 남겨두지는 않았나요?<br><br>

[경제적 여유]<br>
1년간 강아지에게 드는 비용이 어느 정도인지, 자신의 수입과 비교하여 생각해 보았나요?<br>
강아지의 건강을 위한 지출에 대비할 수 있나요?<br>
반려견의 마지막 순간까지 지켜봐 줄 수 있나요?<br><br>

[알레르기 체크]<br>
가족들에게 개와 고양이로 인한 알레르기 반응은 없나요?<br><br>

💬반려견을 위한 ‘여유자금 모으기’ 꿀팁!<br>
- 생식과 간식<br>
사료 대신 직접 생식을 시도하는 방법도 있다. 단, 수의사에게 식단과 알맞은 급여량에 대해 먼저 의논해야 영양 불균형을 피할 수 있다.<br>
생식이 부담스럽다면 최소한의 간식이라도 직접 제조해 먹여보자. 저렴하면서도 몸에 좋은 간식으로는 당근, 오이 등 채소류가 있다.<br><br>

- 물품아끼기<br>
강아지 물품을 살 때 첫 번째 기준은 내구성이다. 강아지 목줄을 구입할 때는 잔고장이 없고 적어도 몇 년은 쓸 수 있는 것을 골라야 한다.<br>
장난감의 경우 서너 개의 여분을 준비해 놓자. 강아지도 한 장난감만 오래 가지고 놀면 싫증을 내기 마련이다.<br>
일정 주기로 장난감을 바꿔가며 놀아주면 강아지는 매번 새로운 장난감을 갖고 노는 기분을 가질뿐더러, 장난감의 수명도 길어지기 때문이다.<br><br>

💬 입양 전 반드시! 강아지 알레르기 체크 <br>
미국의 천식/알레르기 재단에 따르면 15~30%의 사람이 강아지 알레르기를 갖고 있다고 한다. 알레르기는 강 아지 비듬에 대한 면역체계 반응으로 생기기 때문에 치료 방법이 마땅치 않다. 만약 자녀가 강아지를 키우고 싶어 하는데, 알레르기 반응이 걱정된다면 어떻게 해야 할까?<br><br>

- 팁! 알레르기 테스트<br>
강아지를 입양하기 전, 자녀에게 알레르기 테스트를 해보는 것이 좋다. 만약 알레르기가 있다 하더라도 심한 수준이 아니라면 저알레르기 반응을 일으키는 견종을 잠시 위탁하거나 애견카페 등에서 반응을 살펴보자. 저알레르기 견종일 경우라도 특정 알레르기 유발체가 자녀에게 작용할 수 있기 때문이다.<br>
이런 고비를 잘 넘긴다면 자녀에게 유년시절을 따뜻하게 떠올릴 수 있는 좋은 친구를 만들어 줄 수 있을 것이다.<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('1-2-1', 'none', '개춘기', '<br>
여러 변수에도 불구하고 강아지를 키울 수 있다는 판단이 들었다면, 입양 준비를 시작한다.<br>
우선 자신에게 잘 맞는 견종을 찾고, 해당 견종에 대해 미리 공부를 해두는 것이 좋다. <br><br>

조용하고 다정한 강아지가 좋은지, 활동적인 아이가 좋은지, 훈련 능력이 뛰어난 아이가 좋은지, 반려인의 생활패턴과 성격, 환경 등에 잘 맞는 견종을 찾는다. 자신에게 꼭 맞는 견종을 찾는 것은 무엇보다 즐거운 일이다.<br>
일반적으로 혈통에 따라 특유의 성품이 있다고 알려져 있기는 하나, 결론적으로는 견주의 노력 여하에 달려 있다.<br><br>

하단의 견종별 특징은 결코 절대적인 것이 아님을 밝혀둔다. 1차적으로 자신에게 적합한 견종을 알아보고, 함께 생활하며 꾸준히 맞춰 나가는 과정을 거쳐야 한다. <br><br>

[견종 특성]<br>
- 집을 지키는 데 적합한 견종<br>
셰퍼드, 도베르만, 로트와일러, 마스티프 등<br><br>

- 활동량이 많은 견종<br>
불테리어, 콜리, 세인트 버나드, 아이리시, 세터, 알래스카 말라뮤트, 비글, 닥스훈트 등<br><br>

- 털 빠짐이 적은 견종<br>
베들링턴 테리어, 푸들, 슈나우저 등<br><br>

- 맹견<br>
도사견, 아메리칸 핏불 테리어, 아메리칸 스태퍼드셔 테리어, 스태퍼드셔 불 테리어, 로트와일러<br><br>

💬 맹견 견주 의무사항<br>
- 소유자 없이 통제된 장소를 벗어나지 않게끔 할 것<br>
- 생후 3개월 이상의 맹견과 함께 외출할 경우 목줄(가슴줄 불가)과 사람에 대한 공격을 효과적으로 차단할 수 있는 입마개를 착용하게 할 것<br>
- 맹견이 탈출할 수 없고, 쉽게 파손되지 않는 견고한 재질의 이동장치를 할 것<br>
- 맹견의 안전한 사육 및 관리에 관한 정기적인 교육을 받을 것(최초 취득 시 6개월 이내 3시간, 그 외 소유자 매년 3시간)<br>
- 어린이집, 유치원, 초등학교 및 특수구역, 그밖에 시·도의 조례로 정하는 장소의 출입을 하지 않을 것<br><br>

※ 위 사항을 준수하지 않은 사람에게는 300만 원 이하의 과태료가 부과되거나 사람에게 신체적 피해를 주는 경우 소유자 동의 없이 맹견에 대한 격리조치 가능<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('1-3-1', 'none', '달라진 일상', '<br>충분한 고민을 거쳐 강아지를 책임질 수 있는지 따져보고, 자신에게 맞는 견종을 골랐다면 입양 경로를 선택해 야 한다.</br>
반려견을 데려올 수 있는 경로는 크게 동물보호센터(유기동물 보호소), 동물판매업소, 전문 브리더, 해외 등으로 나눠진다.</br></br>

[동물보호센터(유기동물 보호소)]</br>
동물보호센터란 분실 또는 유기된 반려동물들을 위해 지방자치단체가 설치, 운영하거나 지방자치단체로부터 위탁 받은 시설에서 운영하는 여타의 동물보호시설을 말한다. 동물보호센터에서 입양할 경우 사전에 동물보호 센터에서 임시보호를 하거나 각종 봉사활동을 하면서 강아지와의 애정을 쌓을 수 있고, 예행연습을 할 수 있어 자 신의 결정을 확실히 하는 데 도움이 될 것이다.</br>

[동물판매업소]</br>
동물판매업자가 반려동물을 판매할 때에는 반려동물 매매 계약서와 해당 내용을 증명하는 서류를 제공해야 하며, 계약서를 제공할 의무가 있음을 영업장 내부(온라인의 경우 인터넷 홈페이지 또는 휴대전화에서 사용되는 응용프로그램을 포함함)의 잘 보이는 곳에 게시해야 한다.</br></br>

[전문 브리더]</br>
전문 브리더를 통해 강아지를 데려오면 부모견을 확인할 수 있다는 장점이 있다. 부모견이 정서적으로 안정되 어 있는지, 신체적 문제는 없는지의 여부를 확인하는 게 좋다. 하지만 견종에 따른 비용 부담이 있을 수 있고, 간혹 전문 브리더를 사칭한 개 농장이 다수 존재하므로 입양 전 필수적으로 견사를 방문해보아야 한다.</br>
무엇보다 동물 생산업 허가 여부를 확인하는 것이 중요하다.</br></br>

💬 ‘반려동물 매매계약서’ 필수 기재 내용 꿀팁!</br>
- 동물판매업 등록번호, 업소명, 주소 및 전화번호</br>
- 동물의 출생일자 및 판매업자가 입수한 날</br>
- 동물을 생산하거나 수입한 업자의 업소명 주소</br>
- 동물의 종류, 품종, 모색 및 판매 시 특징</br>
- 예방접종, 약물투여 등 수의사 치료기록 등</br>
- 판매 시 건강 상태와 증빙서류</br>
- 판매일 및 판매금액</br>
- 판매한 동물에게 질병 또는 사망 등 건강상의 문제가 생긴 경우 처리방법</br>
- 등록된 동물인 경우 등록내역</br></br>

💬 ‘반려동물 매매계약서’를 받아야 하는 이유는?</br>
- 반려동물이 죽거나 질병에 걸렸을 때 이 계약서가 중요한 자료가 될 수 있으므로 반려동물을 구입할 때는 계약서를 잊지 않고 받아야 한다.</br>
- 만약 동물판매업소에서 계약서를 제공하지 않았다면, 소비자는 시장·군수·구청장에게 이를 신고해 행정처분(등록 또는 허가 취소 등)을 요구할 수 있다.</br></br>

특히 반려견을 데려올 때는 해당 동물판매업소가 동물 판매업 등록이 되어 있는 곳인지 확인하는 것이 중요하다.</br>
「동물보호법」은 소비자를 보호하기 위해 일정한 시설과 인력을 갖추고, 시장·군수·구청장(자치구의 구청장을 말함)에게 동물판매업 등록을 한 업자에게만 반려동물을 판매할 수 있도록 하고 있다. 이처럼 법적인 책임이 요구되는 곳에서 반려동물을 구입해야만 이후 예상치 못한 분쟁이 발생했을 때 훨씬 대처하기 쉽다. 동물판매업 등록 여부는 영업장 내에 게시된 동물판매업 등록증으로 확인할 수 있다.</br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('1-4-1', 'none', 'none', '💬입양 전 한 번 더 점검해요!<br>
- 유기견 보호소에서 데려온 경우, 머문 기간은 어떻게 되는지<br>
- 유기견 보호소에서 데려온 경우, 보호소에 병에 걸린 강아지가 없었는지<br>
- 급여중인 사료는 무엇인지<br>
- 좋아하는 음식과 싫어하는 음식<br>
- 알레르기 여부 - 특이 병력은 없는지<br>
- 다른 개들과 잘 어울리는 편인지<br>
- 부모견을 알 수 있는 경우 병력 및 성격은 어떤지<br>
- 배변은 어디서 어떻게 봤는지, 배변 횟수는 어떻게 되는지<br>
- 예방접종 여부 (애견수첩이 있으면 요구할 것)<br>
- 마이크로칩 등록 여부<br>
- 그 외 특이사항<br><br>

[준비사항]<br>
배변패드, 침대쿠션, 밥그릇, 물그릇, 이전에 먹던 사료, 약간의 간식<br><br>

[첫 대면 시]<br>
- 반려견의 이름을 부르며 몸을 낮춘다.<br>
- 조심스럽게 손을 내밀어 냄새를 맡게 한다.<br>
- 반려견의 턱 아래를 부드럽게 만져준다.<br><br>

[집 도착 시]<br>
- 반려견이 불안해하지 않도록 예전에 쓰던 물건을 같이 둔다.<br>
- 첫날이 지나면 반려견이 자유롭게 집안을 돌아다니도록 한다.<br><br>

[반려동물 등록]<br>
- 시장·군수·구청장이 대행업체로 지정한 동물병원을 방문해 신청서 작성 후 수수료 납부<br>
- 해당 동물의 소유권을 취득한 날 또는 소유 동물이 등록대상동물이 된 날부터 30일 이내<br>
동물등록 신청서를 시장·군수·구청장에게 제출<br><br>

5. Q&A<br>
Q. 반려동물을 데려온 후 발생한 피해를 보상받는 방법은?<br>
A.<br>
- 데려온 날로부터 15일 이내 폐사 시<br>
같은 종류의 반려동물로 교환 또는 입양 비용 환불<br><br>

- 데려온 날로부터 15일 이내 질병 발생 시<br>
판매업소(사업자)가 제반 비용을 부담해서 회복시킨 후 소비자에게 인도<br><br>

Q. 반려동물 수수료는?<br>
A.<br>
- 내장형 등록 인식표｜무료~8만원(서울시 1만원)<br>
- 외장형 등록 인식표｜4만원 이내<br>
* 내장형, 외장형 등록 비용은 지자체별 지원에 따라 상이할 수 있음<br>
* 동물등록대행업체는 동물보호관리시스템에서 검색 가능<br>
- 등물등록 변경신고 시 소유자명의·주소·전화번호가 변경된 경우, 동물등록증 재발급 시 무료<br><br>

Q. 주민등록상 거주지가 아닌 곳에서도 동물등록신청이 가능할까?<br>
A. 타 지역 거주민이 신청을 하는 경우에도 신청을 받은 시·군·구청에서 동물등록 처리 및 동물등록증 발급 가능<br><br>

▶ 수수료는 정부수입인지, 해당 지방자치단체의 수입인지, 현금, 계좌이체, 신용카드, 직불카드 또는 정보통신망을 이용한 전자화폐/전자결제 등의 방법으로 내야 한다.<br><br>

▶ 시장·군수·구청장은 필요한 경우 관할 지역 내에 있는 모든 동물등록대행자에 대하여 해당 동물등록대행자가 판매하는 무선식별장치의 제품명과 판매가격을 동물보호관리시스템에 게재하게 하고 해당 영업소 안의 눈에 띄기 쉬운 곳에 게시하도록 할 수 있다.');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-1-1', '/images/knowAfterAdopt2.png', '작고 약한 이빨', '<br>
💬강아지 유치는 생후 2주~10주 사이에 모두 자라게 됩니다. 그리고 생후 4~6개월에 유치가 빠지며 이갈이를 하게 됩니다.<br>
유치는 매우 약하기 때문에, 이 시기에는 너무 딱딱한 간식이나 장난감은 주지 않는 게 좋습니다.<br><br>

💬강아지는 총 28개의 유치를 가지고 있습니다. 유치는 앞니, 송곳니 그리고 어금니 순서대로 빠지게 되는데요.<br>
유치가 모두 빠지면 총 42개의 영구치를 가지게 됩니다. 단, 턱 크기나 유전적 이유 등으로 42개가 모두 나지 않는 강아지도 있어요.<br><br>

💬강아지 유치가 빠질 때, 강아지는 잇몸에 통증과 간지러움을 느껴요. 그래서 소파, 의자 다리, 옷 등을 물어 뜯게 될 수 있습니다.<br>
이를 방지하기 위해 강아지가 이갈이를 시작했다면, 너무 딱딱하지 않은 라텍스같이 튼튼하면서도 말랑한 재질의 장난감을 주면 좋습니다.<br>
💬강아지 유치가 제대로 빠지는지 확인해 보는 것도 중요해요. 유치가 자연스럽게 빠지지 않는 경우 병원에서 발치를 해 영구치가 제대로 나올 수 있게 도와줘야 합니다.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-1-2', '/images/knowAfterAdopt3.png', '첫 건강 관리', '<br>
💬강아지가 성장기일 땐 특별히 아프지 않다고 하더라도 동물병원에 자주 가야 합니다. 여러 차례에 걸쳐 강아지 예방접종을 해야 하며, 중성화 수술을 할 수도 있기 때문이에요.<br><br>

💬특히, 이 시기에 선천적 질병이나 유전병이 있지 않은지도 꼭 확인합시다.<br><br>

💬강아지 중성화 수술에는 장단점이 분명히 있어요. 중성화 수술을 하면 수컷은 고환암, 전립선 질병 그리고 암컷은 유선 종양, 자궁축농증 등의 질병을 예방할 수 있습니다. 반대로 살이 쉽게 찔 수 있고 정형외과 질병 위험이 높아질 위험도 있습니다.<br>
중성화 수술의 장단점에 대해 알아보고, 수술을 할지 말지 충분히 고민해 보세요.<br><br>

💬강아지 예방접종은 약 6회에 걸쳐 접종을 마칩니다. 2주 간격으로, 한 번에 2~3가지의 백신을 맞게 돼요. 접종을 모두 마친 뒤에는 1년 주기로 재접종을 하면 됩니다. 참고로 필수 백신의 경우 종합백신, 코로나 장염, 켄넬코프, 관견병이 있어요. 인플루엔자는 선택 사항입니다.<br><br>

💬선천적으로 질병을 가지고 태어나는 경우도 있어요. 그중, 빠르게 발견하고 치료하면 완치가 가능한 질병도 있습니다.<br>
예를 들어, 선천성 심장 기형인 동맥관개종증(PDA)는 생후 1년 이내에 발견하면 대부분 치료가 가능한데요.<br>
치료하지 않으면 생후 2~3년 이내에 사망할 확률이 약 70%나 됩니다.<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-2-1', '/images/knowAfterAdopt5.png', '개춘기', '<br>
💬성견이 되었어도, 강아지 생후 2세까지는 강아지 사춘기(개춘기)라고 불리는 시기입니다. 이 시기에는 강아지가 에너지가 넘쳐서 각종 사고를 칠 수 있어요.<br><br>

💬따라서, 강아지가 에너지를 모두 발산할 수 있도록 산책과 놀이를 충분히 시켜주는 게 매우 중요합니다. 또한, 앉아, 기다려, 켄넬 훈련 같은 기본 훈련을 시키는 게 좋은데요.<br>
이런 훈련들은 강아지의 흥분, 습관성 짖음 그리고 공격성 같은 행동을 고치는 데 도움이 됩니다.<br><br>

💬사춘기에 물건을 마구 물어뜯는 강아지도 많아요. 이 시기에도 이갈이 시기와 마찬가지로 씹기 좋은 장난감을 주는 게 좋습니다.<br>
단, 영구치가 난 상태이기 때문에 조금 더 튼튼한 장난감을 주는 게 좋습니다.<br><br>

💬사춘기 강아지는 에너지가 넘치기 때문에 활동량을 늘려줘야 하는데요. 하지만, 아직 골격이 완벽한 상태는 아니라 너무 강도 높은 격한 운동을 할 경우 관절에 무리가 갈 수 있습니다.<br>
따라서, 노즈워크 같은 머리를 써야 하는 놀이를 해주면 좋습니다.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-2-2', '/images/knowAfterAdopt6.png', '마음 여린 어른', '<br>
💬사춘기가 지나 강아지가 생후 2~3세가 되면 주변 환경에 적응을 해서 정서적으로 안정되고 차분해집니다.<br>
하지만 차분해졌다고 해서 충분히 강아지를 놀아주지 않거나 혼자 두는 시간이 늘어나게 되면 우울증이나 분리불안이 올 수 있으니 주의가 필요합니다.<br>
💬동물행동학자들에 의하면, 성견의 경우 하루에 4~6시간 정도 혼자 있어도 괜찮다고 합니다. 최대한 강아지와 오랜 시간 함께 하려고 노력하는 게 좋습니다.<br>
긴 외출을 할 경우, 외출 전에 미리 시간을 내 산책을 해주는 게 좋습니다.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-2-3', '/images/knowAfterAdopt6-1.png', '꾸준한 관리', '<br>
💬성견이 되면 성장기 때와 달리 자주 동물병원에 갈 일이 없습니다. 건강에 큰 이상이 생기는 경우도 드물기 때문에 예방접종을 할 때 정도만 데려갑니다.<br>
그런 만큼, 성장기 강아지의 건강을 위해서는 보호자의 세심한 관찰이 중요합니다.<br> 평소 활동량, 식욕, 대소변 상태 등을 잘 확인하고 1년에 1~2회 정도 정기검진을 받으면 좋습니다.<br>
💬강아지 건강에 특별한 문제가 없더라도, 증상이 잘 발현되지 않는 질병도 있어 1년에 1회 정도는 건강 검진을 해주는 게 좋습니다.<br>
이 시기에는 가장 기본적인 건강검진 항목만 해보는 것도 방법이에요. 이 시기에는 건강을 위해 꾸준한 운동,<br>
균형 잡힌 식사 급여 그리고 양치질을 잘 해주는 정도의 관리를 해주면 좋습니다.');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-3-1', '/images/knowAfterAdopt8.png', '노년기 (8세~)', '<br>
💬노견 가족의 일상은 조금 달라집니다. 어릴 때는 신나게 뛰며 산책을 했다면 이제는 느리게 여유 있는 산책을 해야 합니다.<br>
치아와 잇몸이 약해진 경우 먹는 것도 신경 써야 하는데요. 치아와 잇몸에 무리를 주지 않는 부드러운 사료와 간식을 주는 게 좋습니다.<br><br>

💬만약 시력 저하로 인해 앞을 잘 보지 못한다면 가구에 완충재를 붙이고 갑잡기 큰소리를 내지 않는 등의 배려가 필요합니다.<br><br>

💬노견의 경우 체력이 떨어지고 관절도 약해지는 등 건강 상태가 나빠지는 경우가 많습니다.<br>
그래서 너무 격한 운동은 하지 않는 게 좋습니다. 특히, 강아지에게 질병이 있는 경우 격한 운동을 하다가 쓰러지는 경우도 있으니 주의해 주세요.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('2-3-2', '/images/knowAfterAdopt9.png', '건강 체크 필수', '<br>
💬노견을 키운다면 건강 상태를 더 열심히 확인해야 합니다. 눈이 탁해지지 않았는지, 눈곱 및 눈물량 증가 여부, 혀 색깔, 피부 등 강아지의 외관은 물론이고 대소변, 식욕, 활력 등도 잘 살펴봅시다.<br><br>

💬노견의 경우 건강해 보일 수 있지만 면역력도 많이 약해집니다.<br>
그래서 스트레스를 받거나 작은 질병에 노출되어도 건강이 빠르게 악화될 수 있습니다. 따라서, 반드시 1년에 1~2회 정기 검진을 받아야 할 필요가 있습니다.<br>
노견이 되면 신장, 심장 등 각종 신체 기관의 기능도 떨어지며 이로 인해 질병이 발생할 수 있다는 걸 알아둡시다.<br><br>

💬강아지 눈이 탁해졌다면, 크게는 백내장 그리고 핵경화증 두 가지를 의심해 볼 수 있습니다. 핵경화증은 단순히 노화로 인한 증상으로 시력에는 큰 문제가 없습니다. 다만, 백내장의 경우 실명이나 다른 안과 질환으로 이어질 수 있습니다.<br>
이 두 가지 질병은 전문가가 아니라면 구분이 어렵습니다. 그러니 눈이 탁해 보인다면 우선 병원에서 확인해 보는 게 좋습니다.');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('3-1-1', '/images/emergency2.png', '잘못먹었을때,', '<br>
💬간식을 먹거나 이물질을 먹어 기도가 막혔을 때는 먼저 해주셔야 할 것은 첫번째 거꾸로 들기 입니다.<br>
뒷다리를 잡고 위로 들어 물구나무서기 하는 자세를 취해 주고 여러번 강아하게 움직여 줍니다.<br>
대형견의 경우는 앞다리가 땅에 딛게 해도 됩니다.<br>
다음은 어깨 사이를 강하게 쳐주기 입니다.<br>
어깨뼈 사이의 등 부분을 4~5회 강하게 쳐줍니다.<br>
마지막으로 하임리히 방법 을 이용합니다.<br>
마지막 갈비뼈 아래에 주먹이나 손가락을 대고 다른 손으로 강하게 눌러 압박 합니다. 갈비뼈가 부러지지 않게 조심합니다.<br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('3-1-2', 'none', '먹지 말아야 할 것을 이미 먹었을 때', '<br>
💬그리고 강아지가 먹지 말아야 할 (초코릿, 커피, 카페인, 알코올, 아보카도, 마카다미아, 포도, 건포도, 양파, 마늘, 파) 이미 먹고 먹은 상태가 얼마 안됐다면 토하게 해주는 것이 중요합니다.<br>
여기서 먹은지 2시간 이상이라면 바로 병원에 가시길 바라며 병원에 가서 처치 받길 바랍니다.
<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('3-1-3', '/images/emergency3.png', '숨을 못쉴때(인공호흡법)', '<br>
💬사람처럼 동물들도 인공호흡을 할 수 있습니다. 먼저 숨을 쉬는지 확인 해주시고 숨을 체크 하는 방법은 안경 또는 거울로 코 앞에 대면 김이 서리는지 안서리는지 체크 해보시면 됩니다.<br>
숨을 쉬지 않는 것이 확인 된다면 입을 막은 상태에서 강아지 코에 입을 대고 바람을 불어 넣어 주시면 됩니다.<br>
(이때는 강아지의 목이 펴진 상태에서 해줘야 합니다.) 제대로 인공호흡이 된다면 가슴이 부풀어 오릅니다.<br>
인공 호흡하는 횟수는 1분에 12~15회가 적당 합니다. (3초~5초에 1회) 입니다.<br><br>
여기서 중요한 것은<br> 입을 다물고 코에 인공호흡을 해주셔야 합니다.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('3-1-4', '/images/emergency4.png', '심장이 안뛸때(심장마비)', '<br>
💬심장이 멎었는지 가슴 부위에 귀나 손을 대봤을 때 박동이 느껴지지 않거나, 대퇴부 안쪽 을 손가락으로 눌렸을때 맥박이 느껴지지 않는 것이 확인 된다면 심장마사지를 해주셔야 합니다.
심장마사지는 소형견과 대형견의 차이가 있습니다.<br><br>
먼저 평평한 곳에 눕힙니다.<br>
10kg이하의 소형견은 한 손을 심장 부위 (어깨 뒤, 와 배 쪽 가슴) 양쪽 가슴을 감싸듯이 쥐고 가슴의 1/3정도가 눌리도록 압박해 줍니다.<br>
1분당 100회를 실시 합니다.<br><br>
10kg이상 중대형견은 손을 심장 부위 한쪽 가슴에 놓고, 가슴의 1/3 정도가 눌리도록 압박합니다. 압박할 때 는 팔굽치가 구부려지지 않아야 제대로 눌러줄수 있습니다. 분당 80회 정도 실시 합니다.<br><br>
심장 마사지와 함꼐 인공호흡을 실시 합니다. 인공호흡은 바로 알려 드리겠습니다.<br>
너무 세게 압박하면 늑골이 부러지거나 폐출혈이 발생 할수 있으니 주의하시기 바랍니다. 10분이상 했는데 돌아오지 않으면 가망이 없습니다.<br><br><br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('3-1-5', '/images/emergency5.png', '발작할 때', '<br>
💬갑작게 발작을 이르킨다면 호흡을 유지할 수 있도록 고개를 들어 기도를 확보합니다.<br>
혀가 말려들어가 기도가 막히는 경우가 있기 때문에 손가락으로 혀를 잡아 기도 확보를 해주셔야 안전합니다.<br>
머리가 부딪히지 않도록 감싸줍니다.<br>
발작을 일으키는 동영상을 확보해서 동물병원에 보여주면 보다 정확한 원인을 알 수 있으므로, 가능하다면 동영상을 찍어두는 것도 방법입니다.<br>
어느정도 발작이 멎었으면 병원에 가서 꼭 진찰을 받으시길 바랍니다.<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('4-1-1', '/images/petiket2.png', 'none', '?외출시 산책줄을 반드시 사용합니다.<br><br>

💬공공장소에서는 2M이내 산책줄을 사용해주세요.<br><br>

💬외출시 배변봉투를 반드시 챙깁니다.<br><br>

💬다른 개나 사람과 접촉할 시 상대방(견주)의 동의를 먼저 구합니다.<br>
<br>
<br>
<br>
몸이 아프거나 교육을 받고 있는 반려견의 경우 노란리본(옐로우독 캠페인)을 달아 “만지지 말아주세요!” 라는 메세지를 전하는 것도 좋아요!<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('4-2-1', '/images/petiket4.png', 'none', '💬동물등록과 인식표는 필수입니다.<br><br>

💬반려견이 물었던 경험이나 가능성이 있다면 입마개는 필수입니다.<br><br>

💬사람을 향해 뛰어오르지 않도록 교육해주세요.<br><br>

💬개들도 적절한 사회화와 매너교육이 필요합니다.<br><br>

💬무분별한 마킹은 그만! 상가나 집앞에는 마킹하지 않도록 해주세요.<br>
<br>
<br>
<br>
반려견 인사법<br>
무작정 다가가지 않고 먼저 손바닥을 보여줍니다.<br>
손을 내민 채 강아지가 올 때까지 기다립니다.<br>
강아지가 다가오면 천천히 쓰다듬어 줍니다.<br>
단, 보호자가 없을 때는 다가가지 않아야 합니다.<br>');
INSERT INTO daalgae.encycle_info (ENCYCLE_ORDER, ENCYCLE_IMAGE, ENCYCLE_TITLE, ENCYCLE_CONTENT) VALUES ('4-3-1', '/images/petiket6.png', 'none', '💬타인의 반려견을 함부로 만지지 말아야합니다.<br>
💬양해를 구했다면 천천히 다가가야 합니다.<br>
💬큰소리를 내지 말아야 합니다.<br>
💬눈을 너무 빤히 바라보지 않아야합니다, 개들에게 눈 마주침은 도전적인 의미가 될 수 있습니다.<br>
💬노란리본은 개에게 자신만의 공간이 필요하다는 의미입니다.<br>
💬아이가 갑자기 다가오면 놀랄 수 있어요. 아이들에게도 강아지에게 인사하는 방법을 천천히 알려주세요..<br>');
