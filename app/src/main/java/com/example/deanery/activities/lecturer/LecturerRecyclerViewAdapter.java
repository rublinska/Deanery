package com.example.deanery.activities.lecturer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.lecturer.Lecturer;

import java.util.List;


public class LecturerRecyclerViewAdapter extends RecyclerView.Adapter<LecturerRecyclerViewAdapter.LecturerPlaceHolder> {


    private List<Lecturer> lecturers;
    private String token;
    private Context context;
    Lecturer lecturer;

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

        lecturer = lecturers.get(position);

        lecturerPlaceHolder.fullName.setText(lecturer.getFullName());
        lecturerPlaceHolder.phone.setText(lecturer.getPhoneNumber());
        lecturerPlaceHolder.position.setText(lecturer.getPosition());
        lecturerPlaceHolder.department.setText(lecturer.getDepartment().getName());

        lecturerPlaceHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lecturer = lecturers.get(position);

            //    Log.i("lizatestOnclick", department.getFullName());
                Intent i = new Intent(v.getContext(), LecturerUpdateActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putParcelable("department", lecturer);
                i.putExtras(mBundle);

                i.putExtra("token", token);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lecturers.size();
    }


    class LecturerPlaceHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView department;
        TextView phone;
        TextView position;
        Button item;

        public LecturerPlaceHolder(@NonNull View itemView) {
            super(itemView);

            fullName = (TextView) itemView.findViewById(R.id.lecturer_full_name);
            department = (TextView) itemView.findViewById(R.id.lecturer_department);
            phone = (TextView) itemView.findViewById(R.id.lecturer_phone);
            position = (TextView) itemView.findViewById(R.id.lecturer_position);
            item = (Button) itemView.findViewById(R.id.lecturer_button);
        }

    }

}
