package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    // Тесты для SimpleTask
    @Test
    public void testSimpleTaskMatchesWithSubstring() {
        SimpleTask task = new SimpleTask(1, "Test Task");
        assertTrue(task.matches("Test"));
    }

    @Test
    public void testSimpleTaskMatchesWithoutSubstring() {
        SimpleTask task = new SimpleTask(1, "Test Task");
        assertFalse(task.matches("Nonexistent"));
    }

    @Test
    public void testSimpleTaskGetTitle() {
        SimpleTask task = new SimpleTask(1, "Test Task");
        assertEquals("Test Task", task.getTitle());
    }

    // Тесты для Epic
    @Test
    public void testEpicMatchesWithSubtask() {
        Epic epic = new Epic(1, new String[]{"Subtask 1", "Subtask 2"});
        assertTrue(epic.matches("Subtask 1"));
    }

    @Test
    public void testEpicMatchesWithoutSubtask() {
        Epic epic = new Epic(1, new String[]{"Subtask 1", "Subtask 2"});
        assertFalse(epic.matches("Nonexistent"));
    }

    @Test
    public void testEpicGetSubtasks() {
        Epic epic = new Epic(1, new String[]{"Subtask 1", "Subtask 2"});
        assertArrayEquals(new String[]{"Subtask 1", "Subtask 2"}, epic.getSubtasks());
    }

    // Тесты для Meeting
    @Test
    public void testMeetingMatchesWithTopic() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertTrue(meeting.matches("Project Update"));
    }

    @Test
    public void testMeetingMatchesWithProject() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertTrue(meeting.matches("Project A"));
    }

    @Test
    public void testMeetingMatchesWithoutQuery() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertFalse(meeting.matches("Nonexistent"));
    }

    @Test
    public void testMeetingGetTopic() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertEquals("Project Update", meeting.getTopic());
    }

    @Test
    public void testMeetingGetProject() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertEquals("Project A", meeting.getProject());
    }

    @Test
    public void testMeetingGetStart() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertEquals("2024-12-07T10:00", meeting.getStart());
    }

    // Тесты для Task
    @Test
    public void testTaskGetId() {
        Task task = new Task(1);
        assertEquals(1, task.getId());
    }

    @Test
    public void testTaskEqualsWithSameId() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        assertTrue(task1.equals(task2));
    }

    @Test
    public void testTaskEqualsWithDifferentId() {
        Task task1 = new Task(1);
        Task task2 = new Task(2);
        assertFalse(task1.equals(task2));
    }

    @Test
    public void testTaskEqualsWithNull() {
        Task task = new Task(1);
        assertFalse(task.equals(null));
    }

    @Test
    public void testTaskEqualsWithDifferentClass() {
        Task task = new Task(1);
        String other = "Not a Task";
        assertFalse(task.equals(other));
    }

    @Test
    public void testTaskHashCode() {
        Task task = new Task(1);
        assertEquals(Objects.hash(1), task.hashCode());
    }

    @Test
    public void testTaskMatches() {
        Task task = new Task(1);
        assertFalse(task.matches("query")); // Так как метод всегда возвращает false
    }

    // Тесты для Todos
    @Test
    public void testTodosAddTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] result = todos.findAll();
        assertArrayEquals(new Task[]{task}, result);
    }

    @Test
    public void testTodosFindAllWhenEmpty() {
        Todos todos = new Todos();
        Task[] result = todos.findAll();
        assertArrayEquals(new Task[0], result);
    }

    @Test
    public void testTodosSearchWithMatchingTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] result = todos.search("Test");
        assertArrayEquals(new Task[]{task}, result);
    }

    @Test
    public void testTodosSearchWithNoMatchingTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] result = todos.search("Nonexistent");
        assertArrayEquals(new Task[0], result);
    }

    @Test
    public void testTodosSearchWithMultipleMatches() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Test Task");
        Epic epic = new Epic(2, new String[]{"Test Subtask"});
        todos.add(task1);
        todos.add(epic);
        Task[] result = todos.search("Test");
        assertArrayEquals(new Task[]{task1, epic}, result);
    }
}