package com.squarecheck.student.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentStudentDashboardBinding;
import com.squarecheck.databinding.DashboardAttendanceToolbarBinding;
import com.squarecheck.databinding.StudentDashboardToolbarBinding;
import com.squarecheck.login.view.LoginActivity;
import com.squarecheck.student.adapter.ListSubjectRecyclerViewAdapter;
import com.squarecheck.student.adapter.StudentAttendanceSummaryRecyclerViewAdapter;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentDashboardFragment extends BaseFragment<StudentDashboardActivity, StudentDashboardContract.Presenter> implements StudentDashboardContract.View {
    private ContentStudentDashboardBinding binding;

    public final static String SUBJECT_ID = "SUBJECT_ID";
    public final static String TITLE_ID = "TITLE_ID";
    private DashboardAttendanceToolbarBinding additionalLayout;
    private StudentDashboardToolbarBinding titleLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentStudentDashboardBinding.inflate(inflater, container, true);
        setTitleLayout(R.layout.student_dashboard_toolbar);
        setAdditionalLayout(R.layout.dashboard_attendance_toolbar);
        return fragmentView;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().findViewById(R.id.menu_prev).setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.menu_prev).setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToAttendanceDetail(SubjectModel subject) {
        Intent intent = new Intent(activity, StudentAttendanceDetailActivity.class);

        intent.putExtra(SUBJECT_ID, subject.getId());
        intent.putExtra(TITLE_ID, presenter.showNextTitle(subject));
        startActivity(intent);
    }

    @Override
    public void showSubjectsList(List<SubjectModel> SubjectsList) {
        ListSubjectRecyclerViewAdapter adapter = new ListSubjectRecyclerViewAdapter(getContext(), SubjectsList);
        adapter.setListener(this::redirectToAttendanceDetail);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void showCurrentSchedule(ScheduleModel schedule) {
        if (schedule != null) {
            additionalLayout.setSchedule(schedule);
            additionalLayout.btnAttend.setOnClickListener(v -> presenter.attend(schedule.getId()));
        } else {
            additionalLayout.cvAttendance.setVisibility(View.GONE);
        }
    }

    @Override
    public void showAttendanceStats(List<AttendanceStatusItem> attendanceStatusItems) {
        additionalLayout.rvSummary.setAdapter(new StudentAttendanceSummaryRecyclerViewAdapter(attendanceStatusItems));
    }

    @Override
    public void showDetailProfile(StudentModel student) {
        titleLayoutBinding.tvProfileName.setText(student.getName());
        titleLayoutBinding.tvProfileNrp.setText(student.getNrp());
        titleLayoutBinding.tvProfileClass.setText(student.getClassroom().getName());
    }

    @Override
    public void showLogoutConfirmation() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        alertDialogBuilder.setMessage(R.string.logout_confirmation);
        alertDialogBuilder.setPositiveButton(R.string.yes, (dialogInterface, i) -> presenter.logout());
        alertDialogBuilder.setNegativeButton(R.string.no, (dialogInterface, i) -> {
            //Do Nothing
        });
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.show();
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);

        startActivity(intent);
        this.activity.finish();
    }

    @Override
    public void redirectToNotificationSuccess(PresenceModel data) {
        // TODO: Redirect to Notification Success
    }

    @Override
    public void setPresenter(StudentDashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        setupToolbar();
    }

    private void setupToolbar() {
        titleLayoutBinding = (StudentDashboardToolbarBinding) getTitleLayout();
        additionalLayout = (DashboardAttendanceToolbarBinding) getAdditionalLayout();
        titleLayoutBinding.ivProfilePhoto.setOnClickListener(v -> this.showLogoutConfirmation());
    }

    @Override
    public void showProfileImage(String imgURL) {
        ImageView profileImg = titleLayoutBinding.ivProfilePhoto;

        Glide.with(this)
                .load(imgURL)
                .circleCrop()
                .placeholder(R.drawable.default_profile)
                .error(R.drawable.default_profile)
                .apply(new RequestOptions().override(profileImg.getWidth(), profileImg.getHeight()))
                .into(profileImg);
    }
}
