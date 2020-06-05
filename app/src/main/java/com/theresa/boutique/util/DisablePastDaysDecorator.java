package com.theresa.boutique.util;

import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class DisablePastDaysDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        CalendarDay date=CalendarDay.today();
        return  (day.isBefore(date))? true:false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);

    }
}
