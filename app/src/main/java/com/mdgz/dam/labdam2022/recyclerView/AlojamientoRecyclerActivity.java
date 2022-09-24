package com.mdgz.dam.labdam2022.recyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;

public class AlojamientoRecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_detalle_alojamiento);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerAlojamiento);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AlojamientoRecyclerAdapter(null);
        recyclerView.setAdapter(mAdapter);
    }
}
