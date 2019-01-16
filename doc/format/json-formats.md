# JSON formats for objects used in this API

### Article

| Field | Type | Description |
|---------|--------------|----------|
| id | Long | Identifier to retrieve the object directly |
| header | String | Article header, minimal length is 1 |
| description | String | Short article description, minimal length is 1 |
| text | String | Article main content, minimal length is 1 |
| publishDate | String | Creation date. Accepted format is DD-MM-YYYY |
| authors | Author[] | Author list. Each one has name and genre info. |
| keywords | String[] | Keywords for specific search. Recommend to store lowercase keywords. |

##### JSON Representation

````
{
    "id": 1,
    "header": "No se lo digas a Nadie",
    "description": "First Jaime Baily book, wrotten in 1998",
    "text": "Once upon a time.....",
    "publishDate": "16-07-1998",
    "authors": [
        {
            "name": "Jaime Baily",
            "genre": "Male"
        }
    ],
    "keywords": [
        "drama",
        "best seller"
    ]
}

````