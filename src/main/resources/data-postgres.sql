
INSERT INTO master_industry(ID,INDUSTRY_CODE,INDUSTRY_DESCRIPTION,IS_ACTIVE) VALUES
(1,'RS01','INVESTMENT',1),
(2,'RS02','SPORT',0),
(3,'RS03','INSURANCE',0),
(4,'RS04','HOSPITALITY & TOURISM',1),
(5,'RS05','TRAVEL AGENTS',1),
(6,'RS06','INFORMATION TECHNOLOGY',1),
(7,'RS07','TELECOMMUNICATION',1),
(8,'RS08','LEGAL SERVICES',0),
(9,'RS09','MEDIA & COMMUNICATION',1),
(10,'RS10','AGRICULTURE AND FISHING',0) ON CONFLICT DO NOTHING;

INSERT INTO md_state (STATE_ID , TIN, STATE_CODE, DESCRIPTION)
VALUES 
(1,'37','AD','Andhra Pradesh'),
(2,'12','AR','Arunachal Pradesh'),
(3,'18','AS','Assam'),
(4,'10','BR','Bihar'),
(5,'22','CG','Chhattisgarh'),
(6,'30','GA','Goa'),
(7,'24','GJ','Gujarat'),
(8,'06','HR','Haryana'),
(9,'02','HP','Himachal Pradesh'),
(10,'01','JK','Jammu and Kashmir'),
(11,'20','JH','Jharkhand'),
(12,'29','KA','Karnataka'),
(13,'32','KL','Kerala'),
(14,'23','MP','Madhya Pradesh'),
(15,'27','MH','Maharashtra'),
(16,'14','MN','Manipur'),
(17,'17','ML','Meghalaya'),
(18,'15','MZ','Mizoram'),
(19,'13','NL','Nagaland'),
(20,'21','OD','Odisha'),
(21,'03','PB','Punjab'),
(22,'08','RJ','Rajasthan'),
(23,'11','SK','Sikkim'),
(24,'33','TN','Tamil Nadu'),
(25,'36','TS','Telangana'),
(26,'16','TR','Tripura'),
(27,'09','UP','Uttar Pradesh'),
(28,'05','UK','Uttarakhand'),
(29,'19','WB','West Bengal'),
(30,'35','AN','Andaman and Nicobar Islands'),
(31,'04','CH','Chandigarh'),
(32,'26','DN','Dadra and Nagar Haveli'),
(33,'25','DD','Daman and Diu'),
(34,'07','DL','Delhi'),
(35,'97','OT','Ladakh'),
(36,'31','LD','Lakshadweep'),
(37,'34','PY','Puducherry') ON CONFLICT DO NOTHING;