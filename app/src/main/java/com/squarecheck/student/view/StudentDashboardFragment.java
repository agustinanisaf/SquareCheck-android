package com.squarecheck.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.shared.util.SquareCheckUtilProvider;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.interactor.StudentDashboardInteractor;
import com.squarecheck.student.presenter.StudentDashboardPresenter;

import java.util.ArrayList;

public class StudentDashboardFragment extends BaseFragment<StudentDashboardActivity, StudentDashboardContract.Presenter> implements StudentDashboardContract.View {
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.content_student_dashboard, container, false);
        presenter = new StudentDashboardPresenter(this, new StudentDashboardInteractor(SquareCheckUtilProvider.getSharedPreferencesUtil());
        presenter.start();

        showSubjectsList();
        showDetailProfile();
        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                redirectToAttendanceDetail(id);
            }
        });


        return fragmentView;
    }

    @Override
    public void redirectToAttendanceDetail(String id) {
        Intent intent = new Intent(activity, StudentAttendanceDetailActivity.class);

        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void showSubjectsList() {
        ArrayList<String> subjectsList = presenter.requestSubjectsList();

        mRecyclerView = fragmentView.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<String> data = presenter.requestSubjectsList();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showDetailProfile() {
        presenter.requestDetail();
        activity.inflate.tvProfileName.setText("");
        activity.inflate.tvProfileId.setText("");
        activity.inflate.tvProfileClass.setText("");
    }

    @Override
    public void setPresenter(StudentDashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {

    }
}
