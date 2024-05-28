package edu.wilkes.mathcs.wagner.coursedatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {
    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;

    CourseRepository(Application application) {
        CourseRoomDatabase db = CourseRoomDatabase.getDatabase(application);
        courseDao = db.courseDao();
        allCourses = courseDao.getAll();
    }

    LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    void insert(Course course) {
        CourseRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insert(course);
        });
    }

    void update(Course course) {
        CourseRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.update(course);
        });
    }

    void delete(Course course) {
        CourseRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.delete(course);
        });
    }
}
