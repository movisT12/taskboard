# TaskBoard

A full-stack task tracking web application built with Spring Boot, Thymeleaf, and MySQL.

## Stack

- **Backend:** Java 21, Spring Boot 3.2, JdbcTemplate
- **Frontend:** Thymeleaf, HTML5, CSS3 (WCAG 2.0 accessible)
- **Database:** MySQL 8 (manual SQL, no ORM)
- **Build:** Maven

## Features

- Create, edit, and delete tasks
- Track status: TODO, IN_PROGRESS, DONE
- Set priority: LOW, MEDIUM, HIGH
- Due date support
- Task counts by status
- Accessible markup with ARIA labels and semantic HTML

## Getting Started

**Prerequisites:** Java 21+, MySQL 8, Maven 3.8+

```bash
git clone https://github.com/movisT12/taskboard.git
cd taskboard
```

Create the database and table:

```sql
CREATE DATABASE IF NOT EXISTS taskboard_db;

USE taskboard_db;

CREATE TABLE IF NOT EXISTS tasks (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    status      VARCHAR(20)  NOT NULL DEFAULT 'TODO',
    priority    VARCHAR(10)  NOT NULL DEFAULT 'MEDIUM',
    due_date    DATE,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);
```

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.username=root
spring.datasource.password=yourpassword
```

Run the app:

```bash
mvn spring-boot:run
```

Open `http://localhost:8080/tasks`

## Project Structure
src/
├── main/
│   ├── java/ca/sheridancollege/thakumov/
│   │   ├── controller/   TaskController.java
│   │   ├── model/        Task.java
│   │   ├── repository/   TaskRepository.java
│   │   └── service/      TaskService.java
│   └── resources/
│       ├── templates/tasks/   list.html, form.html
│       └── static/css/        style.css
## Known Issues / Future Work

- No user authentication
- No pagination on task list
- Filter by status/priority not yet wired to UI
  
