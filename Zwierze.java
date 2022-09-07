package com.obiektowe.projekt;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Zwierze extends Organizm{
    public Zwierze(Swiat swiat, int sila, int inicjatywa, int pozycjaX, int pozycjaY, char symbol,
                   String nazwa, Color kolor){
        super(swiat, sila, inicjatywa, pozycjaX, pozycjaY, symbol, nazwa, kolor);
    }

    @Override
    public void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        ruch();
    }

    public void kolizja(Organizm atakujacy, Organizm obronca){
        super.kolizja(atakujacy, obronca);
    }

    public void rozmnazanie() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        boolean czyRozmnozono = false;
        for(int i=pozycjaX-1; i<= pozycjaX+1; i++){
            if(czyRozmnozono) break;
            for(int j=pozycjaY-1; j<= pozycjaY+1; j++){
                if(swiat.getOrganizm(i, j) == null && !czyRozmnozono && i>0 && j>0 && i<20 && j<20){
                    Class <?> nowyObiekt = Class.forName("com.obiektowe.projekt.zwierzeta."+nazwa);
                    Constructor<?> constructor = nowyObiekt.getConstructor(Swiat.class, int.class, int.class);
                    swiat.dodajOrganizm((Organizm) constructor.newInstance(swiat, i, j));

                    czyRozmnozono = true;
                    String komunikat = nazwa + " rodzi sie na pozycji " + pozycjaX + "," +pozycjaY;
                    swiat.getPlansza().wpiszKomentarz(komunikat);
                }
            }
        }
    }

    public void ruch() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
            ruch();
        }

        if(czyWykonanoRuch){
            if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY) == null) {
                this.setPozycjaX(nowaPozycjaX);
                this.setPozycjaY(nowaPozycjaY);
            }
            else if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).getSymbol() == symbol) rozmnazanie();
            else swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).kolizja(this, swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY));
        }
    }
}