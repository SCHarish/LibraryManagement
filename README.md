## LibraryManagement
---
### About
This is a simple library management application build using spring boot. There are three entities Book, Author and Tags.

### Tools

* Java 11
* In-memory h2 database
* Swagger
* Spring boot
* Junit
* Mockito
* OpenCSV

## Steps To Run The Application
---

### Pre-requisites
* Download or clone the source code from GitHub to the local machine

* Install JDK 11 - https://www.eclipse.org/downloads/

* Install Apache Maven - https://maven.apache.org/install.html

<ul>
<li>Step 1: In the command line, execute ./mvnw clean install  in the linux machine or Mac</li>
  <img src="https://user-images.githubusercontent.com/25337840/183427384-5accdd43-2535-4e25-801a-c1aed74519ec.png"/>
<li>Step 2: execute the command java -jar LibraryManagementApp-0.0.1-SNAPSHOT.jar </li>
<li>Now the application is running. All the tables will be automatically created in “librarydb”. By default, it runs in port:8080. You can open up the swagger ui from  following url: http://localhost:8080/swagger-ui.html .</li>
</ul>

