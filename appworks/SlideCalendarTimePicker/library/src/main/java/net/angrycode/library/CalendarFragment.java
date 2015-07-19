package net.angrycode.library;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * The fragment for the first page in the ViewPager
 *
 * @author ACCoder
 *
 */
public class CalendarFragment extends Fragment
{
    /**
     * Used to communicate back to the parent fragment as the user
     * is changing the date spinners so we can dynamically update
     * the tab text.
     */
    public interface DateChangedListener
    {
        void onDateChanged(int year, int month, int day);
    }

    private DateChangedListener mCallback;
    private CalendarPickerView mDatePicker;

    public CalendarFragment()
    {
        // Required empty public constructor for fragment.
    }

    /**
     * Cast the reference to {@link SlideCalendarTimeDialogFragment}
     * to a {@link DateChangedListener}.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            mCallback = (DateChangedListener) getTargetFragment();
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException("Calling fragment must implement " +
                "CalendarFragment.DateChangedListener interface");
        }
    }

    /**
     * Return an instance of DateFragment with its bundle filled with the
     * constructor arguments. The values in the bundle are retrieved in
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} below to properly initialize the DatePicker.
     *
     * @param theme
     * @param minDate
     * @param maxDate
     * @return an instance of CalendarFragment
     */
    public static final CalendarFragment newInstance(int theme, Date initialDate,Date minDate, Date maxDate)
    {
        CalendarFragment f = new CalendarFragment();

        Bundle b = new Bundle();
        b.putInt("theme", theme);
        b.putSerializable("initialDate", initialDate);
        b.putSerializable("minDate", minDate);
        b.putSerializable("maxDate", maxDate);
        f.setArguments(b);

        return f;
    }

    /**
     * Create and return the user interface view for this fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        int theme = getArguments().getInt("theme");
        Date initialDate = (Date) getArguments().getSerializable("initialDate");
        Date minDate = (Date) getArguments().getSerializable("minDate");
        Date maxDate = (Date) getArguments().getSerializable("maxDate");

        // Unless we inflate using a cloned inflater with a Holo theme,
        // on Lollipop devices the DatePicker will be the new-style
        // DatePicker, which is not what we want. So we will
        // clone the inflater that we're given but with our specified
        // theme, then inflate the layout with this new inflater.

        Context contextThemeWrapper = new ContextThemeWrapper(
                getActivity(),
                theme == SlideDateTimePicker.HOLO_DARK ?
                         android.R.style.Theme_Holo :
                         android.R.style.Theme_Holo_Light);

        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        View v = localInflater.inflate(R.layout.fragment_calendar, container, false);

        mDatePicker = (CalendarPickerView) v.findViewById(R.id.calendar_picker);
        // block keyboard popping up on touch
        if (initialDate==null) {
            initialDate = new Date();
        }
        if (maxDate == null) {
            final Calendar nextYear = Calendar.getInstance();
            nextYear.add(Calendar.YEAR, 1);
            maxDate = nextYear.getTime();
        }
        if (minDate == null) {
            final Calendar lastYear = Calendar.getInstance();
            lastYear.add(Calendar.YEAR, -1);
            minDate = lastYear.getTime();
        }
        mDatePicker.init(minDate, maxDate) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(initialDate);
        mDatePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                if (mCallback != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    mCallback.onDateChanged(year,month,day);
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return v;
    }
}
