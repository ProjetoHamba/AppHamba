package com.apphamba.hamba.titulo.servicos;



import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Crédito por este algoritmo:
 *      Site: http://www.baeldung.com/java-collaborative-filtering-recommendations
 *      GitHub: https://github.com/eugenp/tutorials/tree/master/algorithms/src/main/java/com/baeldung/algorithms/slope_one
 */

//MUDEI DE PESSOA PARA USUARIO AS CHAMADAS
    //PRECISAMOS DE TESTES
public class SlopeOne {

    private Map<Titulo, Map<Titulo, Double>> matrizDeDiferenca = new HashMap<>();
    private Map<Titulo, Map<Titulo, Integer>> matrizDeFrequencia = new HashMap<>();
    private Map<Usuario, HashMap<Titulo, Double>> dadosDeSaida = new HashMap<>();

    private Map<Usuario, HashMap<Titulo, Double>> matrizFinal = new HashMap<>();
    private Map<Usuario, HashMap<Titulo, Double>> matrizInicial = new HashMap<>();

    private ArrayList<Titulo> listaTituloes = new ArrayList<>();
    private ArrayList<Titulo> listaRecomendados = new ArrayList<>();


    public SlopeOne(HashMap<Usuario, HashMap<Titulo, Double>> matriz, ArrayList<Titulo> listaTitulo) {
        matrizInicial = matriz;
        listaTituloes = listaTitulo;
    }

    public void slopeOne() {
        buildDifferencesMatrix(matrizInicial);
        predict(matrizInicial);

    }

    /**
     * Com base nos dados disponíveis, é calculado as relações entre os
     * usuários e número de ocorrências dos Titulos
     */

    private void buildDifferencesMatrix(Map<Usuario, HashMap<Titulo, Double>> data) {
        for (HashMap<Titulo, Double> user : data.values()) {
            for (Entry<Titulo, Double> e : user.entrySet()) {
                if (!matrizDeDiferenca.containsKey(e.getKey())) {
                    matrizDeDiferenca.put(e.getKey(), new HashMap<Titulo, Double>());
                    matrizDeFrequencia.put(e.getKey(), new HashMap<Titulo, Integer>());
                }

                for (Entry<Titulo, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (matrizDeFrequencia.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = matrizDeFrequencia.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (matrizDeDiferenca.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = matrizDeDiferenca.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    matrizDeFrequencia.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    matrizDeDiferenca.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }

        for (Titulo j : matrizDeDiferenca.keySet()) {
            for (Titulo i : matrizDeDiferenca.get(j).keySet()) {
                double oldValue = matrizDeDiferenca.get(j).get(i).doubleValue();
                int count = matrizDeFrequencia.get(j).get(i).intValue();
                matrizDeDiferenca.get(j).put(i, oldValue / count);
            }
        }
    }

    /**
     * Com base nos dados existentes, prevê todas as classificações faltantes.
     * São dados de usuários existentes e classificações de seus Titulos.
     * @param data
     */

    private void predict(Map<Usuario, HashMap<Titulo, Double>> data) {
        HashMap<Titulo, Double> uPred = new HashMap<Titulo, Double>();
        HashMap<Titulo, Integer> uFreq = new HashMap<Titulo, Integer>();

        for (Titulo j : matrizDeDiferenca.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }

        for (Entry<Usuario, HashMap<Titulo, Double>> e : data.entrySet()) {
            for (Titulo j : e.getValue().keySet()) {
                for (Titulo k : matrizDeDiferenca.keySet()) {
                    try {
                        double predictedValue = matrizDeDiferenca.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * matrizDeFrequencia.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + matrizDeFrequencia.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }


            HashMap<Titulo, Double> clean = new HashMap<Titulo, Double>();
            for (Titulo j : uPred.keySet()) {
                Boolean v = clean.containsKey(j);
                if (uFreq.get(j) > 0 && !v) {
                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
                }
            }
            for (Titulo j : listaTituloes) {
                Boolean v = clean.containsKey(j);
                if (e.getValue().containsKey(j) && !v) {
                    clean.put(j, e.getValue().get(j));
                }
            }
            dadosDeSaida.put(e.getKey(), clean);
        }
        matrizFinal = dadosDeSaida;
    }

    public ArrayList<Titulo> getListaRecomendados(Usuario pessoa) {
        HashMap<Titulo, Double> matrizF = matrizFinal.get(pessoa);
        getRecomendadosAux(matrizF);
        return listaRecomendados;
    }

    public void getRecomendadosAux(HashMap<Titulo, Double> matrizFinal) {
        HashMap<Titulo, Double> m = new HashMap<>();

        ArrayList<Integer> l = new ArrayList<Integer>();
        try {
            for (Titulo Titulo : matrizFinal.keySet()) {
                int x = Titulo.getId();
                if (!l.contains(x)) {
                    l.add(Titulo.getId());
                    m.put(Titulo, matrizFinal.get(Titulo).doubleValue());
                    Titulo.setAvaliacaoUsuario(matrizFinal.get(Titulo).doubleValue());
                    listaRecomendados.add(Titulo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}