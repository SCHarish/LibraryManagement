## Library Management Application
---
### About
<p>This is a simple library management application built in Java using spring boot. It is used to store and manage books in the library. Entities involved here are Book, Author and Tags</p>

---
### Tools

* Java 11
* In-memory h2 database
* Swagger
* Junit
* Mockito
* OpenCSV
* Hibernate
* Spring Data JPA
* Spring Boot
* Model mapper
---
### Pre-requisites

* Download or clone the source code from GitHub to the local machine

* Install JDK 11 - https://www.eclipse.org/downloads/

* Install Apache Maven - https://maven.apache.org/install.html
---
## Steps To Run The Application

* In the command line, execute <b>./mvnw clean install</b>  in the linux machine or Mac. For windows, execute the command <b>mvn clean install</b>

  <img src="https://user-images.githubusercontent.com/25337840/183427384-5accdd43-2535-4e25-801a-c1aed74519ec.png"/>
  
* Execute the command <b>java -jar target/LibraryManagementApp-0.0.1-SNAPSHOT.jar</b>
<img width="1054" alt="image" src="https://user-images.githubusercontent.com/25337840/183438251-b9a4df91-89df-41ab-be5a-4d39488fee28.png">


* Now the application is running. All the tables will be automatically created in “librarydb”. By default, it runs in port:8082. You can open up the    swagger ui from  following url: http://localhost:8082/swagger-ui.html

<img width="1332" alt="image" src="https://user-images.githubusercontent.com/25337840/183473830-a9c31d06-569d-4185-9e86-57815a80c799.png">

* You can view the tables by accessing the URL [http://locahost:8082](http://localhost:8080/h2-console/) Use the username as "sa" and password as "password" to connect.
<img width="814" alt="image" src="https://user-images.githubusercontent.com/25337840/183438806-d35b88e8-a549-43dd-9c78-83cddb5bb2b4.png">

* You can also use this postman collection to test the API https://www.postman.com/collections/89e1500bb70a8baa0694

* For the Bulk import API, use the <b>sample.csv</b> added to this repository.

---
### Note

* To save time and for easy verification, I've added the output jar(LibraryManagementApp-0.0.1-SNAPSHOT.jar) directly in this repo so that, the application can be executed directly by the command <b>java -jar LibraryManagementApp-0.0.1-SNAPSHOT.jar</b>

<p>Before adding the Book entity, Author entity needs to be added. Because, Book entity requires an Author entity(author id). Book entity has N:1 relationship with Author.</p>
