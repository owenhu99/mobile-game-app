package com.example.game.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.game.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_pictures = {
            R.drawable.pikachu,
            R.drawable.pikachu2
    };
    public String[] slide_headings = {
            "TITLE",
            "TITLE"
    };

    public String[] slide_desc = {
            "Description",
            "Description"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.activity_slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.image);
        TextView slideTitle = (TextView) view.findViewById(R.id.title);
        TextView slideDescription = (TextView) view.findViewById(R.id.desc);

        slideImageView.setImageResource(slide_pictures[position]);
        slideTitle.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, @NonNull Object object){
        container.removeView((RelativeLayout) object);
    }
}
