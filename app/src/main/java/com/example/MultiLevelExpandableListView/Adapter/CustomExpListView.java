package com.example.MultiLevelExpandableListView.Adapter;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class CustomExpListView extends ExpandableListView
{
    public CustomExpListView(Activity activity)
    {
        super(activity);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        //heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // measureChildren(widthMeasureSpec+5,heightMeasureSpec);

        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();

        //kenapa gak bisa nambah Expandablistview untuk pertama karena
        //tidak ada penambahan di expandableist berikutnya
    }

    public void CustomExpListView(ListLevel2Adapter listAdapter, int group){
        //ListLevel2Adapter adapter = (ListLevel2Adapter) expandableListView.getExpandableListAdapter();
        //listAdapter = (ListLevel2Adapter) getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null,this);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (((isGroupExpanded(i)) && (i != group))
                    || ((!isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            this);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = getLayoutParams();
        int height = totalHeight
                + (getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        setLayoutParams(params);
        requestLayout();
    }

    public void CustomExpListView(ListLevel3Adapter listAdapter, int group){
        //ListLevel2Adapter adapter = (ListLevel2Adapter) expandableListView.getExpandableListAdapter();
        //listAdapter = (ListLevel2Adapter) getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null,this);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (((isGroupExpanded(i)) && (i != group))
                    || ((!isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            this);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = getLayoutParams();
        int height = totalHeight
                + (getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        setLayoutParams(params);
        requestLayout();
    }

    public void CustomExpListView(ListLevel4Adapter listAdapter, int group){
        //ListLevel2Adapter adapter = (ListLevel2Adapter) expandableListView.getExpandableListAdapter();
        //listAdapter = (ListLevel2Adapter) getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null,this);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (((isGroupExpanded(i)) && (i != group))
                    || ((!isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            this);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = getLayoutParams();
        int height = totalHeight
                + (getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        setLayoutParams(params);
        requestLayout();
    }

    /*protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec, ListLevel2Adapter adapter, int group) {
        //CARA 1
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // measureChildren(widthMeasureSpec+5,heightMeasureSpec);

        //super.setBackgroundResource(R.drawable.border);
        //.setBackgroundResource(R.drawable.radio_states_green);
        //getMeasuredHeight()
        // measureChildren(widthMeasureSpec+5,heightMeasureSpec);

        //CARA 2
        /*int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }*/

        /*width = widthSize;//test

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        //setMeasuredDimension(width, height);
        super.setMeasuredDimension(width, height);
        */

        //CARA 3
        /*widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(20000, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);*/
    //}

    /*public static void setExpandableListViewHeightBasedOnChildren(ExpandableListView expandableListView){
        ListLevel2Adapter adapter = (ListLevel2Adapter) expandableListView.getExpandableListAdapter();
        if (adapter == null){
            return;
        }
        int totalHeight = expandableListView.getPaddingTop() + expandableListView.getPaddingBottom();
        for (int i = 0 ; i < adapter.getGroupCount() ; i++){
            View groupItem = adapter.getGroupView(i, false, null, expandableListView);
            groupItem.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (expandableListView.isGroupExpanded(i) ){
                for( int j = 0 ; j < adapter.getChildrenCount(i) ; j++) {
                    View listItem = adapter.getChildView(i, j, false, null, expandableListView);
                    listItem.setLayoutParams(new LinearLayout.LayoutParams(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED));
                    listItem.measure(View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                            .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
        int height = totalHeight + expandableListView.getDividerHeight() * (adapter.getGroupCount() - 1);

        if (height < 10)
            height = 100;
        params.height = height;
        expandableListView.setLayoutParams(params);
        expandableListView.requestLayout();
    }*/
}