Hospital Management System:

A full-stack Spring Boot web application designed to streamline hospital operations by connecting patients with doctors and providing administrative oversight.         



For Patients:

Secure Registration & Login: Role-based access to a personal dashboard.                                                                 
Doctor Discovery: View a list of available doctors filtered by specialization (Cardiology, Orthopedics, etc.).                           
Appointment Booking: Book appointments with specific doctors for selected dates and times.                                               
History Tracking: View personal appointment history and status.

For Doctors:

Schedule Management: View a dedicated list of all upcoming patient appointments.                                                                
Status Updates: Update or manage appointment details.

For Administrators:

System Oversight: View all appointments across the entire hospital.                                                                        
Profile Management: Add, update, or remove doctor profiles and specializations.



 Tech Stack:
 
Backend: Java 17, Spring Boot 4.0.5, Spring Data JPA

Frontend: HTML5, CSS3, JavaScript , Thymeleaf Templates

Security: Role-Based Access Control (RBAC)

Database: MySQL

Build Tool: Maven




 Database Architecture:                                                                                                                 
The system utilizes a relational MySQL database with the following core entities:

users: Central table for credentials and roles (Admin/Doctor/Patient).                                                                  
doctors: Linked to users, containing specialization, experience, and fees.                                                               
patients: Linked to users, containing DOB and medical address.                                                                          
specializations: A catalog of medical departments.                                                                                      
appointments: The transactional table linking patients and doctors with dates and reasons.                                               


Database Configuration:                                                                                                                   
Update src/main/resources/application.properties with your MySQL credentials:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=your_username
spring.datasource.password=your_password


Bash
./mvnw spring-boot:run
Access the application at http://localhost:8080



hospital-management-system
├── src/main/java
│   └── com.example.hospital
│       ├── hospitalController      # REST & MVC Controllers (Auth, Appointment, Doctor)
│       ├── hospitalDto             # Data Transfer Objects (Requests & Responses)
│       ├── hospitalEntity          # JPA Entities (User, Doctor, Patient, Appointment)
│       ├── hospitalRepository      # Spring Data JPA Repositories
│       └── hospitalService         # Business Logic Layer (Auth, Patient, Doctor Services)
├── src/main/resources
│   ├── static
│   │   ├── images                  # Backgrounds and assets
│   │   └── js                      # Frontend logic (appointment.js, login.js, register.js)
│   ├── templates                   # Thymeleaf HTML Views
│   │   ├── admin                   # Admin dashboard and doctor management
│   │   ├── doctor                  # Doctor appointments and schedule
│   │   ├── home                    # Landing, Login, and Registration pages
│   │   └── patient                 # Appointment booking and history
│   └── application.properties       # Database and Server configurations
└── pom.xml                         # Maven Dependencies 
