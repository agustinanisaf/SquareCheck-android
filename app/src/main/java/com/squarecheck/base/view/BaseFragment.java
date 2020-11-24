package com.squarecheck.base.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.squarecheck.base.presenter.BasePresenter;

public abstract class BaseFragment<T extends FragmentActivity, U extends BasePresenter> extends Fragment {

    protected View titleLayout;
    protected T activity;
    protected String title;
    protected View fragmentView;
    protected U presenter;
    protected FragmentListener fragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (T) context;
        this.fragmentListener = (FragmentListener) context;
    }

    public ViewDataBinding getTitleLayout() {
        return fragmentListener.getTitleLayout();
    }

    protected void setTitleLayout(int layoutId) {
        fragmentListener.setTitleLayout(layoutId);
    }
}
