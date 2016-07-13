package com.task.vasilyevanton.task.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.task.vasilyevanton.task.R;
import com.task.vasilyevanton.task.adapter.PeopleAdapter;
import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener,View.OnClickListener {
    private MainPresenter presenter;
    private ListView peopleListView;
    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loadAPi = (Button) findViewById(R.id.load_api_btn);
        loadAPi.setOnClickListener(this);
        Button loadDB = (Button) findViewById(R.id.load_db_btn);
        loadDB.setOnClickListener(this);

        peopleListView = (ListView) findViewById(R.id.people_list);
        peopleListView.setOnItemClickListener(this);

        presenter = new MainPresenterImpl(this,this);
        presenter.getPeopleInfo();


    }

    //to Presenter
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }


    //from Presenter
    @Override
    public void setPeopleInfoData(ArrayList<PeopleData> list) {
        if (adapter == null) {
            adapter = new PeopleAdapter(MainActivity.this, R.layout.people_list_item, list);

        } else {
            adapter.notifyDataSetChanged();
        }
        peopleListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.load_api_btn:{
                Log.w("LOAD API BTN", "Get Api data");
                presenter.getApiData();
                break;
            }
            case R.id.load_db_btn:{
                Log.w("LOAD DB", "GET PEOPLE INFO");
                presenter.getPeopleInfo();
                break;
            }
        }
    }
}
