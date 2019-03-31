package com.example.deanery.activities.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.activities.lecturer.LecturerUpdateActivity;
import com.example.deanery.dataModels.student.Student;

import java.util.List;


public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.StudentPlaceHolder> {


    private List<Student> students;
    private String token;
    private Context context;
    Student student;

    public StudentRecyclerViewAdapter(List<Student> students, String token, Context context) {
        this.students = students;
        this.token = token;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentPlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_student, viewGroup, false);

        return new StudentPlaceHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentPlaceHolder studentPlaceHolder, final int position) {

        student = students.get(position);

        studentPlaceHolder.fullName.setText(student.getName());
        studentPlaceHolder.startUni.setText(student.getStartUniversity());
        studentPlaceHolder.endUni.setText(student.getEndUniversity());
        studentPlaceHolder.endReason.setText(student.getEndReason());
        //Log.i("lizatestSpecialty", student.getSpecialtyId().toString());
        //TODO add startUni
        studentPlaceHolder.specialty.setText(student.getSpecialty().getName());

        studentPlaceHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = students.get(position);

                Intent i = new Intent(v.getContext(), StudentUpdateActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putParcelable("student", student);
                i.putExtras(mBundle);

                i.putExtra("token", token);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    class StudentPlaceHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView specialty;
        TextView startUni;
        TextView endUni;
        TextView endReason;
        CardView item;

        public StudentPlaceHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.student_full_name);
            specialty = itemView.findViewById(R.id.student_specialty);
            startUni = itemView.findViewById(R.id.student_startUni);
            endUni = itemView.findViewById(R.id.student_endUni);
            endReason = itemView.findViewById(R.id.student_endReason);
            //item = itemView.findViewById(R.id.student_button);
            item = itemView.findViewById(R.id.student_view);
        }

    }

}
