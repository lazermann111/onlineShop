package com.skillsup.model;

public class Dog {



    public String bark(){
        return "Woof!";
    }

    public static void main(String[] args) {

        Dog d = new Dog();
        System.out.println(d.bark());

         Dog hound = new Dog(){

        };

        System.out.println(hound.bark());

        Budka b1 = new Budka();
        Budka b2 = new Budka();

        b1.settle(d);
        b2.settle(hound);
    }


}
