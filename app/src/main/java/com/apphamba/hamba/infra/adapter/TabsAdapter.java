package com.apphamba.hamba.infra.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.EnumTitulos;
import com.apphamba.hamba.infra.fragments.TituloListFragment;
import com.apphamba.hamba.infra.servicos.FiltroTitulo;
import com.apphamba.hamba.titulo.servicos.ServicoTitulo;


public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.tab_text_1);
        } else if (position == 1) {
            return context.getString(R.string.tab_text_2);
        }else {
            return context.getString(R.string.tab_text_3);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        ServicoTitulo servicoTitulo = new ServicoTitulo();
        if (position == 0) {
            FiltroTitulo.instance.setTitulosList(servicoTitulo.getTitulos());
            f = TituloListFragment.newInstance(R.string.tab_text_1);
        } else if (position == 1) {
            FiltroTitulo.instance.setTitulosList(servicoTitulo.getTitulos(EnumTitulos.SERIE.getDescricao()));
            f = TituloListFragment.newInstance(R.string.tab_text_2);
        } else {
            FiltroTitulo.instance.setTitulosList(servicoTitulo.getTitulos(EnumTitulos.FILME.getDescricao()));
            f = TituloListFragment.newInstance(R.string.tab_text_3);
        }
        return f;
    }
}
