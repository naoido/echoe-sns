DROP TABLE IF EXISTS echoes;
CREATE TABLE echoes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(30) NOT NULL,
    echo TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE INDEX IDX_ECHOES ON ECHOES(created_at);