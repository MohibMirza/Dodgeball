package com.kingfrozo.game;

import java.util.Vector;

public class Test {

    static class Cat {
        int life;
        int kills;

        Cat(int life, int kills) {
            this.life = life;
            this.kills = kills;

        }
    }

    public static Cat createCat() {
        return (new Cat(1,2));
    }


    public static void main(String[] args) {

        Vector<Cat> cats = new Vector<Cat>();

        Cat jerry = new Cat(4, 6);

        cats.add(jerry);

        cats.remove(jerry);

        jerry = null;

        while(true) {

        }






    }


}
