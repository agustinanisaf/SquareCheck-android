package com.squarecheck.base.view;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.squarecheck.R;
import com.squarecheck.databinding.ActivityBaseBinding;

public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected ActivityBaseBinding binding;
    protected ViewDataBinding titleToolbarBinding;

    @Override
    protected void initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        // Set change default prev button method
        binding.menuPrev.setOnClickListener(view -> super.onBackPressed());
        //binding.menuPrev.setOnClickListener(view -> Log.d("Base", "initializeView: pressed"));
    }

    @Override
    public ViewDataBinding getTitleLayout() {
        return titleToolbarBinding;
    }

    @Override
    public void setTitleLayout(int layoutId) {
        titleToolbarBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, this.binding.titleLayout, true);
    }
}
