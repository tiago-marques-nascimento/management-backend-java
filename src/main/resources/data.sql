DROP TABLE IF EXISTS User_Role;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    Id uniqueidentifier NOT NULL,
    Name varchar(100) NOT NULL,
    Password varchar(100) NOT NULL,
    PRIMARY KEY (Id)
);

CREATE TABLE Role (
    Id uniqueidentifier NOT NULL,
    Name varchar(100) NOT NULL,
    PRIMARY KEY (Id)
);

CREATE TABLE User_Role (
    Id uniqueidentifier NOT NULL,
    User_Id uniqueidentifier NOT NULL,
    Role_Id uniqueidentifier NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (User_Id) REFERENCES User(Id),
    FOREIGN KEY (Role_Id) REFERENCES Role(Id)
);

INSERT INTO User VALUES ('cf894144-07a3-494e-b636-aa28e2fa9074', 'admin', '$2y$12$o/u74MrDk662izNDGyK3X.BCWbRgJubyrFWciDoO3px/WSgUIZjMu');

INSERT INTO Role VALUES ('aa894144-01a3-494e-b636-aa28e2fa9074', 'admin');
INSERT INTO Role VALUES ('1f894144-01a3-494e-b636-aa28e2fa9071', 'standard');

INSERT INTO User_Role VALUES ('aa114144-07a3-49ae-b636-aa28e1fa9074', (SELECT Id FROM User WHERE Name = 'admin'), (SELECT Id FROM Role WHERE Name = 'admin'));