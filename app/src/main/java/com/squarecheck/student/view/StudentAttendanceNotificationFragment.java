package com.squarecheck.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentNotificationBinding;
import com.squarecheck.student.contract.StudentAttendanceNotificationContract;

public class StudentAttendanceNotificationFragment extends
        BaseFragment<StudentAttendanceNotificationActivity, StudentAttendanceNotificationContract.Presenter> implements
        StudentAttendanceNotificationContract.View {

    private ContentNotificationBinding binding;
    private Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentNotificationBinding.inflate(getLayoutInflater());
        setTitleLayout(R.layout.lecturer_attendance_summary_toolbar);
        intent = getActivity().getIntent();
        binding.btnNotificationFinish.setOnClickListener(v -> backToDashboard());
        return fragmentView;
    }

    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void backToDashboard() {
        Intent intent = new Intent(activity, StudentDashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(StudentAttendanceNotificationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.tvPresenceTime.setText(intent.getStringExtra("time"));
        switch (intent.getStringExtra("status")) {
            case "hadir":
                binding.tvPresenceTime.setTextColor(getResources().getColor(R.color.hadir));
                binding.tvPresenceStatus.setText("Hadir Tepat Waktu");
                break;
            case "telat" :
                binding.tvPresenceTime.setTextColor(getResources().getColor(R.color.telat));
                binding.tvPresenceStatus.setText("Hadir Terlambat");
                break;
            default:
                break;
        }

        binding.tvSubject.setText(intent.getStringExtra("subject"));
    }
}
