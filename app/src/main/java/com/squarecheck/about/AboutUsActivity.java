package com.squarecheck.about;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.R;

public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        initializeDeveloperList();
        initializeSupervisorsMentors();
    }

    private void initializeSupervisorsMentors() {
        RecyclerView recyclerView = findViewById(R.id.rv_supervisors);
        String[] nameList = getResources().getStringArray(R.array.supervisors_mentors_names);
        RecyclerVAdapterSupervisors adapter = new RecyclerVAdapterSupervisors(nameList);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializeDeveloperList() {
        RecyclerView recyclerView = findViewById(R.id.rv_developers);
        Drawable images[] = initializeDeveloperImages();
        String developerNames[] = getResources().getStringArray(R.array.developer_names);
        RecyclerVAdapterDeveloper adapter = new RecyclerVAdapterDeveloper(developerNames, images);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private Drawable[] initializeDeveloperImages(){
        int profileImageId[] = {
                R.drawable.developer_agustin, R.drawable.developer_fadli,
                R.drawable.developer_nafis, R.drawable.developer_rizbach,
                R.drawable.developer_fahreza
        };
        Drawable developerImages[] = new Drawable[profileImageId.length];

        for(int i = 0; i < developerImages.length; i++)
            developerImages[i] = getDrawable(profileImageId[i]);
        return  developerImages;
    }
}
