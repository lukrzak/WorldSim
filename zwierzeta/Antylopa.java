package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Organizm;
import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Antylopa extends Zwierze {
    public Antylopa(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 4, 4, pozycjaX, pozycjaY, 'A', "Antylopa", Color.pink);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        ruch();
        ruch();
    }

    @Override
    public void kolizja(Organizm atakujacy, Organizm obronca){
        final int SZANSA_UCIECZKI = 50;
        int ucieczka = rand.nextInt(100);
        if(ucieczka < SZANSA_UCIECZKI){
            swiat.getPlansza().wpiszKomentarz("Antylopa uniknęła walki");
            int nowaPozycjaX = pozycjaX;
            int nowaPozycjaY = pozycjaY;
            do{
                int kierunek = rand.nextInt(4);
                if(kierunek == 0 && pozycjaY > 0) nowaPozycjaY--;
                else if(kierunek == 1 && pozycjaX > 0) nowaPozycjaX--;
                else if(kierunek == 2 && pozycjaY < 19) nowaPozycjaY++;
                else if(kierunek == 3 && pozycjaX < 19) nowaPozycjaX++;
            }while(swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY) != null);
            setPozycjaX(nowaPozycjaX);
            setPozycjaY(nowaPozycjaY);
        }
        else super.kolizja(atakujacy, obronca);
    }
}
