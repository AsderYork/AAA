INSERT INTO USERS_DATA(Login, Username, HashedPassword, Salt)
VALUES('jdoe','John Doe','67afaf691096743173e2bc3a5b8266c9', 'Salt' );
INSERT INTO USERS_DATA(Login, Username, HashedPassword, Salt)
VALUES('jrow','Jane Row','28f5f0da487d14868db9fe2b00042739', 'AnotherSalt');


INSERT INTO PERMISSION_SDATA(ID, Subresource, Permission)
VALUES(1,'a',1);
INSERT INTO PERMISSION_SDATA(ID, Subresource, Permission)
VALUES(1,'a.b',2);
INSERT INTO PERMISSION_SDATA(ID, Subresource, Permission)
VALUES(2,'a.b.c',4);
INSERT INTO PERMISSION_SDATA(ID, Subresource, Permission)
VALUES(1,'a.bc',4);