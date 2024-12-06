package ru.netology.taskmanager;

public class Meeting extends Task {

    private String topic;   // Тема встречи
    private String project; // Проект
    private String start;   // Дата и время начала

    public Meeting(int id, String topic, String project, String start) { // Конструктор
        super(id); // Вызов конструктора Task
        this.topic = topic;
        this.project = project;
        this.start = start;
    }

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {
        return start;
    }

    // Переопределяем метод поиска
    @Override
    public boolean matches(String query) {
        return topic.contains(query) || project.contains(query); // Поиск в topic и project
    }
}