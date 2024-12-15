package ru.netology.taskmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void TestSimpleMatch() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        boolean actual = simpleTask.matches("Позвонить");

        Assertions.assertTrue(actual);

    }

    @Test
    public void TestSimpleNotMatch() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        boolean actual = simpleTask.matches("Написать");

        Assertions.assertFalse(actual);

    }

    @Test
    public void TestEpicMatch() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean actual = epic.matches("Хлеб");

        Assertions.assertTrue(actual);

    }

    @Test
    public void TestEpicNotMatch() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean actual = epic.matches("Пельмени");

        Assertions.assertFalse(actual);

    }

    @Test
    public void TestMeetingMatchTopic() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean actual = meeting.matches("Выкатка");

        Assertions.assertTrue(actual);

    }

    @Test
    public void TestMeetingMatchProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean actual = meeting.matches("Приложение");

        Assertions.assertTrue(actual);

    }

    @Test
    public void TestMeetingNotMatch() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean actual = meeting.matches("вторник");

        Assertions.assertFalse(actual);

    }
}