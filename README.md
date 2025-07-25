# Services Scheduling API - Service Appointment Management System

A complete RESTful API for service scheduling, built with Java and Spring Boot. This project simulates a real-world system where service providers can configure their schedules and clients can book and pay for appointments.

## Key Features 

### Authentication & Authorization
- JWT-based security system with Spring Security
- Multi-role user management (ADMIN, MANAGER, USER)
- Protected endpoints with role-based access control

### Automatic Schedule Generation
- Service providers can configure automatic time slot generation for entire months
- Customizable rules including:
  - Working hours and slot duration
  - Lunch break configuration
  - Operating days (including weekends)
  - Flexible scheduling patterns

### Booking Flow with Pending Payment
- Clients can reserve time slots with `PENDING_PAYMENT` status
- 24-hour payment window before automatic cancellation
- Scheduled tasks (`@Scheduled`) for automatic cleanup of unpaid reservations
- Robust state management for booking lifecycle

### Stripe Payment Integration
- Secure payment processing using Stripe PaymentIntents
- Webhook integration for asynchronous payment confirmation
- Real-time payment status updates
- Data consistency guaranteed through webhook validation

### Email Notifications
- Asynchronous email sending (`@Async`) with Spring Mail
- Confirmation emails for successful bookings
- Pending payment reminders
- Customizable email templates

## Architecture & Technologies 

### Backend Stack
- **Java 17+** - Modern Java features and performance
- **Spring Boot 3+** - Enterprise-grade framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA (Hibernate)** - Object-relational mapping
- **Spring Cache** - Redis-based caching layer

### Database & Persistence
- **PostgreSQL/MySQL** - Relational database support
- **Redis** - High-performance caching and session storage
- **Database migrations** - Flyway/Liquibase ready

### Payment & External Services
- **Stripe SDK** - Payment processing integration
- **Webhook handling** - Secure payment confirmations
- **Email service** - SMTP integration with Gmail/SendGrid

### DevOps & Infrastructure
- **Docker** - Containerization support
- **Docker Compose** - Complete development environment
- **Environment configuration** - `.env` file support
- **Health checks** - Spring Actuator endpoints

### Testing
- **JUnit 5** - Unit and integration testing
- **Mockito** - Mock objects for isolated testing
- **TestContainers** - Database integration testing

## Project Structure

```
src/
├── main/
│   ├── java/com/arthur/schedulingApi/
│   │   ├── config/           # Configuration classes
│   │   ├── controllers/      # REST endpoints
│   │   ├── models/          # JPA entities
│   │   ├── repositories/    # Data access layer
│   │   ├── security/        # Security configuration
│   │   ├── usecases/        # Business logic
│   │   ├── payment/         # Stripe integration
│   │   └── exceptions/      # Custom exception handling
│   └── resources/
│       ├── application.properties
│       └── static/          # Static resources
└── test/                    # Test classes
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose
- PostgreSQL (or use Docker)
- Redis (or use Docker)

### Environment Setup

1. Clone the repository:
```bash
git clone https://github.com/arthurd3/services-scheduling-api.git
cd services-scheduling-api
```

2. Configure environment variables in `.env`:
```env
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret
STRIPE_SECRET_KEY=your_stripe_secret
STRIPE_PUBLIC_KEY=your_stripe_public_key
STRIPE_WEBHOOK_SECRET=your_webhook_secret
REDIS_PASSWORD=your_redis_password
EMAIL_USERNAME=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
```

3. Start the infrastructure services:
```bash
cd scheduling-api
docker-compose up -d
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Documentation

### Main Endpoints

#### Authentication
- `POST /api/auth/login` - User authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/refresh` - Token refresh

#### Schedule Management
- `GET /api/schedules` - List available time slots
- `POST /api/schedules/generate` - Generate monthly schedule
- `PUT /api/schedules/{id}` - Update schedule configuration

#### Booking Management  
- `POST /api/bookings` - Create new booking
- `GET /api/bookings/{id}` - Get booking details
- `PUT /api/bookings/{id}/cancel` - Cancel booking

#### Payment Processing
- `POST /api/payments/create-intent` - Create payment intent
- `POST /api/payments/webhook` - Stripe webhook handler
- `GET /api/payments/{id}/status` - Payment status

### Health & Monitoring
- `GET /actuator/health` - Application health check
- `GET /actuator/metrics` - Application metrics
- `GET /actuator/info` - Application information

## Why This Project Stands Out for a Portfolio? 

### Demonstrates Core Competencies
- **Spring Ecosystem Mastery**: Shows deep understanding of Spring Boot, Data, Security, and advanced features
- **Enterprise Patterns**: Implements industry-standard patterns like JWT authentication, async processing, and webhook handling
- **Database Design**: Well-structured relational database with proper relationships and constraints

### Solves Real-World Problems
- **Payment Processing**: Handles the complexity of payment integration with proper error handling and state management
- **Asynchronous Operations**: Email notifications and scheduled tasks show understanding of concurrent programming
- **Data Consistency**: Webhook implementation ensures payment state consistency across systems

### Robust Architecture
- **Resilient Design**: PENDING_PAYMENT workflow with automatic cleanup demonstrates system reliability
- **Scalable Infrastructure**: Redis caching and Docker containerization show production readiness
- **Security Best Practices**: JWT implementation, environment variable management, and secure payment handling

### Production-Ready Features
- **Monitoring**: Actuator endpoints for health checks and metrics
- **Configuration Management**: External configuration with environment variables
- **Error Handling**: Comprehensive exception handling and validation
- **Testing Strategy**: Unit and integration tests ensuring code quality

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Arthur - [GitHub](https://github.com/arthurd3)

Project Link: [https://github.com/arthurd3/services-scheduling-api](https://github.com/arthurd3/services-scheduling-api)
