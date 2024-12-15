package ru.netology.taskmanager;

public class Todos {
    private Task[] tasks = new Task[0];

    /**
     * Вспомогательный метод для добавления задачи в массив
     *
     * @param current Массив, в который мы хотим добавить элемент
     * @param task    Элемент, который мы хотим добавить
     * @return Возвращает новый массив, который выглядит как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Task[] addToArray(Task[] current, Task task) {
        Task[] tmp = new Task[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = task;
        return tmp;
    }

    /**
     * Метод добавления задачи в список дел
     *
     * @param task Добавляемая задача
     */
    public void add(Task task) {
        tasks = addToArray(tasks, task);
    }

    /**
     * Метод для получения всех задач
     *
     * @return Массив всех задач
     */
    public Task[] findAll() {
        return tasks;
    }

    /**
     * Метод поиска задач, которые подходят под поисковый запрос
     *
     * @param query Поисковый запрос
     * @return Массив из подошедших задач
     * @throws IllegalArgumentException если query равен null
     */
    public Task[] search(String query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }

        Task[] result = new Task[0]; // массив для ответа
        for (Task task : tasks) { // перебираем все задачи
            if (task.matches(query)) { // если задача подходит под запрос
                result = addToArray(result, task); // добавляем её в массив ответа
            }
        }
        return result;
    }
}