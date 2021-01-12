package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.lecturer.retrofit.LecturerService;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.retrofit.ScheduleService;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;

import java.util.List;

import retrofit2.Call;

public class LecturerDashboardInteractor implements LecturerDashboardContract.Interactor {

    private static final String TAG = LecturerDashboardInteractor.class.getSimpleName();
    private ScheduleService service;

    public LecturerDashboardInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(ScheduleService.class, token);
    }

    @Override
    public void requestSchedules(RequestCallback<List<ScheduleModel>> callback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = service.getSchedules();
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestSchedules"));
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

        call.enqueue(new RetrofitCallback<>(requestCallback, TAG, "requestDetail"));
    }

    @Override
    public void requestProfileImage() {
        // TODO : Fetch Image Link from backend
    }
}
