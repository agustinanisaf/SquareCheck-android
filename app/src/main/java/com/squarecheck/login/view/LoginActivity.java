package com.squarecheck.login.view;

import android.view.View;

import com.squarecheck.base.view.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeView() {
        super.initializeView();
        binding.toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initializeFragment() {
        initializeView();

        LoginFragment loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }
}
