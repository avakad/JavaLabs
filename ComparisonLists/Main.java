package com.company;

public class Main {

    public static void main(String[] args) {
        String dogName = "Charlie";
        String catName = "Bella";
        Comparison comparison = new Comparison();

        System.out.println("                        Number of execution    Execution time");

        comparison.compareAdd(dogName, catName);
        comparison.compareGet();
        comparison.compareSize();
        comparison.compareHashCode();
        comparison.compareDelete();


    }
}
