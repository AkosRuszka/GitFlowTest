insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Vezeto','Peto',23,'Kilimo','nagyagyu@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Alamos','Antos',26,'Kimoi','kisagyu@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blogger(last_name,first_name,age,user_name,email_address,blog_post_counter,reg_date,last_activity_date) values ('Kerek','Perec',99,'Manua','asirba@mukodj.most',0,CURRENT_DATE(),CURRENT_DATE());
insert into blog_post(content,posted_date,title,author_id) values ('Valami sz�veg az els� Blogpostnak. Tal�n lehet egy picit hosszabb is.',CURRENT_DATE(),'Elso BlogPost c�m!',select id from blogger where user_name='Kilimo');
insert into blog_post(content,posted_date,title,author_id) values ('Egy kis sz�veg a m�sodik BlogPostnak.',CURRENT_DATE(),'M�sodik BlogPost C�me',select id from blogger where user_name='Kimoi');
insert into blog_post(content,posted_date,title,author_id) values ('Nagyj�b�l valamennyi kit�lt� sz�veg a harmadik blogpostnak.',CURRENT_DATE(),'Harmadik c�me.',select id from blogger where user_name='Manua');
insert into blog_post(content,posted_date,title,author_id) values ('Ide is valami kit�lt�s.',CURRENT_DATE(),'Negyedik C�m.',select id from blogger where user_name='Kilimo');
insert into comment(content,date,author_id,blog_id) values ('Ez egy eg�sz kir�ly megk�zel�t�se a dolgoknak',CURRENT_DATE(),select id from blogger where user_name='Kilimo',select id from blog_post where title='M�sodik BlogPost C�me');
insert into comment(content,date,author_id,blog_id) values ('Nekem mondjuk annyira nem tetszik.',CURRENT_DATE(),select id from blogger where user_name='Kimoi',select id from blog_post where title='M�sodik BlogPost C�me');
insert into comment(content,date,author_id,blog_id) values ('Els�',CURRENT_DATE(),select id from blogger where user_name='Kilimo',select id from blog_post where title='Harmadik c�me.');
insert into comment(content,date,author_id,blog_id) values ('A Microsoft eg�sz gyorsan v�ltoztatott a v�lem�ny�n, kicsit sz�nalmas.',CURRENT_DATE(),select id from blogger where user_name='Manua',select id from blog_post where title='Elso BlogPost c�m!');
insert into comment(content,date,author_id,blog_id) values ('Az els�z�s felett�bb ideges�t� dolog...Fel k�ne n�n�d!',CURRENT_DATE(),select id from blogger where user_name='Manua',select id from blog_post where title='Harmadik c�me.');
insert into comment(content,date,author_id,blog_id) values ('Ezen nem tudom mit kell csod�lkozni, de komolyan.',CURRENT_DATE(),select id from blogger where user_name='Kimoi',select id from blog_post where title='Elso BlogPost c�m!');

