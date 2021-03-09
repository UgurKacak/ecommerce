# Sample Spring Boot Rest API

✨It is a sample Spring Boot Rest Api application and is not yet ready for prod environments.✨

- [Open Jdk 14] - https://openjdk.java.net/projects/jdk/14/
- [Spring Boot 2.4.3] - https://spring.io/projects/spring-boot
- [PostgreSQL] - https://www.postgresql.org/
- [Lombok] - https://projectlombok.org/
- [SonarLint] - https://www.sonarlint.org/
- [Swagger] - https://swagger.io/
- [Apache Commons Codec] - https://commons.apache.org/proper/commons-codec/
- [Jjwt] - https://github.com/jwtk/jjwt
- [Mockito] - https://site.mockito.org/
- [Assertj] - https://mvnrepository.com/artifact/org.assertj/assertj-core

## Features

- User register and login.
- Sample JWT authentication. Only login and register endpoints can be accessed without auth token.
- CRUD operations for User, Product and Category entities.

## Installation
You can import tables and sample data to the postgresql using the ecommerce_db.sql file.

```sh
psql -U postgres --file ecommerce_db.sql
```

Maven and PostgreSQL server needed.
You can make database and log detailing over the application.properties file.
```sh
git clone https://github.com/UgurKacak/ecommerce.git
./ecommerce
mvn install package
mvn spring-boot:run
```
Api: http://localhost:8080/
Swagger Ui : http://localhost:8080/swagger-ui/index.html#/
ECommerceAPI.postman_collection.json Postman collection can be used with Postman.
## To-do
- Complete Unit Test
- Complete Integration Test
- Code Refactoring.
## License

MIT

**Free Software, Hell Yeah!**