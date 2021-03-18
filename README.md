## Notification MicroService

This is the last of the 3 MicroServices for the Reloadly Code Challenge.

It is responsible for sending out notifications to our customers.

The Account and Transaction MicroServices both leverage on this MicroService to send out
notifications for Account Registration and Completed Transactions.

The MicroService expose a Rest Endpoint which the clients call to send out notifications.
The notifications are sent out Asynchronously to improve performance, and the calling MicroService
gets an acknowledgement for the request sent.

## Running the project locally
To Run the project 

1. Ensure spring.profiles.active=dev is set
2. Simply click on the run button in your IDE or run the Java command

- Open your browser and go to "http://localhost:8084/swagger-ui.html" to access the 
swagger doc from where you can test the endpoints.


## Executing the Unit Tests
To execute the unit tests simply run "mvn clean package" on the terminal and allow 
it to execute or you can navigate to the test package and execute the tests from there.

## Test Coverage
Jacoco is used for test coverage.

To see the test coverage, run "mvn clean package". When it's done packaging, navigate 
to the target folder, inside the target folder, navigate to the "site" folder, inside
the site folder, open the jacoco folder you will see an index.html file. Open the html
page in your browser to see the test coverage.


## Deployment
This MicroService is deployed to Heroku.

Use the below url to access the swagger ui on Heroku for your testing pleasure.

https://reloadly-notification-services.herokuapp.com/swagger-ui.html


Please reach out to me for any question or clarification (Daniel Unogwu).