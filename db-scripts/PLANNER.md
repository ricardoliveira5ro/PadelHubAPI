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

