##User API

### 
Author: Umah Chukwudi\
Email: dev.chukwudiumah@gmail.com\
Framework: SpringBoot\
Database: H2

### Run Steps

2. Edit logback-spring.xml LOGS   <property name="LOGS" value="C:/Users/cwilliams/LOGS" />
3. Build project and run
4. Edit .properties file to reflect local machine properties

When the application starts, it seeds initial 3 users to in memory h2 DB
---

### API Endpoints
###	  1. Get Users
##       [GET]  /api/users
**Headers**:  "Authorization": "3a5pjbqi3kdvs79"\
E.g:  curl -X GET "http://127.0.0.1:8004/api/users" -H  "accept: */*"....

** Responses**\
**HttpCode: 200**\
-200\
-500\
**Response Body**
``` 
{
    "code": 200,
    "message": "",
    "path": "api/users",
    "status": "success",
    "timestamp": "2022-04-06T15:50:24.266+00:00",
    "data": [
        {
            "birthDate": "1995-03-31",
            "email": "dev.tdsy@gmail.com",
            "id": 1,
            "lastName": "Willaims",
            "firstName": "Chukwudi"
        },
        {
            "birthDate": "1988-03-20",
            "email": "chinaza@gmail.com",
            "id": 2,
            "lastName": "John",
            "firstName": "Ebuka"
        },
        {
            "birthDate": "2001-10-14",
            "email": "dal@gmail.com",
            "id": 3,
            "lastName": "Joshua",
            "firstName": "Dalington"
        }
    ],
    "errors": {}
}
    
```


###	  2. Get User
##       [GET]  /api/users/{id}
**Headers**:  "Authorization": "3a5pjbqi3kdvs79"\
E.g:  curl -X GET "http://127.0.0.1:8004/api/users/2" -H  "accept: */*"....

** Responses**\
**HttpCode: 200**\
-200\
-500\
**Response Body**
``` 
{
    "code": 200,
    "message": "",
    "path": "api/users/2",
    "status": "success",
    "timestamp": "2022-04-06T16:07:39.858+00:00",
    "data": [
        {
            "birthDate": "1988-03-20",
            "email": "chinaza@gmail.com",
            "id": 2,
            "lastName": "John",
            "firstName": "Ebuka"
        }
    ],
    "errors": {}
}
```




**HttpCode: 500**\
**Response Body**
```
{
    "code": 404,
    "message": "Resource Not Found",
    "path": "/users/22",
    "status": "error",
    "timestamp": "2022-04-06T16:08:24.796+00:00",
    "data": [],
    "errors": {
        "id": "User with id 22 not found"
    }
}
```
###	  3. Create User
##       [POST]  /api/users
**Headers**:  "Authorization": "3a5pjbqi3kdvs79"\
**Request Body:**
```
 {
        "birthDate": "2010-03-31",
        "email": "dev.chinddedu@gmail.com",
        "firstName": "Chinedu",
        "lastName": "David"
 }
```

E.g: curl -X POST "https://localhost:44351/api/users" -H  "accept: */*" -H  "Content-Type: application/json-patch+json" -d "{\"birthDate\":\"1995-03-31\",.........}"\
** Responses**\
**HttpCode: 400**\
**Response Body**
```
{
    "code": 400,
    "message": "Invalid birth date",
    "path": "api/users",
    "status": "error",
    "timestamp": "2022-04-06T16:17:21.886+00:00",
    "data": [],
    "errors": {
        "BirthDate": "You must be 18 years and above"
    }
}
```
**HttpCode: 200**\
**Response Body**
```
{
    "code": 201,
    "message": "",
    "path": "api/users",
    "status": "success",
    "timestamp": "2022-04-06T16:19:10.426+00:00",
    "data": [
        {
            "birthDate": "1997-03-31",
            "email": "dev.chinddedu@gmail.com",
            "id": 4,
            "firstName": "Chinedu",
            "lastName": "David"
        }
    ],
    "errors": {}
}
```
etc........