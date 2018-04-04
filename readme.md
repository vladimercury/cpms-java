## POST /login

Login into CPMS

##### Params

* `login=string`
* `password=string`

Sample:
```json
{
  "login": "admin",
  "password": "dreamteam"
}
```

##### Success response

* *Login success* Code: `200` 
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
* *Invalid credentials* Code: `200`
  ```json
  {
    "success": false
  }
  ```

##### Error response

* *Missed or empty login/password* Code: `400`
* *Database or other fail* Code: `500`

## POST /logout

Log out from CPMS

##### Params

None

##### Success response

* Code: `200` 
  ```json
  {}
  ```

##### Error response

* *Database or other fail* Code: `500`

## GET /message

Received messages for current user

##### Success response

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

##### Error response

* *Unauthorized user* Code: `403`
* *Database or other fail* Code: `500`