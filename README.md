# customer-management

# Steps to setup in local
# Prerequisites:
Java 17+  
Spring boot 3+  
Spring framework 6+  
Maven 3.8+  
any ide (eg: intellij)  
Add JAVA_HOME and MAVEN_HOME to their home directories as environment variables  
Add bin's of java and maven to PATH environment variable  
# verify
1. java --version  
2. mvn -v  
# Local setup  
1. install and setup git in local or download zip from this repo  
2. goto project directory and do 'mvn clean install'  
3. run this application from intellij or from command / terminal(mvn spring-boot:run from command line)  
# verify  APIs
1. access http://localhost:8080/swagger-ui/index.html from the browser. Following are the endpoints.
   GET /customers/{id} - get customer by id  
   PUT /customers/{id} - update custome by id  
   DELETE /customers/{id} - delete customer by id  
   GET /customers - get customer by name or email  
   POST /customers create customer  
   Sample requests/responses are in the swagger documentation  
3. access h2 database console using http://localhost:8080/h2
   h2 database details  
   url - jdbc:h2:mem:mydb  
   driver class name - org.h2.Driver  
   username - sa  
   password -     

# business use cases  
1. create customer using POST /customers endpoint and note uuid, aname and email
2. use GET /customers/{id} to get customer using uuid. In the response has tier
3. use GET /customers to get customer by name or email
4. update customer data using PUT /customers/{id} endpoint
5. finally DELETE /customers/{id} for deleting customer using id


