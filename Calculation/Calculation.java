package com.company;

import java.util.Arrays;

public class Calculation {
    String expressionString = new String();
    Expression myExpression = new Expression(expressionString);
    public char[] myCharExpression = myExpression.expression.toCharArray();

    char[] Error = {'E','R','R','O','R'};





    boolean Check (char[] expressionArray) {
        boolean check = true;
        int k=0;
        for (int i = 0; i < expressionArray.length; i++){
            if (expressionArray[i]=='(') k++;
            if (expressionArray[i]==')') k--;
            if (expressionArray[i]>='0' && expressionArray[i]<='9'
                    && (i<(expressionArray.length-1) && expressionArray[i+1] =='(' || i>0 && expressionArray[i-1]==')'))
                check = false;
            if ((expressionArray[i]=='+' || expressionArray[i]=='-' || expressionArray[i]=='/' || expressionArray[i]=='*')
                    && i<(expressionArray.length-1) && (expressionArray[i+1]=='+' || expressionArray[i+1]=='-'
                    || expressionArray[i+1]=='/' || expressionArray[i+1]=='*'))
                check=false;
            if ((i==0 || i==(expressionArray.length-1)) && (expressionArray[i]=='+' || expressionArray[i]=='-'
                    || expressionArray[i]=='/' || expressionArray[i]=='*') )
                check=false;
            if (expressionArray[i]>='a' && expressionArray[i]<='z' || expressionArray[i]>='A' && expressionArray[i]<='Z')
                check=false;
            if (expressionArray[i]=='=') check=false;
        }
        if (k!=0) check=false;
        return check;
    }


    int numberLeft(char[] expressionArray, int indexOperation) // было инт
    {
        int indentLeft=0;
        int number1=0;
        int index = indexOperation - indentLeft - 1;

        while (index!=-1 && expressionArray[index] >= '0' && expressionArray[index] <= '9') {

            indentLeft++;
            number1 +=  Character.getNumericValue(expressionArray[index]) * Math.pow(10, indentLeft-1);
            index--;
        }
       // System.out.println("NumberLeft: " + number1);
        return number1;
    }

    int numberRight(char[] expressionArray, int indexOperation) {
        int indentRight=0;
        int number2=0;

        int index = indexOperation + indentRight + 1;
        while(index!=expressionArray.length && expressionArray[index] >= '0' && expressionArray[index] <= '9')
        {
            indentRight++;
            number2 = number2 * 10 + Character.getNumericValue(expressionArray[index]);
            index++;
        }
        //indexEnd = indexOperation+indentRight;
        //System.out.println("NumberRight: " + number2);
        return number2;
    }

    int quantity(int result){  // количество символов в результате операции
        int quantity=0;
        while(result!=0){
            quantity++;
            result/=10;
        }
        return quantity;
    }

    char[] newExpressionArray(char[] expressionArray, int count, int tempResult, int sizeNum1, int sizeNum2)
    {
        int newLength = expressionArray.length - sizeNum1 - sizeNum2 - 1 + quantity(tempResult);
        //System.out.println("newLength:  " + newLength);
        char[] newExpressionArray = new char[newLength];

        char[] resultOnChar = Integer.toString(tempResult).toCharArray();
       // System.out.println("tempResult CHAR  :  " + Arrays.toString(resultOnChar));
        //System.out.println("quantity(tempResult)  :  " + quantity(tempResult));
        int resultIndex = -1;
        int indexAfter=count + sizeNum2+1- sizeNum1-1-sizeNum2 + quantity(tempResult);
        for(int i=0; i < expressionArray.length; i++){
            if (i < (count - sizeNum1))     //начало выражения до начала текущего действия
            {
                newExpressionArray[i] = expressionArray[i];
            }
            if (i >= (count- sizeNum1) && i <= (count + sizeNum2) && resultIndex < quantity(tempResult)-1)
            {
                resultIndex ++;
                newExpressionArray[i] = resultOnChar[resultIndex];        //беда
            }
            if (i > (count + sizeNum2))
            {                                     // выражение после текущего действия
                newExpressionArray[indexAfter] = expressionArray[i];
                indexAfter++;
            }
        }
        System.out.println(" newExpressionArray: " +  Arrays.toString(newExpressionArray));
        return newExpressionArray;  // новое выражение
    }


        ///////////////////////////////////// CALCULATION ///////////////////////////////////////////////

    char[] calculation(char[] expressionArray)
    {
        int priority0=0, priority1=0, priority2=0;

        int indexChar =0;
        while(indexChar < expressionArray.length)
        {
            if(expressionArray[indexChar]=='(') priority0++;
            if (expressionArray[indexChar]=='*' || expressionArray[indexChar]=='/') {
                priority1++;
            }
            if (expressionArray[indexChar]=='-' || expressionArray[indexChar]=='+') {
                priority2++;
            }
            indexChar++;
        }

        ///////////////////////////////////// PRIORITY 0 ///////////////////////////////////////////////////////////////

        while(priority0 > 0){
            int beginBracket=0, endBracket=0;
            int localPriority1=0, localPriority2=0;
            boolean indexFind=false;
            for (int i=0; i<expressionArray.length && !indexFind; i++)
            {
                if (expressionArray[i] == '(')
                    beginBracket = i;

                if (expressionArray[i] == ')')
                {
                    endBracket = i;
                    indexFind = true;
                }
            }

            char[] expressionBracket = Arrays.copyOfRange(expressionArray, beginBracket+1, endBracket);
                //System.out.println(" expressionBracket: " +  Arrays.toString(expressionBracket));

            int indexBracket=0;
            int exprBrLength = expressionBracket.length;
            while(indexBracket < expressionBracket.length)
            {
                if (expressionBracket[indexBracket]=='*' || expressionBracket[indexBracket]=='/') {
                    localPriority1++;
                    //System.out.println(" localPriority1: " +  localPriority1);
                }
                if (expressionBracket[indexBracket]=='-' || expressionBracket[indexBracket]=='+') {
                    localPriority2++;
                    //System.out.println(" localPriority2: " +  localPriority2);
                }
                indexBracket++;
            }
            while (localPriority1 > 0) {
                expressionBracket = getResultForPriority1 (expressionBracket); //  "результат"
                localPriority1--;
                priority1--;
            }
            while (localPriority2>0)
            {
                expressionBracket = getResultForPriority2 (expressionBracket);  //  "результат"
                localPriority2--;
                priority2 --;
            }

           int newLengthExpr = expressionArray.length - exprBrLength + expressionBracket.length - 2;

           char[] newExprWithoutBracket = new char[newLengthExpr];

            if (beginBracket!=0)
                System.arraycopy(expressionArray, 0, newExprWithoutBracket, 0, beginBracket);

            System.arraycopy(expressionBracket, 0, newExprWithoutBracket, beginBracket, expressionBracket.length);

            if(endBracket!=(expressionArray.length-1))
                System.arraycopy(expressionArray, endBracket+1, newExprWithoutBracket, beginBracket+expressionBracket.length, expressionArray.length-1-endBracket);

            expressionArray=Arrays.copyOf(newExprWithoutBracket, newExprWithoutBracket.length);

            priority0--;
        }


        ///////////////////////////////////// PRIORITY 1 ///////////////////////////////////////////////////////////////
        while (priority1 > 0) {
            expressionArray = getResultForPriority1 (expressionArray);
             if (expressionArray[0] == 'E' && expressionArray[4] == 'R')
                 return Error;
            priority1--;
        }

        /////////////////////////////////// PRIORITY 2 //////////////////////////////////////////
        while (priority2>0)
        {
            expressionArray = getResultForPriority2 (expressionArray);
            priority2 --;
        }
        return expressionArray;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////

    char[] getResultForPriority1 (char[] expressionArray)
    {
        boolean indexFind=false;
        int indexOperation=0;

        for (int i=0; i<expressionArray.length && !indexFind; i++){
            if ((expressionArray[i] == '/' || expressionArray[i] == '*'))
            {
                indexFind=true;
                indexOperation = i;
            }
        }

        int tempResult=0;

        int number1 = numberLeft(expressionArray, indexOperation);
        int number2 = numberRight(expressionArray, indexOperation);

        if (expressionArray[indexOperation] == '/' && number2!=0) tempResult=number1/number2;
        if (expressionArray[indexOperation] == '/' && number2==0){
            return Error;
        }
        if (expressionArray[indexOperation] == '*') tempResult=number1*number2;

        //System.out.println("tempResult 1:   " + tempResult);
        char[] newArray = newExpressionArray(expressionArray, indexOperation, tempResult, quantity(number1), quantity(number2));
        // исходный массив выражения, результат вычисления текущего действия, размер текущего действия, индекс начала действия, индекс конца действия

       // Integer.toString(number1).toCharArray();
       // char[] newArray = newExpressionArray(expressionArray, tempResult, quantity(number1)+quantity(number2),(Integer.toString(number1).toCharArray())., Integer.toString(number2).toCharArray());
        return Arrays.copyOf(newArray, newArray.length);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    char[] getResultForPriority2 (char[] expressionArray)
    {
        int indexOperation=0;

        boolean indexFind=false;
        for (int i=0; i<expressionArray.length && !indexFind; i++){         // индекс текущей операции в строке выражения
            if ((expressionArray[i] == '+' || expressionArray[i] == '-'))
            {
                indexFind=true;
                indexOperation = i;
            }
        }

        int tempResult=0;
        int number1 = numberLeft(expressionArray, indexOperation);
        int number2 = numberRight(expressionArray, indexOperation);

        if (expressionArray[indexOperation] == '+') tempResult=number1+number2;
        if (expressionArray[indexOperation] == '-') tempResult=number1-number2;

        //System.out.println("tempResult 2:   " + tempResult);

        //char[] newArray = newExpressionArray(expressionArray, tempResult, quantity(number1)+quantity(number2), indexBegin, indexEnd);
        char[] newArray = newExpressionArray(expressionArray, indexOperation, tempResult, quantity(number1), quantity(number2));
        return Arrays.copyOf(newArray, newArray.length);
    }
}


