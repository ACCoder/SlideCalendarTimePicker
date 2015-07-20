This Library is base on [SlideDateTimePicker](https://github.com/jjobes/SlideDateTimePicker) and [android-times-square](https://github.com/square/android-times-square).

![](https://raw.githubusercontent.com/ACCoder/SlideCalendarTimePicker/master/appworks/SlideCalendarTimePicker/screenshot1.png)

![](https://raw.githubusercontent.com/ACCoder/SlideCalendarTimePicker/master/appworks/SlideCalendarTimePicker/screenshot2.png)

### Setup

``` java       
    dependencies {
        compile project(':library')
    }
```

### How to Use

``` java      
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
    ```
