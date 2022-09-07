package com.obiektowe.projekt.rosliny;

import com.obiektowe.projekt.Organizm;
import com.obiektowe.projekt.Roslina;
import com.obiektowe.projekt.Swiat;

import java.awt.*;

public class Guarana extends Roslina {
    public Guarana(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 0, pozycjaX, pozycjaY, 'G', "Guarana", Color.MAGENTA);
    }

    public void kolizja(Organizm atakujacy, Organizm obronca){
        atakujacy.setSila(atakujacy.getSila()+3);
        swiat.usunOrganizm(obronca.getPozycjaX(), obronca.getPozycjaY());
        atakujacy.setPozycjaX(obronca.getPozycjaX());
        atakujacy.setPozycjaY(obronca.getPozycjaY());
    }
}
