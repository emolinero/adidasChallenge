insert into newsletters (ID,NAME,DESCRIPTION) values (1,'Shirts','Shirts Campaign');
insert into newsletters (ID,NAME,DESCRIPTION) values (2,'Shoes','Shoes Campaign');
insert into newsletters (ID,NAME,DESCRIPTION) values (3,'Jackets','Jackets Campaign');
insert into newsletters (ID,NAME,DESCRIPTION) values (4,'Bottoms','Bottoms Campaign');
insert into newsletters (ID,NAME,DESCRIPTION) values (5,'Accesories','Accesories Campaign');

-- Create s default user, that allow make writes operations
-- password decrypt "123456t"
INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES ( 1, 'user', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.');
