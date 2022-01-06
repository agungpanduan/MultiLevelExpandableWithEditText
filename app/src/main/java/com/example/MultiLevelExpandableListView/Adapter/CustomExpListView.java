package com.example.MultiLevelExpandableListView.Adapter;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class CustomExpListView extends ExpandableListView
{
    public CustomExpListView(Activity activity)
    {
        super(activity);
    }

    boolean expanded = false;

    public CustomExpListView(Context context) {
        super(context);
    }

    public CustomExpListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //CARA 1
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(99999, MeasureSpec.AT_MOST);
        //heightMeasureSpec = MeasureSpec.makeMeasureSpec(99999, MeasureSpec.getMode(1000));//MeasureSpec is reduce allocation ukuran awal agar sesuai jumlah item

        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isExpanded()) {
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

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
    }

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