package com.example.deanery.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.Lecturer;

import java.util.ArrayList;
import java.util.List;


public class LecturerRecyclerViewAdapter extends RecyclerView.Adapter<LecturerRecyclerViewAdapter.LecturerPlaceHolder> {

    private List<Lecturer> lecturers;
    private String token;
    private Context context;


    public LecturerRecyclerViewAdapter(List<Lecturer> lecturers, String token, Context context) {
        this.lecturers = lecturers;
        this.token = token;
        this.context = context;
    }

    @NonNull
    @Override
    public LecturerPlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_lecturer, viewGroup, false);

        return new LecturerPlaceHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull LecturerPlaceHolder lecturerPlaceHolder, final int position) {
        final Lecturer lecturer = lecturers.get(position);
        lecturerPlaceHolder.full_name.setText(lecturer.getFullName());
        lecturerPlaceHolder.phone.setText(lecturer.getPhoneNumber());
        lecturerPlaceHolder.position.setText(lecturer.getPosition());
        lecturerPlaceHolder.department.setText(lecturer.getDepartment().getName());


        lecturerPlaceHolder.lecturer_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LizaTest", lecturers.get(position).getFullName() + "   " + lecturers.get(position).getId());

                Intent i = new Intent(context, LecturerDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("lecturerID", lecturers.get(position).getId());
                i.putExtra("fullName", lecturers.get(position).getFullName());
                i.putExtra("department", lecturers.get(position).getDepartment().getName());
                i.putExtra("phoneNumber", lecturers.get(position).getPhoneNumber());
                i.putExtra("token", token);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lecturers.size();
    }

    class LecturerPlaceHolder extends RecyclerView.ViewHolder {
        TextView full_name;
        TextView position;
        TextView department;
        TextView phone;
        Button lecturer_view;

        public LecturerPlaceHolder(@NonNull View itemView) {
            super(itemView);

            full_name = (TextView) itemView.findViewById(R.id.lecturer_full_name);
            position = (TextView) itemView.findViewById(R.id.lecturer_position);
            department = (TextView) itemView.findViewById(R.id.lecturer_department);
            phone = (TextView) itemView.findViewById(R.id.lecturer_phone);
            lecturer_view  =(CardView) itemView.findViewById(R.id.lecturer_view);
        }
    }
}
