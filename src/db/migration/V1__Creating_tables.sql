CREATE TABLE ACCOUNTED_DATA (
  Action       VARCHAR(255),
  AccountDate  VARCHAR(255),
  UserID       INT,
  Role         VARCHAR(255),
  Path         VARCHAR(255),
  Value        INT,
  DateStart    VARCHAR(255),
  DateFinished VARCHAR(255)
);

CREATE TABLE PERMISSIONSDATA (
  ID          INT,
  Subresource VARCHAR(255),
  Permission  INT
);

CREATE TABLE USERSDATA (
  Login          VARCHAR(255) PRIMARY KEY,
  Username       VARCHAR(255),
  HashedPassword VARCHAR(255),
  Salt           VARCHAR(255),
  ID             INT AUTO_INCREMENT
);