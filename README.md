# Ticketing and Events API

## Overview

This is a **Spring Boot** based API for managing events and ticket bookings. It supports features like user authentication (JWT-based), ticket purchases (via **Stripe**), email notifications, QR code generation for tickets, and event management. Additionally, the API uses cookies for session handling and tracks user login activity.

## Features

- **User Authentication**: JWT-based login and registration system.
- **Event Management**: CRUD operations for managing events.
- **Ticket Booking**: Secure payment integration using **Stripe** API.
- **QR Code Generation**: QR codes for tickets that can be scanned at the event.
- **Email Notifications**: Send confirmation emails via Gmail SMTP on successful booking, and other actions.
- **Session Handling**: Cookies for storing session information.
- **Dockerized**: The API runs inside a Docker container, ensuring easy deployment and scalability.

## Technology Stack

- **Java 17** (or higher)
- **Spring Boot**
- **MySQL** (as database, using Docker container)
- **Stripe API** (for payments)
- **JWT** (for authentication)
- **Gmail SMTP** (for email sending)
- **QR Code Generator** (to generate QR codes for tickets)
- **Docker** (for containerized deployment)

## Prerequisites

- **Docker**: To run the MySQL database and the application container.
- **Stripe Account**: To enable payment processing.
- **Gmail Account**: To enable email notifications.
- **JDK 17**: To run the application locally (if not using Docker).

## Setup Instructions
### 1. Clone the repository
```bash
git clone https://github.com/your-repo/ticketing-api.git
cd ticketing-api
```
#### 2. Replace the missing information from application-docker.properties file with email server information and stripe api key and replace application.properties with it
#### 3. Clean the Project
```bash
mvn clean
```
#### 4. Package the Project
```bash
mvn package -DskipTests
```
#### 5. run docker compose
```bash
docker-compose up
```
#### 6. go to http://localhost:8080/swagger-ui/index.html and test the api endpoints


