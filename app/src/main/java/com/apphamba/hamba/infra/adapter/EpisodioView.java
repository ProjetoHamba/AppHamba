package com.apphamba.hamba.infra.adapter;

public class EpisodioView {
    boolean isSelected;
    String descEp;
    int numEp;

    public void setNumEp(int numEp) {
        this.numEp = numEp;
    }

    public EpisodioView(boolean selecionado, String eita) {
        this.isSelected = selecionado;
        this.descEp = eita;
    }

    public EpisodioView(int numEp, String descEp, Boolean isSelected) {
        this.numEp = numEp;
        this.descEp = descEp;
        this.isSelected = isSelected;
    }

    public String getDescEp() {
        return descEp;
    }

    public void setDescEp(String descEp) {
        this.descEp = descEp;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
