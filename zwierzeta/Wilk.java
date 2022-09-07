package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 9, 5, pozycjaX, pozycjaY, 'W', "Wilk", Color.black);
    }
}
