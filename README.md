# Risk Radar Backend

A Spring Boot REST API for risk management and driver tracking.

## Prerequisites

- Java 17+
- MySQL 8.0+
- Maven 3.8+

## Environment Setup

### 1. Create Environment Variables

Copy `.env.example` to `.env` and update with your actual values:

```bash
cp .env.example .env
```

Update `.env` with your credentials:
```
DB_URL=jdbc:mysql://localhost:3306/risk_radar_db
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
JWT_SECRET=your_very_secure_32_character_minimum_secret_key
JWT_EXPIRATION=3600000
```

### 2. Set Environment Variables

**On Linux/Mac:**
```bash
export $(cat .env | xargs)
```

**On Windows (PowerShell):**
```powershell
Get-Content .env | ForEach-Object {
    if ($_ -and !$_.StartsWith('#')) {
        $key, $value = $_ -split '=', 2
        [Environment]::SetEnvironmentVariable($key, $value)
    }
}
```

**Or use IDE configuration:**
- IntelliJ IDEA: Run → Edit Configurations → VM options or Environment variables
- VS Code: Create `.env` file and use extension like `ms-vscode.makefile-tools`

### 3. Database Setup

1. Create the database:
```sql
CREATE DATABASE risk_radar_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Run migrations (if applicable)

### 4. Build and Run

```bash
./mvnw clean package
./mvnw spring-boot:run
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/validate?token=<jwt_token>` - Validate JWT token

## Security Notes

⚠️ **IMPORTANT: Never commit sensitive information to version control**

- Do NOT commit `.env` file (it's in .gitignore)
- JWT secrets should be at least 32 characters
- Use strong database passwords
- Rotate secrets regularly in production
- Use separate credentials for dev, staging, and production environments

## Common Startup Errors

### `Access denied for user 'root'@'localhost' (using password: NO)`

This means MySQL rejected the connection because **no password** was provided.

Fix it by setting:
- `DB_PASSWORD` (required if your MySQL user has a password)

Example (PowerShell):
```powershell
$env:DB_PASSWORD = "your_mysql_password"
```

### Hibernate Dialect error (`Unable to determine Dialect without JDBC metadata`)

This typically happens *after* the DB connection fails, because Hibernate can't read metadata.

You can also override Hibernate behavior via env vars:
- `HIBERNATE_DDL_AUTO` (e.g., `update`, `validate`, `none`)
- `HIBERNATE_DIALECT` (defaults to `org.hibernate.dialect.MySQLDialect`)

## Project Structure

```
src/main/java/edu/icet/
├── config/           # Spring Configuration
├── controller/       # REST Controllers
├── dto/             # Data Transfer Objects
├── exception/       # Custom Exceptions
├── model/           # JPA Entities
├── repository/      # Data Access Layer
├── security/        # JWT & Security
└── service/         # Business Logic
```

## Build Commits

The project is organized into logical feature commits:
1. JWT Security Infrastructure
2. Security Configuration
3. Data Transfer Objects
4. Custom Exception Handlers
5. Repository Layer
6. Authentication Service
7. Authentication REST Endpoints
8. Build Dependencies

This structure makes it easy to understand and review each feature independently.
