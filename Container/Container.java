package com.company;
import java.util.Arrays;

public class Container
{
    private int sizeArray = 4, sizeNewArray=4, indexObject = -1, indexNewArray=-1;
    double newSize = 1.5;
    private Cat supportArray[];
    public Cat newArray[] = new Cat[sizeNewArray];

    public Cat[] arrayCats = new Cat[sizeArray];

    public void addObject(Cat newCat) // параметр - добавляемый объект
    {
        if (indexObject+1 <= sizeArray -1)
        {
            indexObject++;
            arrayCats[indexObject] = newCat;
        }
        else
        {
            indexObject++;
            sizeArray*=newSize;
            supportArray = new Cat[sizeArray];
            supportArray = Arrays.copyOf(arrayCats, sizeArray);
            supportArray[indexObject] = newCat;
            arrayCats = Arrays.copyOf(supportArray, sizeArray);
        }
    }

    public void deleteObject(int indexDelete)
    {
        supportArray = new Cat[sizeArray];
        System.arraycopy(arrayCats, 0, supportArray, 0, indexDelete);
        System.arraycopy(arrayCats, indexDelete+1, supportArray, indexDelete, indexObject - indexDelete);
        arrayCats[indexObject] = null;
        arrayCats = Arrays.copyOf(supportArray, sizeArray);
        indexObject--;
    }

    public void excludeObject(int indexExclude){
        supportArray = new Cat[sizeArray];
        System.arraycopy(arrayCats, 0, supportArray, 0, indexExclude);
        System.arraycopy(arrayCats, indexExclude+1, supportArray, indexExclude, indexObject - indexExclude);
        if (indexNewArray+1 <= sizeNewArray -1){
            indexNewArray++;
            newArray[indexNewArray]= arrayCats[indexExclude];
        }
        else
        {
            indexNewArray++;
            sizeNewArray*=newSize;
            supportArray = new Cat[sizeNewArray];
            supportArray = Arrays.copyOf(newArray, sizeNewArray);
            supportArray[indexNewArray] = arrayCats[indexExclude];
            newArray = Arrays.copyOf(supportArray, sizeNewArray);
        }
        arrayCats[indexObject] = null;
        arrayCats = Arrays.copyOf(supportArray, sizeArray);
        indexObject--;
    }

}