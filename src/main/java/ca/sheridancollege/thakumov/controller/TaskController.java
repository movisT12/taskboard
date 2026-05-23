package ca.sheridancollege.thakumov.controller;

import ca.sheridancollege.thakumov.model.Task;
import ca.sheridancollege.thakumov.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("todoCount", taskService.countByStatus("TODO"));
        model.addAttribute("inProgressCount", taskService.countByStatus("IN_PROGRESS"));
        model.addAttribute("doneCount", taskService.countByStatus("DONE"));
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", List.of("TODO", "IN_PROGRESS", "DONE"));
        model.addAttribute("priorities", List.of("LOW", "MEDIUM", "HIGH"));
        return "tasks/form";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));
        model.addAttribute("task", task);
        model.addAttribute("statuses", List.of("TODO", "IN_PROGRESS", "DONE"));
        model.addAttribute("priorities", List.of("LOW", "MEDIUM", "HIGH"));
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}