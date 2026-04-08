CREATE TABLE IF NOT EXISTS member (
    id VARCHAR(3) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO member (id, name) VALUES ('001', 'Alice');
INSERT INTO member (id, name) VALUES ('002', 'Bob');
INSERT INTO member (id, name) VALUES ('003', 'Charlie');