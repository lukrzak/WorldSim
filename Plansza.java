package com.obiektowe.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Plansza extends JFrame implements KeyListener, MouseListener{
    private Swiat swiat;
    private final int SZEROKOSC_OKNA = 1000;
    private final int WYSOKOSC_OKNA = 800;
    private final int ROZMIAR_PLANSZY = 20;
    private final int ROZMIAR_POLA = 35;
    private final int PRZESUNIECIE_X = 30;
    private final int PRZESUNIECIE_Y = 65;
    private final int ROZMIAR_WYPELNIENIA = 32;
    private final int SZEROKOSC_PRZYCISKU = 255;
    private final int WYSOKOSC_PRZYCISKU = 50;
    private final int POZYCJA_X_PRZYCISKU = 725;
    private final int POZYCJA_Y_PRZYCISKU = 60;

    private JTextArea poleTekstowe = new JTextArea();
    private List<Organizm> wszystkieOrganizmy = new ArrayList<>();
    private Organizm[][] mapaOrganizmow = new Organizm[20][20];
    public int akcjaCzlowieka;

    Plansza(Swiat swiat){
        setTitle("Lukasz Nowakowski 189396");
        setSize(SZEROKOSC_OKNA, WYSOKOSC_OKNA);
        setVisible(true);
        setLayout(null);
        setFocusable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.swiat = swiat;
        addKeyListener(this);
        addMouseListener(this);
    }

    public void paint(Graphics g){
        mapaOrganizmow = swiat.getMapaOrganizmow();
        super.paint(g);
        for(int i=0; i<ROZMIAR_PLANSZY; i++){
            for (int j=0; j<ROZMIAR_PLANSZY; j++){
                if(mapaOrganizmow[j][i] != null) g.setColor(mapaOrganizmow[j][i].kolor);
                else g.setColor(Color.white);
                g.fillRect(j*ROZMIAR_POLA + PRZESUNIECIE_X, i* ROZMIAR_POLA + PRZESUNIECIE_Y, ROZMIAR_WYPELNIENIA, ROZMIAR_WYPELNIENIA);

            }
        }
        inicjalizacjaPrzyciskow();
    }

    public void inicjalizacjaPrzyciskow(){
        JButton nowaTura = new JButton("Nowa tura");
        JButton zapiszGre = new JButton("Zapisz grę");
        JButton wczytajGre = new JButton("Wczytaj grę");

        nowaTura.setBounds(POZYCJA_X_PRZYCISKU,POZYCJA_Y_PRZYCISKU*0 + ROZMIAR_POLA, SZEROKOSC_PRZYCISKU, WYSOKOSC_PRZYCISKU);
        zapiszGre.setBounds(POZYCJA_X_PRZYCISKU,POZYCJA_Y_PRZYCISKU*1 + ROZMIAR_POLA, SZEROKOSC_PRZYCISKU, WYSOKOSC_PRZYCISKU);
        wczytajGre.setBounds(POZYCJA_X_PRZYCISKU,POZYCJA_Y_PRZYCISKU*2 + ROZMIAR_POLA, SZEROKOSC_PRZYCISKU, WYSOKOSC_PRZYCISKU);
        poleTekstowe.setBounds(POZYCJA_X_PRZYCISKU, POZYCJA_Y_PRZYCISKU*3 + ROZMIAR_POLA, SZEROKOSC_PRZYCISKU, 520);

        nowaTura.setFocusable(false);
        zapiszGre.setFocusable(false);
        wczytajGre.setFocusable(false);
        poleTekstowe.setFocusable(false);

        nowaTura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poleTekstowe.setText("");
                try {
                    swiat.tura();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                } catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });

        zapiszGre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    swiat.zapiszGre();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        wczytajGre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    swiat.wczytajGre();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        add(wczytajGre);
        add(zapiszGre);
        add(nowaTura);
        add(poleTekstowe);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: swiat.setAkcjaCzlowieka(0); break;
            case KeyEvent.VK_RIGHT: swiat.setAkcjaCzlowieka(1); break;
            case KeyEvent.VK_DOWN: swiat.setAkcjaCzlowieka(2); break;
            case KeyEvent.VK_LEFT: swiat.setAkcjaCzlowieka(3); break;
            case KeyEvent.VK_U: swiat.setAkcjaCzlowieka(4); break;
        }
    }

    public void wpiszKomentarz(String komunikat){
        poleTekstowe.append(komunikat + "\n");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int posX = (e.getX()-PRZESUNIECIE_X)/ROZMIAR_POLA;
        int posY = (e.getY()-PRZESUNIECIE_Y)/ROZMIAR_POLA;
        if(posX >=0 && posX < ROZMIAR_PLANSZY && posY >= 0 && posY < ROZMIAR_PLANSZY){
            if(swiat.getOrganizm(posX, posY) == null){
                NowyOrganizm org = new NowyOrganizm(swiat, posX, posY, this);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
