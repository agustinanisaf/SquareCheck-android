package com.squarecheck.lecturer.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.retrofit.SubjectService;

import java.util.List;

import retrofit2.Call;

public class LecturerAttendanceSummaryInteractor implements LecturerAttendanceSummaryContract.Interactor {
    private static final String TAG = LecturerAttendanceSummaryInteractor.class.getSimpleName();
    private final SubjectService service;

    public LecturerAttendanceSummaryInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(SubjectService.class, token);
    }

    @Override
    public void requestSubjectAttendances(int subjectId, RequestCallback<List<ScheduleModel>> callback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = service.getAttendances(subjectId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestSubjectAttendances"));
    }
}
