package com.squarecheck.base.view;

import androidx.databinding.ViewDataBinding;

public interface FragmentListener {
    ViewDataBinding getTitleLayout();

    void setTitleLayout(int layoutId);

    ViewDataBinding getAdditionalLayout();

    void setAdditionalLayout(int layoutId);
}
