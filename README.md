# Store Discounts

Store discounts is an app that calculates the store discounts based on the customer membership

## Tools used

- open-jdk 8
- maven
- Spring
- H2 in memory database to persist the data

## Generates
A runnable jar that will be can be used hit on the enpdoint http://localhost:8080/

## How To generate the jar
```bash
to test the app use mvn test
```

```bash
to run generate the jar use mvn compilse
```

```bash
to run the app use mvn spring-boot:run
```
change default port in application.properties file set the command to the port wanted server.port=8081

## SonarQube
SonarQube will test the code quality and report bugs or defects in the code it is running as a local docker container 

```bash
mvn sonar:sonar -Dsonar.projectKey=com.store.discounts -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d7c4a871f46591a44eae70c3e48401a59b9f0d5c
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

