package com.example.jitendrakumar.incometracker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jitendrakumar.incometracker.R;

public class IncomeFragment extends Fragment {

   // TextView tvIncomeDate;
  //  DatePickerDialog.OnDateSetListener myDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_income, container, false );
     /*   tvIncomeDate = (TextView) view.findViewById( R.id.etIncomeDate );
        tvIncomeDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR );
                int month = cal.get( Calendar.MONTH );
                int day = cal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog dialog = new DatePickerDialog( getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        myDateSetListener,
                        year, month, day );
                dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
                dialog.show();
            }
        } );

        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tvIncomeDate.setText( date );
            }
        };
       */
        return view;
    }
}
