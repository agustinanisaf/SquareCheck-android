package com.squarecheck.calendar.view;

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
import com.squarecheck.calendar.contract.CalendarContract;
import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.databinding.ContentCalendarBinding;
import com.squarecheck.databinding.ContentLecturerDashboardBinding;
import com.squarecheck.shared.model.Title;
import java.util.List;

public class CalendarFragment extends BaseFragment<CalendarActivity, CalendarContract.Presenter> implements CalendarContract.View {

    private ContentCalendarBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentCalendarBinding.inflate(inflater, container, true);
        return fragmentView;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().findViewById(R.id.calendar_prev_btn).setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.calendar_prev_btn).setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.rvCalendar.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void startLoading() {
        binding.rvCalendar.setVisibility(View.GONE);
        //binding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.rvCalendar.setVisibility(View.VISIBLE);
        //binding.progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalendar(List<CalendarModel> data) {

    }

    @Override
    public void showTitle(Title title) {

    }
}
