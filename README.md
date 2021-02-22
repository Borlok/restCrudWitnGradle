[![Build Status](https://travis-ci.com/Borlok/restCrudWitnGradle.svg?branch=master)](https://travis-ci.com/Borlok/restCrudWitnGradle)
REST API, которое взаимодействует с файловым хранилищем AWS S3 и предоставляет возможность получать доступ к файлам и истории загрузок.

Сущности:

1.User

2.Account

3.AccountStatus

4.Event

5.File

6.FileStatus (enum ACTIVE, BANNED, DELETED)

User -> List<File> files + List<Events> + Account account

Взаимодействие с S3 должно быть реализовано с помощью AWS SDK.

Уровни доступа:

ADMIN - полный доступ к приложению

MODERATOR - добавление и удаление файлов

USER - только чтение всех данных кроме User + Account

Технологии: Java, MySQL, Spring (IoC, Data, Security), AWS SDK, Travis, Docker, JUnit, Mockito, Gradle