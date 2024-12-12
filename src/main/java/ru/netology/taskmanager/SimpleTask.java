package ru.netology.taskmanager;

public class SimpleTask extends Task {
    private String title;

    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean matches(String query) {
        if (query == null) return false;
        return title != null && title.contains(query);
    }
}