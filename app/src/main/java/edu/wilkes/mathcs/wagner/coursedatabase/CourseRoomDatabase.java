package edu.wilkes.mathcs.wagner.coursedatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseRoomDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();

    private static volatile CourseRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CourseRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CourseRoomDatabase.class, "course_database").fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
