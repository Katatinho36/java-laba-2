package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Evaluator evaluator = new Evaluator();

        System.out.println("Калькулятор выражений с переменными");
        System.out.println("Поддерживаемые операции: +, -, *, /");
        System.out.println("Поддерживаются переменные (одна буква)");
        System.out.println("Для выхода введите 'exit'");
        System.out.println("=====================================");

        while (true) {
            try {
                System.out.print("\nВведите выражение или присваивание: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Выход из программы...");
                    break;
                }

                // Обработка присваивания переменной (формат: x = 5)
                if (input.contains("=")) {
                    handleVariableAssignment(input, evaluator);
                    continue;
                }

                // Обработка математического выражения
                double result = evaluator.evaluate(input);
                System.out.println("Результат: " + result);

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void handleVariableAssignment(String input, Evaluator evaluator) throws Exception {
        String[] parts = input.split("=");
        if (parts.length != 2) {
            throw new Exception("Неверный формат присваивания. Используйте: переменная = значение");
        }

        String variableName = parts[0].trim();
        String valueStr = parts[1].trim();

        // Проверка имени переменной
        if (variableName.length() != 1 || !Character.isLetter(variableName.charAt(0))) {
            throw new Exception("Имя переменной должно быть одной буквой");
        }

        // Вычисляем значение (может быть выражением)
        double value = evaluator.evaluate(valueStr);
        evaluator.assignVariable(variableName, value);

        System.out.println("Переменная " + variableName + " = " + value);
    }
}