package com.company;
import java.util.ArrayList;
import java.util.LinkedList;

public class Comparison {
    ArrayList<String> Dogs = new ArrayList<>();
    LinkedList<String> Cats = new LinkedList<>();
    int numberOfTimes = 1000000;
    long startTime, endTime;

    void compareAdd(String dogName, String catName){

        startTime = System.currentTimeMillis();
        for (int i=0; i<numberOfTimes; i++){
            Dogs.add(dogName);
        }
        endTime = System.currentTimeMillis();
        System.out.println("add() for ArrayList:        " + numberOfTimes +"                "+ (endTime-startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int i=0; i<numberOfTimes; i++){
            Cats.add(catName);
        }
        endTime = System.currentTimeMillis();
        System.out.println("add() for LinkedList:       " + numberOfTimes +"                "+ (endTime-startTime) + " ms");
    }


    void compareGet()
    {
        String getObject;
        int indexDog = 0;
        int dogsSize = Dogs.size();

        startTime = System.currentTimeMillis();
        while (indexDog < dogsSize/16)
        {
            getObject = Dogs.get(indexDog);
            indexDog++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("get() for ArrayList:        " +indexDog +"                  "+ (endTime-startTime) + " ms");

        int indexCat = 0;
        int catsSize = Cats.size();

        startTime = System.currentTimeMillis();
        while (indexCat < catsSize/16)
        {
            getObject = Cats.get(indexCat);
            indexCat++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("get() for LinkedList:       " +indexCat+"                  "+ (endTime-startTime) + " ms");
    }


    void compareSize()
    {
        int callSize;

        int callNumberArray=0;

        startTime = System.currentTimeMillis();
        while (callNumberArray != numberOfTimes)
        {
            callSize = Dogs.size();
            callNumberArray++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("size() for ArrayList:       " +callNumberArray +"                "+ (endTime-startTime) + " ms");

        int callNumberLinked=0;
        startTime = System.currentTimeMillis();
        while (callNumberLinked != numberOfTimes)
        {
            callSize = Dogs.size();
            callNumberLinked++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("size() for LinkedList:      " +callNumberLinked +"                "+ (endTime-startTime) + " ms");
    }

    void compareHashCode()
    {
        int numberOfDogs = Dogs.size();
        int indexDogs=0;
        int hasCodeOfObject;
        startTime = System.currentTimeMillis();
        while (indexDogs < numberOfDogs/256)
        {
            hasCodeOfObject = Dogs.hashCode();
            indexDogs++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("hashCode() for ArrayList:   " +indexDogs +"                   "+ (endTime-startTime) + " ms");

        int numberOfCats = Cats.size();
        int indexCats=0;
        startTime = System.currentTimeMillis();
        while (indexCats < numberOfDogs/256)
        {
            hasCodeOfObject = Dogs.hashCode();
            indexCats++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("hashCode() for LinkedList:  " +indexCats +"                   "+ (endTime-startTime) + " ms");

    }

    void compareDelete()
    {
        int numberOfDogs = Dogs.size();
        int indexDogs=0;

        startTime = System.currentTimeMillis();
        while (indexDogs < numberOfDogs/64){
            Dogs.remove(indexDogs);
            indexDogs++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("remove() for ArrayList:     " +indexDogs +"                  "+ (endTime-startTime) + " ms");

        int numberOfCats = Cats.size();
        int indexCats=0;

        startTime = System.currentTimeMillis();
        while (indexCats < numberOfCats/64){
            Cats.remove(indexCats);
            indexCats++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("remove() for LinkedList:    " +indexCats +"                  "+ (endTime-startTime) + " ms");
    }

}
