package com.apphamba.hamba.infra.adapter;

public class EpisodioView {
    boolean isSelected;
    String descEp;

    public EpisodioView(boolean selecionado, String eita) {
        this.isSelected = selecionado;
        this.descEp = eita;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
