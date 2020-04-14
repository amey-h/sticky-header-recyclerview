package com.techamey.stickyheader.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.techamey.stickyheader.sample.adapter.StickyHeaderRecyclerAdapter;
import com.techamey.stickyheader.sample.model.DataModel;
import com.techamey.stickyheader.sample.model.ItemType;
import com.techamey.stickyheader.sample.model.ListDataModel;
import com.techamey.stickyheader.sample.model.Model;
import com.techamey.stickyheaderlib.StickyHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StickyHeaderRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        List<DataModel> dataModelList = initAndGetListData();
        adapter = new StickyHeaderRecyclerAdapter(this, dataModelList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        StickyHeaderItemDecoration stickyHeaderItemDecoration = new StickyHeaderItemDecoration(adapter);
        recyclerView.addItemDecoration(stickyHeaderItemDecoration);
        recyclerView.setAdapter(adapter);
    }

    private List<DataModel> initAndGetListData() {

        List<DataModel> dataModelList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            DataModel dataModel1 = new DataModel();
            ListDataModel listDataModel1 = new ListDataModel();
            listDataModel1.setHeader("Header " + i);
            dataModel1.setType(ItemType.TYPE_HEADER);
            dataModel1.setListDataModel(listDataModel1);
            dataModelList.add(dataModel1);
            for (int j = 1; j < 6; j++) {
                DataModel dataModel2 = new DataModel();
                ListDataModel listDataMode2 = new ListDataModel();
                listDataMode2.setData("Data " + i + " " + j);
                dataModel2.setType(ItemType.TYPE_DATA);
                dataModel2.setListDataModel(listDataMode2);
                dataModelList.add(dataModel2);
            }
        }
        return dataModelList;
    }

    /*List<Model> modelList = new ArrayList<>();
        for (Integer i = 1; i < 5; i++) {
            Model headerModel = new Model("Header " + i, ItemType.TYPE_HEADER);
            modelList.add(headerModel);
            for (Integer j = 1; j < 8; j++) {
                Model model = new Model("Data " + i + " " + j, ItemType.TYPE_DATA);
                modelList.add(model);
            }
        }*/
}
