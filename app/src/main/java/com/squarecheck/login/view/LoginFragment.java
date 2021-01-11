package com.squarecheck.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLoginBinding;
import com.squarecheck.lecturer.view.LecturerDashboardActivity;
import com.squarecheck.login.contract.LoginContract;
import com.squarecheck.student.view.StudentDashboardActivity;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private ContentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getResources().getString(R.string.login_title);
        binding = ContentLoginBinding.inflate(inflater, container, true);

        return fragmentView;
    }

    @Override
    public void startLoading() {
        binding.btnLogin.setVisibility(View.GONE);
        binding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.btnLogin.setVisibility(View.VISIBLE);
        binding.progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void redirectToHome(String role) {
        // TODO: Find the *Best* Way to this
        Intent intent = null;
        if (role.equals("student")) {
            intent = new Intent(getContext(), StudentDashboardActivity.class);
            Log.d(TAG, "redirectToHome: redirect to student");
        } else if (role.equals("lecturer")) {
            intent = new Intent(getContext(), LecturerDashboardActivity.class);
            Log.d(TAG, "redirectToHome: redirect to lecturer");
        }
        if (intent != null) {
            startActivity(intent);
            requireActivity().finish();
        } else {
            showError("Not Allowed.");
            Log.d(TAG, "redirectToHome: Unknown user supposedly admin.");
        }
    }

    @Override
    public void initView() {
        binding.btnLogin.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String pass = binding.etPassword.getText().toString();
            Log.d(TAG, "initView: test");
            presenter.authenticate(email, pass);
        });
    }
}
