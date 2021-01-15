package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.api_response.APIResponse;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.retrofit.ScheduleService;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;

import java.util.List;

import retrofit2.Call;

public class LecturerScheduleActionInteractor implements LecturerScheduleActionContract.Interactor {
    private static final String TAG = LecturerScheduleActionInteractor.class.getSimpleName();
    private final ScheduleService service;

    public LecturerScheduleActionInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(ScheduleService.class, token);
    }

    @Override
    public void requestSchedule(Integer scheduleId, RequestCallback<ScheduleModel> callback) {
        Call<APIResponseCollection<ScheduleModel>> call = service.getSchedule(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestSchedule"));
    }

    @Override
    public void requestOpenSchedule(int scheduleId, RequestCallback<ScheduleModel> callback) {
        Call<APIResponseCollection<ScheduleModel>> call = service.open(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestOpenSchedule"));
    }

    @Override
    public void requestRemoveSchedule(int scheduleId, RequestCallback<APIResponse> callback) {
        Call<APIResponse> call = service.remove(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestRemoveSchedule"));
    }

    @Override
    public void requestAttendances(int scheduleId, RequestCallback<List<PresenceModel>> callback) {
        Call<APIResponseCollection<List<PresenceModel>>> call = service.getAttendances(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestAttendances"));
    }

    @Override
    public void requestCloseSchedule(int scheduleId, RequestCallback<ScheduleModel> callback) {
        Call<APIResponseCollection<ScheduleModel>> call = service.close(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestCloseSchedule"));
    }
}
