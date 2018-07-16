package com.example.jitendrakumar.incometracker.activities;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jitendrakumar.incometracker.R;
import com.example.jitendrakumar.incometracker.database.BorrowDatabaseHelper;

import java.util.Calendar;

public class BorrowItemsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextInputLayout input_layout_value;
    EditText etValue;
    TextView tvDate, tvHintDate, tvDesc, tvHintDesc, tvDelete, tvSave, tvPerson, tvHintPerson ;
    private  String Date, Desc, Person;
    private  int Id, year, month, day, hour, minute;
    private float amt;
    BorrowDatabaseHelper bHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_borrow_items );

            bHelper = new BorrowDatabaseHelper( this );

            input_layout_value = (TextInputLayout) findViewById( R.id.input_layout_value );
            tvDate = (TextView) findViewById( R.id.tvDate );
            tvDesc= (TextView) findViewById( R.id.tvDesc);
            tvHintDate = (TextView) findViewById( R.id.tvHintDate );
            tvHintDesc = (TextView) findViewById( R.id.tvHintDesc);
            tvDelete= (TextView) findViewById( R.id.tvDelete );
            tvSave = (TextView) findViewById( R.id.tvSave );
            tvPerson = (TextView) findViewById( R.id.tvPerson );
            tvHintPerson = (TextView) findViewById( R.id.tvHintPerson );
            etValue = (EditText) findViewById( R.id.etValue );

            Calendar c = Calendar.getInstance();
            int incyear = c.get( Calendar.YEAR );
            int incmonth = c.get( Calendar.MONTH);
            int incday = c.get( Calendar.DAY_OF_MONTH );

            final DatePickerDialog datePickerDialog = new DatePickerDialog(this, BorrowItemsActivity.this, incyear, incmonth, incday);

            getSupportActionBar().setTitle( "Edit Borrow" );
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                //The key argument here must match that used in the other activity
                amt = extras.getFloat( "amount");
                Date = extras.getString( "date" );
                Desc = extras.getString( "desc" );
                Person = extras.getString( "person" );
                Id = extras.getInt( "id" );
            }

            tvDate.setText(Date.toString());
            etValue.setText(String.valueOf(amt));
            tvPerson.setText( Person.toString() );
            tvDesc.setText(Desc.toString());

            tvDate.setHintTextColor( getResources().getColor(R.color.colorPrimaryDark) );
            tvDesc.setHintTextColor( getResources().getColor(R.color.colorPrimaryDark) );

            tvDelete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder item_builder = new AlertDialog.Builder(BorrowItemsActivity.this);
                    item_builder.setTitle( "Delete this Record?" );
                    item_builder.setIcon( R.drawable.ic_reset);
                    item_builder.setPositiveButton( "DELETE RECORD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bHelper.deleteBorrowData( String.valueOf( Id ) );
                            Intent i = new Intent( BorrowItemsActivity.this, BorrowActivity.class );
                            startActivity( i );
                            //  Toast.makeText( IncomeItemsActivity.this, "this income is deleted"+id , Toast.LENGTH_SHORT).show();
                        }
                    } );
                    item_builder.setNegativeButton( "CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            item_builder.setCancelable( true );
                        }
                    } );
                    AlertDialog alertDialog = item_builder.create();
                    alertDialog.show();

                }
            } );


            tvSave.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder save_builder = new AlertDialog.Builder(BorrowItemsActivity.this);
                    save_builder.setTitle( "Finally save the changes ?" );

                    save_builder.setPositiveButton( "Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String newDate = tvDate.getText().toString();

                            if(bHelper.updateBorrowData( String.valueOf( Id ),tvPerson.getText().toString(), Float.parseFloat(etValue.getText().toString()) , tvDesc.getText().toString(), tvDate.getText().toString())) {
                                Intent i = new Intent( BorrowItemsActivity.this, BorrowActivity.class );
                                startActivity( i );
                                Toast.makeText( BorrowItemsActivity.this, "this income is updated" + Id, Toast.LENGTH_SHORT ).show();
                            }
                            else {
                                Toast.makeText( BorrowItemsActivity.this, "Some error in updating ", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                    save_builder.setNegativeButton( "CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            save_builder.setCancelable( true );
                        }
                    } );
                    AlertDialog alertDialog = save_builder.create();
                    alertDialog.show();

                }
            } );

            tvHintDate.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePickerDialog.show();
                }
            });

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            populateSetDate(year, month+1, dayOfMonth );
        }

        public void populateSetDate(int year, int month, int day) {
            if(day<=9 && month <=9)
            {
                tvDate.setText("0"+day+"/"+"0"+month+"/"+year);
            }
            else if(day<=9 && month>9)
            {
                tvDate.setText("0"+day+"/"+month+"/"+year);
            }
            else if(day>9 && month<=9){
                tvDate.setText(day+"/"+"0"+month+"/"+year);
            }
            else{
                tvDate.setText(day+"/"+month+"/"+year);
            }
        }

    }

