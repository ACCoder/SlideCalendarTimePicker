package net.angrycode.slidecalendartimepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import net.angrycode.library.SlideCalendarTimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onShowCalendarTimePicker(View view) {
        Calendar nowdate = Calendar.getInstance();
        nowdate.add(Calendar.YEAR,-10);
        Date minDate = nowdate.getTime();
        nowdate.add(Calendar.YEAR,11);
        Date maxDate = nowdate.getTime();
        final SlideCalendarTimePicker picker = new SlideCalendarTimePicker.Builder(getSupportFragmentManager())
                .setInitialDate(new Date())
                .setMaxDate(maxDate)
                .setMinDate(minDate)
                .setIs24HourTime(true)
                .setIndicatorColor(getResources().getColor(android.R.color.holo_green_light))
                .setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        String time = format(date);
                        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                    }
                }).build();
        picker.show();
    }

//    public void onShowDateTimePicker(View view) {
//        Calendar nowdate = Calendar.getInstance();
//        nowdate.add(Calendar.YEAR,-10);
//        Date minDate = nowdate.getTime();
//        nowdate.add(Calendar.YEAR,22);
//        Date maxDate = nowdate.getTime();
//        final SlideDateTimePicker picker = new SlideDateTimePicker.Builder(getSupportFragmentManager())
//                .setInitialDate(new Date())
//                .setMaxDate(maxDate)
//                .setMinDate(minDate)
//                .setIs24HourTime(true)
//                .setListener(new SlideDateTimeListener() {
//                    @Override
//                    public void onDateTimeSet(Date date) {
//                        String time = format(date);
//                        Toast.makeText(getApplicationContext(), time,Toast.LENGTH_LONG).show();
//                    }
//                }).build();
//        picker.show();
//    }

    private String format(Date date) {
        DateFormat sdf = SimpleDateFormat.getInstance();
        return sdf.format(date);
    }
}
