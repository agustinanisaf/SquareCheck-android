package com.squarecheck.student.retrofit;

import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleService {
    @GET("schedules/summarize")
    Call<APIResponseCollection<List<ScheduleModel>>> getSummary();

    @GET("schedules")
    Call<APIResponseCollection<List<ScheduleModel>>> getSchedules();

    @GET("schedules/{id}")
    Call<APIResponseCollection<ScheduleModel>> getSchedule(@Path("id") Integer scheduleId);

    @GET("schedules/{id}/attendances")
    Call<APIResponseCollection<List<PresenceModel>>> getAttendances(@Path("id") Integer scheduleId);

    @POST("schedules/{id}/open")
    Call<APIResponseCollection<ScheduleModel>> open(@Path("id") Integer scheduleId);

    @POST("schedules/{id}/close")
    Call<APIResponseCollection<ScheduleModel>> close(@Path("id") Integer scheduleId);

    @DELETE("schedules/{id}/attendances")
    Call<APIResponse> remove(@Path("id") Integer scheduleId);

    @POST("schedules/{id}/attend")
    Call<APIResponseCollection<PresenceModel>> attend(@Path("id") Integer scheduleId);
}
