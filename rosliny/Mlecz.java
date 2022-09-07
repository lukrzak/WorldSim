package com.obiektowe.projekt.rosliny;

import com.obiektowe.projekt.Roslina;
import com.obiektowe.projekt.Swiat;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Mlecz extends Roslina {
    public Mlecz(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 0, pozycjaX, pozycjaY, 'M', "Mlecz", Color.yellow);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        for(int i = 0; i<3; i++){
            int zasianie = rand.nextInt(100);
            if(zasianie < PRAWDOPODOBIENSTWO_ROZSIANIA) {
                rozsianie();
            }

        }
    }
}
