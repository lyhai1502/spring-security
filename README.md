- Change username and password of your database (mysql) from resources/application-properties.

- Make sure your database connected to project.

- Create table "authentication"

- Run project.

- Using "LOGIN API" to login admin:

    - API: http://localhost:8085/api/login (POST) 
  
      Body: 
  
      {
  
      ​    "email": "admin@gmail.com",
  
      ​    "password": 123456
  
      }

- After login, take admin token.

- Create user from admin token.

http://localhost:8085/api/create-user (POST)

Body:

{

  "email": "user@gmail.com",

  "password": 123456

  "roleName": "Role_User"

  }
