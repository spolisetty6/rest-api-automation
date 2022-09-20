# rest-api-automation

## Purpose

Implement a sample Automation framework from scratch to test REST APIs as per below-mentioned requirements...

1.Pick any 3 open API with multiple related endpoints

2.Write Automations tests with coverage for 2 -3 endpoints

3.The API tests should be using dynamic test data. The test data has to be dynamically derived from other endpoints( Ex: OAuth tokens)

### Test coverage
- The below listed APIs were used for testing
    - https://restful-booker.herokuapp.com/
    - https://restful-booker.herokuapp.com/booking
    - https://restful-booker.herokuapp.com/booking/{id}

    Scenarios:
    - Login to hotel booking portal and acquire authentication token.
    - Create a hotel booking from above user session and update this booking.
    - Create a hotel booking from above user session and partially update this booking.
    - Create a hotel booking from above user session and delete it.
    - Above tests covers all major API calls such as GET, POST, PUT, PATCH and DELETE.

    Test data:
    - User credentials were captured from the open API provider and used as static variables in these tests.
    - User registration details such as first/last names and check-in/out dates were generated dynamically using random data generation

    Test setup/implementation:
    - The java classes (helper) defined under the directory "src/test/java/booking.app.support" are the core units holding test methods
    - JsonHelper unit used to build/update JSON body and to generate random test data
    - AssertHelper unit used to define assert methods (this can be extended to accommodate more if required)
    - RestHelper unit is the key unit that process the APIs and get the response accordingly.


## Instructions to run

This test was developed in Intellij IDEA using Java (SDK 18), RestAssured API libraries and JUnit with Maven build.

- Install following software if not already present
- java >= 18 - [how to install Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- maven >= 3.6.x - [how to install Maven](https://maven.apache.org/install.html)
- all required libraries and references were defined under pom.xml, and they will be automatically imported by Maven once you open the project in IDE


Steps
1. Clone the project from Git to your local directory.
2. Open the project using IntelliJ IDEA
3. You can either run the tests via Maven-->test or Maven-->verify
Note: If needed, you can run the tests individually by selecting each test from 'TestHotelBookingAPI.java' and by selecting Run option

Alternative way to run tests:
1. Open a terminal window/command prompt
2. Clone this project from Git.
3. Cd to the folder to which you cloned the project
4. `mvn verify`
