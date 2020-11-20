package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ErrorUtil;
import com.squarecheck.shared.retrofit.ServiceGenerator;
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
        UserUtil userUtil = (UserUtil) UtilProvider.getUtil(UserUtil.class);
        // TODO: Get Token from UserUtil
        String token = null;
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
}
