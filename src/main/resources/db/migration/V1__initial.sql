CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    is_deleted BIT(1) NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id         BIGINT NOT NULL,
    number_of_hours INT    NOT NULL,
    batch_no        INT    NOT NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id           BIGINT NOT NULL,
    number_of_mentees INT    NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    user_id BIGINT NOT NULL,
    rating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    is_deleted    BIT(1) NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    price DOUBLE NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    user_type         INT NOT NULL,
    first_name        VARCHAR(255) NULL,
    last_name         VARCHAR(255) NULL,
    rating DOUBLE NOT NULL,
    number_of_mentees INT NOT NULL,
    number_of_hours   INT NOT NULL,
    batch_no          INT NOT NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);