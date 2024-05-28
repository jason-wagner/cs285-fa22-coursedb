package edu.wilkes.mathcs.wagner.coursedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private CourseViewModel viewModel;
    public static final int NEW_COURSE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        viewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, NewCourseActivity.class);
            startActivityForResult(i, NEW_COURSE_REQUEST_CODE);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Course deleted.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_COURSE_REQUEST_CODE && resultCode == RESULT_OK) {
            Course course = new Course(data.getStringExtra(NewCourseActivity.EXTRA_TITLE),
                    data.getStringExtra(NewCourseActivity.EXTRA_SUBJECT),
                    data.getStringExtra(NewCourseActivity.EXTRA_NUMBER),
                    data.getStringExtra(NewCourseActivity.EXTRA_PROGLANG));
            viewModel.insert(course);
        } else {
            Toast.makeText(getApplicationContext(), "Course not saved.", Toast.LENGTH_LONG).show();
        }
    }
}