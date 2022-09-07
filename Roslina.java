package com.obiektowe.projekt;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Roslina extends Organizm{
    protected final int PRAWDOPODOBIENSTWO_ROZSIANIA = 15;

    public Roslina(Swiat swiat, int sila, int pozycjaX, int pozycjaY, char symbol, String nazwa, Color kolor){
        super(swiat, sila, 0, pozycjaX, pozycjaY, symbol, nazwa, kolor);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        int zasianie = rand.nextInt(100);
        if(zasianie < PRAWDOPODOBIENSTWO_ROZSIANIA) {
            rozsianie();
        }
    }

    public void rozsianie() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        int kierunek = rand.nextInt(4);
        int nowaPozycjaX = getPozycjaX();
        int nowaPozycjaY = getPozycjaY();

        if(kierunek == 0 && getPozycjaY() > 0) nowaPozycjaY--;
        else if(kierunek == 1 && getPozycjaX() > 0) nowaPozycjaX--;
        else if(kierunek == 2 && getPozycjaY() < 19) nowaPozycjaY++;
        else if(kierunek == 3 && getPozycjaX() < 19) nowaPozycjaX++;

        if((nowaPozycjaX != getPozycjaX() || nowaPozycjaY != getPozycjaY())
                && swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY) == null)
        {
            swiat.getPlansza().wpiszKomentarz("Na polu "+nowaPozycjaX+","+nowaPozycjaY+" wyrosl "+nazwa);
            Class <?> nowyObiekt = Class.forName("com.obiektowe.projekt.rosliny."+nazwa);
            Constructor<?> constructor = nowyObiekt.getConstructor(Swiat.class, int.class, int.class);
            swiat.dodajOrganizm((Organizm) constructor.newInstance(swiat, nowaPozycjaX, nowaPozycjaY));
        }
    }
}
