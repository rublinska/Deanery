package com.example.deanery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deanery.activities.department.DepartmentCreateActivity;

import java.util.Arrays;
import java.util.List;

public class  CustomOnClickListener<T>  implements View.OnClickListener
{
    Button button;
    String[] stringArray;
    boolean[] checkedArray;
    List<Pair<T, Boolean>> allItemsArray;
  //  Activity activity;

    public CustomOnClickListener(Button button, String[] stringArray, boolean[] checkedArray, List<Pair<T, Boolean>> allItemsArray) {
        this.button = button;
        this.stringArray = stringArray;
        this.checkedArray = checkedArray;
        this.allItemsArray = allItemsArray;
     //   this.activity = activity;
    }
    @Override
    public void onClick(final View v) {
        final List<String> list = Arrays.asList(stringArray);

        AlertDialog.Builder builder = new AlertDialog.Builder(button.getContext());

        builder.setMultiChoiceItems(stringArray, checkedArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // Update the current focused item's checked status
                checkedArray[which] = isChecked;

                // Get the current focused item
                String currentItem = list.get(which);

                // Notify the current action
                Toast.makeText(button.getContext(),
                        currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        // Specify the dialog is not cancelable
        builder.setCancelable(false);

        // Set a title for alert dialog
        builder.setTitle("Choose here:");

        // Set the positive/yes button click listener
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
                button.setText("");
                for (int i = 0; i<checkedArray.length; i++){
                    boolean checked = checkedArray[i];
                    if (checked) {
                        button.setText(button.getText() + list.get(i) + "; ");
                    }
                }
            }
        });
/*
            // Set the negative/no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when click the negative button
                }
            });*/

        // Set the neutral/cancel button click listener
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the neutral button
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
}