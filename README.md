# School Registration API

This is a school registration API designed to handle students and courses CRUD as well as registration logic and Reports. (This API is just for demonstration purposes)

This project was made:

- Assuming you already have a list of students
- Assuming you already have a list of courses
- A student can register to multiple courses
- A course can have multiple students enrolled in it.
- A course has 50 students maximum
- A student can register to 5 course maximum

Technology used:

- Java jdk-11
- Spring Boot 2.6.2
- Spring Data JPA
- Gradle
- JUnit
- Mockito
- Lombok
- MySQL
- JasperReport
- Flyway
- Swagger-ui

## Installation

Make sure to have docker up in your machine and run the following command to start the DB:

```
docker-compose up
```
Now we need to run the School Registration API with gradle with the following command:

```
./gradlew bootRun
```
You could also build the app with,

```
./gradlew clean build
```
The app will be running on port ``8080``.

If issues with docker and the MySQL db arise, you could uncomment the h2 datasource in ``application.yml`` and ``runtimeOnly 'com.h2database:h2`` dependency in ``build.gradle`` to make the switch to an in memory database and it should work as well.

## OpenAPI 3

Once the app is up and running you can check its respective api-docs json file at ``http://localhost:8080/v3/api-docs/`` [Api-docs](http://localhost:8080/v3/api-docs/). as well as the Swagger-ui client for easy usage at ``http://localhost:8080/swagger-ui/index.html`` [Swagger-ui](http://localhost:8080/swagger-ui/index.html). This will make it easier for making requests, check payloads and download reports.

### Request Example

This is simple request to get a list of students

```curl --location --request GET 'http://localhost:8080/student/3'```

#### Response

HTTP code: ``200``

Response body:

``` 
{
    "id": 3,
    "firstName": "Jaime",
    "lastName": "Rojas",
    "email": "jaime.rojas@test.io",
    "enrolledCourses": [
        {
            "description": "CSS 101",
            "mnemonic": "CSS101"
        }
    ],
    "createdAt": "2022-01-10T03:05:43"
}
```

### Reports

In order to download any report from their given endpoint I recommend using the help of the swagger-ui client or Postman which will make it easier. If Postman is used please remember to use the ``Send and Download`` button instead of just ``Send``, this way you will download the response. For swagger-ui, a link with the file will appear in the response section.

Happy playing!
