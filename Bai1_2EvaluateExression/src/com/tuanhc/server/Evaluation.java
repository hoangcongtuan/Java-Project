package com.tuanhc.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Evaluation {
    private static Evaluation sInstance;
    static List<String> operator = new ArrayList<>(Arrays.asList("+", "-", "*", "/" , "sin"));
    static List<String> oneArgOperator = new ArrayList<>(Arrays.asList("sin"));
    static List<String> bracket = new ArrayList<>(Arrays.asList("(", ")"));
    static List<String> notOperand = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "sin", "(", ")"));
    static Stack<String> stack = new Stack<>();

    private Evaluation() {
        stack = new Stack<>();
    }

    public static Evaluation getInstance() {
        if (sInstance == null)
            sInstance = new Evaluation();
        return sInstance;
    }

    public float calculate(String exp) {
        exp = refine(exp);
        String postFix = toPostFix(exp);
        System.out.println(postFix);
        Float value = calculatePostFix(postFix);
        return value;
    }

    //chuan hoa xau, xoa ki tu trang o dau va cuoi, moi token cach nhau bang mot dau cach
    private String refine(String exp) {
        StringBuilder strBuilder = new StringBuilder(exp);
        for(int i = 0; i < strBuilder.length(); i++) {
            if (notOperand.indexOf(strBuilder.charAt(i) + "") != -1) {
                strBuilder.insert(i, " ").insert(i + 2, " ");
                i += 2;
            }
        }
        String res = strBuilder.toString();
        res = res.trim().replaceAll(" +", " ");
        return res;
    }

    //infix to postfix
    private String toPostFix(String exp) {
        String postFix = "";
        stack.clear();
        String token = "";
        exp += " ";
        for(int i = 0 ; i < exp.length(); i++) {
            if (exp.charAt(i) == ' ') {
                //is end of token
                if (notOperand.indexOf(token) == -1) {
                    //is operand, append it to postFix
                    postFix += token;
                    postFix += " ";
                    token = "";
                }
                else if (token.equals("(")) {
                    stack.push(token);
                    token = "";
                }
                else if (token.equals(")")) {
                    String element = stack.pop();
                    while (!element.equals("(")) {
                        postFix += element;
                        postFix += " ";
                        element = stack.pop();
                    }
                    token = "";
                }
                else {
                    //is operator
                    if (stack.size() > 0) {
                        String elementAtTop = stack.peek();
                        while (operator.indexOf(elementAtTop) != -1 && priorityOf(elementAtTop) >= priorityOf(token)) {
                            String element = stack.pop();
                            postFix += element;
                            postFix += " ";
                            if (stack.size() > 0)
                                elementAtTop = stack.peek();
                            else break;
                        }
                    }
                    stack.push(token);
                    token = "";
                }
            }
            else token += exp.charAt(i);
        }

        while (stack.size() != 0) {
            postFix += stack.pop();
            postFix += " ";
        }
        return postFix;
    }

    //calculate postix expression
    private float calculatePostFix(String postFix) {
        stack.clear();
        String token = "";
        for(int i = 0; i < postFix.length(); i++) {
            char c = postFix.charAt(i);
            if (c == ' ') {
                //is end of token
                if (notOperand.indexOf(token) == -1) {
                    //is operand, push it to stack
                    stack.push(token);
                    token = "";
                }
                else {
                    //is operator, pop 2 operand from stack and calculate
                    if (oneArgOperator.indexOf(token) != -1) {
                        //la toan tu 1 ngoi
                        float operand = Float.parseFloat(stack.pop());
                        float res = calc(operand, token);
                        stack.push(String.valueOf(res));
                        token = "";
                    }
                    else {
                        float operand1 = Float.parseFloat(stack.pop());
                        float operand2 = Float.parseFloat(stack.pop());
                        float res = calc(operand2, operand1, token);
                        stack.push(String.valueOf(res));
                        token = "";
                    }

                }
            }
            else
                token += c;
        }
        float result = Float.parseFloat(stack.pop());
        return result;
    }

    //return operand1 + operand2
    private float calc(float operand1, float operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
        }
        return 1;
    }

    //return operator(operand)
    private float calc(float operand, String operator) {
        switch (operator) {
            case "sin":
                return (float) Math.sin(operand);
        }
        return 1;
    }

    private int priorityOf(String operator) {
        switch (operator) {
            case "+": case "-":
                return 0;
            case "*": case "/":
                return 1;
            case "sin":
                return 2;
        }
        return -1;
    }
}
