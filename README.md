# 🍰 Cake Website Management

## 📌 Project Overview
The **Cake Website Management** system is a web application built with **Spring Boot** and **Thymeleaf** that allows admins to manage cake products, categories, and orders efficiently. The system provides features for adding, updating, deleting, and displaying cakes, along with authentication for admin access.

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, Bootstrap, HTML, CSS, JavaScript
- **Database:** MySQL
- **Build Tool:** Maven
- **Version Control:** Git

## 📂 Folder Structure
```
CakeWebsiteManagement/
│── src/
│   ├── main/
│   │   ├── java/com/example/cakeprj/
│   │   │   ├── controller/      
│   │   │   ├── entity/        
│   │   │   ├── repository/    
│   │   │   ├── service/       
│   │   │   ├── security/       
│   │   │   ├── CakeApplication.java  
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   │   ├── css/        
│   │   │   │   ├── js/       
│   │   │   │   ├── uploads/   
│   │   │   ├── templates/      
│   │   │   ├── application.properties 
│── pom.xml  # Maven dependencies
│── README.md
```

## ✨ Features
- **Admin Panel:**
  - Add, edit, and delete cakes
  - Manage categories
  - View and manage orders
- **User Authentication:**
  - Login & Registration
  - Secure routes with Spring Security
- **Cake Management:**
  - Upload cake images to the `/uploads/` directory
  - Display cakes with price and details
- **Order Management:**
  - Users can add cakes to the cart and place orders

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven
- Git

### Setup Steps
1. **Clone the Repository:**
   ```sh
   git clone <your-repo-url>
   cd CakeWebsiteManagement
   ```
2. **Configure Database:**

```properties
server:
  port: 8080
  servlet:
    context-path: /daibacbakery

spring:
  datasource:
    url: "jdbc:mysql://localhost:3306/cake_db"  # Changed database name to cake_db
    username: root
    password: yourpassword  # Replace 'yourpassword' with the actual password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    mode: HTML
    cache: false
    logging-level: TRACE
```
3. **Build and Run the Application:**
   ```sh
   mvn spring-boot:run
   ```
4. **Access the App:**
   - Admin: `http://localhost:8080/admin`
   - User: `http://localhost:8080`

## 🔗 API Endpoints
| Method | Endpoint            | Description          |
|--------|---------------------|----------------------|
| `GET`  | `/cakes`            | View all cakes       |
| `POST` | `/cake/add`         | Add a new cake      |
| `GET`  | `/order/history`    | View order history  |
| `POST` | `/register`         | User registration   |
| `POST` | `/login`            | User login          |
(Still have more, adding...)

## 🖼️ Image Storage
- Uploaded images are stored in the `uploads/` folder.
- The image URL is saved as a `String` in the database, e.g., `uploads/cake1.png`.
- Make sure the `/uploads/` directory is accessible in Spring Security configuration.

## ⚡ Future Improvements
- Payment gateway integration
- Email notifications for orders
- User profile management
- Train a deep learning model (like CNN) to automatically classify cakes based on uploaded images.
- Use machine learning to analyze user behavior and suggest cakes based on browsing history and past orders


## 📜 License
This project is licensed under the MIT License.

---
**Happy Baking! 🎂**
