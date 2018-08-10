package com.apphamba.hamba.titulo.servicos;




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

    private Map<Usuario, Map<String, Double>> data = new HashMap<>();
    private Map<Usuario, Map<String, Double>> mData;
    private Map<String, HashMap<String, Double>> diffMatrix;
    private Map<String, HashMap<String, Integer>> freqMatrix;


    public SlopeOne(Map<Usuario, Map<String, Double>> data) {
        mData = data;
        buildDiffMatrix();
    }


    private void buildDiffMatrix() {
        diffMatrix = new HashMap<String, HashMap<String, Double>>();
        freqMatrix = new HashMap<String, HashMap<String, Integer>>();
        for (Map<String, Double> user : mData.values()) {
            for (Entry<String, Double> entry : user.entrySet()) {
                String i1 = entry.getKey();
                double r1 = entry.getValue();
                if (!diffMatrix.containsKey(i1)) {
                    diffMatrix.put(i1, new HashMap<String, Double>());
                    freqMatrix.put(i1, new HashMap<String, Integer>());
                }
                for (Entry<String, Double> entry2 : user.entrySet()) {
                    String i2 = entry2.getKey();
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
        for (String j : diffMatrix.keySet()) {
            for (String i : diffMatrix.get(j).keySet()) {
                Double oldvalue = diffMatrix.get(j).get(i);
                int count = freqMatrix.get(j).get(i).intValue();
                diffMatrix.get(j).put(i, oldvalue / count);
            }
        }
    }
    public Map<String, Double> predict(Map<String, Double> user) {
        HashMap<String, Double> predictions = new HashMap<>();
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String j : diffMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (String j : user.keySet()) {
            for (String k : diffMatrix.keySet()) {
                try {
                    Double newval = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        HashMap<String, Double> cleanpredictions = new HashMap<>();
        for (String j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
            }
        }
        for (String j : user.keySet()) {
            cleanpredictions.put(j, user.get(j));
        }
        return cleanpredictions;
    }



}
