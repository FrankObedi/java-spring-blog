# Java Spring Blog

A full-stack blog web application built with **Java**, **Spring Boot**, **Spring Data JPA**, and **Thymeleaf**. 
This project allows users to register, log in, and manage blog posts within a secure, responsive interface.

## ✨ Features

- User registration and login with session-based authentication
- Create, update, and delete blog posts
- Dynamic page rendering using Thymeleaf templates
- Responsive UI using a Bootstrap-based template
- MVC architecture for clean separation of concerns
- Backend powered by Spring Boot and Spring Data JPA

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap, HTML, CSS
- **Database**: H2 (for now while under development); Will use MySQL in production
- **Build Tool**: Maven

## 🔧 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/FrankObedi/java-spring-blog.git
   cd java-spring-blog
   
2. **Configure the Database**
- Default setup uses an in-memory H2 database.
- To switch to MySQL, update application.properties:
  - spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
  - spring.datasource.username=yourUsername
  - pring.datasource.password=yourPassword
  - spring.jpa.hibernate.ddl-auto=update

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run


📌 Future Enhancements
- Add user roles (admin/editor)

- Enable post categories and tagging

- Add rich text editing with a WYSIWYG editor

- Deploy to Heroku or Render

- Add REST API for external integration
