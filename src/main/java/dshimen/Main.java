package dshimen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static dshimen.Utils.*;

public class Main {
    //7*3*(4*3)*(((30-3*15)-20)-15)/7+14/7+3/2
    public static void main(String[] args) throws IOException {
        System.out.println("Program jest prostym kalkulatorem");
        String dataString = "";
        while(!dataString.equals("quit") && !dataString.equals("q")){
            System.out.print("Wpisz ciąg znaków składający się tylko z ()[0-9]+-*/: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            dataString = reader.readLine().toLowerCase();
            Object result = calculate(dataString);
            if(result != null){
                System.out.println("Wynik zadanego wyrazenia to: " + result);
            }else{
                System.out.println("Kalkulator sie poddal, podaj prostrze wyrazenie");
            }
        }

    }
    //(203+15)/34*(-127+-1)
    public static Object calculate(String dataString) {
        List<Object> elements = Parser.parser(dataString);
        if(elements == null){
            System.out.println("Wyrażenie było błędne i nie dało się go policzyć");
            System.exit(0);
        }else{
            double currentCalc = 0;
            smartMultiplyAndDevide(elements);
            removeMultiply(elements);
            removeDevide(elements);
            simplifyBrackets(elements);
            removeSomeBrackets(elements);
            finalSimplify(elements);
            StringBuilder orginal = new StringBuilder();
            StringBuilder toCompare_2;
            for(Object o : elements){
                orginal.append(o);
            }
            while (true){
//                for (Object o : elements){
//                    System.out.print(o + " ");
//                }
//                System.out.println("");
                toCompare_2 = new StringBuilder();
                smartMultiplyAndDevide(elements);
//                for (Object o : elements){
//                    System.out.print(o + " ");
//                }
//                System.out.println("");
                removeMultiply(elements);
                removeDevide(elements);
                simplifyBrackets(elements);
                removeSomeBrackets(elements);
                finalSimplify(elements);
                for(Object o : elements){
                    toCompare_2.append(o);
                }
//                System.out.println("o: " + orginal);
//                System.out.println("t: " + toCompare_2);
                if(!orginal.toString().equals(toCompare_2.toString())){
                    orginal = toCompare_2;
                }else {
                    break;
                }
            }
            if(elements.size() == 1){
                if(elements.get(0) instanceof Double){
                    currentCalc = (double)elements.get(0);
                }else if(elements.get(0) instanceof Integer){
                    currentCalc = (int)elements.get(0);
                }
                return currentCalc;

            }
        }
        return null;
    }
}
