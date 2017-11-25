package random;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by Azal on 4/11/2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = (int) (Math.random() * 1000 +1);
        int selectedNumber = -1;
        int tryNumber = 1;
        while(selectedNumber!=number) {
            Toolkit.getDefaultToolkit().beep();
            System.out.print("Select your number :");
            selectedNumber = in.nextInt();
            if(number > selectedNumber){
                System.out.println("Smaller");
            }
            if(number < selectedNumber){
                System.out.println("Larger");
            }
            tryNumber++;
        }
        System.out.println(String.format("You win after %s try.", tryNumber));
    }
}
