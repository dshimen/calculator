package dshimen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Program jest prostym kalkulatorem");
        String dataString = "";
        while(!dataString.equals("quit") && !dataString.equals("q")){
            System.out.print("Wpisz ciąg znaków składający się tylko z ()[0-9]+-*/: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            dataString = reader.readLine().toLowerCase();
            System.out.println("Wynik zadanego wyrazenia to: " + calculate(dataString));
        }
    }
    //7*3*(4*3)*(((30-3*15)-20)-15)/7+14/7+3/2
    public static int calculate(String dataString) {
        List<Object> elements = Parser.parser(dataString);
        if(elements == null){
            System.out.println("Wyrażenie było błędne i dało się go policzyć");
            System.exit(0);
        }else{
            double currentCalc = 0;
            removeMultiply(elements);
            removeDevide(elements);
            simplifyBrackets(elements);
            removeSomeBrackets(elements);
            StringBuilder orginal = new StringBuilder();
            StringBuilder toCompare_2;
            for(Object o : elements){
                orginal.append(o);
            }
            while (true){
                toCompare_2 = new StringBuilder();
                removeMultiply(elements);
                removeDevide(elements);
                simplifyBrackets(elements);
                removeSomeBrackets(elements);
                for(Object o : elements){
                    toCompare_2.append(o);
                }
                System.out.println("orginal: " + orginal);
                System.out.println("tocomapre: " + toCompare_2);
                if(!orginal.toString().equals(toCompare_2.toString())){
                    orginal = toCompare_2;
                }else {
                    break;
                }
            }
//            while (elements.size() > 3){
//                finalSimplify(elements);
//            }
            for(Object o : elements){
                System.out.print(o + " ");
            }
            System.out.println("");
            System.out.println(currentCalc);
        }
        return 0;
    }

    private static List<Object> removeMultiply(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(elements.get(i) instanceof Integer){
                    if(i > 1){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '*'){
                                elements.set(i, (int) elements.get(i) * (int) elements.get(i-2));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        } else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '*'){
                                elements.set(i, (int) elements.get(i) * (double) elements.get(i-2));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                }else if(elements.get(i) instanceof Double){
                    if(i > 1){
                        if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '*'){
                                elements.set(i, (double) elements.get(i) * (double) elements.get(i-2));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '*'){
                                elements.set(i, (double) elements.get(i) * (int) elements.get(i-2));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                }
                if( i == elements.size()-1){
                    beforeAllMultuply = false;
                }
            }
        }
        return elements;
    }
    private static List<Object> removeDevide(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(elements.get(i) instanceof Integer){
                    if(i > 1){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '/'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 / ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '/'){
                                elements.set(i, ((double) elements.get(i - 2)) / ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                } else if(elements.get(i) instanceof Double){
                    if(i > 1){
                        if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '/'){
                                elements.set(i, ((double) elements.get(i - 2)) / ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        } else if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '/'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 / ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                }
                if( i == elements.size()-1){
                    beforeAllMultuply = false;
                }
            }
        }
        return elements;
    }

    private static List<Object> simplifyBrackets(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(elements.get(i) instanceof Integer){
                    if(i > 2){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 - ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 + ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                        if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((double) elements.get(i - 2)) - ((int) elements.get(i))*1.0);
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((double) elements.get(i - 2)) + ((int) elements.get(i))*1.0);
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                }else if(elements.get(i) instanceof Double){
                    if(i > 2){
                        if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((double) elements.get(i - 2)) - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((double) elements.get(i - 2)) + ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        } else if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((int) elements.get(i - 2))*1.0 - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && (char)elements.get(i-3) == '('){
                                elements.set(i, ((int) elements.get(i - 2))*1.0 + ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }
                }
                if( i == elements.size()-1){
                    beforeAllMultuply = false;
                }
            }
        }
        return elements;
    }
    private static List<Object> removeSomeBrackets(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(i > 1){
                    if(elements.get(i) instanceof Integer){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 - ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 + ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-'){
                                elements.set(i, ((double) elements.get(i - 2)) - ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+'){
                                elements.set(i, ((double) elements.get(i - 2)) + ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }

                    }else if(elements.get(i) instanceof Double){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+'){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 + ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-'){
                                elements.set(i, ((double) elements.get(i - 2)) - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+'){
                                elements.set(i, ((double) elements.get(i - 2)) + ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }
                    }

                }
                if( i == elements.size()-1){
                    beforeAllMultuply = false;
                }
            }
        }
        return elements;
    }
    private static List<Object> finalSimplify(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(i > 1){
                    if(elements.get(i-1) instanceof Integer){
                        if((char)elements.get(i-2) == '(' && (char)elements.get(i) == ')'){
                            elements.remove(i);
                            elements.remove(i-2);
                            break;
                        }
                    }else if(elements.get(i-1) instanceof Double){
                        if((char)elements.get(i-2) == '(' && (char)elements.get(i) == ')'){
                            elements.remove(i);
                            elements.remove(i-2);
                            break;
                        }
                    }
                }
                if( i == elements.size()-1){
                    beforeAllMultuply = false;
                }
            }
        }
        return elements;
    }
}
