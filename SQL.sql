DROP TABLE IF EXISTS AndroidProjects_Users;
CREATE TABLE IF NOT EXISTS AndroidProjects_Users ( 
            Id INTEGER PRIMARY KEY AUTO_INCREMENT, 
            FirstName VARCHAR(500), 
            MiddleName VARCHAR(500), 
            LastName VARCHAR(500), 
            EmailAddress VARCHAR(500) NOT NULL, 
            SkypeId VARCHAR(500), 
            WatsAppNo VARCHAR(500), 
            AddressLine1 VARCHAR(1000), 
            AddressLine2 VARCHAR(1000), 
            City VARCHAR(500), 
            State VARCHAR(500), 
            Country VARCHAR(500), 
            UnReadOnly INTEGER, 
            SyncDuration INTEGER, 
            CreatedAt DATETIME, 
            UpdatedAt DATETIME, 
			IsLocked int default 0 not null,
            CONSTRAINT uq_email UNIQUE (EmailAddress)
            );

DROP TABLE IF EXISTS AndroidProjects_Projects;
CREATE TABLE IF NOT EXISTS  AndroidProjects_Projects  ( 
            Id INTEGER PRIMARY KEY AUTO_INCREMENT, 
            Story TEXT, 
            FilePaths TEXT,
            EstimatedHrs VARCHAR(500), 
            EstimateCost VARCHAR(500), 
            DeliveryDate DATETIME, 
            CreatedAt DATETIME, 
            UpdatedAt DATETIME, 
            UserId INTEGER, 
            ParentId INTEGER
            );
DROP TABLE IF EXISTS AndroidProjects_Requests;
CREATE TABLE IF NOT EXISTS AndroidProjects_Requests
	(Id INTEGER Primary Key AUTO_INCREMENT,
	Request TEXT);
