-- data.sql
INSERT INTO Broths (Name, IsVegan) VALUES ('콩국물', TRUE);
INSERT INTO Broths (Name, IsVegan) VALUES ('멸치국물', FALSE);
INSERT INTO Broths (Name, IsVegan) VALUES ('라면국물', TRUE);

INSERT INTO Noodles (Name, BrothId) VALUES ('우동', 2);
INSERT INTO Noodles (Name, BrothId) VALUES ('쌀국수', 1);
INSERT INTO Noodles (Name, BrothId) VALUES ('라면', 3);

INSERT INTO Garnishes (Name, NoodleId) VALUES ('달걀', 1);
INSERT INTO Garnishes (Name, NoodleId) VALUES ('초고추장', 3);