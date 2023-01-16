USE eksamen24;

INSERT INTO developer (id, name, email, phone, billing_pr_hour) VALUES (1,'Frederik Andersen','FrederikAndersen@gmail.com','28205532',150);
INSERT INTO developer (id, name, email, phone, billing_pr_hour) VALUES (2,'Albert Rasmussen','AlbertRasmussen@gmail.com','81119072',200);
INSERT INTO developer (id, name, email, phone, billing_pr_hour) VALUES (3,'Nicolai Lorenzen','NicolaiLorenzen@gmail.com','50436748',180);
INSERT INTO developer (id, name, email, phone, billing_pr_hour) VALUES (4,'Amanda Schmidt','AmandaSchmidtn@gmail.com','23115567',240);
INSERT INTO developer (id, name, email, phone, billing_pr_hour) VALUES (5,'Liva Henriksen','LivaHenriksen@gmail.com','20421670',200);

INSERT INTO project (id, name, description) VALUES (1,'Dating App','Dating app til android og IOS');
INSERT INTO project (id, name, description) VALUES (2,'Social medie platform','En hjemmeside og app');
INSERT INTO project (id, name, description) VALUES (3,'Review app','En app hvor man kan anmelde restauranter');

INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (1,'4','1','4 timer arbejdet på userstory 1',1,1);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (2,'2','2','2 timer arbejdet på userstory 2',2,1);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (3,'3','3','3 timer arbejdet på userstory 3',1,1);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (4,'4','4','4 timer arbejdet på userstory 4',2,1);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (5,'3','1','3 timer arbejdet på userstory 1',4,2);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (6,'6','2','6 timer arbejdet på userstory 2',3,2);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (7,'2','3','2 timer arbejdet på userstory 3',4,2);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (8,'4','4','4 timer arbejdet på userstory 4',3,2);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (9,'1','5','1 timer arbejdet på userstory 5',4,2);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (10,'3','1','3 timer arbejdet på userstory 1',5,3);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (11,'2','2','2 timer arbejdet på userstory 2',5,3);
INSERT INTO project_hours (id, hours_spent, user_story, description, developer_id, project_id) VALUES (12,'5','3','5 timer arbejdet på userstory 3',5,3);