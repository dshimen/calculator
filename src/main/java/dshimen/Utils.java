package dshimen;

import java.util.List;

public class Utils {
    public static List<Object> smartMultiplyAndDevide(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            int remberI = 0;
            boolean startRemeber = false;
            for(int i = 0;i < elements.size();i ++){
                if((elements.get(i) instanceof Character && !(elements.get(i) instanceof Integer)&& !(elements.get(i) instanceof Double)) || (remberI != 0 && i == elements.size() -1)){
                    if(elements.get(i) instanceof Character && !(elements.get(i) instanceof Integer) && !(elements.get(i) instanceof Double)){
                        if((char)elements.get(i) == '*' || (char)elements.get(i) == '/'){
                            if(!startRemeber && (elements.get(i+1) instanceof Integer || elements.get(i+1) instanceof Double) && (elements.get(i-1) instanceof Integer || elements.get(i-1) instanceof Double) ){
                                remberI = i;
                                startRemeber = true;
                            }
                        }
                    }else if(((i == elements.size()-1) && startRemeber )|| ((char)elements.get(i) != '*' && (char)elements.get(i) != '/')){
                        boolean exitSecond = false;
                        for(int j = remberI; j < i;j++){
                            if((char)elements.get(j) == '*'){
                                if(elements.get(j-1) instanceof Integer){
                                    if(elements.get(j+1) instanceof Integer){
                                        elements.set(j+1, (int)elements.get(j-1)*1.0 * (int)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }else if(elements.get(j+1) instanceof Double){
                                        elements.set(j+1, (int)elements.get(j-1)*1.0 * (double)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }
                                }else if(elements.get(j-1) instanceof Double){
                                    if(elements.get(j+1) instanceof Integer){
                                        elements.set(j+1, (double)elements.get(j-1)*1.0 * (int)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }else if(elements.get(j+1) instanceof Double){
                                        elements.set(j+1, (double)elements.get(j-1)*1.0 * (double)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }
                                }
                            }else{
                                if(elements.get(j-1) instanceof Integer){
                                    if(elements.get(j+1) instanceof Integer){
                                        if((int)elements.get(j+1) == 0){
                                            System.out.println("Doprowadzono do dzielenia przez 0");
                                            return null;
                                        }
                                        elements.set(j+1, (int)elements.get(j-1)*1.0 / (int)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }else if(elements.get(j+1) instanceof Double){
                                        if((double)elements.get(j+1) == 0.0){
                                            System.out.println("Doprowadzono do dzielenia przez 0");
                                            return null;
                                        }
                                        elements.set(j+1, (int)elements.get(j-1)*1.0 / (double)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }
                                }else if(elements.get(j-1) instanceof Double){
                                    if(elements.get(j+1) instanceof Integer){
                                        if((int)elements.get(j+1) == 0){
                                            System.out.println("Doprowadzono do dzielenia przez 0");
                                            return null;
                                        }
                                        elements.set(j+1, (double) elements.get(j - 1) / (int)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }else if(elements.get(j+1) instanceof Double){
                                        if((double)elements.get(j+1) == 0){
                                            System.out.println("Doprowadzono do dzielenia przez 0");
                                            return null;
                                        }
                                        elements.set(j+1, (double) elements.get(j - 1) / (double)elements.get(j+1));
                                        elements.remove(j);
                                        elements.remove(j-1);
                                        exitSecond = true;
                                        startRemeber = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(exitSecond){
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
    public static List<Object> removeMultiply(List<Object> elements){
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
    public static List<Object> removeDevide(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(elements.get(i) instanceof Integer){
                    if(i > 1){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '/'){
                                if((int)elements.get(i) == 0){
                                    System.out.println("Doprowadzono do dzielenia przez 0");
                                    return null;
                                }
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 / ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '/'){
                                if((int)elements.get(i) == 0){
                                    System.out.println("Doprowadzono do dzielenia przez 0");
                                    return null;
                                }
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
                                if((double)elements.get(i) == 0){
                                    System.out.println("Doprowadzono do dzielenia przez 0");
                                    return null;
                                }
                                elements.set(i, ((double) elements.get(i - 2)) / ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        } else if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '/'){
                                if((double)elements.get(i) == 0){
                                    System.out.println("Doprowadzono do dzielenia przez 0");
                                    return null;
                                }
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

    public static List<Object> simplifyBrackets(List<Object> elements){
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
    public static List<Object> removeSomeBrackets(List<Object> elements){
        boolean beforeAllMultuply = true;
        while (beforeAllMultuply){
            for(int i = 0;i < elements.size();i ++){
                if(i > 1){
                    if(elements.get(i) instanceof Integer){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 - ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 + ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((double) elements.get(i - 2)) - ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((double) elements.get(i - 2)) + ((int) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }

                    }else if(elements.get(i) instanceof Double){
                        if(elements.get(i-2) instanceof Integer){
                            if((char)elements.get(i-1) == '-' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((int) elements.get(i - 2)) * 1.0 + ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }
                        }else if(elements.get(i-2) instanceof Double){
                            if((char)elements.get(i-1) == '-' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
                                elements.set(i, ((double) elements.get(i - 2)) - ((double) elements.get(i)));
                                elements.remove(i-1);
                                elements.remove(i-2);
                                break;
                            }else if((char)elements.get(i-1) == '+' && ((i > 2 && (char)elements.get(i-3) != '/' && (char)elements.get(i-3) != '*') || (i == 2))){
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
    public static List<Object> finalSimplify(List<Object> elements){
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
