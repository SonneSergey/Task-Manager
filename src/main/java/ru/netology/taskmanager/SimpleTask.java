package ru.netology.taskmanager;

public class SimpleTask extends Task {

    protected String title; // Название задачи

    public SimpleTask(int id, String title) { // Конструктор
        super(id); // Вызов конструктора из класса Task
        this.title = title;
    }

    public String getTitle() { // Геттер для названия
        return title;
    }

    // Переопределяем метод поиска
    @Override
    public boolean matches(String query) {
        return title.contains(query); // Возвращает true, если query есть в title
    }
}