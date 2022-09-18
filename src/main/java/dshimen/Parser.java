package dshimen;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Object> parser(String dataString) {
        char[] dataChar = dataString.replace(" ","").toCharArray();
        List<Object> elements = new ArrayList<>();
        int state = 0;  //66 - state was number, 1 - plus, 2 - minus, 3 - multiply, 3 - divide
        for(int i = 0;i < dataChar.length;i++){
            if(dataChar[i] == '('){
                if(state == 66){
                    state = 0;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add('(');
                }else {
                    elements.add('(');
                }
            }else if(dataChar[i] == ')'){
                if(state == 66){
                    state = 0;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add(')');
                }else {
                    elements.add(')');
                }
            }else if(dataChar[i] == '+'){
                if(state == 66){
                    state = 1;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add('+');
                }else {
                    elements.add('+');
                }
            }else if(dataChar[i] == '-'){
                if(state == 66){
                    state = 2;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add('-');
                }else {
                    elements.add('-');
                }
            }else if(dataChar[i] == '*'){
                if(state == 66){
                    state = 3;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add('*');
                }else {
                    elements.add('*');
                }
            }else if(dataChar[i] == '/'){
                if(state == 66){
                    state = 4;
                    elements.add(getNumber(dataChar, dataString, i));
                    elements.add('/');
                }else {
                    elements.add('/');
                }
            }else if(isNumber(dataChar[i])){
                state = 66;
            }else{
                return null;
            }
        }
        if(state == 66){
            elements.add(getNumber(dataChar, dataString, dataChar.length));
        }
        return elements;
    }
    private static int getNumber(char [] dataChar, String dataString, int length){
        for(int i = length-1; i >= 0;i--){
            if(!isNumber(dataChar[i]) || i == 0){
                if(dataChar[i] == '-' && ((i > 0 && dataChar[i-1] == '-') || (i == 0))){
                    return  Integer.parseInt(dataString.substring(i, length));
                }else{
                    if(i==0){
                        return  Integer.parseInt(dataString.substring(i, length));
                    }else{
                        return  Integer.parseInt(dataString.substring(i+1, length));
                    }
                }
            }
        }
        System.out.println("ERROR");
        return 0;
    }
    private static boolean isNumber(char number){
        if(number >= 48 && number <= 57){
            return true;
        }else{
            return false;
        }
    }
}
