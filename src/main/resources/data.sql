DROP TABLE IF EXISTS short_url;

CREATE TABLE short_url (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    long_url varchar(1024) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    expire_at TIMESTAMP DEFAULT NULL
);
