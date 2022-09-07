package com.obiektowe.projekt.rosliny;

import com.obiektowe.projekt.Roslina;
import com.obiektowe.projekt.Swiat;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 11, pozycjaX, pozycjaY, 'B', "BarszczSosnowskiego", Color.orange);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        int zasianie = rand.nextInt(100);
        if(zasianie < PRAWDOPODOBIENSTWO_ROZSIANIA) {
            rozsianie();
        }
        for(int i=getPozycjaX()-1; i<= getPozycjaX()+1; i++){
            for(int j=getPozycjaY()-1; j<= getPozycjaY()+1; j++){
                if (swiat.getOrganizm(i, j) != null && swiat.getOrganizm(i, j).getInicjatywa() > 0){
                    swiat.getPlansza().wpiszKomentarz(nazwa + " zabija " + swiat.getOrganizm(i,j).getNazwa());
                    swiat.usunOrganizm(i,j);
                }
            }
        }
    }
}
