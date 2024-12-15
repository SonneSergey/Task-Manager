package ru.netology.taskmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForSimpleTaskByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForEpicBySubtaskQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Todos todos = new Todos();
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMeetingByTopicQuery() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenQueryDoesNotMatchAnyTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);

        Task[] expected = {};
        Task[] actual = todos.search("Неизвестный запрос");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenQueryIsNull() {
        Todos todos = new Todos();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todos.search(null);
        });
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoTasksAdded() {
        Todos todos = new Todos();

        Task[] expected = {};
        Task[] actual = todos.search("Любой запрос");

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты для методов get...

    @Test
    public void shouldReturnCorrectTitleForSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        assertEquals("Позвонить родителям", simpleTask.getTitle());
    }

    @Test
    public void shouldReturnCorrectSubtasksForEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    @Test
    public void shouldReturnCorrectTopicForMeeting() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        assertEquals("Выкатка 3й версии приложения", meeting.getTopic());
    }

    @Test
    public void shouldReturnCorrectProjectForMeeting() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        assertEquals("Приложение НетоБанка", meeting.getProject());
    }

    @Test
    public void shouldReturnCorrectStartForMeeting() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        assertEquals("Во вторник после обеда", meeting.getStart());
    }

    @Test
    public void shouldReturnCorrectIdForTask() {
        Task task = new SimpleTask(1, "Позвонить родителям");
        assertEquals(1, task.getId());
    }
}