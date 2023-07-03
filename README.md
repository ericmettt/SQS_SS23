# Frucht-Suchmaschine

SQS-Demoprojekt that calls the https://fruityvice.com/ API for various fruit information

[ARC24 Documentation](https://github.com/ericmettt/SQS_SS23/wiki/Dokumentation-Arc24)

## Technologies Used

- Spring Boot
- MySQL
- React

## Prerequisites

- JDK 8 or later
- Node.js
- MySQL Server

## Getting Started

### Backend (Spring Boot)

1. Clone the repository: `git clone https://github.com/ericmettt/SQS_SS23.git`
2. Navigate to the backend directory: 
3. Update the MySQL database configuration in `application.properties`
4. Build and run the Spring Boot application: `./mvnw spring-boot:run`

### Frontend (React)

1. Navigate to the frontend directory: `cd frontend/sqs-fruit-frontend`
2. Install dependencies: `npm install`
3. Update the API base URL in the `.env` file, if necessary
4. Start the React development server: `npm start`

## Database Configuration

1. Install MySQL Server if not already installed
2. Create a new database with the desired name: `CREATE DATABASE your-database-name;`
3. Update the database connection details in the backend's `application.properties`

The currently used application.properties username and password are for my local development database

## Tests

For the End to End test via Selenium the latest version of [Chromedriver](https://chromedriver.chromium.org/) has to be installed






