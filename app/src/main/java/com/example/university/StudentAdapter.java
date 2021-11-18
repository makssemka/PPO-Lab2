package com.example.university;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private LayoutInflater inflater;
    private int layout;
    private List<Student> students;

    StudentAdapter(Context context, int resource,List<Student> students) {
        super(context, resource, students);
        this.layout = resource;
        this.students = students;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = students.get(position);

        viewHolder.nameView.setText(student.getName());
        viewHolder.mark1View.setText(student.getMarks("rus"));
        viewHolder.mark2View.setText(student.getMarks("math"));
        viewHolder.mark3View.setText(student.getMarks("phy"));

        return convertView;
    }

    public class ViewHolder {
        final TextView nameView, mark1View, mark2View, mark3View;
        ViewHolder(View view){
            nameView = view.findViewById(R.id.tvName);
            mark1View = view.findViewById(R.id.tvMark1);
            mark2View = view.findViewById(R.id.tvMark2);
            mark3View = view.findViewById(R.id.tvMark3);
        }
    }
}
