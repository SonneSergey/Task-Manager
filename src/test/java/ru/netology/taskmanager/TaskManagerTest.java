package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    // Тесты для Epic
    @Test
    public void testEpicGetSubtasks() {
        Epic epic = new Epic(1, new String[]{"Subtask 1", "Subtask 2"});
        assertArrayEquals(new String[]{"Subtask 1", "Subtask 2"}, epic.getSubtasks());
    }

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

    // Тесты для Todos
    @Test
    public void testTodosAddTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] expected = {task};
        assertArrayEquals(expected, todos.findAll());
    }

    @Test
    public void testTodosFindAllWhenEmpty() {
        Todos todos = new Todos();
        Task[] expected = {};
        assertArrayEquals(expected, todos.findAll());
    }

    @Test
    public void testTodosSearchWithMatchingTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] expected = {task};
        assertArrayEquals(expected, todos.search("Test"));
    }

    @Test
    public void testTodosSearchWithNoMatchingTask() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Test Task");
        todos.add(task);
        Task[] expected = {};
        assertArrayEquals(expected, todos.search("Nonexistent"));
    }

    @Test
    public void testTodosSearchWithMultipleMatches() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Test Task");
        Epic epic = new Epic(2, new String[]{"Test Subtask"});
        todos.add(task1);
        todos.add(epic);
        Task[] expected = {task1, epic};
        assertArrayEquals(expected, todos.search("Test"));
    }
}