package com.theresa.boutique.util;

import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

public class DisableSundayDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        DayOfWeek weekDay = day.getDate().getDayOfWeek();
        return weekDay==DayOfWeek.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
view.setDaysDisabled(true);
    }
}
