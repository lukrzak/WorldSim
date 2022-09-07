package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Lis extends Zwierze {
    public Lis(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 2,7, pozycjaX, pozycjaY, 'L', "Lis", Color.lightGray);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        boolean czyWykonanoRuch = true;

        int kierunek = rand.nextInt(4);
        int nowaPozycjaX = getPozycjaX();
        int nowaPozycjaY = getPozycjaY();

        if(kierunek == 0 && getPozycjaY() > 0) nowaPozycjaY--;
        else if(kierunek == 1 && getPozycjaX() > 0) nowaPozycjaX--;
        else if(kierunek == 2 && getPozycjaY() < 19) nowaPozycjaY++;
        else if(kierunek == 3 && getPozycjaX() < 19) nowaPozycjaX++;
        else{
            czyWykonanoRuch = false;
            akcja();
        }

        if(czyWykonanoRuch){
            if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY) == null) {
                this.setPozycjaX(nowaPozycjaX);
                this.setPozycjaY(nowaPozycjaY);
            }
            else if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).getSymbol() == symbol) rozmnazanie();
            else if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).getSila() > sila)
                swiat.getPlansza().wpiszKomentarz("Lis uniknął silniejszego przeciwnika");
            else swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).kolizja(this, swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY));
        }
    }
}
