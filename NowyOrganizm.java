package com.obiektowe.projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NowyOrganizm implements ActionListener {
    private final int SZEROKOSC_OKNA = 200;
    private final int WYSOKOSC_OKNA = 300;
    private final Swiat swiat;
    private JList<String> lista;
    private JFrame okno;
    private int pozycjaX;
    private int pozycjaY;
    private final Plansza plansza;
    private final String[] opcje = {"BarszczSosnowskiego", "Guarana", "Mlecz", "Trawa", "WilczeJagody", "Antylopa",
            "Lis", "Owca", "Wilk", "Zolw"};

    NowyOrganizm(Swiat swiat, int pozycjaX, int pozycjaY, Plansza plansza){
        this.swiat = swiat;
        this.pozycjaX = pozycjaX;
        this.pozycjaY = pozycjaY;
        this.plansza = plansza;

        okno= new JFrame();
        lista = new JList<>(opcje);
        lista.setBounds(0,0, SZEROKOSC_OKNA,WYSOKOSC_OKNA -100);

        JButton zatwierdz = new JButton("Zatwierdź");
        zatwierdz.setVisible(true);
        zatwierdz.setBounds(10, SZEROKOSC_OKNA, 80, 30);
        zatwierdz.setFocusable(false);
        zatwierdz.addActionListener(this);

        okno.add(lista);
        okno.add(zatwierdz);
        okno.setSize(SZEROKOSC_OKNA,WYSOKOSC_OKNA);
        okno.setLayout(null);
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Zatwierdź")){
            String wybrane = lista.getSelectedValue().toString();
            try {
                dodajNowyOrganizm(wybrane, lista.getSelectedIndex());
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
        okno.dispose();
    }

    public void dodajNowyOrganizm(String nazwa, int index) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String path = index < 5 ? "com.obiektowe.projekt.rosliny."+nazwa : "com.obiektowe.projekt.zwierzeta."+nazwa;
        Class<?> klasa = Class.forName(path);
        Constructor<?> constructor = klasa.getConstructor(Swiat.class, int.class, int.class);
        swiat.dodajOrganizm((Organizm) constructor.newInstance(swiat, pozycjaX, pozycjaY));
    }
}
