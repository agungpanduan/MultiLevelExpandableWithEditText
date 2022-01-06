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
import java.util.List;

public class ListLevel3Adapter extends BaseExpandableListAdapter {
    public LayoutInflater minflater;
    private Activity activity;
    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    public static ArrayList<Level> AllDataItem;
    public static ArrayList<Level> ItemLevel3;
    public static ArrayList<Level> ItemLevel4;
    private String ChildLevel;
    private Level func;

    private ListLevel4Adapter customListLevel4Adapter;

    public ListLevel3Adapter(Activity activity, ArrayList<Level> AllDataItem, ArrayList<Level> ItemLevel3
    ) {
        this.activity = activity;
        this.AllDataItem = AllDataItem;
        this.ItemLevel3 = ItemLevel3;
//        this.mContext = mContext;
    }

    private class GroupViewlevel3 {
        public TextView txtParent;
        public TextView txtLevel3Name;
        public EditText txtLevel3Value;
        public Button btnExpandLevel3;
        public TextView txtLevel3;
    }

    @Override
    public int getGroupCount() {
        return ItemLevel3.size();
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

    private int indexstop=0;
    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final ListLevel3Adapter.GroupViewlevel3 groupViewlevel3;

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.level3, null);
            groupViewlevel3 = new ListLevel3Adapter.GroupViewlevel3();
            groupViewlevel3.btnExpandLevel3 = (Button) convertView.findViewById(R.id.btnExpandLevel3);
            groupViewlevel3.txtParent = (TextView) convertView.findViewById(R.id.txtParent);
            groupViewlevel3.txtLevel3Name = (TextView) convertView.findViewById(R.id.txtLevel3Name);
            groupViewlevel3.txtLevel3Value = (EditText) convertView.findViewById(R.id.txtLevel3Value);
            groupViewlevel3.txtLevel3 = (TextView) convertView.findViewById(R.id.txtLevel3);

            groupViewlevel3.txtParent.setText(ItemLevel3.get(groupPosition).getParent());
            groupViewlevel3.txtLevel3Name.setText(ItemLevel3.get(groupPosition).getName());
            groupViewlevel3.txtLevel3Value.setText(ItemLevel3.get(groupPosition).getValue());
            groupViewlevel3.btnExpandLevel3.setTag(ItemLevel3.get(groupPosition).getChildLevel());
            groupViewlevel3.txtLevel3.setText(ItemLevel3.get(groupPosition).getLevel());

            convertView.setTag(groupViewlevel3);
        }
        else{
            groupViewlevel3 = (ListLevel3Adapter.GroupViewlevel3) convertView.getTag();
        }

        groupViewlevel3.btnExpandLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //groupItem.get(groupPosition).getArrayList().add(new EdittextValues(""));
                ChildLevel =groupViewlevel3.btnExpandLevel3.getTag().toString();
                for(int l=0; l<=ItemLevel3.size(); l++){
                    ((ExpandableListView) viewGroup).collapseGroup(l);
                }

                if(isExpanded)
                {
                    CollapseGroup3(groupPosition,viewGroup);
                } else
                {
                    ExpandGroup3(groupPosition,viewGroup);
                    //masalahnya data expand tidak ada
                }

                getChildrenCount(groupPosition);
                notifyDataSetChanged();
            }
        });

        indexstop=0;
        groupViewlevel3.txtLevel3Value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value_text = editable.toString();
                String name_text = groupViewlevel3.txtLevel3Name.getText().toString();
                String parent_text = groupViewlevel3.txtParent.getText().toString();
                String level_text =groupViewlevel3.txtLevel3.getText().toString();

                int test2 =findUsingEnhancedForLoop(name_text,parent_text,level_text,AllDataItem);

                AllDataItem.get(test2).setValue(value_text);

                //AllDataItem.set(groupPosition,new Level1("A","AA1","AA1","2","AA1"));
                //customListLevel3Adapter.notifyDataSetChanged();

                notifyDataSetChanged();
            }
        });

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

    public void ExpandGroup3(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).expandGroup(groupPosition);
    }

    public void CollapseGroup3(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).collapseGroup(groupPosition);
    }

    //masalah pertama, data tidak berdasarkan jumlah child per group tp seluruh data child jadi
    //ada masuk data yang bukan termasuk group
    //jika childrencount = child size maka
    private int indext=0;
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final CustomExpListView level4Custom = new CustomExpListView(this.activity);

        ItemLevel4 = new ArrayList<Level>();
        for(Level data : AllDataItem){
            if (data.getParent().equals(ChildLevel)){
                Level Level4Item = new Level();
                Level4Item.setParent(data.getParent());
                Level4Item.setName(data.getName());
                Level4Item.setValue(data.getValue());
                Level4Item.setLevel(data.getLevel());
                Level4Item.setChildLevel(data.getChildLevel());
                ItemLevel4.add(Level4Item);
            }
        }

        if (convertView == null) {
            customListLevel4Adapter = new ListLevel4Adapter(this.activity, AllDataItem, ItemLevel4);
            customListLevel4Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

            level4Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
            level4Custom.setDividerHeight(0);
            level4Custom.setGroupIndicator(null);
            level4Custom.setAdapter(customListLevel4Adapter);
        }
        else{
            customListLevel4Adapter = new ListLevel4Adapter(this.activity, AllDataItem, ItemLevel4);
            customListLevel4Adapter.setInflater((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

            level4Custom.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); //beforeDescendants
            level4Custom.setDividerHeight(0);
            level4Custom.setGroupIndicator(null);
            level4Custom.setAdapter(customListLevel4Adapter);
        }
        indext++;
        //}
        //AllDataItem3.remove(groupPosition);
        return level4Custom;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
