# Soft Shop API Service

This service is a white-label solution to the existing retailer companies/hotels.

## Setting up this project

Ensure Java 1.8 or above, Maven, MySQL v5.7 or above, and Git are installed in your environment.

Clone this repository:

```bash
git clone https://github.com/samsnz/shop-api-service
```

In MySQL, create a database called `soft_shop_4`. If you want to use a different database schema name, you can change it in `application-development.properties` - `spring.datasource.url=jdbc:mysql://localhost:3306/soft_shop_4`. That also applies to your database location and port.

Now run the project with the following command:

```bash
mvn spring-boot:run
```

**Note**: You might as well use any IDE of your preference to run this project.

That's it. You are all set now. The app is running on port 8081. You can change this port in `application-development.properties` if you wish to. `server.port=8081`

## Accessing the API documentation

Navigate to this link in your browser: `http://localhost:8081/api/v1/api-docs/`
