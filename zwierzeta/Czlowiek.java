package com.obiektowe.projekt.zwierzeta;

import com.obiektowe.projekt.Swiat;
import com.obiektowe.projekt.Zwierze;

import java.awt.*;

public class Czlowiek extends Zwierze {
    int odnowienieUmiejetnosci = 0;
    public Czlowiek(Swiat swiat, int pozycjaX, int pozycjaY){
        super(swiat, 5, 4, pozycjaX, pozycjaY, 'C', "Człowiek", Color.BLUE);
    }

    public void akcja(){
        int nowaPozycjaX = pozycjaX;
        int nowaPozycjaY = pozycjaY;
        if(odnowienieUmiejetnosci > 5){
            odnowienieUmiejetnosci--;
            sila--;
        }
        else if(odnowienieUmiejetnosci> 0) odnowienieUmiejetnosci--;
        switch (swiat.getAkcjaCzlowieka()){
            case 0: nowaPozycjaY--; break;
            case 1: nowaPozycjaX++; break;
            case 2: nowaPozycjaY++; break;
            case 3: nowaPozycjaX--; break;
            case 4: umiejetnoscSpecjalna(); break;
        }

        if(nowaPozycjaX < 20 && nowaPozycjaY < 20 && nowaPozycjaX >= 0 && nowaPozycjaY >= 0) {
            if (swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY) == null) {
                this.setPozycjaX(nowaPozycjaX);
                this.setPozycjaY(nowaPozycjaY);
            }
            else if(swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).getNazwa() != "Człowiek")
                swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY).kolizja(this, swiat.getOrganizm(nowaPozycjaX, nowaPozycjaY));
        }
    }

    public void umiejetnoscSpecjalna(){
        if(odnowienieUmiejetnosci <=0){
            odnowienieUmiejetnosci = 10;
            sila += 5;
            swiat.getPlansza().wpiszKomentarz("Człowiek aktywował umiejętność specjalną");
        }
    }
}
