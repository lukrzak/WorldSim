package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Organizm;
import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Zolw extends Zwierze {
    public Zolw(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 2, 1, pozycjaX, pozycjaY, 'Z', "Zolw", Color.darkGray);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        final int SZANSA_RUCHU = 25;
        int zmianaPolozenia = rand.nextInt(100);
        if(zmianaPolozenia < SZANSA_RUCHU) ruch();
    }

    @Override
    public void kolizja(Organizm atakujacy, Organizm obronca){
        if(atakujacy.getSila() < 5) swiat.getPlansza().wpiszKomentarz("Żółw odparł atak");
        else super.kolizja(atakujacy, obronca);
    }
}
