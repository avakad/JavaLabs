package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculation calculation1 = new Calculation();
        System.out.println("Введите выражение:");
        Scanner inputExpression = new Scanner(System.in);
        Expression expression1 = new Expression(inputExpression.nextLine());
        
        //System.out.println(calculation1.Check(expression1.expression.toCharArray()));
        if (calculation1.Check(expression1.expression.toCharArray())){
            System.out.println("Result: ");
            System.out.println(calculation1.calculation(expression1.expression.toCharArray()));
        }
        else System.out.println("Введенное не является выражением");


    }
}
