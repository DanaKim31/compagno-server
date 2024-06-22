DROP TABLE notification;

DROP TABLE ad_recommend_logic;

DROP TABLE note_file;
DROP TABLE note;

DROP TABLE notice_board_image;
DROP TABLE notice_board_comment;
DROP TABLE notice_board;

DROP TABLE user_answer_choose;
DROP TABLE user_answer_board;
DROP TABLE user_question_like;
DROP TABLE user_question_board_image;
DROP TABLE user_question_board;

DROP TABLE qna_q_board_image;
DROP TABLE qna_q_board;
DROP TABLE qna_a_board_image;
DROP TABLE qna_a_board;

DROP TABLE animal_board_comment;
DROP TABLE animal_board_image;
DROP TABLE animal_board_favorite;
DROP TABLE animal_board;

DROP TABLE oneday_class_board_main_image;
DROP TABLE oneday_class_board;

DROP TABLE parsing;

DROP TABLE product_board_recommend;
DROP TABLE product_board_comment;
DROP TABLE product_board_bookmark;
DROP TABLE product_board_image;
DROP TABLE product_board;

DROP TABLE neighbor_board_bookmark;
DROP TABLE neighbor_board_comment;
DROP TABLE neighbor_board_image;
DROP TABLE neighbor_board;

DROP TABLE sitter_board_bookmark;
DROP TABLE sitter_board_comment;
DROP TABLE sitter_board_image;
DROP TABLE sitter_board;
DROP TABLE sitter_category;

DROP TABLE animal_category;

DROP TABLE adoption_board_comment;
DROP TABLE adoption_board_image;
DROP TABLE adoption_board;

DROP TABLE lost_board_comment;
DROP TABLE lost_board_image;
DROP TABLE lost_board;

DROP TABLE register_pet_faq;
DROP TABLE register_pet_board;

DROP TABLE location;

DROP TABLE user;

-- ======================================== [SH] USER ========================================
-- user
CREATE TABLE user (
	user_id VARCHAR(20) PRIMARY KEY UNIQUE,
    user_person_name VARCHAR(20),
    user_phone VARCHAR(20),
    user_pwd VARCHAR(500),
    user_nickname VARCHAR(20),
    user_email  VARCHAR(50),
    user_status char(1) DEFAULT 'n',
    user_enroll_date VARCHAR(50),
    user_quit_date  DATETIME,
    user_role VARCHAR(20) DEFAULT 'ROLE_USER',
    user_img VARCHAR(500),
    user_point INT DEFAULT 0
    );
    
-- ======================================== [Dana] LOCATION ========================================
CREATE TABLE location(
	location_code INT PRIMARY KEY AUTO_INCREMENT,
	location_name VARCHAR(10),
    location_parent_code INT
);

-- ======================================== [Dana] REGISTER PET ========================================
-- register_pet_board
CREATE TABLE register_pet_board(
	rp_board_code INT PRIMARY KEY AUTO_INCREMENT,
    rp_inst_name VARCHAR(100),
    rp_inst_addr VARCHAR(200),
    rp_inst_owner VARCHAR(20),
    rp_inst_phone VARCHAR(20)
);

-- register_pet_faq
CREATE TABLE register_pet_faq(
	rp_faq_code INT PRIMARY KEY AUTO_INCREMENT,
    rp_faq_question VARCHAR(200),
    rp_faq_answer text,
    rp_faq_status CHAR(1) CHECK (rp_faq_status IN ('Y', 'N')) DEFAULT 'Y'
);

-- ======================================== [MJ] LOST ========================================
-- lost_board
CREATE TABLE lost_board(
	lost_board_code	INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    user_img VARCHAR(500),
	user_nickname VARCHAR(20),
    user_phone VARCHAR(20),
    lost_regi_date Date default(current_date),
    lost_view_count INT DEFAULT 0,
    lost_animal_image VARCHAR(150),
    lost_animal_name VARCHAR(30) NOT NULL,
    lost_date DATE NOT NULL,
    lost_location VARCHAR(50) NOT NULL,
    lost_location_detail VARCHAR(100),
    lost_animal_kind VARCHAR(30),
    lost_animal_color VARCHAR(30),
    lost_animal_gender VARCHAR(10) NOT NULL,
    lost_animal_age INT,
    lost_animal_feature TEXT,
    lost_animal_RFID VARCHAR(15)
);

-- lost_board_image
CREATE TABLE lost_board_image(
	lost_image_code INT PRIMARY KEY AUTO_INCREMENT,
    lost_board_code INT,
    lost_image VARCHAR(300)
);

-- lost_board_comment
CREATE TABLE lost_board_comment(
	lost_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    lost_board_code INT,
    user_id VARCHAR(20),
    user_img VARCHAR(500),
    user_nickname VARCHAR(20),
    comment_content TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT current_timestamp,
    lost_parent_code INT
);

-- ======================================== [MJ] ADOPTION ========================================
-- adoption_board
CREATE TABLE adoption_board(
	adoption_board_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    user_img VARCHAR(500),
     user_phone VARCHAR(20),
	user_nickname VARCHAR(20),
    adoption_regi_date TimeStamp,
    adoption_view_count INT DEFAULT 0,
    adoption_animal_image VARCHAR(150),
    adoption_animal_kind VARCHAR(30),
    adoption_animal_color VARCHAR(30),
    adoption_animal_findplace VARCHAR(30) NOT NULL,
    adoption_animal_gender VARCHAR(10) NOT NULL,
    adoption_animal_neuter varCHAR(10),
    adoption_animal_age INT,
    adoption_animal_kg INT,
    adoption_animal_feature TEXT,
    adoption_center_name VARCHAR(30) NOT NULL,
    adoption_center_phone VARCHAR(20) NOT NULL
);

-- adoption_board_image
CREATE TABLE adoption_board_image(
	adoption_image_code INT PRIMARY KEY AUTO_INCREMENT,
    adoption_board_code INT,
    adoption_image VARCHAR(300)
);

-- adoption_board_comment
CREATE TABLE adoption_board_comment(
	adoption_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    adoption_board_code INT,
    user_id VARCHAR(20),
    user_img VARCHAR(500),
    comment_content TEXT NOT NULL,
    comment_date TIMESTAMP NOT NULL,
    adoption_parent_code INT
);
alter table adoption_board_comment modify comment_date timestamp default current_timestamp;
alter table adoption_board_comment add user_nickname varchar(20);
-- ======================================== [Dana] SITTER ========================================
-- sitter_board
CREATE TABLE sitter_board(
	sitter_board_code INT PRIMARY KEY AUTO_INCREMENT,
    sitter_category_code INT,
    animal_category_code INT,
	location_code VARCHAR(100),
    sitter_title VARCHAR(100),
    sitter_content TEXT,
    user_id VARCHAR(20),
    sitter_view_count INT DEFAULT 0,
    sitter_regi_date TIMESTAMP DEFAULT current_timestamp,
    sitter_update_date DATE,
    sitter_status CHAR(1) CHECK (sitter_status IN ('Y', 'N')) DEFAULT 'Y'
);

-- sitter_category
CREATE TABLE sitter_category(
	sitter_category_code INT PRIMARY KEY AUTO_INCREMENT,
    sitter_category_type VARCHAR(20)
);
INSERT INTO sitter_category(sitter_category_type) VALUES("구인");
INSERT INTO sitter_category(sitter_category_type) VALUES("구직");

-- sitter_board_image
CREATE TABLE sitter_board_image(
	sitter_image_code INT PRIMARY KEY AUTO_INCREMENT,
    sitter_board_code INT,
    sitter_bd_image	VARCHAR(200)
);

-- sitter_board_comment
CREATE TABLE sitter_board_comment(
	sitter_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    sitter_board_code INT,
    user_id VARCHAR(20),
    sitter_com_content TEXT NOT NULL,
    sitter_com_regi_date TIMESTAMP DEFAULT current_timestamp,
    sitter_com_status CHAR(1) CHECK(sitter_com_status IN ('Y', 'N')) DEFAULT 'Y',
    sitter_com_delete_date DATE,
    sitter_com_parent_code INT
);

-- sitter_board_bookmark
CREATE TABLE sitter_board_bookmark(
	sitter_bookmark_code INT PRIMARY KEY AUTO_INCREMENT,
    sitter_board_code INT,
    user_id VARCHAR(20),
    sitter_bookmark_date TIMESTAMP DEFAULT current_timestamp
);

-- ======================================== [Dana] NEIGHBOR ========================================
CREATE TABLE neighbor_board(
	neighbor_board_code INT PRIMARY KEY AUTO_INCREMENT,
    animal_category_code INT,
	location_code VARCHAR(100),
    neighbor_title VARCHAR(100),
    neighbor_content TEXT,
    user_id VARCHAR(20),
    neighbor_view_count INT DEFAULT 0,
    neighbor_regi_date TIMESTAMP DEFAULT current_timestamp,
    neighbor_update_date DATE,
    neighbor_status CHAR(1) CHECK (neighbor_status IN ('Y', 'N')) DEFAULT 'Y'
);

-- neighbor_board_image
CREATE TABLE neighbor_board_image(
	neighbor_image_code INT PRIMARY KEY AUTO_INCREMENT,
    neighbor_board_code INT,
    neighbor_bd_image VARCHAR(200)
);

-- neighbor_board_comment
CREATE TABLE neighbor_board_comment(
	neighbor_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    neighbor_board_code INT,
    user_id VARCHAR(20),
    neighbor_com_content TEXT NOT NULL,
    neighbor_com_regi_date TIMESTAMP DEFAULT current_timestamp,
    neighbor_com_status CHAR(1) CHECK(neighbor_com_status IN ('Y', 'N')) DEFAULT 'Y',
    neighbor_com_delete_date DATE,
    neighbor_com_parent_code INT
);

-- neighbor_board_bookmark
CREATE TABLE neighbor_board_bookmark(
	neighbor_bookmark_code INT PRIMARY KEY AUTO_INCREMENT,
    neighbor_board_code INT,
    user_id VARCHAR(20),
    neighbor_bookmark_date TIMESTAMP DEFAULT current_timestamp
);

-- ======================================== [HY] PRODUCT ========================================
-- product_board
CREATE TABLE product_board(
	product_board_code INT PRIMARY KEY AUTO_INCREMENT,
    product_board_title	VARCHAR(50) NOT NULL,
    product_main_image VARCHAR(150),
    product_name VARCHAR(40) NOT NULL,
    product_price INT NOT NULL,
    product_category VARCHAR(20),
    animal_category_code INT,
    product_board_grade DECIMAL(2,1) NOT NULL,
    product_board_content TEXT NOT NULL,
    product_board_regi_date DATETIME DEFAULT now(),
    user_id VARCHAR(20),
    product_board_view_count INT DEFAULT 0
);

-- product_board_image
CREATE TABLE product_board_image(
	product_image_code INT PRIMARY KEY AUTO_INCREMENT,
    product_board_code INT,
    product_image VARCHAR(150) NOT NULL
);

-- product_board_comment
CREATE TABLE product_board_comment(
	product_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    product_board_code INT,
    user_id VARCHAR(20),
    product_comment_content TEXT NOT NULL,
    product_comment_regi_date DATETIME DEFAULT now(),
    product_parent_code INT,
    product_comment_delete CHAR(1) CHECK (product_comment_delete IN ('Y', 'N')) DEFAULT 'N'
);

-- product_board_recommend
CREATE TABLE product_board_recommend(
	product_recommend_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    product_board_code INT,
    product_recommend_date DATETIME DEFAULT now()
);

-- product_board_bookmark
CREATE TABLE product_board_bookmark(
	product_bookmark_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    product_board_code INT
);

-- ======================================== [HS] ONEDAY CLASS ========================================
-- oneday_class_board
CREATE TABLE oneday_class_board(
	odc_code INT PRIMARY KEY AUTO_INCREMENT,
    odc_title VARCHAR(200) NOT NULL,
    odc_content TEXT NOT NULL,
    odc_accompaying CHAR(1) CHECK (odc_accompaying IN ('Y', 'N')) DEFAULT 'Y',
    odc_regi_date TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp,
    odc_start_date DATE NOT NULL,
    odc_last_date DATE NOT NULL,
    user_id VARCHAR(20)
);

CREATE TABLE oneday_class_board_main_image(
	odc_image_code INT PRIMARY KEY AUTO_INCREMENT,
    odc_code INT,
    odc_main_image VARCHAR(200) NOT NULL,
    user_id INT
);

-- ======================================== [J] ANIMAL ========================================
CREATE TABLE animal_board(
	animal_board_code INT PRIMARY KEY AUTO_INCREMENT,
    animal_category_code INT,
    animal_main_image VARCHAR(300),
    animal_board_title VARCHAR(50),
    animal_board_content LONGTEXT,
    animal_board_view INT DEFAULT 0,
    animal_board_date DATETIME DEFAULT now(), 
    user_id VARCHAR(20),
    animal_board_favorite_count INT DEFAULT(0)
);

-- animal_category
CREATE TABLE animal_category(
	animal_category_code INT PRIMARY KEY AUTO_INCREMENT,
    animal_type VARCHAR(20)
);

INSERT INTO animal_category(animal_type) VALUES("개");
INSERT INTO animal_category(animal_type) VALUES("고양이");
INSERT INTO animal_category(animal_type) VALUES("비둘기");
INSERT INTO animal_category(animal_type) VALUES("기타");

-- favorite
CREATE TABLE animal_board_favorite(
	animal_favorite_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    animal_board_code INT,
    animal_favorite_date DATETIME DEFAULT now()
);

CREATE TABLE animal_board_image(
	animal_image_code INT PRIMARY KEY AUTO_INCREMENT,
    animal_board_code INT,
    animal_board_image VARCHAR(300)
);

CREATE TABLE animal_board_comment(
	animal_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    animal_board_code INT,
    user_id VARCHAR(20),
    animal_comment_content TEXT,
    animal_comment_date DATETIME DEFAULT now(),
    animal_parent_code INT,
    animal_comment_tag VARCHAR(20) DEFAULT(null)
);

-- ======================================== [YB] PARSING ========================================
CREATE TABLE parsing(
	num INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    main_cate VARCHAR(20),
    main_cate_code INT,
    sub_cate VARCHAR(20),
    sub_cate_code INT,
    mainreg_cate VARCHAR(20),
    mainreg_code INT,
    subreg_cate VARCHAR(100),
	latitude VARCHAR(100),
    longtitude VARCHAR(100),
    road_addr VARCHAR(100),
    addr VARCHAR(100),
    phone  VARCHAR(30),
    url VARCHAR(500),
    parking VARCHAR(30),
    fee VARCHAR(100),
    holiday VARCHAR(100),
    operating_hours VARCHAR(300),
    viewcount INT default 0
);

-- Parsing UPDATE문
set sql_safe_updates=0; -- parsing Update 위해(일시적)
UPDATE parsing SET main_cate_code=1 WHERE main_cate="반려의료";
UPDATE parsing SET main_cate_code=2 WHERE main_cate="반려동반여행";
UPDATE parsing SET main_cate_code=3 WHERE main_cate="반려동물식당카페";
UPDATE parsing SET main_cate_code=4 WHERE main_cate="반려동물 서비스";
UPDATE parsing SET main_cate_code=2 WHERE main_cate="반려문화시설";
UPDATE parsing SET sub_cate_code=1 WHERE sub_cate="동물약국";
UPDATE parsing SET sub_cate_code=2 WHERE sub_cate="동물병원";
UPDATE parsing SET sub_cate_code=3 WHERE sub_cate="미술관";
UPDATE parsing SET sub_cate_code=4 WHERE sub_cate="문예회관";
UPDATE parsing SET sub_cate_code=5 WHERE sub_cate="펜션";
UPDATE parsing SET sub_cate_code=6 WHERE sub_cate="여행지";
UPDATE parsing SET sub_cate_code=7 WHERE sub_cate="박물관";
UPDATE parsing SET sub_cate_code=8 WHERE sub_cate="카페";
UPDATE parsing SET sub_cate_code=9 WHERE sub_cate="식당";
UPDATE parsing SET sub_cate_code=10 WHERE sub_cate="반려동물용품";
UPDATE parsing SET sub_cate_code=11 WHERE sub_cate="미용";
UPDATE parsing SET sub_cate_code=12 WHERE sub_cate="위탁관리";
UPDATE parsing SET sub_cate_code=5 WHERE sub_cate="호텔";
UPDATE parsing SET mainreg_code = 1 WHERE mainreg_cate = "경기도";
UPDATE parsing SET mainreg_code = 2 WHERE mainreg_cate = "서울특별시";
UPDATE parsing SET mainreg_code = 3 WHERE mainreg_cate = "인천광역시";
UPDATE parsing SET mainreg_code = 4 WHERE mainreg_cate = "세종특별자치시";
UPDATE parsing SET mainreg_code = 5 WHERE mainreg_cate = "강원도";
UPDATE parsing SET mainreg_code = 6 WHERE mainreg_cate = "부산광역시";
UPDATE parsing SET mainreg_code = 7 WHERE mainreg_cate = "광주광역시";
UPDATE parsing SET mainreg_code = 8 WHERE mainreg_cate = "경상북도";
UPDATE parsing SET mainreg_code = 9 WHERE mainreg_cate = "전라북도";
UPDATE parsing SET mainreg_code = 10 WHERE mainreg_cate = "대구광역시";
UPDATE parsing SET mainreg_code = 11 WHERE mainreg_cate = "전라남도";
UPDATE parsing SET mainreg_code = 12 WHERE mainreg_cate = "경상남도";
UPDATE parsing SET mainreg_code = 13 WHERE mainreg_cate = "제주특별자치도";
UPDATE parsing SET mainreg_code = 14 WHERE mainreg_cate = "충청남도";
UPDATE parsing SET mainreg_code = 15 WHERE mainreg_cate = "대전광역시";
UPDATE parsing SET mainreg_code = 16 WHERE mainreg_cate = "울산광역시";
UPDATE parsing SET mainreg_code = 17 WHERE mainreg_cate = "충청북도";

-- ======================================== [YB] QNA ========================================
-- qna_question
CREATE TABLE qna_q_board(
   qna_q_code INT PRIMARY KEY AUTO_INCREMENT,
    qna_a_code INT,
    user_id VARCHAR(20),
    user_nickname VARCHAR(20),
    user_img VARCHAR(300),
    qna_q_title VARCHAR(30) NOT NULL,
    qna_q_content TEXT NOT NULL,
    qna_q_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    qna_q_date_update TIMESTAMP,
    qna_q_status CHAR(1) CHECK(qna_q_status IN ('Y', 'N')) DEFAULT 'N',
	secret VARCHAR(100)
);

CREATE TABLE qna_q_board_image(
	qna_q_img_code INT PRIMARY KEY AUTO_INCREMENT,
    qna_q_code INT,
    qna_q_url VARCHAR(300)
);

-- qna_answer
CREATE TABLE qna_a_board(
   qna_a_code INT PRIMARY KEY AUTO_INCREMENT,
    qna_q_code INT,
    user_id VARCHAR(20),
    qna_a_title VARCHAR(30) NOT NULL,
    qna_a_content TEXT NOT NULL,
    qna_a_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    qna_a_date_update TIMESTAMP
);

CREATE TABLE qna_a_board_image(
	qna_a_img_code INT PRIMARY KEY AUTO_INCREMENT,
    qna_a_code INT,
    qna_a_url VARCHAR(300)
);

-- ======================================== [YB] User Q&A ========================================
-- User Q
CREATE TABLE user_question_board(
	user_question_board_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    user_nickname VARCHAR(20),
    user_img VARCHAR(300),
    animal_category_code INT,
    user_question_board_title VARCHAR(30) NOT NULL,
    user_question_board_content TEXT NOT NULL,
    user_question_board_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_question_board_date_update TIMESTAMP,
    user_question_board_status CHAR(1) DEFAULT 'N',
    user_question_board_count INT,
    viewcount INT default 0,
    likecount INT default 0
);

CREATE TABLE user_question_board_image(
	user_question_img_code INT PRIMARY KEY AUTO_INCREMENT,
	user_question_board_code INT,
	user_question_img_url VARCHAR(300)
);

CREATE TABLE user_question_like(
	user_question_like_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    user_question_board_code INT
);

-- User A
CREATE TABLE user_answer_board(
	user_answer_board_code INT PRIMARY KEY AUTO_INCREMENT,
    user_question_board_code INT,
    user_id VARCHAR(20),
    user_nickname VARCHAR(20),
    user_img VARCHAR(300),
    user_answer_content TEXT NOT NULL,
    user_answer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_answer_date_update TIMESTAMP,
    answer_parent_code INT
);

CREATE TABLE user_answer_choose(
	choose_code INT PRIMARY KEY AUTO_INCREMENT,
    user_question_board_code INT UNIQUE,
    user_answer_board_code INT
);

-- ======================================== [HY] NOTICE ========================================
CREATE TABLE notice_board(
	notice_board_code INT PRIMARY KEY AUTO_INCREMENT,
    notice_board_title VARCHAR(50) NOT NULL,
    notice_board_content TEXT NOT NULL,
    notice_board_regi_date DATETIME DEFAULT now(),
    user_id VARCHAR(20),
    notice_board_view_count INT DEFAULT 0
);

CREATE TABLE notice_board_comment(
	notice_comment_code INT PRIMARY KEY AUTO_INCREMENT,
    notice_board_code INT,
    user_id VARCHAR(20) NOT NULL,
    notice_comment_content TEXT NOT NULL,
    notice_comment_regi_date DATETIME DEFAULT now(),
    notice_parent_code INT DEFAULT 0,
    notice_comment_delete CHAR(1) CHECK (notice_comment_delete IN ('Y', 'N')) DEFAULT 'N'
);

CREATE TABLE notice_board_image(
	notice_image_code INT PRIMARY KEY AUTO_INCREMENT,
    notice_board_code INT,
    notice_image VARCHAR(150)
);

-- ======================================== [MJ] NOTE ========================================
CREATE TABLE note(
	note_code INT PRIMARY KEY AUTO_INCREMENT,
    note_title VARCHAR(100) NOT NULL,
    note_content TEXT,
    sender VARCHAR(20) NOT NULL,
    receiver VARCHAR(20) NOT NULL,
    note_regi_date Timestamp ,
    deleted_by_sender BOOLEAN DEFAULT false,
    deleted_by_receiver BOOLEAN DEFAULT false,
    star_sender BOOLEAN DEFAULT false,
    star_receiver BOOLEAN DEFAULT false
);

CREATE TABLE note_file(
	note_file_code INT PRIMARY KEY AUTO_INCREMENT,
    note_code INT NOT NULL,
    note_file_url VARCHAR(300)
);

-- ======================================== [J] AD ========================================
Create table ad_recommend_logic(
	logic_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    category_code INT,
    input_value DECIMAL(5,2) DEFAULT 0,
    total_score DECIMAL(8, 5) DEFAULT 0
);

-- ======================================== [Dana] NOTIFICATION ========================================
CREATE TABLE notification(
	noti_code INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    noti_date TIMESTAMP DEFAULT current_timestamp,
    noti_category VARCHAR(100) NOT NULL
);


ALTER TABLE lost_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade;
ALTER TABLE lost_board_image ADD FOREIGN KEY (lost_board_code) REFERENCES lost_board(lost_board_code)ON DELETE CASCADE;
ALTER TABLE lost_board_comment ADD FOREIGN KEY(user_id) REFERENCES user(user_id) on delete cascade;
ALTER TABLE lost_board_comment ADD FOREIGN KEY(lost_board_code) REFERENCES lost_board(lost_board_code) on delete cascade;

ALTER TABLE adoption_board ADD FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE;
ALTER TABLE adoption_board_image ADD foreign key(adoption_board_code) references adoption_board(adoption_board_code) ON DELETE CASCADE;
ALTER TABLE adoption_board_comment ADD FOREIGN KEY(user_id) REFERENCES user(user_id) on delete cascade;
ALTER TABLE adoption_board_comment ADD FOREIGN KEY(adoption_board_code) REFERENCES adoption_board(adoption_board_code) on delete cascade;

ALTER TABLE sitter_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE sitter_board ADD FOREIGN KEY (animal_category_code) REFERENCES animal_category(animal_category_code);
ALTER TABLE sitter_board_image ADD FOREIGN KEY (sitter_board_code) REFERENCES sitter_board(sitter_board_code) ON DELETE CASCADE;
ALTER TABLE sitter_board_comment ADD FOREIGN KEY (sitter_board_code) REFERENCES sitter_board(sitter_board_code) ON DELETE CASCADE;
ALTER TABLE sitter_board_comment ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE neighbor_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE neighbor_board ADD FOREIGN KEY (animal_category_code) REFERENCES animal_category(animal_category_code);
ALTER TABLE neighbor_board_image ADD FOREIGN KEY (neighbor_board_code) REFERENCES neighbor_board(neighbor_board_code) ON DELETE CASCADE;
ALTER TABLE neighbor_board_comment ADD FOREIGN KEY (neighbor_board_code) REFERENCES neighbor_board(neighbor_board_code) ON DELETE CASCADE;
ALTER TABLE neighbor_board_comment ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE product_board ADD FOREIGN KEY (animal_category_code) REFERENCES animal_category(animal_category_code);
ALTER TABLE product_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE product_board_image ADD FOREIGN KEY (product_board_code) REFERENCES product_board(product_board_code) ON DELETE CASCADE;
ALTER TABLE product_board_comment ADD FOREIGN KEY (product_board_code) REFERENCES product_board(product_board_code) ON DELETE CASCADE;
ALTER TABLE product_board_comment ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE product_board_recommend ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE product_board_recommend ADD FOREIGN KEY (product_board_code) REFERENCES product_board(product_board_code) ON DELETE CASCADE;
ALTER TABLE product_board_bookmark ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE product_board_bookmark ADD FOREIGN KEY (product_board_code) REFERENCES product_board(product_board_code) ON DELETE CASCADE;

ALTER TABLE oneday_class_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE oneday_class_board_main_image ADD FOREIGN KEY (odc_code) REFERENCES oneday_class_board(odc_code);

ALTER TABLE animal_board ADD FOREIGN KEY (animal_category_code) REFERENCES animal_category(animal_category_code);
ALTER TABLE animal_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE animal_board_favorite ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE animal_board_favorite ADD FOREIGN KEY (animal_board_code) REFERENCES animal_board(animal_board_code) ON DELETE CASCADE;
ALTER TABLE animal_board_image ADD FOREIGN KEY (animal_board_code) REFERENCES animal_board(animal_board_code) ON DELETE CASCADE;
ALTER TABLE animal_board_comment ADD FOREIGN KEY (animal_board_code) REFERENCES animal_board(animal_board_code) ON DELETE CASCADE;
ALTER TABLE animal_board_comment ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE notice_board ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE notice_board_comment ADD FOREIGN KEY (notice_board_code) REFERENCES notice_board(notice_board_code) ON DELETE CASCADE;
ALTER TABLE notice_board_comment ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE notice_board_image ADD FOREIGN KEY (notice_board_code) REFERENCES notice_board(notice_board_code) ON DELETE CASCADE;

ALTER TABLE note_file ADD FOREIGN KEY (note_code) REFERENCES note(note_code) ON DELETE CASCADE;

ALTER TABLE ad_recommend_logic ADD FOREIGN KEY (category_code) REFERENCES animal_category(animal_category_code);
ALTER TABLE ad_recommend_logic add foreign key (user_id) references user (user_id);

ALTER TABLE notification ADD FOREIGN KEY (user_id) REFERENCES user(user_id);