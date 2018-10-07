insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Vezeto','Peto',23,'Kilimo','nagyagyu@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Alamos','Antos',26,'Kimoi','kisagyu@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Kerek','Perec',99,'Manua','asirba@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blog_post(content,posted_date,title,author_id) values ('Valami szöveg az elsõ Blogpostnak. Talán lehet egy picit hosszabb is.',CURRENT_DATE(),'Elso BlogPost cím!',select id from blogger where user_name='Kilimo');
insert into blog_post(content,posted_date,title,author_id) values ('Egy kis szöveg a második BlogPostnak.',CURRENT_DATE(),'Második BlogPost Címe',select id from blogger where user_name='Kimoi');
insert into blog_post(content,posted_date,title,author_id) values ('Nagyjából valamennyi kitöltõ szöveg a harmadik blogpostnak.',CURRENT_DATE(),'Harmadik címe.',select id from blogger where user_name='Manua');
insert into blog_post(content,posted_date,title,author_id) values ('Ide is valami kitöltés.',CURRENT_DATE(),'Negyedik Cím.',select id from blogger where user_name='Kilimo');
insert into comment(content,date,author_id,blog_id) values ('Ez egy egész király megközelítése a dolgoknak',CURRENT_DATE(),select id from blogger where user_name='Kilimo',select id from blog_post where title='Második BlogPost Címe');
insert into comment(content,date,author_id,blog_id) values ('Nekem mondjuk annyira nem tetszik.',CURRENT_DATE(),select id from blogger where user_name='Kimoi',select id from blog_post where title='Második BlogPost Címe');
insert into comment(content,date,author_id,blog_id) values ('Elsõ',CURRENT_DATE(),select id from blogger where user_name='Kilimo',select id from blog_post where title='Harmadik címe.');
insert into comment(content,date,author_id,blog_id) values ('A Microsoft egész gyorsan változtatott a véleményén, kicsit szánalmas.',CURRENT_DATE(),select id from blogger where user_name='Manua',select id from blog_post where title='Elso BlogPost cím!');
insert into comment(content,date,author_id,blog_id) values ('Az elsõzés felettébb idegesítõ dolog...Fel kéne nõnöd!',CURRENT_DATE(),select id from blogger where user_name='Manua',select id from blog_post where title='Harmadik címe.');
insert into comment(content,date,author_id,blog_id) values ('Ezen nem tudom mit kell csodálkozni, de komolyan.',CURRENT_DATE(),select id from blogger where user_name='Kimoi',select id from blog_post where title='Elso BlogPost cím!');

