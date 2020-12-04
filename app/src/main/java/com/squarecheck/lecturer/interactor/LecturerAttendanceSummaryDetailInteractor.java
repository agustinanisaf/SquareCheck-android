package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryDetailContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.retrofit.ScheduleService;
import com.squarecheck.student.retrofit.SubjectService;

import java.util.List;

import retrofit2.Call;

public class LecturerAttendanceSummaryDetailInteractor implements LecturerAttendanceSummaryDetailContract.Interactor {
    private static final String TAG = LecturerAttendanceSummaryDetailInteractor.class.getSimpleName();
    private final ScheduleService service;

    public LecturerAttendanceSummaryDetailInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(ScheduleService.class, token);
    }

    @Override
    public void requestStudentAttendances(int scheduleId, RequestCallback<List<PresenceModel>> callback) {
        Call<APIResponseCollection<List<PresenceModel>>> call = service.getAttendances(scheduleId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestStudentAttendances"));
    }
}
