package services.impl;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        IntegerListImpl integerList = new IntegerListImpl();

        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.print();
        System.out.println(integerList.contains(7));


    }
}
