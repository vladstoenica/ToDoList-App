package com.stoe.ticker.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.stoe.ticker.data.TaskDao;
import com.stoe.ticker.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TaskRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public static final String DATABASE_NAME = "ticker_database";
    public static volatile TaskRoomDatabase INSTANCE;  //reads and writes are visible across threads
    public static final ExecutorService databaseWriterExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static final RoomDatabase.Callback sRoomDatabaseCallback
            = new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriterExecutor.execute(() -> {
                        //invoke Dao
                        TaskDao taskDao = INSTANCE.taskDao();
                        taskDao.deleteAll();  //clean DB

                        //writing to our table
                    });
                }
            };

    public static TaskRoomDatabase getDatabase(final Context context) {  //folosita ca sa cream o instanta a BD
        if (INSTANCE == null){  //ca sa ne asiguram ca totul se duce in background thread
            synchronized (TaskRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //abstract methods have no body!
    public abstract TaskDao taskDao();
}
