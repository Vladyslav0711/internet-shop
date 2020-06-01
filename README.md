# Internet-shop
Simple version of online-store

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Author](#author)

<hr/>

## <a name = "general-info">General info</a>
This is a simple implementation of online store.

**Project functionality:**
- For all users:
  * Registration
  * Login 
  * View all products
- For users who have role "USER": 
    * Add products to shopping cart
    * View user shopping cart
    * Remove products from shoping cart
    * Complete orders
    * View user's orders view and information about them
- For users who have role "ADMIN":
    * Add and remove products from the database
    * Remove users from system
    * View a list of all registered users

<hr/>

## <a name = "technologies">Technologies</a>
  * Java 11
  * Maven 4.0.0
  * javax.servlet-api 4.0.1
  * jstl 1.2
  * log4j 1.2.17
  * mysql-connector-java 8.0.20
  * maven-checkstyle-plugin 3.1.1
  * bootstrap

<hr/>

## <a name = "setup">Setup</a>
#### To run this project you need to install:
  * [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  * [Tomcat](https://tomcat.apache.org/download-90.cgi)
  * [MySql 8](https://www.mysql.com/downloads/) (optional)
  
<hr/>
  
1. Add this project to your IDE as Maven project. 
2. Add Java SDK 11 in project structure. 
3. Configure Tomcat:
   - Add artifact
   - Add Java SDK 11
4. Change a path to your Log file in src/main/resources/log4j2.xml on line 4.
5. Use src/main/resources/init_db.sql file to create all tables in database.
6. Change username and password to match with MySQL in src/main/java/org/example/internetshop/utill/ConnectionUtil.java 
7. Run the project.

>To use test data - go to .../{context_path}/inject <br/>
After that you will get admin with login = "admin", password = "admin", <br/>
and user with login = "user", password = "user". 

## <a name = "author">Author</a>
[Vladyslav Bezsikernykh](https://github.com/Handsome0711)


    
    
    