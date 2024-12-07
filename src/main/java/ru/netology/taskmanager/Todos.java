package ru.netology.taskmanager;

public class Todos {

    private Task[] tasks = new Task[0]; // Массив задач

    // Метод для добавления задачи
    public void add(Task task) {
        Task[] tmp = new Task[tasks.length + 1];
        for (int i = 0; i < tasks.length; i++) {
            tmp[i] = tasks[i];
        }
        tmp[tmp.length - 1] = task;
        tasks = tmp;
    }

    // Возвращает все задачи
    public Task[] findAll() {
        return tasks;
    }

    // Поиск задач по запросу
    public Task[] search(String query) {
        Task[] result = new Task[0];
        for (Task task : tasks) {
            if (task.matches(query)) {
                Task[] tmp = new Task[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length); // Копирование массива
                tmp[tmp.length - 1] = task;
                result = tmp;
            }
        }
        return result;
    }
}