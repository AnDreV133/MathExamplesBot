package org.example;

import java.util.*;


public class BotLogics {
    public String[] lvl0(int minNumber, int maxNumber) {
        LinkedList<String> expression = new LinkedList<>();
        StringBuilder expressionInLine = new StringBuilder();

        expression.add(getRandomNumber(minNumber, maxNumber));
        expression.add(plusOrMinus());
        expression.add(getRandomNumber(minNumber, maxNumber));

        for (String i : expression) {
            expressionInLine.append(i + " ");
        }

        return new String[]{expressionInLine.toString(), takeAnswer(RPNConverter(expression))};
    }

    public String[] lvl1_multiplication(int minNumber, int maxNumber, int maxSmallNumber) {
        LinkedList<String> expression = new LinkedList<>();
        StringBuilder expressionInLine = new StringBuilder();

        expression.add(getRandomNumber(minNumber, maxNumber));
        expression.add("*");
        expression.add(getSmallRandomNumber(maxSmallNumber));

        for (String i : expression) {
            expressionInLine.append(i + " ");
        }

        return new String[]{expressionInLine.toString(), takeAnswer(RPNConverter(expression))};
    }

    public String[] lvl1_additionNSubtraction(int minNumber, int maxNumber) {
        LinkedList<String> expression = new LinkedList<>();
        StringBuilder expressionInLine = new StringBuilder();

        expression.add(getRandomNumber(minNumber, maxNumber));
        expression.add(plusOrMinus());
        expression.add(getRandomNumber(minNumber, maxNumber));
        expression.add(plusOrMinus());
        expression.add(getRandomNumber(minNumber, maxNumber));

        for (String i : expression) {
            expressionInLine.append(i + " ");
        }

        return new String[]{expressionInLine.toString(), takeAnswer(RPNConverter(expression))};
    }

    public String[] lvl2(int minNumber, int maxNumber, int maxSmallNumber) {
        Random randomNumber = new Random();
        LinkedList<String> expression = new LinkedList<>();
        StringBuilder expressionInLine = new StringBuilder();

        switch (randomNumber.nextInt(2)) {
            case 0: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 1: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 2: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                break;
            }
        }

        for (String i : expression) {
            expressionInLine.append(i + " ");
        }

        return new String[]{expressionInLine.toString(), takeAnswer(RPNConverter(expression))};
    }

    public String[] lvl3(int minNumber, int maxNumber, int maxSmallNumber) {
        Random randomNumber = new Random();
        LinkedList<String> expression = new LinkedList<>();
        StringBuilder expressionInLine = new StringBuilder();

        switch (randomNumber.nextInt(10)) {
            case 0: {
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 1: {
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(")");
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 2: {
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                break;
            }
            case 3: {
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add("*");
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                break;
            }
            case 4: {
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add("*");
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 5: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                break;
            }
            case 6: {
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(")");
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 7: {
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                break;
            }
            case 8: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(")");
                break;
            }
            case 9: {
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add("(");
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add(plusOrMinus());
                expression.add(getRandomNumber(minNumber, maxNumber));
                expression.add("*");
                expression.add(getSmallRandomNumber(maxSmallNumber));
                expression.add(")");
                break;
            }
        }

        for (int i = 0; i < expression.size(); i++) {
            try {
                if (expression.get(i).equals("(")) {
                    expressionInLine.append(expression.get(i));
                } else if (expression.get(i+1).equals(")")) {
                    expressionInLine.append(expression.get(i));
                } else {
                    expressionInLine.append(expression.get(i) + " ");
                }
            } catch (IndexOutOfBoundsException e) {
                expressionInLine.append(expression.get(expression.size() - 1) + " ");
                break;
            }
        }

        return new String[]{expressionInLine.toString(), takeAnswer(RPNConverter(expression))};
    }

    private static ArrayList<String> RPNConverter(LinkedList<String> expression) {
        ArrayList<String> expressionPostfix = new ArrayList<>(expression.size());
        Stack<String> stack = new Stack<>();
        int priority;
        for (int i = 0; i < expression.size(); i++) {
            priority = getPriority(expression.get(i));
            if (priority == 0) expressionPostfix.add(expression.get(i));
            else if (priority == 1) stack.push(expression.get(i));
            else if (priority > 1) {
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        expressionPostfix.add(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(expression.get(i));
            } else if (priority == -1) {
                while (getPriority(stack.peek()) != 1) {
                    expressionPostfix.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            expressionPostfix.add(stack.pop());
        }

        return expressionPostfix;
    }

    private static String takeAnswer(ArrayList<String> expressionPostfix) throws IllegalArgumentException {
        Stack<Integer> answer = new Stack<>();
        int priority;
        for (int i = 0; i < size(expressionPostfix); i++) {
            priority = getPriority(expressionPostfix.get(i));
            if (priority == 0) {
                answer.push(Integer.valueOf(expressionPostfix.get(i)));
            } else if (expressionPostfix.get(i).equals("*")) {
                answer.push(answer.pop() * answer.pop());
            } else if (expressionPostfix.get(i).equals("/")) {
                Integer secondNumber = answer.pop();
                Integer firstNumber = answer.pop();
                answer.push(firstNumber / secondNumber);
            } else if (expressionPostfix.get(i).equals("+")) {
                answer.push(answer.pop() + answer.pop());
            } else if (expressionPostfix.get(i).equals("-")) {
                Integer secondNumber = answer.pop();
                Integer firstNumber = answer.pop();
                answer.push(firstNumber - secondNumber);
            } else {
                throw new IllegalArgumentException(("NoneOperators"));
            }
        }
        return String.valueOf(answer.pop());
    }

    private static int getPriority(String symbol) {
        switch (symbol) {
            case "*":
                return 3;
            case "/":
                return 3;
            case "+":
                return 2;
            case "-":
                return 2;
            case "(":
                return 1;
            case ")":
                return -1;
            default:
                return 0;
        }
    }


    private static int size(String[] array) {
        int len = 0;
        for (String ignored : array) {
            len += 1;
        }
        return len;
    }

    private static int size(ArrayList<String> array) {
        return array.size();
    }

    private static String getRandomOperator(String[] operators) {
        Random randomPosition = new Random();
        int size = size(operators);
        return operators[randomPosition.nextInt(size)];
    }

    private static String plusOrMinus() {
        Random randomOperator = new Random();
        switch (randomOperator.nextInt(2)) {
            case 0: {
                return "+";
            }
            case 1: {
                return "-";
            }
            default: {
                return null;
            }
        }
    }

    private static String getRandomNumber(int minNumber, int maxNumber) {
        Random randomNumber = new Random();
        return String.valueOf(randomNumber.nextInt(maxNumber - minNumber + 1) + minNumber);
    }

    private static String getSmallRandomNumber(int maxSmallNumber) {
        Random randomNumber = new Random();
        return String.valueOf(randomNumber.nextInt(maxSmallNumber) + 1);
    }
}

class BotLogicsTest {
    public static void main(String[] args) {
//        String[] str0 = new String[] {"2", "+", "2", "*", "2"};
//        System.out.println(takeAnswer(RPNConverter(str0)) == 6);
//        String[] str1 = new String[] {"3", "*", "(", "2", "+", "2", ")", "*", "3"};
//        System.out.println(takeAnswer(RPNConverter(str1)).equals("20") + RPNConverter(str1));
//        String[] str2 = new String[] {};
//        System.out.println(takeAnswer(RPNConverter(str2)).equals("36"));
        BotLogics botFunc = new BotLogics();
        String[] str1 = botFunc.lvl0(10, 30);
        String[] str2 = botFunc.lvl1_multiplication(2, 12, 5);
        String[] str3 = botFunc.lvl1_additionNSubtraction(10, 30);
        String[] str4 = botFunc.lvl2(10, 30, 3);
        String[] str5 = botFunc.lvl3(10, 30, 3);
        System.out.println(str1[0] + "= " + str1[1]);
        System.out.println(str2[0] + "= " + str2[1]);
        System.out.println(str3[0] + "= " + str3[1]);
        System.out.println(str4[0] + "= " + str4[1]);
        System.out.println(str5[0] + "= " + str5[1]);
    }
}