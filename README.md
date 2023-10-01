# Local development 
## Dependencies:
+ "Positive and creative mood"
+ Postgres or Docker
+ Java

## Build script
+ run FrontBaconend with the location of the frontend dir as a parameter

# Chris P. Bacon Academy Endpoints

## `/api/chrispbacon/auth/authenticate` - Authenticate User

- **HTTP Method**: POST
- **Summary**: Authenticate a user
- **Request Body**:
    - Content Type: application/json
    - Required: Yes
- **Responses**:
    - HTTP 200 OK: Successful authentication
        - Content Type: application/json

## `/api/chrispbacon/auth/refresh-token` - Refresh Authentication Token

- **HTTP Method**: POST
- **Summary**: Refresh authentication token
- **Responses**:
    - HTTP 200 OK: Token refresh successful

## `/api/chrispbacon/auth/register` - Register User

- **HTTP Method**: POST
- **Summary**: Register a new user
- **Request Body**:
    - Content Type: application/json
    - Required: Yes
- **Responses**:
    - HTTP 200 OK: User registration successful
        - Content Type: application/json

## `/api/chrispbacon/auth/validate-token` - Validate Authentication Token

- **HTTP Method**: POST
- **Summary**: Validate an authentication token
- **Parameters**:
    - `token` (query parameter)
        - Type: string
        - Required: Yes
- **Responses**:
    - HTTP 200 OK: Token validation successful
        - Content Type: application/json

