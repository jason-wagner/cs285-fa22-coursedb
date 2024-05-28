package edu.wilkes.mathcs.wagner.coursedatabase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepository repository;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        repository.insert(course);
    }

    public void delete(Course course) {
        repository.delete(course);
    }
}
