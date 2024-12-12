package ru.netology.taskmanager;

public class Todos {
    private Task[] tasks = new Task[0];

    // Вспомогательный метод для добавления задачи в массив
    private Task[] addToArray(Task[] current, Task task) {
        Task[] tmp = new Task[current.length + 1];
        System.arraycopy(current, 0, tmp, 0, current.length);
        tmp[tmp.length - 1] = task;
        return tmp;
    }

    // Метод добавления задачи
    public void add(Task task) {
        tasks = addToArray(tasks, task);
    }

    // Метод поиска всех задач
    public Task[] findAll() {
        return tasks;
    }

    // Метод поиска задач по запросу
    public Task[] search(String query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }

        Task[] result = new Task[0];
        for (Task task : tasks) {
            if (task.matches(query)) {
                result = addToArray(result, task);
            }
        }
        return result;
    }
}