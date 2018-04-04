# Корпоративная система управления проектами

### Функциональность мобильного приложения 

```
Роли:
    ? - любой неавторизованный пользователь
    any - любой авторизованный пользователь
    member - любой участник проекта, включает в себя возможности any
    manager - любой менеджер проекта, включает в себя возможности member
```

* Пользователи
    * Аутентификация по логину и паролю `?`
    * Просмотр списка пользователей `any`
    * Просмотр информации о любом пользователе `any`
    * Редактирование информации о своем пользователе `any`
    * Просмотр списка должностей (при редактировании собственной информации) `any`
* Проекты
    * Просмотр списка проектов `any`
    * Просмотр списка проектов любого пользователя `any`
    * Просмотр базовой информации о проекте `any`
    * Просмотр участников проекта `any`
    * Просмотр истории действий в проекте `member`
    * Назначение участников проекта (кроме менеджеров) `manager`
    * Изменение ролей участников проекта (кроме менеджеров) `manager`
    * Редактирование базовой информациии о проекте `manager`
* Стадии проекта
    * Просмотр списка стадий проекта своего пользователя `any`
    * Просмотр стадий проекта и назначенных участников `member`
    * Назначение участников на стадии проекта `manager`
    * Создание, редактирование и удаление стадий проекта `manager`
    * Просмотр списка шаблонов стадий проекта (при создании новой стадии) `manager`
* Поставки
    * Создание поставок в проекте `member`
    * Редактирование и удаление собственных поставок в проекте `member`
    * Редактирование и удаление любых поставок в проекте `manager`
* Сообщения
    * Отправка сообщения любому пользователю `any`
    * Просмотр списка полученных сообщений `any`

### Веб-интерфейс - указанный выше функционал + следующий:

```
    admin - администратор системы, включает в себя возможности manager

    В мобильной версии admin получает возможности manager для всех проектов; указанная ниже функциональность в мобильной версии недоступна:
```

* Пользователи
    * Создание и удаление пользователей `admin`
    * Редактирование информации о любом пользователе `admin`
    * Просмотр и редактирование списка должностей в системе `admin`
* Проекты
    * Создание и удаление проектов `admin`
    * Назначение участников проекта (включая менеджеров) `admin`
    * Изменение ролей участников проекта (включая менеджеров) `admin`
    * Создание, редактирование и удаление типов проекта `admin`
* История проекта
    * Редактирование истории проекта `admin`


# REST API

## POST /login

Логин в систему

##### Параметры запроса

* `login=string`
* `password=string`

```json
{
  "login": "admin",
  "password": "dreamteam"
}
```

##### Успешный запрос

* *Удачный логин* Code: `200` 
  ```json
  {
    "success": true,
    "user": {
      "id": 1,
      "login": "admin",
      "firstName": "Ivan",
      "lastName": "Ivanov",
      "middleName": "Ivanovich",
      "isAdmin": true
    }
  }
  ```
* *Неверный логин или пароль* Code: `200`
  ```json
  {
    "success": false
  }
  ```

##### Ошибки

* *Логин/пароль отсутствует или пустой* Code: `400`
* *Внутренняя ошибка приложения (скорее всего БД)* Code: `500`

## POST /logout

Выход из системы

##### Параметры запроса

Нет

##### Успешный запрос

* Code: `200` 
  ```json
  {}
  ```

##### Ошибки

* *Внутренняя ошибка приложения (скорее всего БД)* Code: `500`

## GET /message

Список полученных сообщений для текущего пользователя

##### Успешный запрос

* Code: `200` 
  ```json
  [
    {
      "id": 1,
      "content": "Test message",
      "creationDate": "10.01.2018 08:09:10",
      "unread": false,
      "author": {
        "id": 1,
        "login": "admin",
        "firstName": "Ivan",
        "lastName": "Ivanov",
        "middleName": "Ivanovich",
        "isAdmin": true
      }
    },
    {
      "id": 2,
      "content": "More message",
      "creationDate": "02.05.2018 20:02:23",
      "unread": true,
      "author": {
        "id": 2,
        "login": "ppp",
        "firstName": "Petrov",
        "lastName": "Petr",
        "middleName": "Petrovich",
        "isAdmin": false
      }
    }
  ]
  ```

##### Ошибки

* *Пользователь не авторизован* Code: `403`
* *Внутренняя ошибка приложения (скорее всего БД)* Code: `500`