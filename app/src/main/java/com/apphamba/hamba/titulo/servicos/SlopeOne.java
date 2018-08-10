package com.apphamba.hamba.titulo.servicos;



import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.usuario.dominio.Usuario;

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

    private Map<Usuario, Map<Titulo, Double>> data = new HashMap<>();
    private Map<Usuario, Map<Titulo, Double>> mData;
    private Map<Titulo, HashMap<Titulo, Double>> diffMatrix;
    private Map<Titulo, HashMap<Titulo, Integer>> freqMatrix;


    public SlopeOne(Map<Usuario, Map<Titulo, Double>> data) {
        mData = data;
        buildDiffMatrix();
    }


    private void buildDiffMatrix() {
        diffMatrix = new HashMap<Titulo, HashMap<Titulo, Double>>();
        freqMatrix = new HashMap<Titulo, HashMap<Titulo, Integer>>();
        for (Map<Titulo, Double> user : mData.values()) {
            for (Entry<Titulo, Double> entry : user.entrySet()) {
                Titulo i1 = entry.getKey();
                double r1 = entry.getValue();
                if (!diffMatrix.containsKey(i1)) {
                    diffMatrix.put(i1, new HashMap<Titulo, Double>());
                    freqMatrix.put(i1, new HashMap<Titulo, Integer>());
                }
                for (Entry<Titulo, Double> entry2 : user.entrySet()) {
                    Titulo i2 = entry2.getKey();
                    double r2 = entry2.getValue();
                    int cnt = 0;
                    if (freqMatrix.get(i1).containsKey(i2))
                        cnt = freqMatrix.get(i1).get(i2);
                    double diff = 0.0;
                    if (diffMatrix.get(i1).containsKey(i2))
                        diff = diffMatrix.get(i1).get(i2);
                    double new_diff = r1 - r2;
                    freqMatrix.get(i1).put(i2, cnt + 1);
                    diffMatrix.get(i1).put(i2, diff + new_diff);
                }
            }
        }
        for (Titulo j : diffMatrix.keySet()) {
            for (Titulo i : diffMatrix.get(j).keySet()) {
                Double oldvalue = diffMatrix.get(j).get(i);
                int count = freqMatrix.get(j).get(i).intValue();
                diffMatrix.get(j).put(i, oldvalue / count);
            }
        }
    }
    public Map<Titulo, Double> predict(Map<Titulo, Double> user) {
        HashMap<Titulo, Double> predictions = new HashMap<>();
        HashMap<Titulo, Integer> frequencies = new HashMap<>();
        for (Titulo j : diffMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Titulo j : user.keySet()) {
            for (Titulo k : diffMatrix.keySet()) {
                try {
                    Double newval = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        HashMap<Titulo, Double> cleanpredictions = new HashMap<>();
        for (Titulo j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
            }
        }
        for (Titulo j : user.keySet()) {
            cleanpredictions.put(j, user.get(j));
        }
        return cleanpredictions;
    }



}
