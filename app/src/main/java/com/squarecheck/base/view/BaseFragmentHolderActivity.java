package com.squarecheck.base.view;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.databinding.DataBindingUtil;

import com.squarecheck.R;
import com.squarecheck.databinding.ActivityBaseBinding;

public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected ActivityBaseBinding binding;

    @Override
    protected void initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        // Set change default prev button method
        binding.menuPrev.setOnClickListener(view -> super.onBackPressed());
        //binding.menuPrev.setOnClickListener(view -> Log.d("Base", "initializeView: pressed"));
    }

    @Override
    public void setTitleLayout(View titleLayout) {
        RelativeLayout toolbarLayout = this.binding.toolbarLayout;
        final int index = toolbarLayout.indexOfChild(titleLayout);
        toolbarLayout.removeView(titleLayout);
        toolbarLayout.addView(titleLayout, index);
    }
}
