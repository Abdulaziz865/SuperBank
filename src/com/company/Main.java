package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int fp = 0;
    public static boolean popolNenie() {
        int full1 = 0;
        for (int i = 0; i < 12; i++) {
            if (kEnter[i] - k[i] > 0) {
                full1 += nom[i] * kEnter[i] - k[i];
            }
        }
        System.out.println("\n" + "Общая сумма пополнения : " + full1);
        return true;
    }

    public static boolean popolNenie2() {
        int full2 = 0;
        for (int i = 0; i < 12; i++) {
            if (kEnter[i] - k[i] > 0) {
                full2 += nom[i] * kEnter[i] - k[i];
            }
        }
        if (full2 > 0) {
            return true;
        }
        return false;
    }

    static final String RESET = "\u001b[0m";
    static final String BLACK = "\u001b[30m";
    static final String RED = "\u001b[31m";
    static final String GREEN = "\u001b[32m";
    static final String YELLOW = "\u001b[33m";
    static final String BLUE = "\u001b[34m";
    static final String PURPLE = "\u001b[35m";
    static final String CH = "\u001b[36m";


    public static void print(String color) {
        System.out.println(color);
    }


    private static int sum;
    private static int pass = 123;
    private static int menuN;

    private final static int[] nom = {5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 3, 1};
    private final static int[] k = new int[12];
    private final static int[] kEnter = new int[12];

    private static Integer fullsum = 0;
    private final static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) {

        print(GREEN);
        System.out.println("Привет! тебя приветствует Банкомат-шутник!");
        print(YELLOW);

        while (true) {
            try {
                System.out.println("Введи пароль для входа:");
                try {
                    pass = Integer.parseInt(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                print(GREEN);
                if (pass == 123) {
                    System.out.println("Поздравляю ты успешно вошел в банкомат");
                    break;

                } else System.out.println("Малой попробуй ещё раз))");
                print(RED);
            } catch (NumberFormatException e) {
                System.out.println("Чел ливни с жизни.. или");
            }
        }

        while (true) {
            print(GREEN);
            try {

                System.out.println("Ваш счёт - " + popolNenie2());
                System.out.println("Выберите функции ");
                System.out.println("1. Закинуть сумму на ваш аккаунт ");
                System.out.println("2. Выдача денег");
                System.out.println("3. Смотаться отсюда");
                System.out.println("4. Текущий баланс");

                try {
                    menuN = Integer.parseInt(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    if (menuN == 1) add();
                    else if (menuN == 4) print();
                    else if (menuN == 2) money();
                    else if (menuN == 3) {
                        print(BLUE);
                        System.out.println("СМОЙСЯ!!!");
                        break;
                    } else System.out.println("Чел ты....................");
                } catch (NumberFormatException e) {
                    popolNenie();
                }
            } catch (NumberFormatException e) {
                System.out.println("Не ведусь!");
            }
        }
    }


    private static void add() {
        int nepol = 0;
        print(YELLOW);
        System.out.println("//Примечание - если хотите пропустить или пополнить ограниченными видами номиналов , нажмите кнопку <ENTER>");
        print(PURPLE);
        for (int i = 0; i < 12; i++) {
            System.out.println("Пожалуйста введите количество номинала" + ":" + nom[i]);

            try {
                kEnter[i] = Integer.parseInt(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        print(GREEN);
        System.out.println("Вы добавили на счет:");
        for (int j = 0; j < 12; j++) {
            if ((kEnter[j] - k[j]) > 0)
                System.out.print(nom[j] + " x " + (kEnter[j] - k[j]) + ", ");
            if (kEnter[j] - k[j] > 0) {
                nepol += nom[j] * kEnter[j] - k[j];
            }

        }
        System.out.println("Общая сумма пополнения : " + nepol);
    }

    private static int count(int nominal, int sum, int put) {

        return Math.min((sum / nominal), put);
    }

    private static void money() {
        int f = 0;
        print(RESET);
        System.out.println("Введите сумму: ");
        try {
            sum = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 12; i++) {
            if (kEnter[i] - k[i] > 0) {
                f += nom[i] * kEnter[i] - k[i];
                if (f > sum) {
                    k[i] = count(nom[i], sum, kEnter[i]);
                    sum = sum - nom[i] * k[i];
                }
            }
        }
        if (f < sum) {
            System.out.println("У вас недостаточно средств... вы можете снять только : " + f);
        }
        print(RESET);
        if (sum == 0) {
            System.out.println("Вы получили сумму в:");
            for (int i = 0; i < 12; i++) {


                if (k[i] > 0) {
                    System.out.print(nom[i] + " " + " x " + " " + k[i] + ", ");
                }
            }
            for (int i = 0; i < 12; i++) {
                if (k[i] > 0) {
                    fullsum += nom[i] * k[i];
                }
            }
            System.out.println("\n" + "Совокупность выдачи : " + fullsum + "$");

        }


        for (int i = 0; i < 12; i++) {

        }


        print(BLUE);
        System.out.println();
        System.out.println("Остаток баланса : ");
        for (int i = 0; i < 12; i++) {
            if ((kEnter[i] - k[i]) > 0) {
                System.out.print(nom[i] + " x " + (kEnter[i] -= k[i]) + ", ");
            }
        }
        if (f == 0) {
            System.out.println("Остаток баланса : 0 ");
        }
    }


    private static void print() {
        int full = 0;
        print(RESET);
        System.out.println("У вас имеется : ");
        for (int j = 0; j < 12; j++) {
            if ((kEnter[j] - k[j]) > 0) {
                System.out.print(nom[j] + " x " + (kEnter[j] - k[j]) + ", ");
            }
        }

        for (int i = 0; i < 12; i++) {
            if (kEnter[i] - k[i] > 0) {
                full += nom[i] * kEnter[i] - k[i];
            }
        }
        System.out.println("\n" + "Общая сумма : " + full);
        print(GREEN);
    }
}