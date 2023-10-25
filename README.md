- change username and password of your database (mysql) from resources/application-properties

- make sure your database connected to source and create table "authentication"

- comment 2 lines: 

  ```
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('admin')")
  ```

  from "Controller" to create admin

- API: http://localhost:8085/api/create-user (POST) 
  Body: 

  {

  ​    "email": "admin@gmail.com",

  ​    "password": 123456

  }

- After create admin, re-comment 2 lines from "Controller", add 2 row (admin and user) to "app_role" table;

  and add row (1,1,1) to "user_role" table (role_id is id of ADMIN in "app_role" table, user_id is id of ADMIN in "app_user" table)

- You can login admin to take token and create user from admin token

