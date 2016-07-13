package com.task.vasilyevanton.task.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.task.vasilyevanton.task.R;
import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<PeopleData> {

    protected List<PeopleData> mDataList;
    private Activity activity;
    private Integer mLayout;


    public PeopleAdapter(Activity context, Integer mLayout, List<PeopleData> dataList) {
        super(context, mLayout, dataList);
        this.activity = context;
        this.mLayout = mLayout;
        this.mDataList = dataList;
    }

    @Override
    public View getView(final int position, View mConvertView, ViewGroup parent) {

        final ViewHolder mHolder;
        if (mConvertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            mConvertView = inflater.inflate(this.mLayout, null, false);
            mHolder = new ViewHolder();
            //
            mHolder.name = (TextView) mConvertView.findViewById(R.id.html_url);
            mHolder.image = (ImageView) mConvertView.findViewById(R.id.image_people);

            //
            mConvertView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) mConvertView.getTag();
        }
        mHolder.name.setText(mDataList.get(position).getHtmlUrl());

        Log.w("ImUri", mDataList.get(position).getAvatarUrl()+"");
        Picasso.with(activity).load(mDataList.get(position).getAvatarUrl()).into(mHolder.image);
        return mConvertView;
    }

    static class ViewHolder {
        public TextView name;
        public ImageView image;
    }

}
