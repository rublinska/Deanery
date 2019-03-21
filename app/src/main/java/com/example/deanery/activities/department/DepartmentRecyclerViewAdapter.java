package com.example.deanery.activities.department;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.department.Department;

import java.util.List;


public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.DepartmentPlaceHolder> {


    private List<Department> departments;
    private String token;
    private Context context;
    Department department;

    public DepartmentRecyclerViewAdapter(List<Department> departments, String token, Context context) {
        this.departments = departments;
        this.token = token;
        this.context = context;
    }

    @NonNull
    @Override
    public DepartmentPlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_department, viewGroup, false);

        return new DepartmentPlaceHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentPlaceHolder departmentPlaceHolder, final int position) {

        department = departments.get(position);

        departmentPlaceHolder.name.setText(department.getName());
        departmentPlaceHolder.auditoriesNum.setText(String.valueOf(department.getAuditories().size()));
        departmentPlaceHolder.lecturersNum.setText(String.valueOf(department.getLecturers().size()));

        departmentPlaceHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                department = departments.get(position);

            //    Log.i("lizatestOnclick", department.getFullName());
                Intent i = new Intent(v.getContext(), DepartmentUpdateActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putParcelable("department", department);
                i.putExtras(mBundle);

                i.putExtra("token", token);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }


    class DepartmentPlaceHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView auditoriesNum;
        TextView lecturersNum;
        Button item;

        public DepartmentPlaceHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.department_name);
            auditoriesNum = (TextView) itemView.findViewById(R.id.lecturers_num);
            lecturersNum = (TextView) itemView.findViewById(R.id.auditories_num);
            item = (Button) itemView.findViewById(R.id.department_button);
        }

    }

}
