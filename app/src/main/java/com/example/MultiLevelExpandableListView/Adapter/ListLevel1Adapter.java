package com.example.MultiLevelExpandableListView.Adapter;
//kekurangan adalah klik Parent A dan diklik Child A maka nilainya betul
//namun ketika klik Parent B dan diklik child A maka akan menggunakan child B bukan Child A ini salah
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.MultiLevelExpandableListView.Model.Level;
import com.example.MultiLevelExpandableListView.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListLevel1Adapter extends BaseExpandableListAdapter {
    public LayoutInflater minflater;
    private Activity activity;
    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    public static ArrayList<Level> AllDataItem; //all data part
    public static ArrayList<Level> itemLevel; //all data part
    public static ArrayList<Level> ItemLevel2; //all data part
    private String ChildLevel;
    private Level func;

    private ListLevel2Adapter customListLevel2Adapter;
    CustomExpListView level2Custom;

    public ListLevel1Adapter(Activity activity, ArrayList<Level> AllDataItem
    ) {

        this.AllDataItem=AllDataItem;
        this.activity = activity;
        itemLevel = new ArrayList<Level>();
        for(Level data : AllDataItem){
            if (data.getParent().equals("0")){
                Level levelItem = new Level();
                levelItem.setParent(data.getParent());
                levelItem.setName(data.getName());
                levelItem.setValue(data.getValue());
                levelItem.setLevel(data.getLevel());
                levelItem.setChildLevel(data.getChildLevel());
                itemLevel.add(levelItem);
            }
        }

        //SORT AGAR TIDAK BERUBAH OTOMATIS KARENA ASC ATAU DESC, jadi saya tetapkan DESCENDING
        Collections.sort(itemLevel, new Comparator<Level>() {
            @Override
            public int compare(Level lhs, Level rhs) {
                //return lhs.getName().compareTo(rhs.getName()); //Ascending
                return rhs.getName().compareTo(lhs.getName()); //Descending
            }
        });
//        this.mContext = mContext;
    }

    private class GroupViewlevel1 {
        public TextView txtParent;
        public TextView txtLevel1Name;
        public EditText txtLevel1Value;
        public Button btnExpandLevel1;
        public TextView txtLevel1;
    }

    @Override
    public int getGroupCount() {
        return itemLevel.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1; //because call New Adapter, so dont need looping
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final GroupViewlevel1 groupViewlevel1;

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.level1, null);
            //String test = convertView

            groupViewlevel1 = new GroupViewlevel1();
            groupViewlevel1.btnExpandLevel1 = (Button) convertView.findViewById(R.id.btnExpandLevel1);
            groupViewlevel1.txtParent = (TextView) convertView.findViewById(R.id.txtParent);
            groupViewlevel1.txtLevel1Name = (TextView) convertView.findViewById(R.id.txtLevel1Name);
            groupViewlevel1.txtLevel1Value = (EditText) convertView.findViewById(R.id.txtLevel1Value);
            groupViewlevel1.txtLevel1 =(TextView) convertView.findViewById(R.id.txtLevel1);

            groupViewlevel1.txtParent.setText(itemLevel.get(groupPosition).getParent());
            groupViewlevel1.txtLevel1Name.setText(itemLevel.get(groupPosition).getName());
            groupViewlevel1.txtLevel1Value.setText(itemLevel.get(groupPosition).getValue());
            groupViewlevel1.btnExpandLevel1.setTag(itemLevel.get(groupPosition).getChildLevel());
            groupViewlevel1.txtLevel1.setText(itemLevel.get(groupPosition).getLevel());


            convertView.setTag(groupViewlevel1);
        }
        else{
            groupViewlevel1 = (GroupViewlevel1) convertView.getTag();
        }

        groupViewlevel1.btnExpandLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //groupItem.get(groupPosition).getArrayList().add(new EdittextValues(""));
                ChildLevel =groupViewlevel1.btnExpandLevel1.getTag().toString();

                //refresh all collapse
                for(int l = 0; l<= itemLevel.size(); l++){
                    ((ExpandableListView) viewGroup).collapseGroup(l);
                }

                if(isExpanded)
                {
                    CollapseGroup1(groupPosition,viewGroup);
                } else
                {
                    ExpandGroup1(groupPosition,viewGroup);
                }

                getChildrenCount(groupPosition);
                notifyDataSetChanged();
            }
        });

        groupViewlevel1.txtLevel1Value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value_text = editable.toString();
                String name_text = groupViewlevel1.txtLevel1Name.getText().toString();
                String parent_text = groupViewlevel1.txtParent.getText().toString();
                String level_text =groupViewlevel1.txtLevel1.getText().toString();

                int test2 =findUsingEnhancedForLoop(name_text,parent_text,level_text,AllDataItem);

                AllDataItem.get(test2).setValue(value_text);

                //AllDataItem.set(groupPosition,new Level1("A","AA1","AA1","2","AA1"));
                //customListLevel3Adapter.notifyDataSetChanged();

                notifyDataSetChanged();
            }
        });
        //convertView.setLayoutParams(new ExpandableListView.LayoutParams(ExpandableListView.LayoutParams.MATCH_PARENT,200));
        return convertView;
    };

    public int findUsingEnhancedForLoop(
            String name, String parent, String level, List<Level> Data) {
        int inloop = 0;
        int intval=0;
        for (Level data : Data) {
            if (data.getName().equals(name) && data.getParent().equals(parent) && data.getLevel().equals(level)) {
                intval =inloop;
                return intval;
            }
            inloop++;
        }
        return intval;
    }

    public void ExpandGroup1(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        ((ExpandableListView) parent).expandGroup(groupPosition);
    }

    public void CollapseGroup1(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        ((ExpandableListView) parent).collapseGroup(groupPosition);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        //ExpandableListView level2Custom = new ExpandableListView(this.activity);
        final CustomExpListView level2Custom = new CustomExpListView(this.activity);

        ItemLevel2 = new ArrayList<Level>();
        for(Level data : AllDataItem){
            if (data.getParent().equals(ChildLevel)){
                Level Level2Item = new Level();
                Level2Item.setParent(data.getParent());
                Level2Item.setName(data.getName());
                Level2Item.setValue(data.getValue());
                Level2Item.setLevel(data.getLevel());
                Level2Item.setChildLevel(data.getChildLevel());
                ItemLevel2.add(Level2Item);
            }
        }

            if (convertView == null) {
                customListLevel2Adapter = new ListLevel2Adapter(this.activity,AllDataItem, ItemLevel2);
                customListLevel2Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                //secondLevelELV.setGroupIndicator(null);
                level2Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
                level2Custom.setDividerHeight(0);
                level2Custom.setGroupIndicator(null);
                level2Custom.setLayoutParams(viewGroup.getLayoutParams());
                //level2Custom.requestLayout();
                //level2Custom.setScrollContainer(true);

                level2Custom.setAdapter(customListLevel2Adapter);
                level2Custom.CustomExpListView(customListLevel2Adapter,groupPosition);
                //setExpandableListViewHeightBasedOnChildren(level2Custom, customListLevel2Adapter, groupPosition);

            }
            else{
                customListLevel2Adapter = new ListLevel2Adapter(this.activity, AllDataItem, ItemLevel2);
                customListLevel2Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                //secondLevelELV.setGroupIndicator(null);
                level2Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
                level2Custom.setDividerHeight(0);
                level2Custom.setGroupIndicator(null);
                level2Custom.setLayoutParams(viewGroup.getLayoutParams());
                //level2Custom.requestLayout();
                //level2Custom.setScrollContainer(true);

                level2Custom.setAdapter(customListLevel2Adapter);
                level2Custom.CustomExpListView(customListLevel2Adapter,groupPosition);
                //setExpandableListViewHeightBasedOnChildren(level2Custom, customListLevel2Adapter,groupPosition);

            }
        //AllDataItem2.remove(groupPosition);
        return level2Custom;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}
