# PadelHub-API

PadelHub API is a RESTful API designed to manage padel club activities such as user registration, login, and reservations. This API allows padel clubs to streamline operations by handling users, clubs, and reservation management.
The application implements JWT-based authentication and role-based authorization, ensuring secure access to resources.

`padelhubapi.azurewebsites.net/` hosts a public instance of the API.

## 🚀 Getting Started

This section will guide you through using PadelHub API, including how to authenticate and access the available resources.

----

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