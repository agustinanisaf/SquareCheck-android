package com.squarecheck.student.interactor;

import com.squarecheck.R;
import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ErrorUtil;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;
import com.squarecheck.student.retrofit.ScheduleService;
import com.squarecheck.student.retrofit.StudentService;
import com.squarecheck.student.retrofit.SubjectService;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashboardInteractor implements StudentDashboardContract.Interactor {
    private static final String TAG = StudentDashboardInteractor.class.getSimpleName();
    private final ScheduleService subjectService;
    String token;

    public StudentDashboardInteractor() {
        token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        subjectService = ServiceGenerator.createService(ScheduleService.class, token);
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
    public void requestCurrentSchedule(RequestCallback<ScheduleModel> requestCallback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = subjectService.getSchedules();
        call.enqueue(new RetrofitCallback<APIResponseCollection<List<ScheduleModel>>>(requestCallback, TAG, "requestCurrentSchedule") {
            @Override
            public Object processData(Object data) {
                return processCurrentSchedule((List<ScheduleModel>) data);
            }
        });
    }

    private ScheduleModel processCurrentSchedule(List<ScheduleModel> data) {
        return data.stream().filter(d -> d.getStartTime() != null).sorted().findFirst().orElse(null);
    }

    @Override
    public void requestAttendanceStats(RequestCallback<List<AttendanceStatusItem>> requestCallback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = subjectService.getSummary();
        call.enqueue(new RetrofitCallback<APIResponseCollection<List<ScheduleModel>>>(requestCallback, TAG, "requestAttendanceStats") {
            @Override
            public Object processData(Object data) {
                return processAttendanceStats((List<ScheduleModel>) data);
            }
        });
    }

    private List<AttendanceStatusItem> processAttendanceStats(List<ScheduleModel> data) {
        if (data != null || data.size() > 0) {
            AttendanceStatusItem presenceStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("hadir")).count()), "Hadir", R.color.hadir);
            AttendanceStatusItem excuseStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("izin")).count()), "Izin", R.color.ijin);
            AttendanceStatusItem lateStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("terlambat")).count()), "Terlambat", R.color.telat);
            AttendanceStatusItem absentStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("alpa")).count()), "Alpa", R.color.alpa);
            return Arrays.asList(presenceStat, excuseStat, lateStat, absentStat);
        }
        return Arrays.asList(
                new AttendanceStatusItem("0", "Hadir", R.color.hadir),
                new AttendanceStatusItem("0", "Izin", R.color.ijin),
                new AttendanceStatusItem("0", "Terlambat", R.color.telat),
                new AttendanceStatusItem("0", "Alpa", R.color.alpa)
        );
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

    @Override
    public void attend() {

    }
}
