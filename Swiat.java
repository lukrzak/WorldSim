package com.obiektowe.projekt;

import com.obiektowe.projekt.rosliny.*;
import com.obiektowe.projekt.zwierzeta.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

public class Swiat implements SwiatInterface {
    public final int WYSOKOSC = 20;
    public final int SZEROKOSC = 20;
    private final Plansza plansza = new Plansza(this);

    private List<Organizm> wszystkieOrganizmy = new ArrayList<>();
    private List<Organizm> noweOrganizmy = new ArrayList<>();
    private List<Organizm> doUsuniecia = new ArrayList<>();
    private Organizm[][] mapaOrganizmow = new Organizm[20][20];
    private int akcjaCzlowieka = -1;

    public Swiat() throws FileNotFoundException {
    }

    @Override
    public void dodajOrganizm(Organizm organizm) {
        noweOrganizmy.add(organizm);
    }

    @Override
    public void usunOrganizm(int x, int y) {
        for(Organizm o : wszystkieOrganizmy) {
            if (o.getPozycjaX() == x && o.getPozycjaY() == y) doUsuniecia.add(o);
        }
        for(Organizm o : noweOrganizmy) {
            if (o.getPozycjaX() == x && o.getPozycjaY() == y) doUsuniecia.add(o);
        }
    }

    @Override
    public void rozpocznijSymulacje() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        inicjalizacjaSwiata();
        tura();
    }

    public void tura() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Collections.sort(wszystkieOrganizmy);
        for(Organizm o : wszystkieOrganizmy){
            o.akcja();
            o.zwiekszWiek();
        }
        dodajNoweOrganizmy();
        usunOrganizmy();
        wypelnijPlanszeOrganizmow();
        plansza.repaint();
    }

    public void inicjalizacjaSwiata(){
        dodajOrganizm(new Czlowiek(this, 10,10));
        dodajOrganizm(new Guarana(this, 17,18));
        dodajOrganizm(new Wilk(this, 7,8));
        dodajOrganizm(new Wilk(this, 6,3));
        dodajOrganizm(new Antylopa(this, 8,3));
        dodajOrganizm(new Trawa(this, 9,2));
        dodajOrganizm(new Mlecz(this, 9,3));
        dodajOrganizm(new Zolw(this, 9,4));
        dodajOrganizm(new Owca(this, 1,17));
        dodajOrganizm(new Owca(this, 3,17));
        dodajOrganizm(new Mlecz(this, 7,15));
        dodajOrganizm(new Lis(this, 1, 1));
        dodajOrganizm(new Zolw(this, 10, 15));
        dodajOrganizm(new Antylopa(this, 10, 16));
        dodajOrganizm(new BarszczSosnowskiego(this, 4,4));

    }

    public Organizm getOrganizm(int x, int y){
        for(Organizm o : wszystkieOrganizmy){
            if(o.getPozycjaX() == x && o.getPozycjaY() == y) return o;
        }
        for(Organizm o : noweOrganizmy){
            if(o.getPozycjaX() == x && o.getPozycjaY() == y) return o;
        }
        return null;
    }

    public void test(){
        for(Organizm o : wszystkieOrganizmy){
            System.out.println(o.getPozycjaX());
        }
    }

    public void dodajNoweOrganizmy(){
        wszystkieOrganizmy.addAll(noweOrganizmy);
        noweOrganizmy.clear();
    }

    public void usunOrganizmy(){
        wszystkieOrganizmy.removeAll(doUsuniecia);
        doUsuniecia.clear();
    }

    public void wypelnijPlanszeOrganizmow(){
        for(int i=0; i<WYSOKOSC; i++){
            for(int j=0; j<SZEROKOSC; j++){
                mapaOrganizmow[i][j] = null;
            }
        }
        for(Organizm o : wszystkieOrganizmy){
            mapaOrganizmow[o.getPozycjaX()][o.getPozycjaY()] = o;
        }
    }

    public List<Organizm> getWszystkieOrganizmy() {
        return wszystkieOrganizmy;
    }

    public Organizm[][] getMapaOrganizmow() {
        return mapaOrganizmow;
    }

    public int getAkcjaCzlowieka() {
        return akcjaCzlowieka;
    }

    public void setAkcjaCzlowieka(int akcjaCzlowieka) {
        this.akcjaCzlowieka = akcjaCzlowieka;
    }

    public void zapiszGre() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("zapis.txt");
        for (Organizm o: wszystkieOrganizmy){
            zapis.print(o.getSymbol());
            zapis.print(" ");
            zapis.print(o.getPozycjaX());
            zapis.print(" ");
            zapis.print(o.getPozycjaY());
            zapis.print(" ");
            zapis.print(o.getSila());
            zapis.print(" ");
            zapis.println(o.getWiek());
        }
        zapis.close();
    }

    public void wczytajGre() throws FileNotFoundException {
        File plik = new File("zapis.txt");
        Scanner in = new Scanner(plik);
        wszystkieOrganizmy.clear();
        while(in.hasNextLine() && in.hasNext()){
            String symbol = in.next();
            int pozycjaX = Integer.parseInt(in.next());
            int pozycjaY = Integer.parseInt(in.next());
            int sila = Integer.parseInt(in.next());
            int wiek = Integer.parseInt(in.next());
            Organizm nowy = null;

            if(symbol.equals("B")) nowy = new BarszczSosnowskiego(this, pozycjaX, pozycjaY);
            else if(symbol.equals("G")) nowy = new Guarana(this, pozycjaX, pozycjaY);
            else if(symbol.equals("M")) nowy = new Mlecz(this, pozycjaX, pozycjaY);
            else if(symbol.equals("T")) nowy = new Trawa(this, pozycjaX, pozycjaY);
            else if(symbol.equals("J")) nowy = new WilczeJagody(this, pozycjaX, pozycjaY);
            else if(symbol.equals("A")) nowy = new Antylopa(this, pozycjaX, pozycjaY);
            else if(symbol.equals("C")) nowy = new Czlowiek(this, pozycjaX, pozycjaY);
            else if(symbol.equals("L")) nowy = new Lis(this, pozycjaX, pozycjaY);
            else if(symbol.equals("O")) nowy = new Owca(this, pozycjaX, pozycjaY);
            else if(symbol.equals("W")) nowy = new Wilk(this, pozycjaX, pozycjaY);
            else if(symbol.equals("Z")) nowy = new Zolw(this, pozycjaX, pozycjaY);

            nowy.setSila(sila);
            nowy.setWiek(wiek);
            dodajOrganizm(nowy);
        }
        dodajNoweOrganizmy();
        wypelnijPlanszeOrganizmow();
        plansza.repaint();
    }

    public Plansza getPlansza() {
        return plansza;
    }
}
