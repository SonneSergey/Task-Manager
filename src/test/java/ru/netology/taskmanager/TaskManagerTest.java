package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    // Тесты для SimpleTask
    @Test
    public void testSimpleTaskMatchesWithEmptyQuery() {
        SimpleTask task = new SimpleTask(1, "Test Task");
        assertFalse(task.matches(""));
    }

    @Test
    public void testSimpleTaskMatchesWithSpecialCharacters() {
        SimpleTask task = new SimpleTask(1, "Test Task @2024");
        assertTrue(task.matches("@2024"));
    }

    // Тесты для Epic
    @Test
    public void testEpicMatchesWithEmptySubtasks() {
        Epic epic = new Epic(1, new String[0]);
        assertFalse(epic.matches("Test"));
    }

    @Test
    public void testEpicMatchesWithExactSubtask() {
        Epic epic = new Epic(1, new String[]{"Exact Match"});
        assertTrue(epic.matches("Exact Match"));
    }

    @Test
    public void testEpicGetSubtasksWhenEmpty() {
        Epic epic = new Epic(1, new String[0]);
        assertArrayEquals(new String[0], epic.getSubtasks());
    }

    // Тесты для Meeting
    @Test
    public void testMeetingMatchesWithEmptyTopicAndProject() {
        Meeting meeting = new Meeting(1, "", "", "2024-12-07T10:00");
        assertFalse(meeting.matches("Test"));
    }

    @Test
    public void testMeetingMatchesWithLongQuery() {
        Meeting meeting = new Meeting(1, "Project Update", "Project A", "2024-12-07T10:00");
        assertFalse(meeting.matches("This is a very long query that does not match anything"));
    }

    // Тесты для Task
    @Test
    public void testTaskEqualsWithSameObject() {
        Task task = new Task(1);
        assertTrue(task.equals(task));
    }

    @Test
    public void testTaskHashCodeWithSameId() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    // Дополнительные тесты для Todos
    @Test
    public void testTodosAddMultipleTasks() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Task 1");
        SimpleTask task2 = new SimpleTask(2, "Task 2");
        todos.add(task1);
        todos.add(task2);
        Task[] result = todos.findAll();
        assertArrayEquals(new Task[]{task1, task2}, result);
    }

    @Test
    public void testTodosSearchWithEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("Test");
        assertArrayEquals(new Task[0], result);
    }

    @Test
    public void testTodosSearchWithExactMatch() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Exact Match");
        todos.add(task);
        Task[] result = todos.search("Exact Match");
        assertArrayEquals(new Task[]{task}, result);
    }

    @Test
    public void testTodosAddAndSearchWithSpecialCharacters() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Task with @Special #Characters!");
        todos.add(task);
        Task[] result = todos.search("@Special");
        assertArrayEquals(new Task[]{task}, result);
    }

    @Test
    public void testTodosAddDuplicateTasks() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Duplicate Task");
        todos.add(task);
        todos.add(task);
        Task[] result = todos.findAll();
        assertEquals(2, result.length);
        assertEquals(task, result[0]);
        assertEquals(task, result[1]);
    }
}