# Micronaut JWT Authentication

## 🔐 Overview

A modern, secure authentication system built with Micronaut framework and JWT (JSON Web Tokens). This project provides a complete authentication solution with beautiful UI components built using Tailwind CSS and Alpine.js.

![Index Preview](docs/images/dashboard-preview.png)

## ✨ Features

- 🔑 **JWT-based Authentication**: Secure token-based authentication system
- 👤 **User Management**: Registration, login, and profile management
- 🔄 **Token Refresh**: Automatic token refreshing mechanism
- 🔒 **Role-based Access Control**: Granular permissions for different user types
- 🎨 **Modern UI**: Responsive design built with Tailwind CSS and Alpine.js
- 📱 **API-Ready**: RESTful API endpoints for integration with any frontend

## 🛠️ Technologies

### Backend
- [Micronaut Framework](https://micronaut.io/) - Modern, JVM-based framework
- [Java 17](https://openjdk.java.net/projects/jdk/17/) - Latest LTS Java version
- [JWT (JJWT)](https://github.com/jwtk/jjwt) - JSON Web Token implementation
- [Hibernate/JPA](https://hibernate.org/) - Object-relational mapping

### Frontend
- [Tailwind CSS](https://tailwindcss.com/) - Utility-first CSS framework
- [Alpine.js](https://alpinejs.dev/) - Lightweight JavaScript framework
- [Chart.js](https://www.chartjs.org/) - Interactive charts and graphs
- [Animate.css](https://animate.style/) - CSS animations library

## 📋 Prerequisites

- Java 17 or later
- Maven 3.8 or later
## 🚀 Getting Started

### Clone the repository

```bash
git clone https://github.com/Vin-it-9/Micronaut-JWT.git
cd Micronaut-JWT
```

### Build and run

```bash
./gradlew run
```

The application will be available at [http://localhost:8080](http://localhost:8080)

## 🔄 API Endpoints

### Authentication

```
POST /api/auth/register - Register a new user
POST /api/auth/login    - Login and receive JWT tokens
POST /api/auth/logout   - Invalidate current token
POST /api/auth/refresh  - Refresh an expired access token
```

### User Management

```
GET    /api/user/me     - Get current user profile
PUT    /api/user/me     - Update user profile
DELETE /api/user/me     - Delete user account
```

## ⚙️ Configuration Options

| Property | Description | Default Value |
|----------|-------------|---------------|
| `jwt.secret` | Secret key for signing JWT tokens | *Required* |
| `jwt.accessToken.expiration` | Access token expiration time in seconds | 3600 (1 hour) |
| `jwt.refreshToken.expiration` | Refresh token expiration time in seconds | 2592000 (30 days) |
| `security.oauth.enabled` | Enable OAuth providers | false |

## 🖼️ Screenshots

### Login Page
![Login Page](docs/images/login-page.png)

### Registration Page
![Registration Page](docs/images/registration-page.png)

### Dashboard
![Dashboard](docs/images/dashboard-page.png)

