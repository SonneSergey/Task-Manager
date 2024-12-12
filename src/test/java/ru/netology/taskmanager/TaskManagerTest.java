package ru.netology.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    public void shouldAddThreeTasksOfDifferentTypes() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Meeting meeting = new Meeting (
                3,
                "Выкатка 3-й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForTaskByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Meeting meeting = new Meeting(
                3,
                "Выкатка 3-й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("молоко");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForTaskIfQueryIsNull() {
        Todos todos = new Todos();
        assertThrows(IllegalArgumentException.class, () -> todos.search(null));
    }

    @Test
    public void shouldSearchMeetingByTopic() {
        Meeting meeting = new Meeting(3, "Выкатка 3-й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        Task[] expected = {meeting};
        Task[] actual = todos.search("выкатка");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNoMatch() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);
        Task[] expected = {};
        Task[] actual = todos.search("не существует");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpicSubtasksByQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        Todos todos = new Todos();
        todos.add(epic);
        Task[] expected = {epic};
        Task[] actual = todos.search("хлеб");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeetingByDate() {
        Meeting meeting = new Meeting(3, "Выкатка 3-й версии приложения", "НетоБанк", "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        Task[] expected = {meeting};
        Task[] actual = todos.search("вторник");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeetingByProject() {
        Meeting meeting = new Meeting(3, "Выкатка 3-й версии приложения", "НетоБанк", "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        Task[] expected = {meeting};
        Task[] actual = todos.search("НетоБанк");
        assertArrayEquals(expected, actual);
    }
}