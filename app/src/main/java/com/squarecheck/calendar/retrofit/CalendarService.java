package com.squarecheck.calendar.retrofit;

import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.shared.model.APIResponseCollection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CalendarService {
    @GET("academic-calendars")
    Call<APIResponseCollection<List<CalendarModel>>> getCalendars();
}
