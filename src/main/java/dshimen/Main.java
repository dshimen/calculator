package dshimen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //(aa)*bb(a|b)(a)?
    public static void main(String[] args) throws IOException {
        System.out.println("Program sprawdza czy dany ciąg liter AB spełnia wyrażenie {aa}bb(a|b)[a]");
        String dataString = "";
        while(!dataString.equals("quit") && !dataString.equals("q")){
            System.out.print("Wpisz ciąg znaków składający się tylko z ()[0-9]+-*/: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            dataString = reader.readLine().toLowerCase();
            System.out.println("Wynik zadanego wyrazenia to: " + calculate(dataString));
        }
    }
    public static int calculate(String dataString) {
        Parser.parser(dataString);
        int state = 0;
        return 0;
    }
}
