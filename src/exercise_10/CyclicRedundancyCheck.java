package exercise_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CyclicRedundancyCheck {

    public static void main(String[] args) throws IOException {
       
        String generator, message, dividend, transmittedData, recievedData;
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("ERROR DETECTION(CRC)\n\nEnter the generator: ");
        generator = br.readLine();
       
        System.out.print("Enter the message: ");
        message = br.readLine();
        dividend = message;
       
        for (int i = 0; i < generator.length() - 1; i++) {
            dividend += "0";
        }
       
        transmittedData = message + divide(dividend, generator);
        System.out.println("Transmitted Data: " + transmittedData);
        
        System.out.print("Enter the message recieved: ");
        recievedData = br.readLine();
        
        if (Integer.parseInt(divide(recievedData, generator)) == 0) {
        	System.out.print("\nThe recieved message is accepted!");
        } else {
        	System.out.print("\nThe recieved message has been corrupted!");
        }
    }
   
    private static String divide(String dividend, String divisor) {
    	String temp;
    	int pick;
    	
        pick = divisor.length();
        temp = dividend.substring(0, pick);
       
        while (pick < dividend.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp);
            }
            temp = temp.substring(1, temp.length()) + dividend.charAt(pick);
            pick++;
        }
       
        if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp);
        }
        temp = temp.substring(1, temp.length());
       
        return temp;
    }
   
    private static String xor(String a, String b) {
        String result = "";
       
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                result += "0";
            } else {
                result += "1";
            }
        }
       
        return result;
    }
}