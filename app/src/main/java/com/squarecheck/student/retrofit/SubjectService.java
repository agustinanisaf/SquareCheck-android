package com.squarecheck.student.retrofit;

import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.SubjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubjectService {
    @GET("subjects")
    Call<APIResponseCollection<List<SubjectModel>>> getSubjects();

    @GET("subjects/{id}/attendances")
    Call<APIResponseCollection<List<ScheduleModel>>> getAttendances(@Path("id") Integer subjectId);
}
