CREATE TABLE tasks (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    completed BOOLEAN NOT NULL,
    userid TEXT,
    FOREIGN KEY (userid) REFERENCES users(id) ON DELETE CASCADE,
    active BOOLEAN NOT NULL
);