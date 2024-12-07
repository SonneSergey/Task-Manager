package ru.netology.taskmanager;

public class Epic extends Task {

    private String[] subtasks; // Массив подзадач

    public Epic(int id, String[] subtasks) { // Конструктор
        super(id); // Вызов конструктора из базового класса Task
        this.subtasks = subtasks;
    }

    public String[] getSubtasks() { // Геттер для подзадач
        return subtasks;
    }

    // Переопределяем метод поиска
    @Override
    public boolean matches(String query) {
        for (String subtask : subtasks) { // Перебираем подзадачи
            if (subtask.contains(query)) { // Если query есть в подзадаче
                return true;
            }
        }
        return false;
    }
}
