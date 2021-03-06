package com.enterprises.woof.woof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Medications extends AppCompatActivity {

    private ArrayList<MedicationObject> medications = new ArrayList<>();
    public Date thisDate1 = new Date();
    public SimpleDateFormat dayForm1 = new SimpleDateFormat("EEEE");
    public SimpleDateFormat timeForm1  = new SimpleDateFormat("h : mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        TextView scrollingView = (TextView) findViewById(R.id.currentMedication);
        scrollingView.setMovementMethod(new ScrollingMovementMethod());

        ((TextView)findViewById(R.id.medicationDay)).setText(dayForm1.format(thisDate1));
        ((TextView)findViewById(R.id.medicationTime)).setText(timeForm1.format(thisDate1));

        findViewById(R.id.addMedication).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    String title = ((TextView) findViewById(R.id.medicationTitle)).getText().toString();
                    String day = ((TextView) findViewById(R.id.medicationDay)).getText().toString();
                    String time = ((TextView) findViewById(R.id.medicationTime)).getText().toString();

                    if (!title.isEmpty() && title != null) {

                        medications.add(0, new MedicationObject(title, day, time));
                    MedicationObject m = medications.get(0);
                    dayForm1.setLenient(false);
                    timeForm1.setLenient(false);
                    thisDate1 = dayForm1.parse(m.getDay());
                    thisDate1 = timeForm1.parse(m.getTime());

                    String texts = "";
                    for (int i = 0; i < medications.size(); i++) {
                        m = medications.get(i);
                        if(i == 0){
                            texts = texts.concat("• "+m.getTitle()+" on "+m.getDay()+" at "+m.getTime());
                        } else {
                            texts = texts.concat("\n\n"+"• "+m.getTitle()+" on "+m.getDay()+" at "+m.getTime());
                        }
                    }

                        ((TextView) findViewById(R.id.currentMedication)).setText(texts);
                        ((TextView) findViewById(R.id.medicationTitle)).setText("");
                        ((TextView) findViewById(R.id.medicationDay)).setText(dayForm1.format(thisDate1));
                        ((TextView) findViewById(R.id.medicationTime)).setText(timeForm1.format(thisDate1));
                        Context context = getApplicationContext();
                        CharSequence message = "Successfully added to medications!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message, duration);
                        toast.setGravity(Gravity.TOP, 0, 700);
                        toast.show();

                    } else {
                            Context context = getApplicationContext();
                            CharSequence message = "Please fill out all inputs!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, message, duration);
                            toast.setGravity(Gravity.TOP, 0, 700);
                            toast.show();
                        }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Context context = getApplicationContext();
                    CharSequence message = "Input invalid!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.setGravity(Gravity.TOP, 0, 700);
                    toast.show();
                }
            }
        });
    }

}
