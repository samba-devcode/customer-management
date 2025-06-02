# Customer Management Application - maintains customers in the applications

# Steps to setup in local
## Prerequisites:
 - Java 17+  
 - Spring boot 3+  
 - Spring framework 6+  
 - Maven 3.8+  
 - any ide (eg: intellij)  

## Setting up and testing application in local
### Setting up JAVA_HOME and MAVEN_HOME
#### On Windows
1. find installation directories of Java and Maven(eg: C:\Program Files\Java and C:\Program Files\Maven)
2. Set environment variables in the Edit environment variables from Start Menu(Start Menu->Search 'Edit Environment variables'->click 'Edit the system environment variables')
   Click New = JAVA_HOME=java home directory and MAVEN_HOME=maven home directory
3.Open new command prompt
  type java -version and mvn -v
4. Both should print java version with extra meta data 

### Local setup  
1. install and setup git in your computer 
2. download source code using 'git clone https://github.com/samba-devcode/customer-management' from command prompt or download zip from this repo url - https://github.com/samba-devcode/customer-management and extract to your favorite directory.
3. Import the project to your favorite ide (eg: intellij here)
4. goto terminal in the intellij and do 'mvn clean install' and build should get success.  
5. run this application by right clicking on CustomerManagementApplication.java file and clicking on Run <class-name>.main(). or enter this - mvn spring-boot:run in the intellij terminal
6. check the console logs whether server started on 8080(default port)

### Run unit tests
run junits using 'mvn test'  

### Configurations
H2 database main configurations in the application.properties
```  
spring.datasource.url=jdbc:h2:mem:mydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```  
Spring configurations
```  
server.port=8080
spring.application.name=<service-name>
```  
### Swagger url
Access swagger ui to test endpoints http://localhost:8080/swagger-ui/index.html  

### testing APIs  
1. GET /customers/{id} - get customer by id - pass customer id in the path parameter
2. PUT /customers/{id} - update custome by id - pass customer id in the path parameter whichever needs to update
3. DELETE /customers/{id} - delete customer by id - pass customer id in the path parameter whichever needs to delete
4. GET /customers - get customer by name or email - get customers using email or name in the query parameter
5. POST /customers create customer - create customer with uuid as unique key
     
Note: Sample requests/responses are in the swagger documentation      

## business use cases  
1. create customer using POST /customers endpoint and note uuid, naame and email
2. use GET /customers/{id} to get customer using uuid. In the response has tier
3. use GET /customers to get customer by name or email
4. update customer data using PUT /customers/{id} endpoint
5. finally DELETE /customers/{id} for deleting customer using id


