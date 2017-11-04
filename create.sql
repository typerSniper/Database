CREATE TABLE department(
	did			VARCHAR(10),
	name		VARCHAR(20) not null,
	PRIMARY KEY (did)
);


CREATE TABLE program(
	pid			VARCHAR(10),
	name		VARCHAR(20) not null,
	PRIMARY KEY (pid)
);


CREATE TABLE password(
	id 			VARCHAR(10) not null,
	password	VARCHAR(10) not null,
	role		VARCHAR(20) not null,
	PRIMARY KEY (id)
);


CREATE TYPE address AS (state VARCHAR(15), city VARCHAR(20), pin VARCHAR(6), locality VARCHAR(30), country VARCHAR(15));
CREATE TYPE details AS (university VARCHAR(30), institute VARCHAR(30), year VARCHAR(5), cpi VARCHAR(5));
CREATE TABLE student(
	sid					VARCHAR(10),
	name					VARCHAR(100) not null,
	did					VARCHAR(10),
	pid					VARCHAR(10),
	CPI					VARCHAR(5) not null,
	stage					VARCHAR(5),
	PRIMARY KEY (sid),
	FOREIGN KEY (did) REFERENCES department(did) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (pid) REFERENCES program(pid) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (sid) REFERENCES password(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE studentDetails(
	sid				VARCHAR(10),	
	univemail			VARCHAR(30),
	peremail			VARCHAR(30),
	dob				VARCHAR(10),
	sex				VARCHAR(3),
	category			VARCHAR(10),
	nationality 			VARCHAR(15),
	hosteladdress			VARCHAR(15),
	contact1			VARCHAR(15),
	contact2			VARCHAR(15),
	skypeid				VARCHAR(20),
	homeaddress			address,
	collegedetails			details,
	detail12th			details,
	detail10th			details,
	others				details,
	PRIMARY KEY (sid),
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resume(
	rtype		VARCHAR(10),
	sid			VARCHAR(10) not null,
	resume		BYTEA,
	proofs		BYTEA,
	PRIMARY KEY (rtype, sid),
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE company(
	cid			VARCHAR(10),
	name 		VARCHAR(20) not null,
	budget		VARCHAR(10),
	location	VARCHAR(20),
	PRIMARY KEY (cid)
);

CREATE TABLE ic(
	ic_id		VARCHAR(10),
	name		VARCHAR(20) not null,
	PRIMARY KEY (ic_id),
	FOREIGN KEY (ic_id) REFERENCES password(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE ic_student(
	ic_id		VARCHAR(10) not null,
	sid			VARCHAR(10) not null,
	PRIMARY KEY (ic_id, sid),
	FOREIGN KEY (ic_id) REFERENCES ic(ic_id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE jobs(
	jid			VARCHAR(10),
	cid			VARCHAR(10) not null,
	jname		VARCHAR(20),
	salary		VARCHAR(10),
	location	VARCHAR(30),
	stage		VARCHAR(4),
	PRIMARY KEY (jid, cid),
	FOREIGN KEY (cid) REFERENCES company(cid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE eligibility(
	jid			VARCHAR(10) not null,
	cid			VARCHAR(10) not null,
	cpicutoff 	VARCHAR(5),
	deptid		VARCHAR(10),
	programid	VARCHAR(10),
	PRIMARY KEY (jid, cid, deptid, programid),
	FOREIGN KEY (cid, jid) REFERENCES jobs(cid, jid) ON DELETE CASCADE ON UPDATE CASCADE
);
