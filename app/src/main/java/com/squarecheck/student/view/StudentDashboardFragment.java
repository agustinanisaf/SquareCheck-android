package com.squarecheck.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentStudentDashboardBinding;
import com.squarecheck.student.adapter.ListSubjectRecyclerViewAdapter;
import com.squarecheck.student.adapter.SubjectClickListener;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentDashboardFragment extends BaseFragment<StudentDashboardActivity, StudentDashboardContract.Presenter> implements StudentDashboardContract.View {
    private ContentStudentDashboardBinding binding;

    public final static String SUBJECT_ID = "SUBJECT_ID";

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentStudentDashboardBinding.inflate(inflater, container, true);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void redirectToAttendanceDetail(int subjectId) {
        Intent intent = new Intent(activity, StudentAttendanceDetailActivity.class);

        intent.putExtra(SUBJECT_ID, subjectId);
        startActivity(intent);
    }

    @Override
    public void showSubjectsList(List<SubjectModel> SubjectsList) {
        ListSubjectRecyclerViewAdapter adapter = new ListSubjectRecyclerViewAdapter(getContext(), SubjectsList);
        SubjectClickListener listener = subject -> redirectToAttendanceDetail(subject.getId());
        adapter.setListener(listener);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void showDetailProfile(StudentModel student) {
        activity.inflate.tvProfileName.setText(student.getName());
        activity.inflate.tvProfileNrp.setText(student.getNrp());
        activity.inflate.tvProfileClass.setText(student.getClassroom().getName());
    }

    @Override
    public void setPresenter(StudentDashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
