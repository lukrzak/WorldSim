package com.obiektowe.projekt;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Swiat swiat = new Swiat();
        swiat.rozpocznijSymulacje();
    }
}
