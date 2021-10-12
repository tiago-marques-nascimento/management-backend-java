# User Management Service

# Description
The User Management Service is capable of storing simple user/role data on a memory relational database, as well as
performing oauth JWT authentication, much like an AD service.

# Running instructions
Please run the following command on the terminal to setup the environment variables:
#. .envrc

To run the application, just execute the following command:
#./gradlew bootRun

To test the application, just execute the following command:
#./gradlew test

# Key Features

* Java Development Kit 11
* Embedded Spring Boot
* Integration with Swagger v2 (https://localhost:8080/api/swagger-ui.html)
* HTTPS secured requests with self signed certificate
* JWT authentication
* BCrypt password encoding
* LogBack implementation with 4 rolling files covering INFO, WARN, ERROR, BUSINESS ERROR, DEBUG log levels
* H2 memory database with JPA integration
* CORS feature

# The Software Architecture

* A Monolith software architecture was chosen due to the simplicity of the project and the tight deadline,
however, changing it to a Microservice architecture in order to improve scalability and software maintenance
is a trivial task. Demanding the development of a Proxy Gateway to route incoming requests and handle
authentication and authorization.

* All requests/responses follow the REST conventions and are in the Json format type as well.

* The application consists of the following layers:

    * The Application - It consists of the Controllers (responsible for receiving incoming requests and handling
    responses), the Facades (responsible for communication with the Domain Services and mapping of the ViewModels
    into Entities) and the ViewModels (POJOs deserialized from Json data from incoming requests)

    * The Domain - It consists of the Entities (JPA mapped classes providing Database intercommunication by means
    of the Repository), the Repositories (Spring interface which provide JPA queries able to perform database
    operations), the Services (Transactional units capable of handling business logic and validations) and Validators
    (pretty straightforward, they are responsible for carring out validations necessary for data persistence)

    * The Infrastructure - It consists of the Datasource Configuration (which defines the URL and Port of the server
    as well database provider, in this case the H2 Database), the Environment Configuration (sensitive information
    is stored as environment variable as opposed to property files so information is hidden from the project
    source code and, therefore, protected), the Application Beans (Automatically injected classes by the Spring
    Data Context, these includes the Facades, Services, Validators, Repositories and so on), the JWT configuration
    (Authentication and Authorization filters are added to the WebAdapter so that the application is capable of
    handling JWT generations and validations), Mapper Configuration (following the Mapper software pattern,
    all conversions from ViewModel to Entitied and vice-versa are added as mappings) and the WebMVC Configuration
    (which enabled Swagger documentation)

    * Tests - In order to ensure the quality and consistency of the code, unit tests were created covering the
    Service and Facade application layers, as these hold the more complex and sensitive logic. In order to test
    differente cenarios, Mockito was chosen as to manipulate the behaviour of the methods to satisfy testing
    scenarios. Furthermore, the Factory pattern was chosen to hold building instructions of the tested objects.
    Finally, the production code was fully audited using the Checkstyle tool.

# Implemented test cases

* Facade
    * List all roles
    * List all users
    * Find user by name
    
* Service
    * List all roles
    * List all users
    * Find user by name
    * Save user with validation error
    * Save user with success