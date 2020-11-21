package com.squarecheck.base.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (T) context;
        this.fragmentListener = (FragmentListener) context;
    }

    public View getTitleLayout() {
        return titleLayout;
    }

    protected void setTitleLayout(View titleLayout) {
        this.titleLayout = titleLayout;
        fragmentListener.setTitleLayout(titleLayout);
    }
}
