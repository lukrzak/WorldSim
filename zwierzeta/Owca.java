package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 4,4, pozycjaX, pozycjaY, 'O', "Owca", Color.GRAY);
    }
}
