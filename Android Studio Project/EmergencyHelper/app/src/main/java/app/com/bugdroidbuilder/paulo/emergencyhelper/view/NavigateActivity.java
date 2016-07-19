package app.com.bugdroidbuilder.paulo.emergencyhelper.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import app.com.bugdroidbuilder.paulo.emergencyhelper.R;
import app.com.bugdroidbuilder.paulo.emergencyhelper.model.Hospital;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigateActivity extends AppCompatActivity {

    @Bind(R.id.navigate_toolbar)
    Toolbar toolbar;

    @Bind(R.id.list_hospitais)
    RecyclerView recyclerView;


    private Set<Hospital> setHospitais;
    private ArrayList<Hospital> listaHospitais = new ArrayList<>();

    private HospitaisAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        ButterKnife.bind(this);
        Gson gson = new Gson();
        String json = getIntent().getStringExtra("hospitais");
        setHospitais = gson.fromJson(json, Set.class);
        for(Hospital hospital: setHospitais){
            listaHospitais.add(hospital);
        }

        ToolbarSupport.startToolbarWithArrow(this, toolbar, "Navegar para");


        iniciaRecyclerView();
    }


    public void iniciaRecyclerView() {


        mAdapter = new HospitaisAdapter(listaHospitais);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewListener.RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerViewListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Hospital Hospital = listaHospitais.get(position);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}