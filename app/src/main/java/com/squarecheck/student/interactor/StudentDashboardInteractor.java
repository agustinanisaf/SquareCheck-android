package com.squarecheck.student.interactor;

import android.util.Log;
import android.widget.Toast;

import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.SubjectModel;
import com.squarecheck.student.retrofit.SubjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashboardInteractor implements StudentDashboardContract.Interactor {
    @Override
    public void requestSubjectsList() {
        SubjectService service = ServiceGenerator.retrofit().create(SubjectService.class);
        Call<APIResponseCollection<List<SubjectModel>>> call = service.getSubjects();

        call.enqueue(new Callback<APIResponseCollection<List<SubjectModel>>>(){
            @Override
            public void onResponse(Call<APIResponseCollection<List<SubjectModel>>> call, Response<APIResponseCollection<List<SubjectModel>>> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<APIResponseCollection<List<SubjectModel>>> call, Throwable t) {
                Log.d("1", "API Fetch failed");
            }
        });
    }

    @Override
    public void requestDetail() {

    }
}
