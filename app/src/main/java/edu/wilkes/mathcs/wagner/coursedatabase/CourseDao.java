package edu.wilkes.mathcs.wagner.coursedatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("DELETE FROM course_table")
    void deleteAll();

    @Query("SELECT * FROM course_table ORDER BY subject_code, course_number")
    LiveData<List<Course>> getAll();
}
