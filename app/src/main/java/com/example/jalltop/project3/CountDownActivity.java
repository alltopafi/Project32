package com.example.jalltop.project3;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;


public class CountDownActivity extends Activity {

    long hour;
    long minute;
    long month;
    long day;
    long year;
    static int alarmID=7;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
               DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            month = datePicker.getMonth();
            day = datePicker.getDayOfMonth();
            year = datePicker.getYear();
                System.out.println("Hour " + hour + " Minute " + minute);
                System.out.println("Month " + month + " Day " + day + " year " + year);
                System.out.println("alarms size: " + MainActivity.alarms.size());



                if(MainActivity.alarms.size()!=0)
                {
                    alarmID = MainActivity.alarms.get(MainActivity.alarms.size()-1).alarmID+1;

                }


                MainActivity.alarms.add(new AlarmObject("Alarm "+alarmID,alarmID));

                scheduleAlarm();


            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count_down, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void scheduleAlarm()
    {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day



        Long time = new GregorianCalendar().getTimeInMillis()+5*1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and

        Intent intentAlarm = new Intent(this, AlarmReciever.class);

        // create the object
        //AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
       // Toast.makeText(this, "Alarm fired", Toast.LENGTH_LONG).show();






    }

}
