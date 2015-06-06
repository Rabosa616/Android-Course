package com.example.coordenadas_geograficas;

/**
 * Created by Rabosa on 17/05/2015.
 */
public class Principal {
    public static void main(String[] main) {
        GeoPunto z, w;
        z = new GeoPunto(-1.5, 3.0);
        w = new GeoPunto(-1.2, 2.4);
        double distancia = z.distancia(w);
        System.out.println("Distancia : " + distancia);

    }
}
