package com.squarecheck.student.interactor;

import com.squarecheck.R;
import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.model.SubjectModel;
import com.squarecheck.shared.retrofit.ScheduleService;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.retrofit.SubjectService;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.NotificationPresenceItem;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.retrofit.StudentService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class StudentDashboardInteractor implements StudentDashboardContract.Interactor {
    private static final String TAG = StudentDashboardInteractor.class.getSimpleName();
    private final ScheduleService scheduleService;
    String token;

    public StudentDashboardInteractor() {
        token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        scheduleService = ServiceGenerator.createService(ScheduleService.class, token);
    }

    @Override
    public void requestSubjectsList(RequestCallback<List<SubjectModel>> requestCallback) {
        SubjectService service = ServiceGenerator.createService(SubjectService.class, token);
        Call<APIResponseCollection<List<SubjectModel>>> call = service.getSubjects();

        call.enqueue(new RetrofitCallback<>(requestCallback, TAG, "requestSubjectsList"));
    }

    @Override
    public void requestCurrentSchedule(RequestCallback<ScheduleModel> requestCallback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = scheduleService.getSchedules();
        call.enqueue(new RetrofitCallback<APIResponseCollection<List<ScheduleModel>>>(requestCallback, TAG, "requestCurrentSchedule") {
            @Override
            public Object processData(Object data) {
                return processCurrentSchedule((List<ScheduleModel>) data);
            }
        });
    }

    @Override
    public void requestAttend(ScheduleModel schedule, RequestCallback<NotificationPresenceItem> requestCallback) {
        Call<APIResponseCollection<PresenceModel>> call = scheduleService.attend(schedule.getId());
        call.enqueue(new RetrofitCallback<APIResponseCollection<PresenceModel>>(requestCallback, TAG, "requestAttend") {
            @Override
            public Object processData(Object data) {
                return processAttend(schedule, (PresenceModel) data);
            }
        });
    }

    private NotificationPresenceItem processAttend(ScheduleModel schedule, PresenceModel data) {
        return NotificationPresenceItem.fromPresence(schedule, data);
    }

    private ScheduleModel processCurrentSchedule(List<ScheduleModel> data) {
        return data.stream().filter(d -> (d.getStartTime() != null && d.getEndTime() == null)).sorted().findFirst().orElse(null);
    }

    @Override
    public void requestAttendanceStats(RequestCallback<List<AttendanceStatusItem>> requestCallback) {
        Call<APIResponseCollection<List<ScheduleModel>>> call = scheduleService.getSummary();
        call.enqueue(new RetrofitCallback<APIResponseCollection<List<ScheduleModel>>>(requestCallback, TAG, "requestAttendanceStats") {
            @Override
            public Object processData(Object data) {
                return processAttendanceStats((List<ScheduleModel>) data);
            }
        });
    }

    private List<AttendanceStatusItem> processAttendanceStats(List<ScheduleModel> data) {
        if (data.size() > 0) {
            AttendanceStatusItem presenceStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getHadir() == 1).count()), "Hadir", R.color.hadir);
            AttendanceStatusItem excuseStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getIzin() == 1).count()), "Izin", R.color.ijin);
            AttendanceStatusItem lateStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getTerlambat() == 1).count()), "Terlambat", R.color.telat);
            AttendanceStatusItem absentStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAlpa() == 1).count()), "Alpa", R.color.alpa);
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

        call.enqueue(new RetrofitCallback<>(requestCallback, TAG, "requestCallback"));
    }

    @Override
    public void logout() {
        ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).destroy();
        ((UserUtil) UtilProvider.getUtil(UserUtil.class)).destroy();
    }

    @Override
    public void requestProfileImage() {
        // TODO : Fetch Image Link from backend
    }
}
