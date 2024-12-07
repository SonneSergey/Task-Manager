package ru.netology.taskmanager;

public class Task {

    protected int id; // Уникальный идентификатор задачи

    public Task(int id) { // Конструктор класса
        this.id = id;
    }

    public int getId() { // Геттер для id
        return id;
    }

    // Метод для сравнения задач
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    // Хэш-код для сравнения объектов
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id); // Полный путь к Objects
    }

    // Метод для поиска задачи
    public boolean matches(String query) {
        return false;
    }
}
