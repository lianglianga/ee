/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : bookmanager
Source Server Version : 110200
Source Host           : 127.0.0.1:1521
Source Schema         : SCOTT

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2017-12-29 16:17:09
*/


-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE "SCOTT"."book";
CREATE TABLE "SCOTT"."book" (
"book_id" NUMBER NOT NULL ,
"book_name" VARCHAR2(255 BYTE) NULL ,
"author" VARCHAR2(255 BYTE) NULL ,
"image_url" VARCHAR2(255 BYTE) NULL ,
"location" VARCHAR2(255 BYTE) NULL ,
"type_id" NUMBER NULL ,
"isbn" VARCHAR2(255 BYTE) NULL ,
"state" NUMBER NULL ,
"book_date" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO "SCOTT"."book" VALUES ('1', '福大師大1111', 'dsad', 'dsad', 'dsad', '1', '3123123', '1', TO_DATE('2017-12-29 16:12:43', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE "SCOTT"."group";
CREATE TABLE "SCOTT"."group" (
"user_group_id" NUMBER NOT NULL ,
"user_group_info" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "SCOTT"."group" IS '用户组实体';

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO "SCOTT"."group" VALUES ('0', '超级管理员');
INSERT INTO "SCOTT"."group" VALUES ('1', '普通管理员');
INSERT INTO "SCOTT"."group" VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for LOGS
-- ----------------------------
DROP TABLE "SCOTT"."LOGS";
CREATE TABLE "SCOTT"."LOGS" (
"LOG_ID" NUMBER(10) NOT NULL ,
"LOG_TABLE" VARCHAR2(10 BYTE) NOT NULL ,
"LOG_DML" VARCHAR2(10 BYTE) NULL ,
"LOG_KEY_ID" NUMBER(10) NULL ,
"LOG_DATE" DATE NULL ,
"LOG_USER" VARCHAR2(15 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of LOGS
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE "SCOTT"."order";
CREATE TABLE "SCOTT"."order" (
"order_id" NUMBER NOT NULL ,
"borrower_id" NUMBER NULL ,
"book_id" NUMBER NULL ,
"status" NUMBER NULL ,
"create_date" DATE NULL ,
"update_date" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE "SCOTT"."state";
CREATE TABLE "SCOTT"."state" (
"state_id" NUMBER NOT NULL ,
"state_name" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO "SCOTT"."state" VALUES ('0', '可借');
INSERT INTO "SCOTT"."state" VALUES ('1', '已借出');
INSERT INTO "SCOTT"."state" VALUES ('2', '已销毁');
INSERT INTO "SCOTT"."state" VALUES ('3', '预约中');
INSERT INTO "SCOTT"."state" VALUES ('4', '还书中');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE "SCOTT"."type";
CREATE TABLE "SCOTT"."type" (
"book_type_id" NUMBER NOT NULL ,
"type_name" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO "SCOTT"."type" VALUES ('1', '计算机');
INSERT INTO "SCOTT"."type" VALUES ('2', '文学');
INSERT INTO "SCOTT"."type" VALUES ('3', '语言');
INSERT INTO "SCOTT"."type" VALUES ('4', '数学');
INSERT INTO "SCOTT"."type" VALUES ('5', '科普');
INSERT INTO "SCOTT"."type" VALUES ('6', '考研');
INSERT INTO "SCOTT"."type" VALUES ('7', '艺术');
INSERT INTO "SCOTT"."type" VALUES ('8', '历史');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE "SCOTT"."user";
CREATE TABLE "SCOTT"."user" (
"user_id" NUMBER NOT NULL ,
"username" VARCHAR2(255 BYTE) NULL ,
"password" VARCHAR2(255 BYTE) NULL ,
"email" VARCHAR2(255 BYTE) NULL ,
"nickname" VARCHAR2(255 BYTE) NULL ,
"avatar_image" VARCHAR2(255 BYTE) NULL ,
"points" NUMBER NULL ,
"group" NUMBER NULL ,
"user_state" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "SCOTT"."user" VALUES ('1', 'ZHOULIN', '123456', 'ZHOULIN', '1513335169254_blob', '50', '0', '0', '1111');
INSERT INTO "SCOTT"."user" VALUES ('2', 'admin1', '123456', '849723885@qq.com', 'yjy1', '1513335169254_blob', '23', '1', '0');
INSERT INTO "SCOTT"."user" VALUES ('3', 'admin23', '123456', '849723885@qq.com', '姚家杨', '1513335169254_blob', '32', '2', '2');
INSERT INTO "SCOTT"."user" VALUES ('4', 'admin3aaaaaa', '123456', '849723885@qq.com', 'das', '1513692468750_blob', '50', '2', '1');
INSERT INTO "SCOTT"."user" VALUES ('5', 'admin44', '123456', '849723885@qq.com', 'zczxc', '1513335169254_blob', '50', '1', '0');

-- ----------------------------
-- Table structure for user_state
-- ----------------------------
DROP TABLE "SCOTT"."user_state";
CREATE TABLE "SCOTT"."user_state" (
"user_state_id" NUMBER NOT NULL ,
"user_state_info" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of user_state
-- ----------------------------

-- ----------------------------
-- View structure for BOOK_VIEW
-- ----------------------------
CREATE OR REPLACE FORCE VIEW "SCOTT"."BOOK_VIEW" AS 
select "book_id","book_name","isbn","author"
from "book";

-- ----------------------------
-- View structure for USER_ORDER_VIEW
-- ----------------------------
CREATE OR REPLACE FORCE VIEW "SCOTT"."USER_ORDER_VIEW" AS 
select "username","order_id","create_date"
from "user","order"
where "user"."user_id" = "order"."borrower_id";

-- ----------------------------
-- Procedure structure for user_email_proc
-- ----------------------------
CREATE OR REPLACE procedure "SCOTT"."user_email_proc"
as
"email" varchar2(255)
begin 
select "email" into "email" from "user"
end user_email_proc
/

-- ----------------------------
-- Procedure structure for USER_EMAIL_PROC
-- ----------------------------
CREATE OR REPLACE procedure "SCOTT"."USER_EMAIL_PROC"
is
email varchar2(255)
begin 
select "email" into "email" from "user"
end user_email_proc
/

-- ----------------------------
-- Function structure for BOOK_COUNT
-- ----------------------------
CREATE OR REPLACE function "SCOTT"."BOOK_COUNT"()
return number is
count_book number;
begin 
 select count(*) into count_book from "book";
	return(count_book)
end book_count;
/

-- ----------------------------
-- Sequence structure for LOGS_ID_SQU
-- ----------------------------
DROP SEQUENCE "SCOTT"."LOGS_ID_SQU";
CREATE SEQUENCE "SCOTT"."LOGS_ID_SQU"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999
 START WITH 1
 NOCACHE ;

-- ----------------------------
-- Sequence structure for SEQ_BOOK
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_BOOK";
CREATE SEQUENCE "SCOTT"."SEQ_BOOK"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_GROUP
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_GROUP";
CREATE SEQUENCE "SCOTT"."SEQ_GROUP"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_ORDER
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_ORDER";
CREATE SEQUENCE "SCOTT"."SEQ_ORDER"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_STATE
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_STATE";
CREATE SEQUENCE "SCOTT"."SEQ_STATE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_TYPE
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_TYPE";
CREATE SEQUENCE "SCOTT"."SEQ_TYPE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_USER
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_USER";
CREATE SEQUENCE "SCOTT"."SEQ_USER"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_USER_STATE
-- ----------------------------
DROP SEQUENCE "SCOTT"."SEQ_USER_STATE";
CREATE SEQUENCE "SCOTT"."SEQ_USER_STATE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Indexes structure for table book
-- ----------------------------

-- ----------------------------
-- Checks structure for table book
-- ----------------------------
ALTER TABLE "SCOTT"."book" ADD CHECK ("book_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table book
-- ----------------------------
ALTER TABLE "SCOTT"."book" ADD PRIMARY KEY ("book_id");

-- ----------------------------
-- Indexes structure for table group
-- ----------------------------

-- ----------------------------
-- Checks structure for table group
-- ----------------------------
ALTER TABLE "SCOTT"."group" ADD CHECK ("user_group_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table group
-- ----------------------------
ALTER TABLE "SCOTT"."group" ADD PRIMARY KEY ("user_group_id");

-- ----------------------------
-- Indexes structure for table LOGS
-- ----------------------------

-- ----------------------------
-- Checks structure for table LOGS
-- ----------------------------
ALTER TABLE "SCOTT"."LOGS" ADD CHECK ("LOG_TABLE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table LOGS
-- ----------------------------
ALTER TABLE "SCOTT"."LOGS" ADD PRIMARY KEY ("LOG_ID");

-- ----------------------------
-- Indexes structure for table order
-- ----------------------------

-- ----------------------------
-- Checks structure for table order
-- ----------------------------
ALTER TABLE "SCOTT"."order" ADD CHECK ("order_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table order
-- ----------------------------
ALTER TABLE "SCOTT"."order" ADD PRIMARY KEY ("order_id");

-- ----------------------------
-- Indexes structure for table state
-- ----------------------------

-- ----------------------------
-- Checks structure for table state
-- ----------------------------
ALTER TABLE "SCOTT"."state" ADD CHECK ("state_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table state
-- ----------------------------
ALTER TABLE "SCOTT"."state" ADD PRIMARY KEY ("state_id");

-- ----------------------------
-- Indexes structure for table type
-- ----------------------------

-- ----------------------------
-- Checks structure for table type
-- ----------------------------
ALTER TABLE "SCOTT"."type" ADD CHECK ("book_type_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table type
-- ----------------------------
ALTER TABLE "SCOTT"."type" ADD PRIMARY KEY ("book_type_id");

-- ----------------------------
-- Indexes structure for table user
-- ----------------------------

-- ----------------------------
-- Checks structure for table user
-- ----------------------------
ALTER TABLE "SCOTT"."user" ADD CHECK ("user_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "SCOTT"."user" ADD PRIMARY KEY ("user_id");

-- ----------------------------
-- Indexes structure for table user_state
-- ----------------------------

-- ----------------------------
-- Checks structure for table user_state
-- ----------------------------
ALTER TABLE "SCOTT"."user_state" ADD CHECK ("user_state_id" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table user_state
-- ----------------------------
ALTER TABLE "SCOTT"."user_state" ADD PRIMARY KEY ("user_state_id");
