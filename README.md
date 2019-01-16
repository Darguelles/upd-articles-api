# Upday Articles Store API

Welcome to the Upday articles API. This project allows you to create, store, delete and update your created articles and perform custom search based on author's name, date creation or some predefined keyword.


## Api Overview

This API is based on RESTFUL and returns results in JSON format. Response objects will always have an HTTP status code indicating the operation status. The different json object formats used by this Api can be founded [here](/doc/format/json-formats.md).
If you need more technical detail about how this api is developed, don't hesitate to visit the `doc/arch` folder in the application root and read the architecture design records.


## Supported operations

This API has available the following operations:

- Create an article
- Update an article
- Search by author name, some keyword or both.
- Search for articles created between two dates.

You can look this [detailed guide](/doc/format/operations.md) to get more detail about how to invoke this methods and start making requests.

## Result types

This API returns 3 types of responses:

- Single objects for creation requests.
- Headers-only for updating requests or errors.
- Lists for search requests.


## Deploy

This application can be deployed running the following commands:

- Using the gradle plugin: `gradle bootRun`
- Using the generated artifact: `java -jar target/upd-articles-api-0.0.1-SNAPSHOT.jar`
- Using Docker: Execute the integrated .sh file -> `sh ./docker-deploy.sh`


## Execute integration tests

- Using the gradle wrapper `./gradlew intTest`

## Code smell and Reports

You can find all the detected warning / errors of the code smell scan performed by Findbugs and Checkstyle in the following directory:


`./build/reports/`

Reports are generated in HTML format to better readability, keep in mind that this reports will be generated / updated each time you perform the build task.

Note: If you are performing a build using docker, you wont be able to see those reports unless you enter to the WORKDIR route and search for the reports in the container.


## Commonly asked questions

**Q:** I'm getting a Bad Requests status each time i want to perform a search between two dates. What is happening?

**A:** A Bad Request status indicates that the parameter format is worng. Remember that the Date format accepted for this API is `DD-MM-YYYY`.

**Q:** What will i get if i didn't specify any search criteria?

**A:** You will get an empty list.

**Q:** The stored data persists even after restart the server?

**A:** Currently this api store data on memory level, so all data will be loose if you stop/restart the server.

**Q:** Can i search articles by more than one keyword?

**A:** Currently the API only supports search operations just by one keyword at time.
