package com.squarecheck.student.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ErrorUtil;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;
import com.squarecheck.student.retrofit.StudentService;
import com.squarecheck.student.retrofit.SubjectService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashboardInteractor implements StudentDashboardContract.Interactor {
    String token;

    public StudentDashboardInteractor() {
        token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
    }

    @Override
    public void requestSubjectsList(RequestCallback<List<SubjectModel>> requestCallback) {
        SubjectService service = ServiceGenerator.createService(SubjectService.class, token);
        Call<APIResponseCollection<List<SubjectModel>>> call = service.getSubjects();

        call.enqueue(new Callback<APIResponseCollection<List<SubjectModel>>>() {
            @Override
            public void onResponse(@NotNull Call<APIResponseCollection<List<SubjectModel>>> call,
                                   @NotNull Response<APIResponseCollection<List<SubjectModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestCallback.requestSuccess(response.body().getData());
                } else {
                    requestCallback.requestError(ErrorUtil.parseError(response).getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponseCollection<List<SubjectModel>>> call, @NotNull Throwable t) {
                requestCallback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void requestDetail(RequestCallback<StudentModel> requestCallback) {
        StudentService service = ServiceGenerator.createService(StudentService.class);
        Call<APIResponseCollection<StudentModel>> call = service.getStudent();

        call.enqueue(new Callback<APIResponseCollection<StudentModel>>() {
            @Override
            public void onResponse(@NotNull Call<APIResponseCollection<StudentModel>> call,
                                   @NotNull Response<APIResponseCollection<StudentModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestCallback.requestSuccess(response.body().getData());
                } else {
                    requestCallback.requestError(ErrorUtil.parseError(response).getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponseCollection<StudentModel>> call, @NotNull Throwable t) {
                requestCallback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void logout() {
        ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).destroy();
        ((UserUtil) UtilProvider.getUtil(UserUtil.class)).destroy();
    }
}
