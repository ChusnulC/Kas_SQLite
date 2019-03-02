package com.example.chusnul.kassqlite;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.chusnul.kassqlite.helper.CurrentDate;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FilterActivity extends AppCompatActivity {
    EditText edit_Dari, edit_ke;
    RippleView rip_filter;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        edit_Dari = findViewById(R.id.edit_dari);
        edit_ke = findViewById(R.id.edit_ke);
        rip_filter = findViewById(R.id.rip_filter);

        edit_Dari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        MainActivity.tgl_dari = year + "-" + numberFormat.format(month +1) + "-" + numberFormat.format(dayOfMonth);
                        Log.e("_dari", MainActivity.tgl_dari);

                        edit_Dari.setText(numberFormat.format(dayOfMonth) + "/" + numberFormat.format((month+1)) +
                                "/" + year);

                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
            }
        });

        edit_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        MainActivity.tgl_ke = year + "-" + numberFormat.format(month +1) + "-" + numberFormat.format(dayOfMonth);
                        Log.e("_ke", MainActivity.tgl_ke);

                        edit_ke.setText(numberFormat.format(dayOfMonth) + "/" + numberFormat.format((month+1)) +
                                "/" + year);

                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
            }
        });

        rip_filter.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if(edit_Dari.getText().toString().equals("")||edit_ke.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_SHORT).show();
                }else {
                    MainActivity.filter = true;
                    MainActivity.text_filter.setText(edit_Dari.getText().toString() + " " + edit_ke.getText().toString());
                    MainActivity.text_filter.setVisibility(View.VISIBLE);

                    finish();
                }
            }
        });

        getSupportActionBar().setTitle("Filter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
