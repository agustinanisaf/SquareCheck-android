package com.squarecheck.login.view;

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
import com.squarecheck.login.contract.LoginContract;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    private static final String TAG = LoginFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        ContentLoginBinding binding = ContentLoginBinding.inflate(inflater, container, true);

        Log.d(TAG, "onCreateView: check");
//        presenter.start();

        binding.btnLogin.setOnClickListener(view -> {
        });
        Log.d(TAG, "onCreateView: " + binding);

        title = getResources().getString(R.string.login_title);

        return fragmentView;
    }

    @Override
    public void redirectToHome() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void initView() {

    }
}
