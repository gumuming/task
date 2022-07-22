package com.gim.java.base;

/**
 * @author Gim
 */
public class DecordBox {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c.equals(d));
        System.out.println(e.equals(f));
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        System.out.println(g.equals(a+h));

//        Integer i1 = 40;
//        Integer i2 = new Integer(40);
//        System.out.println(i1==i2);//输出 false
    }
}
