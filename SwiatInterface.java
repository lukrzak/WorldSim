package com.obiektowe.projekt;

import java.lang.reflect.InvocationTargetException;

public interface SwiatInterface {
    void dodajOrganizm(Organizm organizm);
    void usunOrganizm(int x, int y);
    void rozpocznijSymulacje() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
