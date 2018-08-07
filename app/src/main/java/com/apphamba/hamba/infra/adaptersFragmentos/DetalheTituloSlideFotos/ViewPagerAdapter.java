package com.apphamba.hamba.infra.adaptersFragmentos.DetalheTituloSlideFotos;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Integer[] images = {R.drawable.got, R.drawable.breakingbad};
    private List<Bitmap> imagens;
    private Context context;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, ArrayList<Bitmap> imagens) {
        this.context = context;
        this.imagens = imagens;

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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.viewpager_item, null);
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        Bitmap imagemBitMap = imagens.get(position);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageDetalhe);
        imageView.setImageBitmap(imagemBitMap);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(item_view, 0);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}