# Operations

### Create article

| Method | Path |
|------|--------------|
| POST | /articles |

- Body parameters: Article body(without id)
- Response type: Article body (with generated id)
- Response headers: Location
- Success code: Created (201)
- Errors codes: Bad request (400)


### Update article

| Method | Path |
|------|--------------|
| PUT | /articles/{id} |

- Path parameters: Article Id
- Body parameters: Article body
- Response type: Article body
- Response headers: Location
- Success code: Ok (200)
- Errors codes: Bad request (400), Not Found (404)


### Get single article

| Method | Path |
|------|--------------|
| GET | /articles/{id} |

- Path parameters: Article Id
- Response type: Article body
- Response headers: None
- Success code: Ok (200)
- Errors codes: Not Found (404)


### Search article (By keyword / author)

| Method | Path |
|------|--------------|
| GET | /articles/search |

- Query parameters: 

| Parameter | Type |
|------|--------------|
| keyword | String |
| author | String |

- Response type: Article list
- Response headers: None
- Success code: Ok (200)
- Errors codes: Not Found (404)


### Search article (Between dates)

| Method | Path |
|------|--------------|
| GET | /articles/created |

- Query parameters: 

| Parameter | Type |
|------|--------------|
| after | String in format DD-MM-YYYY|
| before | String in format DD-MM-YYYY|

- Response type: Article list
- Response headers: None
- Success code: Ok (200)
- Errors codes: Not Found (404)


## Example search requests URL

- http://localhost:8080/articles/search?keyword=drama&author=jaime will return all drama articles written by Jaime.
- http://localhost:8080/articles/search?keyword=drama will return all drama articles.
- http://localhost:8080/articles/search?author=jaime will return all articles written by Jaime.
- http://localhost:8080/articles/created?after=10-11-1995&before=01-01-2017 will return all written articles between these two dates.
- http://localhost:8080/articles/created?after=10-11-1995 will return all written articles between after date and today.
- http://localhost:8080/articles/created?before=10-11-1995 will return all written articles before the specified date.