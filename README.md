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

src/main/java/com/example/hospital/
├── controller/                 # Entry points for Web & REST requests
│   ├── AdminController.java    # Handles doctor management and system oversight
│   ├── DoctorController.java   # Manages doctor schedules and profile views
│   ├── PatientController.java  # Handles appointment booking and patient records
│   └── AuthController.java     # Manages login, registration, and role redirection
├── hospitalService/            # Business logic and validation layer
│   ├── AppointmentService.java # Logic for scheduling and availability checks
│   ├── UserService.java        # Authentication and role-based logic
│   └── DoctorService.java      # Doctor profile and specialization logic
├── hospitalRepository/         # Data access layer (Spring Data JPA)
│   ├── UserRepository.java     # interface for 'users' table operations
│   ├── DoctorRepository.java   # interface for 'doctors' table operations
│   └── AppointmentRepository.java # interface for 'appointments' table operations
└── hospitalDto/                # Data Transfer Objects
    ├── UserDto.java            # Formats data for Login/Register forms
    └── AppointmentDto.java     # Formats data for booking appointments

src/main/resources/
├── static/                     # Static assets (No server-side processing)
│   ├── css/                    # Custom styling (style.css)
│   ├── js/                     # Frontend logic (login.js)
│   └── img/                    # UI images and doctor profile photos
└── templates/                  # Dynamic HTML views (Thymeleaf)
    ├── login.html              # Unified login page
    ├── admin-dashboard.html    # Admin management panel
    ├── doctor-schedule.html    # Doctor's patient list view
    └── book-appointment.html   # Patient appointment booking form
