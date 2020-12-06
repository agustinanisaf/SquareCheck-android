package com.squarecheck.lecturer.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerDashboardBinding;
import com.squarecheck.lecturer.adapter.ScheduleClickListener;
import com.squarecheck.lecturer.adapter.SchedulesAdapter;
import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.login.view.LoginActivity;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class LecturerDashboardFragment extends BaseFragment<LecturerDashboardActivity, LecturerDashboardContract.Presenter> implements LecturerDashboardContract.View {

    public static final String SUBJECT_ID = "SUBJECT_ID";

    private ContentLecturerDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentLecturerDashboardBinding.inflate(inflater, container, true);
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
    public void setPresenter(LecturerDashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.rvSchedules.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void startLoading() {
        binding.rvSchedules.setVisibility(View.GONE);
        binding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.rvSchedules.setVisibility(View.VISIBLE);
        binding.progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSchedules(List<ScheduleModel> schedules) {
        ScheduleClickListener clickListener = schedule -> {
            Intent intent = new Intent(getContext(), LecturerScheduleActionActivity.class);
            intent.putExtra(SUBJECT_ID, schedule.getId());
            startActivity(intent);
        };
        binding.rvSchedules.setAdapter(new SchedulesAdapter(clickListener, schedules));
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);

        startActivity(intent);
        this.activity.finish();
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
    public void showDetailProfile(LecturerModel lecturer) {
        activity.toolbarBinding.tvProfileName.setText(lecturer.getName());
        activity.toolbarBinding.tvProfileNrp.setText(lecturer.getNip());
        activity.toolbarBinding.tvProfileClass.setText(lecturer.getDepartment().getName());
    }
}
