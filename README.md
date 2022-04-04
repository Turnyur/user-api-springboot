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
[
    {
        "birthDate": "1995-03-31",
        "id": 1,
        "lastName": "Umah",
        "email": "dev.chukwudiumah@gmail.com",
        "firstName": "Chukwudi"
    },
    {
        "birthDate": "1988-03-20",
        "id": 2,
        "lastName": "John",
        "email": "chinaza@gmail.com",
        "firstName": "Ebuka"
    },
    {
        "birthDate": "2001-10-14",
        "id": 3,
        "lastName": "Joshua",
        "email": "dal@gmail.com",
        "firstName": "Dalington"
    }
]
    
```


###	  2. Get User
##       [GET]  /api/users/{id}
**Headers**:  "Authorization": "3a5pjbqi3kdvs79"\
E.g:  curl -X GET "http://127.0.0.1:8004/api/users/1" -H  "accept: */*"....

** Responses**\
**HttpCode: 200**\
-200\
-500\
**Response Body**
``` 
{
    "birthDate": "1995-03-31",
    "id": 1,
    "lastName": "Umah",
    "email": "dev.chukwudiumah@gmail.com",
    "firstName": "Chukwudi"
}
    
```




**HttpCode: 500**\
**Response Body**
```
{
    "timestamp": "2022-04-01T08:06:12.265+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "'User' not found",
    "path": "/api/users/5"
}
```
###	  3. Create User
##       [POST]  /api/users
**Headers**:  "Authorization": "3a5pjbqi3kdvs79"\
**Request Body:**
```
 {
        "birthDate": "1995-03-31",
        "email": "cynthia@gmail.com",
        "firstName": "Chi",
        "lastName": "Cynthia"
    }
```

E.g: curl -X POST "https://localhost:44351/api/users" -H  "accept: */*" -H  "Content-Type: application/json-patch+json" -d "{\"birthDate\":\"1995-03-31\",.........}"\
** Responses**\
**HttpCode: 200**\
**Response Body**
```
{
    "birthDate": "1995-03-31",
    "id": 4,
    "lastName": "Cynthia",
    "email": "DeeOneDay@gmail.com",
    "firstName": "Chi"
}
```
**HttpCode: 500**\
**Response Body**
```
{
    "timestamp": "2022-04-01T08:10:52.327+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error Field: 'BirthDate'. Reason: 'You must be 18 years and above'",
    "path": "/api/users"
}
```
etc........