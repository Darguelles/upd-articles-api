#Operations

###Create article

| Method | Url |
|------|--------------|
| POST | http://localhost:8080/articles |

- Parameters: Article body (without id)
- Response type: Article body (with generated id)
- Response headers: Location
- Success code: Created (201)
- Errors codes: Bad request (400)