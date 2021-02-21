insert into users (id) value (1);
insert into accounts (user_id, name, account_status) value (1,'Yura', 'ACTIVE');
insert into files ( user_id, file_status) value (1,'ACTIVE');
insert into events (user_id, name) value (1,'Miting');
insert into access (email,first_name,last_name,password,role,status)
 VALUES ('admin@mail.com','Admin','Adminov',
         '$2y$12$PEiyzJ0PePdlFfX3jJAwgOGG1nMdMZyEXCYWwsaozWx1C97YHfF7u',
         'ADMIN','ACTIVE'),
        ('moderator@mail.com','Moderator','Moderatorov',
         '$2y$12$sirxc28pnbeSD8crVfSHNuWHPRymc2MyJC4uJMcR96wcDWAIL7Qx.',
         'MODERATOR','ACTIVE'),
        ('user@mail.com','User','Userov',
         '$2y$12$Ud1AwtXOJ21faUqk4FSXS.mtooIIDLWYyfEb0JXkeUYiqAaC4lS8q',
         'USER','ACTIVE');