package com.squarecheck.lecturer.retrofit;

import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.shared.api_response.APIResponseCollection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LecturerService {
    @GET("lecturers")
    Call<APIResponseCollection<LecturerModel>> getLecturer();
}
