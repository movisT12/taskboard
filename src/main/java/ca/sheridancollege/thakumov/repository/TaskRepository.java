package ca.sheridancollege.thakumov.repository;

import ca.sheridancollege.thakumov.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbc;

    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Task> rowMapper = new RowMapper<Task>() {
        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setStatus(rs.getString("status"));
            task.setPriority(rs.getString("priority"));
            task.setDueDate(rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null);
            task.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            task.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return task;
        }
    };

    public List<Task> findAll() {
        return jdbc.query("SELECT * FROM tasks ORDER BY created_at DESC", rowMapper);
    }

    public Optional<Task> findById(Long id) {
        List<Task> results = jdbc.query("SELECT * FROM tasks WHERE id = ?", rowMapper, id);
        return results.stream().findFirst();
    }

    public void save(Task task) {
        jdbc.update(
            "INSERT INTO tasks (title, description, status, priority, due_date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())",
            task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate()
        );
    }

    public void update(Task task) {
        jdbc.update(
            "UPDATE tasks SET title=?, description=?, status=?, priority=?, due_date=?, updated_at=NOW() WHERE id=?",
            task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate(), task.getId()
        );
    }

    public void deleteById(Long id) {
        jdbc.update("DELETE FROM tasks WHERE id = ?", id);
    }

    public long countByStatus(String status) {
        Long count = jdbc.queryForObject("SELECT COUNT(*) FROM tasks WHERE status = ?", Long.class, status);
        return count != null ? count : 0;
    }
}