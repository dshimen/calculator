package dshimen;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void parser(String dataString) {
        char[] dataChar = dataString.replace(" ","").toCharArray();
        List<Inside> insideBrackets = new ArrayList<>();
        int putInside = -1;
        int state = 0;  //66 - state was number
        for(int i = 0;i < dataChar.length;i++){
            if(dataChar[i] == '('){
                putInside++;
                if(state == 66){
                    state = 0;
                }
            }else if(dataChar[i] == ')'){
                putInside--;
                if(state == 66){
                    state = 0;
                }
            }else if(dataChar[i] == '+'){
                if(state == 66){
                    state = 0;
                }
            }else if(dataChar[i] == '-'){
                if(state == 66){
                    state = 0;
                }
            }else if(dataChar[i] == '*'){
                if(state == 66){
                    state = 0;
                }
            }else if(dataChar[i] == '/'){
                if(state == 66){
                    state = 0;
                }
            }else if(isNumber(dataChar[i])){
                state = 66;
            }
        }
        if(state == 66){
            for(int i = dataChar.length-1; i >= 0;i--){
                if(!isNumber(dataChar[i])){
                    System.out.println("number: " + Integer.parseInt(dataString.substring(i+1, dataChar.length)));
                    break;
                }
            }
        }
    }
    private static boolean isNumber(char number){
        if(number >= 48 && number <= 57){
            return true;
        }else{
            return false;
        }
    }
}
