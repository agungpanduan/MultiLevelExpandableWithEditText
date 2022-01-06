package com.example.MultiLevelExpandableListView.Adapter;
import android.app.Activity;
import android.widget.ExpandableListView;

public class CustomExpListView2 extends ExpandableListView
{
    public CustomExpListView2(Activity activity)
    {
        super(activity);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // measureChildren(widthMeasureSpec+5,heightMeasureSpec);
    }


}