package com.example.deanery.activities.discipline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.discipline.Discipline;

import java.util.List;


public class DisciplineRecyclerViewAdapter extends RecyclerView.Adapter<DisciplineRecyclerViewAdapter.DisciplinePlaceHolder> {


    private List<Discipline> disciplines;
    private String token;
    private Context context;
    Discipline discipline;

    public DisciplineRecyclerViewAdapter(List<Discipline> disciplines, String token, Context context) {
        this.disciplines = disciplines;
        this.token = token;
        this.context = context;
    }

    @NonNull
    @Override
    public DisciplinePlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_discipline, viewGroup, false);

        return new DisciplinePlaceHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DisciplinePlaceHolder disciplinePlaceHolder, final int position) {

        discipline = disciplines.get(position);

        disciplinePlaceHolder.fullName.setText(discipline.getName());
        disciplinePlaceHolder.selfWorkTime.setText(discipline.getSelfWorkTime().toString());
        if (discipline.getPreDisciplineId() == null)
            disciplinePlaceHolder.preDiscipline.setText("any required pre disciplines");
        else
            disciplinePlaceHolder.preDiscipline.setText(discipline.getPreDiscipline().getName());

        if (discipline.getSpecialtyId() == null)
            disciplinePlaceHolder.preDiscipline.setText("any specialty is required to learn it");
        else
            disciplinePlaceHolder.specialty.setText(discipline.getSpecialty().getName());

        disciplinePlaceHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discipline = disciplines.get(position);

            //    Log.i("lizatestOnclick", selfWorkTime.getFullName());
                Intent i = new Intent(v.getContext(), DisciplineUpdateActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putParcelable("discipline", discipline);
                i.putExtras(mBundle);

                i.putExtra("token", token);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplines.size();
    }


    class DisciplinePlaceHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView specialty;
        TextView selfWorkTime;
        TextView preDiscipline;
        CardView item;

        public DisciplinePlaceHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.discipline_name);
            specialty = itemView.findViewById(R.id.discipline_specialty);
            selfWorkTime = itemView.findViewById(R.id.discipline_selfWorkTime);
            preDiscipline = itemView.findViewById(R.id.discipline_preDiscipline);
            //item = itemView.findViewById(R.id.discipline_button);
            item = itemView.findViewById(R.id.discipline_view);
        }

    }

}
