package com.example.MultiLevelExpandableListView.Adapter;

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
import android.widget.TextView;

import com.example.MultiLevelExpandableListView.Model.Level;
import com.example.MultiLevelExpandableListView.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListLevel2Adapter extends BaseExpandableListAdapter {
    public LayoutInflater minflater;
    private Activity activity;
    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    public static ArrayList<Level> AllDataItem;
    public static ArrayList<Level> ItemLevel2;
    public static ArrayList<Level> ItemLevel3;
    private String ChildLevel;
    private ListLevel3Adapter customListLevel3Adapter;
    private HashMap<String, String> child;
    private Level func;

    public ListLevel2Adapter(Activity activity, ArrayList<Level> AllDataItem, ArrayList<Level> ItemLevel2
    ) {
        this.activity = activity;
        this.AllDataItem = AllDataItem;
        this.ItemLevel2 = ItemLevel2;
//        this.mContext = mContext;
    }

    private class GroupViewlevel2 {
        public TextView txtParent;
        public TextView txtLevel2Name;
        public EditText txtLevel2Value;
        public Button btnExpandLevel2;
        public TextView txtLevel2;
    }

    @Override
    public int getGroupCount() {
        return ItemLevel2.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;//because call New Adapter, so dont need looping
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
        final GroupViewlevel2 groupViewlevel2;

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.level2, null);
            groupViewlevel2 = new GroupViewlevel2();
            groupViewlevel2.btnExpandLevel2 = (Button) convertView.findViewById(R.id.btnExpandLevel2);
            groupViewlevel2.txtParent = (TextView) convertView.findViewById(R.id.txtParent);
            groupViewlevel2.txtLevel2Name = (TextView) convertView.findViewById(R.id.txtLevel2Name);
            groupViewlevel2.txtLevel2Value = (EditText) convertView.findViewById(R.id.txtLevel2Value);
            groupViewlevel2.txtLevel2 =(TextView) convertView.findViewById(R.id.txtLevel2);

            groupViewlevel2.txtParent.setText(ItemLevel2.get(groupPosition).getParent());
            groupViewlevel2.txtLevel2Name.setText(ItemLevel2.get(groupPosition).getName());
            groupViewlevel2.txtLevel2Value.setText(ItemLevel2.get(groupPosition).getValue());
            groupViewlevel2.btnExpandLevel2.setTag(ItemLevel2.get(groupPosition).getChildLevel());
            groupViewlevel2.txtLevel2.setText(ItemLevel2.get(groupPosition).getLevel());

            convertView.setTag(groupViewlevel2);
        }
        else{
            groupViewlevel2 = (GroupViewlevel2) convertView.getTag();
        }

        groupViewlevel2.btnExpandLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChildLevel =groupViewlevel2.btnExpandLevel2.getTag().toString();
                //groupItem.get(groupPosition).getArrayList().add(new EdittextValues(""));
                //refresh all collapse
                for(int l=0; l<=ItemLevel2.size(); l++){
                    ((ExpandableListView) viewGroup).collapseGroup(l);
                }

                if(isExpanded)
                {
                    CollapseGroup2(groupPosition,viewGroup);
                } else
                {
                    ExpandGroup2(groupPosition,viewGroup);
                    //masalahnya data expand tidak ada
                }

                getChildrenCount(groupPosition);
                notifyDataSetChanged();
            }
        });

        groupViewlevel2.txtLevel2Value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value_text = editable.toString();
                String name_text = groupViewlevel2.txtLevel2Name.getText().toString();
                String parent_text = groupViewlevel2.txtParent.getText().toString();
                String level_text =groupViewlevel2.txtLevel2.getText().toString();

                int test2 =findUsingEnhancedForLoop(name_text,parent_text,level_text,AllDataItem);

                AllDataItem.get(test2).setValue(value_text);

                //AllDataItem.set(groupPosition,new Level1("A","AA1","AA1","2","AA1"));
                //customListLevel3Adapter.notifyDataSetChanged();

                notifyDataSetChanged();
            }
        });

        //convertView.setLayoutParams(new ExpandableListView.LayoutParams(ExpandableListView.LayoutParams.MATCH_PARENT,200));
        return convertView;
    }

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

    public void ExpandGroup2(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).expandGroup(groupPosition);
        int sub_item_size = 40;  // 40px
        //((ExpandableListView) parent).getLayoutParams().height += sub_item_size*groupPosition;
    }

    public void CollapseGroup2(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).collapseGroup(groupPosition);

        int sub_item_size = 40;  // 40px
        //((ExpandableListView) parent).getLayoutParams().height -= sub_item_size*groupPosition;
    }

    //masalah pertama, data tidak berdasarkan jumlah child per group tp seluruh data child jadi
    //ada masuk data yang bukan termasuk group
    //jika childrencount = child size maka
    private int indext=0;
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final CustomExpListView level3Custom = new CustomExpListView(this.activity);

        ItemLevel3 = new ArrayList<Level>();
        for(Level data : AllDataItem){
            if (data.getParent().equals(ChildLevel)){
                Level Level3Item = new Level();
                Level3Item.setParent(data.getParent());
                Level3Item.setName(data.getName());
                Level3Item.setValue(data.getValue());
                Level3Item.setLevel(data.getLevel());
                Level3Item.setChildLevel(data.getChildLevel());
                ItemLevel3.add(Level3Item);
            }
        }

            if (convertView == null) {
                customListLevel3Adapter = new ListLevel3Adapter(this.activity, AllDataItem, ItemLevel3);
                customListLevel3Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

                level3Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
                level3Custom.setDividerHeight(0);
                level3Custom.setGroupIndicator(null);
                level3Custom.setAdapter(customListLevel3Adapter);
            }
            else{
                customListLevel3Adapter = new ListLevel3Adapter(this.activity, AllDataItem, ItemLevel3);
                customListLevel3Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

                level3Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
                level3Custom.setDividerHeight(0);
                level3Custom.setGroupIndicator(null);
                level3Custom.setAdapter(customListLevel3Adapter);
            }
            indext++;
        //}
        //AllDataItem3.remove(groupPosition);

        return level3Custom;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
