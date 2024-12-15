package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void shouldReturnFalseWhenQueryDoesNotMatchInBaseTask() {
        Task task = new Task(1) {
        }; // Создаем анонимный подкласс, так как Task абстрактный
        assertFalse(task.matches("query")); // У базового класса всегда false
    }

    @Test
    public void shouldCheckEqualityOfTasks() {
        Task task1 = new SimpleTask(1, "Позвонить родителям");
        Task task2 = new SimpleTask(1, "Позвонить родителям");
        Task task3 = new SimpleTask(2, "Позвонить друзьям");

        assertEquals(task1, task2); // Одинаковые ID — должны быть равны
        assertNotEquals(task1, task3); // Разные ID — не равны
    }

    @Test
    public void shouldCheckHashCode() {
        Task task1 = new SimpleTask(1, "Позвонить родителям");
        Task task2 = new SimpleTask(1, "Позвонить родителям");

        assertEquals(task1.hashCode(), task2.hashCode()); // Одинаковый hashCode
    }

    @Test
    public void shouldReturnCorrectIdForTask() {
        Task task = new SimpleTask(1, "Позвонить родителям");
        assertEquals(1, task.getId()); // Проверка ID
    }

    @Test
    public void shouldReturnTrueWhenTasksHaveSameId() {
        Task task1 = new SimpleTask(1, "Позвонить родителям");
        Task task2 = new SimpleTask(1, "Позвонить родителям");

        assertTrue(task1.equals(task2)); // Одинаковые ID — должны быть равны
    }

    @Test
    public void shouldReturnFalseWhenTasksHaveDifferentId() {
        Task task1 = new SimpleTask(1, "Позвонить родителям");
        Task task2 = new SimpleTask(2, "Позвонить друзьям");

        assertFalse(task1.equals(task2)); // Разные ID — не равны
    }

    @Test
    public void shouldReturnFalseWhenComparingWithDifferentClass() {
        Task task = new SimpleTask(1, "Позвонить родителям");
        String otherObject = "Some string";

        assertFalse(task.equals(otherObject)); // Сравниваем с объектом другого класса — не равны
    }

    @Test
    public void shouldReturnFalseWhenComparingWithNull() {
        Task task = new SimpleTask(1, "Позвонить родителям");

        assertFalse(task.equals(null)); // Сравниваем с null — не равны
    }

    @Test
    public void shouldReturnTrueWhenComparingObjectWithItself() {
        Task task = new SimpleTask(1, "Позвонить родителям");

        assertTrue(task.equals(task)); // Сравниваем объект с самим собой — равны
    }

    @Test
    public void shouldReturnTrueWhenTaskMatchesQuery() {
        Task task = new SimpleTask(1, "Позвонить родителям");
        assertTrue(task.matches("Позвонить")); // Строка "Позвонить" должна совпасть с задачей
    }

    @Test
    public void shouldReturnFalseWhenTaskDoesNotMatchQuery() {
        Task task = new SimpleTask(1, "Позвонить родителям");
        assertFalse(task.matches("Позвонить друзьям")); // Строка "Позвонить друзей" не должна совпасть
    }
}
