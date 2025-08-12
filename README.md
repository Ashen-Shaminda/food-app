# Foods - Spring Boot Food Delivery Application

A modern food delivery application built with Spring Boot that allows users to browse food items, add them to cart, and place orders with secure payment processing.

## Features

- **User Authentication**: Secure registration and login with JWT token-based authentication
- **Food Management**: Browse, search, and view detailed information about food items
- **Shopping Cart**: Add, remove, and manage food items in your cart
- **Order Processing**: Place orders with delivery information
- **Payment Integration**: Secure payment processing with Stripe
- **Image Storage**: Food images stored in AWS S3

## Technologies Used

- **Backend Framework**: Spring Boot 3.4.6
- **Database**: MongoDB
- **Security**: Spring Security with JWT authentication
- **Payment Processing**: Stripe API
- **Cloud Storage**: AWS S3
- **Build Tool**: Maven
- **Java Version**: Java 21
- **Other Libraries**:
  - Lombok for reducing boilerplate code
  - MapStruct for object mapping
  - Spring Data MongoDB for database operations

## Prerequisites

- Java 21 or higher
- MongoDB
- AWS Account (for S3 storage)
- Stripe Account (for payment processing)

## Setup and Installation

1. Clone the repository
   ```
   git clone <repository-url>
   cd foods
   ```

2. Configure environment variables
   - AWS_ACCESS_KEY: Your AWS access key
   - AWS_SECRET_KEY: Your AWS secret key
   - STRIPE_PUBLIC_KEY: Your Stripe public key
   - STRIPE_SECRET_KEY: Your Stripe secret key

3. Configure MongoDB
   - Ensure MongoDB is running on localhost:27017 or update the connection string in application.properties

4. Build and run the application
   ```
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. The application will be available at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/register` - Register a new user
- `POST /api/login` - Authenticate and get JWT token

### Food Management
- `GET /api/foods` - Get all food items
- `GET /api/foods/{id}` - Get food item by ID
- `POST /api/foods` - Add a new food item (requires authentication)
- `DELETE /api/foods/{id}` - Delete a food item (requires authentication)

### Cart Management
- `GET /api/cart` - Get current user's cart
- `POST /api/cart` - Add item to cart
- `POST /api/cart/remove` - Remove item from cart
- `DELETE /api/cart` - Clear cart

### Order Management
- `POST /api/orders/create` - Create a new order with payment

## Configuration

The application can be configured through the `application.properties` file:

## Project Structure

The project follows a standard Spring Boot structure:

- `controllers`: REST API endpoints
- `services`: Business logic
- `repositories`: Data access layer
- `domain`: Data models (entities and DTOs)
- `config`: Application configuration
- `filters`: Security filters
- `exceptions`: Exception handling
- `util`: Utility classes

## Security

The application uses Spring Security with JWT for authentication. All endpoints except for registration and login require authentication.

## Payment Processing

The application integrates with Stripe for payment processing. When an order is created, a Stripe checkout session is created and the user is redirected to the Stripe checkout page.

## Future Improvements

- Implement order tracking
- Add email notifications
- Add unit and integration tests
