package com.example.shamsad.tutionhub.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.shamsad.tutionhub.R;
import com.example.shamsad.tutionhub.activities.DetailActivity;
import com.example.shamsad.tutionhub.models.InfiniteFeedInfo;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by shamsad on 11/13/17.
 */

@Layout(R.layout.load_more_item_view)
public class ItemView {
    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.captionTxt)
    private TextView captionTxt;

    @View(R.id.salaryTxt)
    private TextView salaryTxt;

    @View(R.id.routineTxt)
    private TextView routineTxt;

    @View(R.id.timeTxt)
    private TextView timeTxt;

    private InfiniteFeedInfo mInfo;
    private Context mContext;

    public ItemView(Context context, InfiniteFeedInfo info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        titleTxt.setText(mInfo.getTitle());
        captionTxt.setText(mInfo.getCaption());
        salaryTxt.setText(mInfo.getSalary());
        routineTxt.setText(mInfo.getRoutine());
        timeTxt.setText(mInfo.getTime());
//        Glide.with(mContext).load(mInfo.getImageUrl()).into(imageView);
    }

    @Click(R.id.rootView)
	public void onCardClick() {
		Intent intent = new Intent(mContext, DetailActivity.class);
		intent.putExtra("title", mInfo.getTitle());
        intent.putExtra("caption", mInfo.getCaption());
        intent.putExtra("salary", mInfo.getSalary());
        intent.putExtra("routine", mInfo.getRoutine());
        intent.putExtra("time", mInfo.getTime());
        Log.d("sajid", "onClick");
        mContext.startActivity(intent);
	}

}

