package com.squarecheck.login.view;

import android.view.View;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.login.interactor.LoginInteractor;
import com.squarecheck.login.presenter.LoginPresenter;

public class LoginActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeView() {
        super.initializeView();
        binding.toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initializeFragment() {
        LoginFragment currentFragment = new LoginFragment();
        currentFragment.setPresenter(new LoginPresenter(currentFragment, new LoginInteractor()));
        setCurrentFragment(currentFragment, false);
    }
}
