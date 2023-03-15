Movie-Ticket Booking Application

This is a Spring Boot application that allows users to book movie tickets and get movie related information such as show timings, theatre locations etc.

Description

The Technology used to build this project is Java, Spring Framework, MySQL, JPA Hibernate. Implemented the functionalities related to ticket booking such as Book Ticket, Add User, Get Movie details, Get Theatres showing a particular movie, Get Movies playing in a location etc. Developed RESTful APIs to implement the applications functionalities. Moreover, I made use of JPA Persistence API to connect and interact with database using Java. Finally, The application is integrated with Swagger UI for API documentation and testing. 

This application is aimed at providing a simple and easy way to book movie tickets online and also to get movie related information.

Getting Started:

1) Knowledge on Java, Spring Framework is required. You should be able to write SQL queries.

2) Libraries: Spring Web, Spring Data JPA, MySQL Driver

Installing:

  1) Clone the repository to your local machine and import it into your IDE of choice
  2) You will need to have Java 8 or above version and Maven installed on your computer.
  3) Next, configure the application's database connection by editing the application.properties file located in the src/main/resources directory. You will need to          provide your database credentials.
  4) Once you have configured the database connection, build the application by running the following command in the terminal:mvn clean install.
  5) Once the build is successful, you can run the application by running the following command:mvn spring-boot:run The application will start up on port 8080.
 
Advise for common problems or issues:

  1) If you are facing issues while integrating Swagger UI, chances are Swagger might not be supporting Spring higher version. There are 3 steps you need to take to        overcome this issue:
  
      -> Downgrade the Spring version to 2.4.3.
                     
     -> Replace dependency mysql-connector-j with mysql-connector-java.
                     
     -> Earlier, if you are using Spring version above 2.8 and downgraded to 2.4.3, you have to change from Jakarta Persistence API to                                         javax.persistence for the management of relational data in applications using Java Platform.

  2) Otherwise, you can skip integrating Swagger UI with your Java application. As an alternative, you can test the APIs using Postman.
