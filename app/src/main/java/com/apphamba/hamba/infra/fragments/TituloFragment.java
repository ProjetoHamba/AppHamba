package com.apphamba.hamba.infra.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apphamba.hamba.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TituloFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_titulo, container, false);

        int imgTitulo = getArguments().getInt("imgTitulo", 0);
        if (imgTitulo > 0) {
            ImageView img = (ImageView) view.findViewById(R.id.img);
			// (1) Chave da animação
            //Aq embaixo pega a string
            ViewCompat.setTransitionName(img, getString(R.string.transition_key));
            img.setImageResource(imgTitulo);
        }

        return view;
    }

}
