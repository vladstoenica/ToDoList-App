package com.stoe.ticker.data;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.stoe.ticker.model.Task;
import com.stoe.ticker.util.TaskRoomDatabase;

import java.util.List;

//Repository nu e o clasa mega importanta de avut dar e buna pt ca avem un loc central unde vine data
public class TaskRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();  //metoda din taskroomdb care returneaza taskDao
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public void insert(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute( () -> taskDao.insertTask(task));
    }

    public LiveData<Task> get(long id) {return taskDao.get(id);}

    public void update (Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute( () -> taskDao.update(task));
    }

    public void delete (Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.delete(task));
    }
}
