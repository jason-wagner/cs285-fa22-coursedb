package edu.wilkes.mathcs.wagner.coursedatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "course_title")
    public String title;

    @NonNull
    @ColumnInfo(name = "subject_code")
    public String subject;

    @NonNull
    @ColumnInfo(name = "course_number")
    public String number;

    @NonNull
    @ColumnInfo(name = "programming_langauge")
    public String proglang;

    public Course(@NonNull String title, @NonNull String subject, @NonNull String number, @NonNull String proglang) {
        this.title = title;
        this.subject = subject;
        this.number = number;
        this.proglang = proglang;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getNumber() {
        return number;
    }

    public String getProglang() {
        return proglang;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setProglang(String proglang) {
        this.proglang = proglang;
    }
}
