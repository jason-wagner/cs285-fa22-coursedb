package edu.wilkes.mathcs.wagner.coursedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvItemTitle;
    private final TextView tvItemSubject;
    private final TextView tvItemNumber;
    private final TextView tvItemProgLang;

    private CourseViewHolder(View itemView) {
        super(itemView);

        tvItemTitle = itemView.findViewById(R.id.tvTitle);
        tvItemSubject = itemView.findViewById(R.id.tvSubject);
        tvItemNumber = itemView.findViewById(R.id.tvNumber);
        tvItemProgLang = itemView.findViewById(R.id.tvProgLang);
    }

    public void bind(String title, String subject, String number, String progLang) {
        tvItemTitle.setText(title);
        tvItemSubject.setText(subject);
        tvItemNumber.setText(number);
        tvItemProgLang.setText(progLang);
    }

    static CourseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new CourseViewHolder(view);
    }
}
