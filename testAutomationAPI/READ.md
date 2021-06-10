Objective:
This is a sample Api testing application using maven, java 8, Reset assured, TestNG to test given API end point and for the following acceptance criteria.

Acceptance Criteria:

1. Name = "Carbon credits"
2. CanRelist = true
3. The Promotions element with Name = "Gallery" has a Description that contains the text  
   "2x larger image"

Note:
according to the given endpoint response received contain no data for criteria 3 (I assume it is for a negative test, since there was no data reference for an image as mentioned in criteria 3). First and second criteria  provide test data for a positive test.

Prereqisites(technologies):
- Java SE Development kit 8
- Maven 3.6.3
- TestNG
- Rest Assured
- eclipse IDE

set environment variables
JAVA_HOME=C:\devTools\Java\jdk1.8_u_251
Maven_HOME=C:\devTools\apache-maven-3.6.3

add  C:\devTools\Java\jdk1.8_u_251\bin   and C:\devTools\apache-maven-3.6.3\bin
to PATH variable


Use of the application
download from github location
Import Maven Project to the Java IDE 

Run Test cases
using the IDE 
select the Project testAutomationAPI , right click and select Run As  Maven test or TestNG test.

using command line

go to project directory of testAutomationAPI
        enter mvn clean install or mvn test to run using Maven.
