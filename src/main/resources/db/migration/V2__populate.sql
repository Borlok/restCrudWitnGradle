insert into users (email,password,role) VALUES ('admin@mail.com',
                                                       '$2y$12$3VWUBSw5/7fOP04lpPJGH.c02HV4R64i4HNrSP/s2.dumq.F8Gj86',
                                                       'ADMIN'),
                                                      ('moderator@mail.com',
                                                       '$2y$12$sZI0AhL.rT1zHG7UAVg9luvzTjbCdUKn4bzIzFekWBDTvV7juckvC',
                                                       'MODERATOR'),
                                                      ('user@mail.com',
                                                       '$2y$12$YSdCajai3ieIwBTG6gqEtuAD8txK6KaesmoDWsZBo3pMExNpMUDS6',
                                                       'USER');
insert into accounts (user_id, name, account_status) values (1,'Admin', 'ACTIVE'),
                                                            (2,'Moderator', 'ACTIVE'),
                                                            (3,'User', 'ACTIVE');
