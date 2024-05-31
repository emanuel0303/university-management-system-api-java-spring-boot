# 🎓 University Management System

This repository contains the backend for a University Management System built with Java Spring Boot. It manages student registrations, courses, scheduling, faculty, and examinations.

## 📋 Features

### Student Management
- 📝 Register Students: Add new students with details like name, age, major, and contact information.
- 🔄 Update Student Profile: Update personal and academic information.
- 📚 Student Enrollments: Enroll in courses.
- 📊 View Academic Record: View grades and course completion statuses.

### Course Management
- ➕ Create Course: Create new courses with credits, prerequisites, and syllabus.
- ✏️ Update Course Details: Update existing course information.
- ❌ Delete Course: Remove courses from the catalog.
- 📋 List Courses: View available courses.

### Faculty Management
- 🧑‍🏫 Add Faculty: Add faculty members with details like name, department, and qualifications.
- 🔄 Update Faculty Profile: Update faculty profiles.
- 🗂️ Assign Faculty to Courses: Assign faculty to specific courses each semester.

### Class Scheduling
- 📅 Create Timetable: Create class schedules considering faculty availability and classroom capacity.
- 🔄 Update Timetable: Adjust timetables due to changes (e.g., faculty absence, room unavailability).

### Examination Management
- 🗓️ Schedule Exams: Schedule examinations with time and venue details.
- 📝 Record Results: Post and update student grades.
- 📈 View Results: Access examination results and overall grades.

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/emanuel0303/university-management-system-api-java-spring-boot

2. Navigate to the project directory:
bash
Copy code
cd university-management-system
Configure the database in src/main/resources/application.properties.

3.Build the project:
bash
Copy code
mvn clean install

4.Run the application:
bash
Copy code
mvn spring-boot:run

## API Documentation
Access the API documentation at http://localhost:8080/swagger-ui.html after running the application.

## 🗂️ Database Schema
The database schema includes tables for students, courses, faculty, enrollments, timetables, and exams, with appropriate relationships and indexes.

## 🧪 Testing
Run the test suite using:
bash
Copy code
mvn test
## 🛠️ Deployment
For deployment instructions, refer to the Deployment Guide.

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

## ✨ Contributors
Emanuel Soloman.
Feel free to fork this project and contribute!

Enjoy managing your university operations with this robust and comprehensive system! 🎉
