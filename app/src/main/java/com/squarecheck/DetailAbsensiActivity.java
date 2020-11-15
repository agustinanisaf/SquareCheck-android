package com.squarecheck;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailAbsensiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absensi);
        // TODO: Fix Recycler View
        RecyclerView recyclerView = findViewById(R.id.attendance_recycler);
        TableViewAdapter adapter = new TableViewAdapter(getPresenceList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List getPresenceList() {
        List presenceList = new ArrayList<>();

        presenceList.add(new PresenceModel("Jumat, 2 Nov 20", "08:03"));
        presenceList.add(new PresenceModel("Jumat, 9 Nov 20", "08:05"));
        presenceList.add(new PresenceModel("Jumat, 16 Nov 20", "08:00"));
        presenceList.add(new PresenceModel("Jumat, 23 Nov 20", "08:02"));
        presenceList.add(new PresenceModel("Jumat, 30 Nov 20", "08:04"));

        return presenceList;
    }
}