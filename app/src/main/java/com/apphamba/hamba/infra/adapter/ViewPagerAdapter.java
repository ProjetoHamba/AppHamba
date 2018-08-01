package com.apphamba.hamba.infra.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apphamba.hamba.R;

public class ViewPagerAdapter extends PagerAdapter{
    private Integer[] images = {R.drawable.got,R.drawable.breakingbad};
    private Context context;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.viewpager_item, null);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageDetalhe);
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(item_view,0);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,Object object) {
        ViewPager viewPager = (ViewPager)container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
