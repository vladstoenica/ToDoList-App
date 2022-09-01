package com.stoe.ticker.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.stoe.ticker.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public static TaskRepository repository;
    public final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();     //sa fiu atent la ce tip returneaza metoda declarata initial (de aia l-am pus si aici tot cu liveData)

    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public static void insert(Task task) {repository.insert(task);}

    public LiveData<Task> get(long id) {return repository.get(id);}

    public static void update(Task task) {repository.update(task);}

    public static void delete(Task task) {repository.delete(task);}
}
