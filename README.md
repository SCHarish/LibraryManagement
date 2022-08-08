## LibraryManagement
---
### About
* This is a simple library management application built using spring boot. There are three entities Book, Author and Tags.
---
### Tools

* Java 11
* In-memory h2 database
* Swagger
* Spring boot
* Junit
* Mockito
* OpenCSV
---
### Pre-requisites

* Download or clone the source code from GitHub to the local machine

* Install JDK 11 - https://www.eclipse.org/downloads/

* Install Apache Maven - https://maven.apache.org/install.html
---
## Steps To Run The Application

* In the command line, execute <b>./mvnw clean install</b>  in the linux machine or Mac</li>
  <img src="https://user-images.githubusercontent.com/25337840/183427384-5accdd43-2535-4e25-801a-c1aed74519ec.png"/>
* Execute the command java -jar LibraryManagementApp-0.0.1-SNAPSHOT.jar </li>
* Now the application is running. All the tables will be automatically created in “librarydb”. By default, it runs in port:8080. You can open up the    swagger ui from  following url: http://localhost:8080/swagger-ui.html
* You can also use the postman collection to test the API
* For import API, use the <b>sample.csv</b> added in this repository.
