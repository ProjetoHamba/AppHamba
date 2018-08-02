package com.apphamba.hamba.titulo.gui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.apphamba.hamba.R;

public class TituloActivity extends android.support.v7.app.AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbar;

    private ImageView appBarImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_titulo);
        setUpToolbar();
    }

    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Título da CollapsingToolbarLayout
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        // Header
        appBarImg = (ImageView) findViewById(R.id.appBarImg);
        ViewCompat.setTransitionName(appBarImg, getString(R.string.transition_key));
    }
    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void setAppBarTitle(String title) {
        collapsingToolbar.setTitle(title);
    }

    public void setAppBarImage(int resId) {
        appBarImg.setImageResource(resId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
