package prime.sample;

import java.util.List;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {

        PrimeNumeber PN;
        PN = new PrimeNumeber();

        System.out.println("何個欲しい？");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();

        List<Integer> getlist;

        getlist = PN.generate(num);
        for(int i=1; i <= getlist.size();i++){
            System.out.println(i + ":" + getlist.get(i-1));
        }

    }
}
