# Upday Articles Store API

Welcome to the Upday articles API. This project allows you to create, store and update your created articles and perform custom search based on author's name, date creation or some predefined keyword.


## Api Overview

This API is based on RESTFUL and returns results in JSON format. Response objects will always have an HTTP status code indicating the operation status. The different json formats returned by this Api can be founded [here](/doc/format/json-formats.md).
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

## Commonly asked questions

**Q:** I'm getting a Bad Requests status each time i want to perform a search between two dates. What is happening?

**A:** A Bad Request status indicates that the parameter format is worng. Remember that the Date format accepted for this API is `DD-MM-YYYY`.

**Q:** What will i get if i didn't specify any search criteria?

**A:** You will get an empty list.

**Q:** The stored data persists even after restart the server?

**A:** Currently this api store data on memory level, so all data will be loose if you stop/restart the server.

**Q:** Can i search articles by more than one keyword?

**A:** Currently the API only supports search operations just by one keyword at time.
