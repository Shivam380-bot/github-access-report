## GitHub Access Report Service

## 📌 Project Overview

Ye project ek **Spring Boot based backend service** hai jo GitHub APIs use karke ye batata hai ki **kaun user kis repository ko access karta hai**.

Ye system GitHub organization ya user ke repos fetch karta hai aur unke contributors ke basis par ek **user → repository mapping** generate karta hai.



##  Problem Statement

Organizations ko ye track karna hota hai ki:

* Kaun user kis repository pe kaam kar raha hai
* Kis user ke paas kaunse repos ka access hai

Is problem ko solve karne ke liye ye API banayi gayi hai.



## ⚙️ Tech Stack

* Java
* Spring Boot
* REST API
* GitHub API



##  Authentication

GitHub API ko access karne ke liye **Personal Access Token (PAT)** use kiya gaya hai.

### Steps:

1. GitHub → Settings → Developer Settings
2. Generate Token (classic)
3. Scopes:

   * repo
   * read:org



## How to Run the Project

### Step 1: Clone the repo

```bash
git clone https://github.com/Shivam380-bot/github-access-report.git
```

### Step 2: Add token in application.properties

```properties
github.token=YOUR_TOKEN_HERE
github.base.url=https://api.github.com
```

### Step 3: Run the project

   bash
mvn spring-boot:run


---

##  API Endpoint

### GET Access Report

```bash
GET /api/access-report?org={orgName}
```

### Example:

```bash
http://localhost:8080/api/access-report?org=Shivam380-bot
```

---

## Sample Output

```json
{
  "user1": ["repo1", "repo2"],
  "user2": ["repo3"]
}
```

---

## How it Works

1. User request deta hai with org/user name
2. Backend GitHub API call karta hai:

   * Repositories fetch karta hai
   * Har repo ke contributors fetch karta hai
3. Data ko transform karta hai:

   * repo → users ❌
   * user → repos ✅
4. Final JSON response return karta hai

---

## ⚠️ Important Notes

* `collaborators` API restricted hoti hai → isliye `contributors` use kiya gaya
* System organization aur user dono support karta hai
* Empty repo hone par empty output aata hai (error nahi)

---

##  Assumptions

* Public repositories use kiye gaye hain
* Contributors ko access indicator maana gaya hai
* Token valid aur authorized hai

---

## Future Improvements

* Parallel API calls (performance boost)
* Pagination support
* DTO based clean response
* Logging & monitoring

---

## 👨‍💻 Author

Shivam Sahu
