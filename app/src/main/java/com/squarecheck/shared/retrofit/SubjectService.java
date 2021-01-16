package com.squarecheck.shared.retrofit;

import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.model.SubjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubjectService {
    @GET("subjects")
    Call<APIResponseCollection<List<SubjectModel>>> getSubjects();

    @GET("subjects/{id}/attendances?order_by=+time")
    Call<APIResponseCollection<List<ScheduleModel>>> getAttendances(@Path("id") Integer subjectId);
}
