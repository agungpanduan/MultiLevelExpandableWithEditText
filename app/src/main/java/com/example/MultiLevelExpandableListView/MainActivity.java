package com.example.MultiLevelExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import com.example.MultiLevelExpandableListView.Model.Level;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.MultiLevelExpandableListView.Adapter.ListLevel1Adapter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Level> listLevel;
    private ArrayList<HashMap<String, String>> Level1Items; //title group

    private ListLevel1Adapter customListLevel1Adapter;
    private ExpandableListView Level1List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupReferences();
    }

    private void setupReferences() {
        Level1List=findViewById(R.id.listViewPartLevel);

        Level1Items = new ArrayList<>();

        listLevel = new ArrayList<>();
        Level levelItem = new Level();
        levelItem.setParent("0");
        levelItem.setName("A");
        levelItem.setValue("A");
        levelItem.setLevel("1");
        levelItem.setChildLevel("A");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("0");
        levelItem.setName("B");
        levelItem.setValue("B");
        levelItem.setLevel("1");
        levelItem.setChildLevel("B");
        listLevel.add(levelItem);

        /*Level1Item = new Level1();
        Level1Item.setParent("0");
        Level1Item.setName("");
        Level1Item.setValue("");
        Level1Item.setLevel("");
        Level1Item.setChildLevel("");
        listLevel1.add(Level1Item);*/

        levelItem = new Level();
        levelItem.setParent("A");
        levelItem.setName("AA1");
        levelItem.setValue("AA1");
        levelItem.setLevel("2");
        levelItem.setChildLevel("AA1");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("A");
        levelItem.setName("AA2");
        levelItem.setValue("AA2");
        levelItem.setLevel("2");
        levelItem.setChildLevel("AA2");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("B");
        levelItem.setName("BB1");
        levelItem.setValue("BB1");
        levelItem.setLevel("2");
        levelItem.setChildLevel("BB1");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("B");
        levelItem.setName("BB2");
        levelItem.setValue("BB2");
        levelItem.setLevel("2");
        levelItem.setChildLevel("BB2");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("AA1");
        levelItem.setName("AA11");
        levelItem.setValue("AA11");
        levelItem.setLevel("3");
        levelItem.setChildLevel("AA11");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("AA1");
        levelItem.setName("AA12");
        levelItem.setValue("AA12");
        levelItem.setLevel("3");
        levelItem.setChildLevel("AA12");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("AA2");
        levelItem.setName("AA21");
        levelItem.setValue("AA21");
        levelItem.setLevel("3");
        levelItem.setChildLevel("AA21");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("AA2");
        levelItem.setName("AA22");
        levelItem.setValue("AA22");
        levelItem.setLevel("3");
        levelItem.setChildLevel("AA22");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("BB1");
        levelItem.setName("BB11");
        levelItem.setValue("BB11");
        levelItem.setLevel("3");
        levelItem.setChildLevel("BB11");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("BB11");
        levelItem.setName("BB111");
        levelItem.setValue("BB111");
        levelItem.setLevel("4");
        levelItem.setChildLevel("BB111");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("BB11");
        levelItem.setName("BB112");
        levelItem.setValue("BB112");
        levelItem.setLevel("4");
        levelItem.setChildLevel("BB112");
        listLevel.add(levelItem);


        levelItem = new Level();
        levelItem.setParent("AA11");
        levelItem.setName("AA111");
        levelItem.setValue("AA111");
        levelItem.setLevel("4");
        levelItem.setChildLevel("AA111");
        listLevel.add(levelItem);

        levelItem = new Level();
        levelItem.setParent("AA11");
        levelItem.setName("AA112");
        levelItem.setValue("AA112");
        levelItem.setLevel("4");
        levelItem.setChildLevel("AA112");
        listLevel.add(levelItem);

        customListLevel1Adapter = new ListLevel1Adapter(this, listLevel);
        customListLevel1Adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));

        Level1List.setAdapter(customListLevel1Adapter);
    }
}
