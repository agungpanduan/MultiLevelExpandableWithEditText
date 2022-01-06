package com.example.MultiLevelExpandableListView.Adapter;

import android.app.Activity;
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

public class ListLevel4Adapter extends BaseExpandableListAdapter {
    public LayoutInflater minflater;
    private Activity activity;
    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    public static ArrayList<Level> AllDataItem;
    public static ArrayList<Level> ItemLevel4;
    private Level func;

    public ListLevel4Adapter(Activity activity, ArrayList<Level> AllDataItem, ArrayList<Level> ItemLevel4
    ) {
        this.activity = activity;
        this.AllDataItem = AllDataItem;
        this.ItemLevel4 = ItemLevel4;
//        this.mContext = mContext;
    }

    private class GroupViewlevel4 {
        public TextView txtParent;
        public TextView txtLevel4Name;
        public EditText txtLevel4Value;
        public Button btnExpandLevel4;
        public TextView txtLevel4;
    }

    private class CloseViewlevel {
        public TextView txtCloseLevel;
    }

    CloseViewlevel closeViewlevel;
    @Override
    public int getGroupCount() {
        return ItemLevel4.size();
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
        final ListLevel4Adapter.GroupViewlevel4 groupViewlevel4;

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.level4, null);
            groupViewlevel4 = new ListLevel4Adapter.GroupViewlevel4();
            groupViewlevel4.btnExpandLevel4 = (Button) convertView.findViewById(R.id.btnExpandLevel4);
            groupViewlevel4.txtParent = (TextView) convertView.findViewById(R.id.txtParent);
            groupViewlevel4.txtLevel4Name = (TextView) convertView.findViewById(R.id.txtLevel4Name);
            groupViewlevel4.txtLevel4Value = (EditText) convertView.findViewById(R.id.txtLevel4Value);
            groupViewlevel4.txtLevel4 = (TextView) convertView.findViewById(R.id.txtLevel4);

            groupViewlevel4.txtParent.setText(ItemLevel4.get(groupPosition).getParent());
            groupViewlevel4.txtLevel4Name.setText(ItemLevel4.get(groupPosition).getName());
            groupViewlevel4.txtLevel4Value.setText(ItemLevel4.get(groupPosition).getValue());
            groupViewlevel4.btnExpandLevel4.setTag(ItemLevel4.get(groupPosition).getChildLevel());
            groupViewlevel4.txtLevel4.setText(ItemLevel4.get(groupPosition).getLevel());

            convertView.setTag(groupViewlevel4);
        }
        else{
            groupViewlevel4 = (ListLevel4Adapter.GroupViewlevel4) convertView.getTag();
        }

        groupViewlevel4.btnExpandLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //groupItem.get(groupPosition).getArrayList().add(new EdittextValues(""));
                if(isExpanded)
                {
                    CollapseGroup4(groupPosition,viewGroup);
                } else
                {
                    ExpandGroup4(groupPosition,viewGroup);
                    //masalahnya data expand tidak ada
                }

                getChildrenCount(groupPosition);
                notifyDataSetChanged();
            }
        });

        indexstop=0;
        groupViewlevel4.txtLevel4Value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value_text = editable.toString();
                String name_text = groupViewlevel4.txtLevel4Name.getText().toString();
                String parent_text = groupViewlevel4.txtParent.getText().toString();
                String level_text =groupViewlevel4.txtLevel4.getText().toString();

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

    public void ExpandGroup4(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).expandGroup(groupPosition);
    }

    public void CollapseGroup4(int groupPosition,ViewGroup parent ){
        //ExpandableListView eLV = (ExpandableListView) parent;
        indext=0;
        ((ExpandableListView) parent).collapseGroup(groupPosition);
    }

    private int indext=0;
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final CloseViewlevel closeViewlevel;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.closelevel, null);
            closeViewlevel = new CloseViewlevel();
            closeViewlevel.txtCloseLevel = (TextView)convertView.findViewById(R.id.txtCloseLevel);
            convertView.setTag(closeViewlevel);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
