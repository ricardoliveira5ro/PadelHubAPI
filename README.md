# PadelHub-API

PadelHub API is a RESTful API designed to manage padel club activities such as user registration, login, and reservations. This API allows padel clubs to streamline operations by handling users, clubs, and reservation management.
The application implements JWT-based authentication and role-based authorization, ensuring secure access to resources.

`padelhubapi.azurewebsites.net/` hosts a public instance of the API.
<br/>

## 🚀 Getting Started

This section will guide you through using PadelHub API, including how to authenticate and access the available resources.

#### 🔐 Authentication / Authorization

Each endpoint requires prior authentication based on bearer token. This token can be obtained after signing up and/or logging in as returned header. The token has 1 day expiration date.

**POST** /users/signup
```
{
    "user": {
        "email": "[your-email@email.com]",
        "password": "[your-password]",
        "name": "[your-username]"
    }
}
```

**POST** /users/login
```
{
    "user": {
        "email": "[your-email@email.com]",
        "password": "[your-password]"
    }
}
```
In the response body you will see you got your **authorization token** that will be needed in every request you make to the available endpoints.

**GET** /users/current-user
<br/><br/>

#### 📍 Endpoints

**GET** /clubs/:id <br/>
**GET** /clubs/my-club <br/>
**GET** /clubs/my-club/players

**DELETE** /clubs/delete-club

**POST** /clubs/add-club <br/>
**PUT** /clubs/update-club
```
{
    "name":"ClubTester",
    "description":"Club tester description",
    "address":"somewhere",
    "contactEmail":"clubtester@example.com",
    "contactPhone":"123456789",
    "courts": [
        {
            "name": "Court1",
            "surface": "Grass",
            "courtEnvironment":"indoor"
        },
        {
            "name": "Court2",
            "surface": "Grass",
            "courtEnvironment":"outdoor"
        }
    ]
}
```
*courts* field is **optional** and exclusive for POST action

---

**GET** /courts/:id <br/>
**GET** /courts/my-courts <br/>
**GET** /courts/courtsByClub/:id

**DELETE** /courts/:id

**POST** /courts/add-court <br/>
**PUT** /courts/update-court/:id
```
{
    "name":"Test court",
    "surface":"grass",
    "courtEnvironment":"indoor"
}
```

---

**GET** /reservations/club-reservations <br/>
**GET** /reservations/club-reservations/:id <br/>
**GET** /reservations/my-games

**DELETE** /reservations/:id

**POST** /reservations/add-game
```
{
    "reservationStartTime": "2024-09-12T15:00:00",
    "reservationEndTime": "2024-09-12T16:00:00",
    "courtId": "1"
}
```

**POST** /reservations/:id/change-status
```
{
    "status":"CANCELLED"
}
```

<br/>

## 💻 Technical Details

This section is for the developers who want to explore the technical characteristics of the API.

#### 🛠️ Tech stack

* Java (v21)
* Spring
* Spring Boot
* Hibernate
* Postgresql
* Maven
* JWT

#### 📐 Architecture Layers

* Controller
* Repository (implementing JPA repository)
* Service
* Model (Hibernate Entities)
* DTO (Request and Response)
* Configuration
* Aspect (Logging)
* Exception Handler

#### 🚀 Build and Deploy

* Run `mvn clean package` to generate app .jar file
* (Optional) Using Azure CLI
  * Login into your Azure account `az login`
  * Run `az webapp deploy --resource-group <your-resource-group> --name <your-app-service-name> --src-path target/<your-snapshot>.jar`

#### 💭 Considerations

* Database schema is automatically generated or updated by Hibernate
* Postgre database hosted in *neon.tech*
* Webservice hosted in *Microsoft Azure*
* All endpoints were tested with *Postman*

#### 🚧 Difficulties Encountered

* Application properties secured keys
* Azure web service max Java version 21 (forced downgrade)
* Cascade operations