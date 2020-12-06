package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.lecturer.retrofit.LecturerService;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ErrorUtil;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.retrofit.ScheduleService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerDashboardInteractor implements LecturerDashboardContract.Interactor {

    private ScheduleService service;

    public LecturerDashboardInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(ScheduleService.class, token);
    }

    @Override
    public void requestSchedules(RequestCallback<List<ScheduleModel>> callback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = service.getSchedules();
        call.enqueue(new Callback<APIResponseCollection<List<ScheduleModel>>>() {
            @Override
            public void onResponse(@NotNull Call<APIResponseCollection<List<ScheduleModel>>> call,
                                   @NotNull Response<APIResponseCollection<List<ScheduleModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.requestSuccess(response.body().getData());
                } else {
                    callback.requestError(ErrorUtil.parseError(response).getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponseCollection<List<ScheduleModel>>> call,
                                  @NotNull Throwable t) {
                callback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void logout() {
        ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).destroy();
        ((UserUtil) UtilProvider.getUtil(UserUtil.class)).destroy();
    }

    @Override
    public void requestDetail(RequestCallback<LecturerModel> requestCallback) {
        LecturerService service = ServiceGenerator.createService(LecturerService.class);
        Call<APIResponseCollection<LecturerModel>> call = service.getLecturer();

        call.enqueue(new Callback<APIResponseCollection<LecturerModel>>() {
            @Override
            public void onResponse(@NotNull Call<APIResponseCollection<LecturerModel>> call,
                                   @NotNull Response<APIResponseCollection<LecturerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestCallback.requestSuccess(response.body().getData());
                } else {
                    requestCallback.requestError(ErrorUtil.parseError(response).getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponseCollection<LecturerModel>> call, @NotNull Throwable t) {
                requestCallback.requestError(t.getMessage());
            }
        });
    }
}
