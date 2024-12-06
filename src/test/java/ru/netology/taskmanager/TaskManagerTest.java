package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testSimpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        assertTrue(task.matches("Позвонить"));
        assertFalse(task.matches("Прочитать"));
    }

    @Test
    void testEpicTaskMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic task = new Epic(2, subtasks);
        assertTrue(task.matches("Яйца"));
        assertFalse(task.matches("Мясо"));
    }

    @Test
    void testMeetingTaskMatches() {
        Meeting task = new Meeting(3, "Встреча с клиентом", "Проект А", "2024-12-12");
        assertTrue(task.matches("Встреча"));
        assertTrue(task.matches("Проект"));
        assertFalse(task.matches("Задача"));
    }

    @Test
    void testTaskEquality() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);

        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
    }

    @Test
    void testTaskHashCode() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);

        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }
}