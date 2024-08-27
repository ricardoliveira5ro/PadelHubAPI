# Database Planning

This document outlines the database schema and relationships for the PadelHubAPI. The API will handle user authentication, club management, court management, and booking management.

### Entities

* **User**
  * Represents a registered user in the system. 
  * Users can be administrators (club owners) or regular users (players).
  <br/><br/>

* **Club**
  * Represents a padel club with multiple courts.
    <br/><br/>

* **Court**
  * Represents a court within a club. 
  * Each court belongs to a specific club.
    <br/><br/>

* **Reservation**
  * Represents a reservation for a specific court. 
  * Each booking is associated with a court, a user, and a time slot
    <br/><br/>

### Schema

#### User

* Fields
  * `id`: Primary Key, UUID
  * `username`: String
  * `contact_phone`: String
  * `contact_email`: String
  * `password`: String
  * `role`: String (ADMIN, CLUB_MANAGER, PLAYER)
  * `created_at`: Timestamp
  * `updated_at`: Timestamp
    <br/><br/>

* Relationships
  * One-to-Many with Reservation (A user can have many reservations).
    <br/><br/>

#### Club

* Fields
  * `id`: Primary Key, UUID
  * `name`: String
  * `description`: String
  * `address`: String
  * `contact_email`: String
  * `contact_phone`: String
  * `created_at`: Timestamp
  * `updated_at`: Timestamp
    <br/><br/>

* Relationships
  * One-to-Many with Court (A club can have many courts).
    <br/><br/>

#### Court

* Fields
  * `id`: Primary Key, UUID
  * `name`: String
  * `surface`: String (Concrete, Cement, Wood, Synthetic Material or Artificial turf)
  * `court_environment`: String (Indoor, Outdoor)
  * `club_id`: Foreign Key, UUID
  * `created_at`: Timestamp
  * `updated_at`: Timestamp
    <br/><br/>

* Relationships
  * Many-to-One with Club (A court belongs to one club). 
  * One-to-Many with Reservation (A court can have many reservations).
    <br/><br/>

#### Reservation

* Fields
  * `id`: Primary Key, UUID
  * `user_id`: Foreign Key, UUID
  * `court_id`: Foreign Key, UUID
  * `reservation_start_time`: Timestamp
  * `reservation_end_time`: Timestamp
  * `status`: String (Pending, Confirmed, Cancelled)
  * `created_at`: Timestamp
  * `updated_at`: Timestamp
    <br/><br/>

* Relationships
  * Many-to-One with User (A reservation is made by one user). 
  * Many-to-One with Court (A reservation is for one court).
    <br/><br/>
