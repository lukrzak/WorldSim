package com.obiektowe.projekt;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.awt.Color;

public abstract class Organizm implements Comparable<Organizm>{
    protected final Swiat swiat;
    protected Random rand = new Random();
    private final int inicjatywa;
    protected final Color kolor;
    protected final char symbol;
    protected int sila;
    protected int pozycjaX;
    protected int pozycjaY;
    protected int wiek = 0;
    protected String nazwa;

    Organizm(Swiat swiat, int sila, int inicjatywa, int pozycjaX, int pozycjaY, char symbol, String nazwa, Color kolor){
        this.swiat = swiat;
        this.inicjatywa = inicjatywa;
        this.sila = sila;
        this.pozycjaX = pozycjaX;
        this.pozycjaY = pozycjaY;
        this.symbol = symbol;
        this.nazwa = nazwa;
        this.kolor = kolor;
    }

    public abstract void akcja() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    public void kolizja(Organizm atakujacy, Organizm obronca){
        if (atakujacy.getSila() >= obronca.getSila()){
            if(obronca.getInicjatywa() == 0) swiat.getPlansza().wpiszKomentarz(atakujacy.getNazwa() + " zjada " + nazwa + " na pozycji "
                    + pozycjaX + "," + pozycjaY);
            else swiat.getPlansza().wpiszKomentarz(atakujacy.getNazwa() + " zabija " + nazwa + " na pozycji "
                    + pozycjaX + "," + pozycjaY);
            swiat.usunOrganizm(obronca.getPozycjaX(), obronca.getPozycjaY());
            atakujacy.setPozycjaX(obronca.getPozycjaX());
            atakujacy.setPozycjaY(obronca.getPozycjaY());
        }
        else{
            String komentarz = nazwa + " zabija atakujacego " + atakujacy.getNazwa() + " na pozycji " + pozycjaX + "," + pozycjaY;
            swiat.getPlansza().wpiszKomentarz(komentarz);
            swiat.usunOrganizm(atakujacy.getPozycjaX(), atakujacy.getPozycjaY());
        }
    }

    public Swiat getSwiat() {
        return swiat;
    }

    public int getSila() {
        return sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public int getPozycjaX() {
        return pozycjaX;
    }

    public int getPozycjaY() {
        return pozycjaY;
    }

    public int getWiek() {
        return wiek;
    }

    public char getSymbol() {return symbol;}

    public void setSila(int sila) {
        this.sila = sila;
    }

    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
    }

    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Color getKolor() {
        return kolor;
    }

    public void zwiekszWiek(){
        wiek++;
    }

    @Override
    public int compareTo(Organizm o){
        int diff = o.getInicjatywa() - this.inicjatywa;
        if (diff == 0) diff = o.getWiek() - this.wiek;
        return diff;
    }
}
