package com.squarecheck.student.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.retrofit.SubjectService;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.student.contract.StudentAttendanceDetailContract;

import java.util.List;

import retrofit2.Call;

public class StudentAttendanceDetailInteractor implements StudentAttendanceDetailContract.Interactor {
    private final static String TAG = StudentAttendanceDetailInteractor.class.getSimpleName();
    private final SubjectService subjectService;

    public StudentAttendanceDetailInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        subjectService = ServiceGenerator.createService(SubjectService.class, token);
    }

    @Override
    public void requestAttendances(int subjectId, RequestCallback<List<ScheduleModel>> callback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = subjectService.getAttendances(subjectId);
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestAttendances"));
    }
}
