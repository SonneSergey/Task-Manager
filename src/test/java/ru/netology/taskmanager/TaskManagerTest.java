package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    public void shouldSearchForTaskByQuery() {
        // Создаем объекты задач
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        Meeting meeting = new Meeting(3, "Выкатка версии", "Проект X", "12 декабря");

        // Создаем объект менеджера задач
        Todos todos = new Todos();

        // Добавляем задачи в менеджер
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Ожидаем, что будет найдено 1 задача, содержащая слово "родителям"
        Task[] expectedSimpleTask = {simpleTask};  // Если запрос 'родителям', ожидаем найти только simpleTask
        Task[] actualSimpleTask = todos.search("родителям");

        // Сравниваем ожидания с реальным результатом
        assertArrayEquals(expectedSimpleTask, actualSimpleTask);

        // Ожидаем, что будет найдено 1 задача, содержащая слово "молоко"
        Task[] expectedEpic = {epic}; // Если запрос "молоко", то только epic
        Task[] actualEpic = todos.search("молоко");

        assertArrayEquals(expectedEpic, actualEpic);

        // Ожидаем, что будет найдено 1 задача, содержащая слово "встреча" в topic
        Task[] expectedMeeting = {meeting};  // Если запрос "встреча", то только meeting
        Task[] actualMeeting = todos.search("встреча");

        assertArrayEquals(expectedMeeting, actualMeeting);

        // Ожидаем, что не найдется ни одной задачи по запросу "не существует"
        Task[] expectedNone = {};  // Не существует задачи с таким запросом
        Task[] actualNone = todos.search("не существует");

        assertArrayEquals(expectedNone, actualNone);
    }

    @Test
    public void shouldNotSearchForTaskIfQueryIsNull() {
        // Создаем задачи
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);

        // Проверяем, что при передаче null в качестве запроса должно быть выброшено исключение
        try {
            todos.search(null);
            // Если исключение не было выброшено, то тест должен упасть
            throw new AssertionError("Expected IllegalArgumentException, but none was thrown.");
        } catch (IllegalArgumentException e) {
            // Ожидаем IllegalArgumentException, значит тест прошел
        }
    }
}