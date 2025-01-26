# Directa24 - Movie App

## Description
This is a movie app built with Springboot. It provides various endpoints to query information about directors and the movies they have directed. The service logic is decoupled to allow future expansion of queries and new functionalities.

## Features
- **Main Endpoint**:
  - `GET /api/directors`: Allows querying information about directors and the movies they have directed. This endpoint requires a **request parameter** `threshold` (an integer) to filter directors based on the number of movies they have directed. For example, if you set the `threshold` parameter to `5`, it will return directors who have directed more than 5 movies.
  
  **Request Parameter**:
  - `threshold`: An integer value used to filter directors based on the number of movies they have directed.

  - `GET /api/by-year`: An additional endpoint that allows querying movies from a specific year. This endpoint requires a **request parameter** `year` (an integer) to return movies released in that particular year.
  
  **Request Parameter**:
  - `year`: An integer value used to filter movies by the release year.

- **Decoupled Services**: The service logic is designed to be easily extendable, allowing the addition of more endpoints or functionalities in the future without impacting the current codebase.

- **Swagger UI**: Swagger has been integrated to provide a graphical interface for exploring available endpoints. You can access the Swagger UI to view the interactive API documentation.
  
  **Swagger UI URL**: `/swagger-ui.html`

- **Unit Tests**: Unit tests have been added using JUnit and Mockito to ensure the proper functioning of the services.

## Improvements Not Implemented
- **User Authentication**: A user authentication system was planned to protect the endpoints, but due to time constraints, it was not implemented. This functionality could be easily added in the future using Spring Security or another authentication and authorization tool.
  
- **Endpoint Expansion**: While the `GET /api/by-year` endpoint has been created, the service logic was designed to be decoupled, which would allow additional queries, such as filtering by genre, rating, or searching by actor, to be added without major difficulty.

## Running the Project

1. Clone this repository:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project folder:

    ```bash
    cd directa24
    ```

3. Run the project:

    ```bash
    ./mvnw spring-boot:run
    ```

4. Make request using the endpoints:

    ```
    http://localhost:8080/api/directors?threshold=4
    ```
     ```
   http://localhost:8080/api/by-year?year=2011
    ```

5. Access Swagger UI to explore the endpoints:

    ```
    http://localhost:8080/swagger-ui.html
    ```

## Technologies Used
- **Spring Boot**: The main framework for building web applications in Java.
- **Springdoc OpenAPI**: For API documentation generation and integration with Swagger UI.
- **JUnit**: A unit testing framework to ensure code quality.
- **Mockito**: A framework for creating mock objects for unit testing.


Thank you for your interest in my project :)
