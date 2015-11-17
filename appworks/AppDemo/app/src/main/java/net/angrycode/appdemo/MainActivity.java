package net.angrycode.appdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import net.angrycode.base.BaseActivity;
import net.angrycode.libs.datetimepicker.SlideCalendarTimeDialogFragment;
import net.angrycode.libs.datetimepicker.SlideCalendarTimePicker;

import java.util.Date;


public class MainActivity extends BaseActivity {
    SlideCalendarTimePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_actioin_menu);
        SlideCalendarTimePicker.Builder builder = new SlideCalendarTimePicker.Builder(getSupportFragmentManager());
        picker = builder.setInitialDate(new Date())
                .setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                .setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {

                    }
                })
                .build();
        picker.show();
        boolean is = false;
        ImageView imageView = new ImageView(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
//        SlideCalendarTimeDialogFragment fragment = picker.create();
//        getSupportFragmentManager().beginTransaction().add(android.R.id.content,fragment,"calendar").commit();
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
            picker.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
