package ru.netology.taskmanager;

public class Epic extends Task {
    private String[] subtasks;

    public Epic(int id, String[] subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    public String[] getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean matches(String query) {
        if (subtasks == null) return false;
        for (String subtask : subtasks) {
            if (subtask != null && subtask.contains(query)) {
                return true;
            }
        }
        return false;
    }
}
