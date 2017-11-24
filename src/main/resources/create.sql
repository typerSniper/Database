CREATE SEQUENCE company_id_sequence START 1;
CREATE SEQUENCE job_id_sequence START 1;

CREATE TABLE department(
	did			VARCHAR(20),
	name		VARCHAR(20) not null,
	PRIMARY KEY (did)
);


CREATE TABLE program(
	pid			VARCHAR(20),
	name		VARCHAR(20) not null,
	PRIMARY KEY (pid)
);


CREATE TABLE password(
	id 			VARCHAR(20) not null,
	password	VARCHAR(20) not null,
	role		VARCHAR(20) not null,
	PRIMARY KEY (id)
);


CREATE TYPE address AS (state VARCHAR(15), city VARCHAR(20), pin VARCHAR(6), locality VARCHAR(30), country VARCHAR(15));
CREATE TYPE details AS (university VARCHAR(30), institute VARCHAR(30), year VARCHAR(20), cpi VARCHAR(20));
CREATE TABLE student(
	sid					VARCHAR(20),
	name					VARCHAR(200) not null,
	did					VARCHAR(20),
	pid					VARCHAR(20),
	CPI					VARCHAR(20) not null,
	stage					VARCHAR(20),
	PRIMARY KEY (sid),
	FOREIGN KEY (did) REFERENCES department(did) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (pid) REFERENCES program(pid) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (sid) REFERENCES password(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE studentDetails(
	sid				VARCHAR(20),
	univemail			VARCHAR(30),
	peremail			VARCHAR(30),
	dob				DATE,
	sex				VARCHAR(20),
	category			VARCHAR(20),
	nationality 			VARCHAR(15),
	hosteladdress			VARCHAR(15),
	contact1			VARCHAR(15),
	contact2			VARCHAR(15),
	skypeid				VARCHAR(20),
	homeaddress			address,
	detail12th			details,
	detail10th			details,
	others				details,
	PRIMARY KEY (sid),
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resume(
	rtype		VARCHAR(20),
	sid			VARCHAR(20) not null,
	resume		BYTEA,
	proofs		BYTEA,
	PRIMARY KEY (rtype, sid),
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resume_deadline(
	deadline 	DATE not null,
	PRIMARY KEY (deadline)
);

CREATE TABLE ic(
	ic_id		VARCHAR(20),
	name		VARCHAR(20) not null,
	phone_number VARCHAR(30) default null,
	PRIMARY KEY (ic_id),
	FOREIGN KEY (ic_id) REFERENCES password(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE company(
	cid			VARCHAR(20),
	name 		VARCHAR(20) not null,
	contact     VARCHAR(20),
	email       VARCHAR(20),
	rep			VARCHAR(30),
	stage       VARCHAR(20),
	PRIMARY KEY (cid)
);

CREATE TABLE ic_student(
	ic_id		VARCHAR(20) not null,
	sid			VARCHAR(20) not null,
	PRIMARY KEY (ic_id, sid),
	FOREIGN KEY (ic_id) REFERENCES ic(ic_id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE ic_company(
	ic_id		VARCHAR(20) not null,
	cid			VARCHAR(20) not null,
	PRIMARY KEY (ic_id, cid),
	FOREIGN KEY (ic_id) REFERENCES ic(ic_id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (cid) REFERENCES company(cid) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE jobs(
	jid			VARCHAR(20),
	cid			VARCHAR(20) not null,
	jname		VARCHAR(100),
	salary		VARCHAR(20),
	location	VARCHAR(30),
	description  VARCHAR(300),
	stage		VARCHAR(4),
	company_deadline 	DATE,
	jaf_deadline		DATE,
	PRIMARY KEY (jid, cid),
	FOREIGN KEY (cid) REFERENCES company(cid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE eligibility(
	jid			VARCHAR(20) not null,
	cid			VARCHAR(20) not null,
	cpicutoff 	VARCHAR(20),
	deptid		VARCHAR(20),
	programid	VARCHAR(20),
	PRIMARY KEY (jid, cid, deptid, programid),
	FOREIGN KEY (cid, jid) REFERENCES jobs(cid, jid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE student_jaf (
    sid VARCHAR(20),
    jid VARCHAR(20),
);