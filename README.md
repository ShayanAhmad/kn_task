# [kn_task] Contacts Service

## Feature:
1. Return contact list,
2. Searchable,
3. Paginated.

## Initial Setup:
0. This project requires `Java11`.
1. Please make sure `docker-compose` is installed in your system.
2. Please refer to the directory `mysql-docker-compose` and within that run: ```docker-compose up```.
3. In case you do not have maven installed, 
please use the inbuilt maven by referring to `./mvnw` (from Linux/Mac) 
or `./mvnw.cmd` (from Windows).
4. Please make sure the `MySQL Docker` is running with `docker ps` before you build this project with maven.

## Building the project:
1. To build the project, please run: `./mvnw clean install` from the root
 of the project where `pom.xml` exists.

## Running the project:
There are two ways to do this:
1. Run `mvn spring-boot:run` inside `web` folder, OR
2. Navigate to `ContactsApplication.java` and run the `main` method as Java application.
3. After application startup, please refer to [http://localhost:8080/contacts](http://localhost:8080/contacts).
