package com.company;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Container Cats = new Container();

        Scanner ScanSelectAction = new Scanner(System.in);
        System.out.println("0 - завершить программу\n" + "1 - добавить объект в конец массива\n"
                + "2 - удалить объект из массива по индексу\n" + "3 - извлечь объект по индексу в другой массив\n");
        System.out.println("Введите номер действие над массивом объектов: ");
        int selectAction = ScanSelectAction.nextInt();


        while(selectAction != 0)
        {
            if (selectAction > 3 && selectAction <= 9)
            {
                System.out.println("Введите номер действия над массивом объектов от 1 до 3 или 0: ");
            }
            if (selectAction == 1)
            {
                System.out.println("Введите имя и цвет очередной кошки:");
                Scanner inputName = new Scanner(System.in);
                Scanner inputColor = new Scanner(System.in);
                Cat addCat = new Cat(inputName.nextLine(), inputColor.nextLine());
                Cats.addObject(addCat);
            }
            if (selectAction == 2)
            {
                Scanner inputDelete = new Scanner(System.in);
                System.out.println("Введите индекс объекта в массиве для удаления: ");
                int selectIndex = inputDelete.nextInt();
                Cats.deleteObject(selectIndex);
            }
            if (selectAction == 3){
                Scanner inputExclude = new Scanner(System.in);
                System.out.println("Введите индекс объекта в массиве для извлечения в новый массив: ");
                int selectIndex = inputExclude.nextInt();
                Cats.excludeObject(selectIndex);
            }

            Scanner inputAction = new Scanner(System.in);
            System.out.println("Введите номер действия над массивом объектов или 0: ");
            selectAction = inputAction.nextInt();
        }

       // Cats.printObjects();

    }
}