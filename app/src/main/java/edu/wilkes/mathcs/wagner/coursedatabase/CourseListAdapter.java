package edu.wilkes.mathcs.wagner.coursedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CourseListAdapter extends ListAdapter<Course, CourseListAdapter.CourseViewHolder> {
    private OnItemClickListener listener;

    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    public Course getCourseAt(int position) {
        return getItem(position);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current.getTitle(), current.getSubject(), current.getNumber(), current.getProglang());
    }

    static class CourseDiff extends DiffUtil.ItemCallback<Course> {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getSubject().equals(newItem.getSubject()) &&
                    oldItem.getNumber().equals(newItem.getNumber()) &&
                    oldItem.getProglang().equals(newItem.getProglang());
        }
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }

        public void bind(String title, String subject, String number, String progLang) {
            tvItemTitle.setText(title);
            tvItemSubject.setText(subject);
            tvItemNumber.setText(number);
            tvItemProgLang.setText(progLang);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
