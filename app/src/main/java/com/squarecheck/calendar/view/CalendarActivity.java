package com.squarecheck.calendar.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;
import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragmentHolderActivity;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarActivity extends BaseFragmentHolderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalendarView calendarView = findViewById(R.id.calendarView);
        YearMonth currentMonth = YearMonth.now();
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        calendarView.setup(YearMonth.now(), currentMonth.plusMonths(5), firstDayOfWeek);
        calendarView.scrollToMonth(currentMonth);
        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @NotNull
            @Override
            public DayViewContainer create(@NotNull View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(@NotNull DayViewContainer viewContainer, @NotNull CalendarDay calendarDay) {
                viewContainer.textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));
                if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                    viewContainer.textView.setTypeface(viewContainer.textView.getTypeface(), Typeface.BOLD);
                } else {
                    viewContainer.textView.setTypeface(Typeface.create("montserrat_regular", Typeface.NORMAL));
                }
            }
        });
        calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthHeaderViewContainer>() {
            @NotNull
            @Override
            public MonthHeaderViewContainer create(@NotNull View view) {
                return new MonthHeaderViewContainer(view);
            }

            @Override
            public void bind(@NotNull MonthHeaderViewContainer monthHeaderViewContainer, @NotNull CalendarMonth calendarMonth) {
            }
        });
    }

    @Override
    protected void initializeFragment() {

    }

    static class DayViewContainer extends ViewContainer {

        TextView textView;

        public DayViewContainer(@NotNull View view) {
            super(view);
            textView = view.findViewById(R.id.tv_calendar_day);
        }
    }

    static class MonthHeaderViewContainer extends ViewContainer {

        final View legendLayout;

        public MonthHeaderViewContainer(@NotNull View view) {
            super(view);
            legendLayout = view.getRootView();
        }
    }
}