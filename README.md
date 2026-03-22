# 🚀 GitHub Access Report Service

## 📌 Overview

This is a Spring Boot-based backend service that integrates with GitHub APIs to generate a user-to-repository access report based on repository contributors.

---

## ▶️ 1. How to Run the Project

### Step 1: Clone the repository

```
git clone https://github.com/Shivam380-bot/github-access-report
```

### Step 2: Navigate to the project directory

```
cd github-access-report
```

### Step 3: Configure GitHub Token

Create or update the `application.properties` file:

```
github.token=YOUR_GITHUB_TOKEN
github.base.url=https://api.github.com
```



### Step 4: Run the application

```
mvn spring-boot:run
```

---

## 🔐 2. Authentication Configuration

The application uses a **GitHub Personal Access Token (PAT)** for authentication.

* The token is passed in the request header:

  ```
  Authorization: Bearer <token>
  ```
* This ensures:

  * Higher API rate limits
  * Secure and authenticated API access

---

## 🌐 3. How to Call the API Endpoint

### Endpoint:

```
GET /api/access-report?org={orgName}
```

### Example:

```
http://localhost:8080/api/access-report?org=Shivam380-bot
```

### Sample Response:

```
{
  "user1": ["repo1", "repo2"],
  "user2": ["repo3"]
}
```

---

## 💡 4. Assumptions & Design Decisions

* The system supports both **organization and user repositories**
* If organization API fails, the system falls back to user repositories
* The **contributors API** is used instead of collaborators due to permission restrictions
* Contributors are treated as users having access to the repository
* Only public repositories are considered
* Sensitive data such as tokens are excluded using `.gitignore`


