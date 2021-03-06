package com.squarecheck.student.retrofit;

import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.student.model.StudentModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentService {
    @GET("students")
    Call<APIResponseCollection<StudentModel>> getStudent();
}
