package com.obiektowe.projekt.rosliny;

import com.obiektowe.projekt.Roslina;
import com.obiektowe.projekt.Swiat;

import java.awt.*;

public class Trawa extends Roslina {
    public Trawa(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 0, pozycjaX, pozycjaY, 'T', "Trawa", Color.green);
    }
}
