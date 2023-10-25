- change username and password of your database (mysql) from resources/application-properties
- make sure your database connected to source and create table "authentication"
- comment 2 lines from "Controller" to create admin![CleanShot 2023-10-25 at 17.44.04@2x](/Users/vanlyhai/Library/Application Support/CleanShot/media/media_Kzxioimrtq/CleanShot 2023-10-25 at 17.44.04@2x.png)

- API: http://localhost:8085/api/create-user (POST) 
  Body: 

  {

  ​    "email": "admin@gmail.com",

  ​    "password": 123456

  }

- After create admin, re-comment 2 lines from "Controller", add row 

  ![CleanShot 2023-10-25 at 17.56.42@2x](/Users/vanlyhai/Library/Application Support/CleanShot/media/media_Hwf0xV41fa/CleanShot 2023-10-25 at 17.56.42@2x.png) to "app_role" table and add row

  ![CleanShot 2023-10-25 at 17.57.20@2x](/Users/vanlyhai/Library/Application Support/CleanShot/media/media_f7opWFw6EV/CleanShot 2023-10-25 at 17.57.20@2x.png) to "user_role" table (user_id is id of admin in "app_user" table)

- You can login admin to take token and create user from admin token

