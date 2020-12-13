package com.squarecheck.lecturer.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ErrorUtil;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.retrofit.ScheduleService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        call.enqueue(new Callback<APIResponseCollection<ScheduleModel>>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                   @NonNull Response<APIResponseCollection<ScheduleModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "requestSchedule: onResponse: success");
                    callback.requestSuccess(response.body().getData());
                } else {
                    APIResponse apiResponse = ErrorUtil.parseError(response);
                    Log.d(TAG, "requestSchedule: onResponse: failed: " + apiResponse.getDescription());
                    callback.requestError(apiResponse.getDescription());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                  @NonNull Throwable t) {
                Log.d(TAG, "requestSchedule: onFailure: failure: " + t.getMessage());
                callback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void requestOpenSchedule(int scheduleId, RequestCallback<ScheduleModel> callback) {
        Call<APIResponseCollection<ScheduleModel>> call = service.open(scheduleId);
        call.enqueue(new Callback<APIResponseCollection<ScheduleModel>>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                   @NonNull Response<APIResponseCollection<ScheduleModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "requestOpenSchedule: onResponse: success");
                    callback.requestSuccess(response.body().getData());
                } else {
                    APIResponse apiResponse = ErrorUtil.parseError(response);
                    Log.d(TAG, "requestOpenSchedule: onResponse: failed: " + apiResponse.getDescription());
                    callback.requestError(apiResponse.getDescription());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                  @NonNull Throwable t) {
                Log.d(TAG, "requestOpenSchedule: onFailure: failure: " + t.getMessage());
                callback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void requestRemoveSchedule(int scheduleId, RequestCallback<APIResponse> callback) {
        Call<APIResponse> call = service.remove(scheduleId);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NotNull Call<APIResponse> call, @NotNull Response<APIResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: success");
                    callback.requestSuccess(response.body());
                } else {
                    APIResponse apiResponse = ErrorUtil.parseError(response);
                    Log.d(TAG, "onResponse: failed: " + apiResponse.getDescription());
                    callback.requestError(apiResponse.getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: failure: " + t.getMessage());
                callback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void requestAttendances(int scheduleId, RequestCallback<List<PresenceModel>> callback) {
        Call<APIResponseCollection<List<PresenceModel>>> call = service.getAttendances(scheduleId);
        call.enqueue(new Callback<APIResponseCollection<List<PresenceModel>>>() {
            @Override
            public void onResponse(@NotNull Call<APIResponseCollection<List<PresenceModel>>> call,
                                   @NonNull Response<APIResponseCollection<List<PresenceModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.requestSuccess(response.body().getData());
                } else {
                    APIResponse apiResponse = ErrorUtil.parseError(response);
                    callback.requestError(apiResponse.getDescription());
                }
            }

            @Override
            public void onFailure(@NotNull Call<APIResponseCollection<List<PresenceModel>>> call,
                                  @NotNull Throwable t) {
                callback.requestError(t.getMessage());
            }
        });
    }

    @Override
    public void requestCloseSchedule(int scheduleId, RequestCallback<ScheduleModel> callback) {
        Call<APIResponseCollection<ScheduleModel>> call = service.close(scheduleId);
        call.enqueue(new Callback<APIResponseCollection<ScheduleModel>>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                   @NonNull Response<APIResponseCollection<ScheduleModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "requestOpenSchedule: onResponse: success");
                    callback.requestSuccess(response.body().getData());
                } else {
                    APIResponse apiResponse = ErrorUtil.parseError(response);
                    Log.d(TAG, "requestOpenSchedule: onResponse: failed: " + apiResponse.getDescription());
                    callback.requestError(apiResponse.getDescription());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseCollection<ScheduleModel>> call,
                                  @NonNull Throwable t) {
                Log.d(TAG, "requestOpenSchedule: onFailure: failure: " + t.getMessage());
                callback.requestError(t.getMessage());
            }
        });
    }
}
